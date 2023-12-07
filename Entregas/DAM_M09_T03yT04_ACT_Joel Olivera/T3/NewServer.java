import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class NewServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5060);
            serverSocket.bind(addr);

            while (true) {
                new Thread(new NewServerThread(serverSocket.accept())).start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class NewServerThread implements Runnable {
    private final Socket clientSocket;
    private final PrintStream clientWriter;
    NewServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.clientWriter = getPrintStream();
    }

    private PrintStream getPrintStream() {
        try {
            return new PrintStream(clientSocket.getOutputStream(), true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final String MENU_UserAction =
                    "¿Qué operación deseas realizar?" +
                    "<LINE_BREAK> add - tarea     (Añade una tarea a la lista de tareas pendientes)" +
                    "<LINE_BREAK> del - tarea     (Borra la tarea de la lista de tareas pendientes)" +
                    "<LINE_BREAK> list            (Lista las tareas pendientes)" +
                    "<LINE_BREAK> count           (Devuelve el número de tareas)" +
                    "<LINE_BREAK> end             (Finaliza la ejecución del programa)";

    public void run() {
        NewActivity act = new NewActivity();
        try {
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
            clientWriter.println("¡Estás conectado al Control de Tareas!");
            String[] action = new String[2];
            do {
                clientWriter.println(MENU_UserAction);
                action[0] = clientReader.readLine();

                if (action[0].contains("add - ") || action[0].contains("del - ")) {
                    String[] response = action[0].split(" - ", 2);
                    action[0] = response[0];
                    action[1] = response[1];
                }

                switch (action[0]) {
                    case "add":
                        clientWriter.println(act.addActivityToTodoList(action[1]));
                        break;
                    case "del":
                        clientWriter.println(act.delActivityToTodoList(action[1]));
                        break;
                    case "list":
                        clientWriter.println(act.getTodoListActivities());
                        break;
                    case "count":
                        clientWriter.println(act.getTodoListLength());
                        break;
                    case "end":
                        clientWriter.println("Adiós.<LINE_BREAK>Buenas tardes.");
                        clientSocket.close();
                        break;
                    default:
                        clientWriter.println("Error.<LINE_BREAK>La acción que has introducido no corresponde a ninguna acción disponible.<LINE_BREAK>");
                        break;
                }
                if (clientSocket.isClosed()) break;
            } while (true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class NewActivity {
    private final ArrayList<String> todoList;

    NewActivity() {this.todoList = new ArrayList<>();}
    protected String getTodoListActivities() {
        if (!todoList.isEmpty()) {
            StringBuilder remainingActivities = new StringBuilder("Tareas pendientes:");
            for (int i = 0; i < todoList.size(); i++) {
                remainingActivities.append("<LINE_BREAK>").append(i + 1).append(". ").append(todoList.get(i));
            }
            return remainingActivities.toString();
        }
        return "No hay actividades pendientes.";
    }

    protected String getTodoListLength() {
        return "Tienes " + todoList.size() + " tarea/s pendiente/s.";
    }

    protected String addActivityToTodoList(String activity) {
        todoList.add(activity);
        return "La actividad:<LINE_BREAK>'" + activity + "'<LINE_BREAK>Ha sido añadida a la lista.";
    }

    protected String delActivityToTodoList(String activity) {
        if (todoList.removeIf(a -> a.equals(activity))) {
            return "La actividad:<LINE_BREAK>'" + activity + "'<LINE_BREAK>Ha sido eliminada de la lista.";
        }
        return "No se ha podido encontrar la actividad solicitada.";
    }
}
