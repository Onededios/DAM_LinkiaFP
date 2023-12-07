import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class NewClient {
    private static final Socket clientSocket = new Socket();
    private static final String hostname = "localhost";
    private static final int port = 5060;

    public static void main(String[] args) {
        InetSocketAddress addr = new InetSocketAddress(hostname, port);
        try {
            clientSocket.connect(addr);
            PrintStream serverWriter = new PrintStream(clientSocket.getOutputStream(), true);
            BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
            String action;
            printServerResponse(serverReader);
            do {
                System.out.println("--------------------------------------");
                printServerResponse(serverReader);
                System.out.println("Tu acción: ");
                action = userReader.readLine();
                serverWriter.println(action);
                System.out.println();
                printServerResponse(serverReader);
                System.out.println();
            } while (!action.equals("end"));
        } catch (ConnectException ce) {
            System.err.println("Connection refused. No server found listening at " + hostname + ":" + port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printServerResponse(BufferedReader serverReader) throws IOException {
        String receivedMessage = serverReader.readLine();
        System.out.print("→ ");
        if (receivedMessage.contains("<LINE_BREAK>"))
            for (String received : receivedMessage.split("<LINE_BREAK>")) {
                System.out.println(received);
            }
        else System.out.println(receivedMessage);
    }
}
