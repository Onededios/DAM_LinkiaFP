import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private static BufferedReader userReader;
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8069);
            ICalculator calculator = (ICalculator) registry.lookup("Calculator");
            userReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            String action;
            do {
                System.out.print(calculator.getMenu());
                System.out.print("\nYour selection: ");
                switch ((action = userReader.readLine().toUpperCase())) {
                    case "B": System.out.println(calculator.getIntToBin(askForInteger()));
                        break;
                    case "P": System.out.println(calculator.getIntIsPrimeOrOdd(askForInteger()));
                        break;
                    case "F": System.out.println(calculator.getIntToFactorial(askForInteger()));
                        break;
                    case "S": System.out.println(calculator.getIntSumStartingBy1(askForInteger()));
                        break;
                    case "D": System.out.println(calculator.getIntAllPossibleDivisors(askForInteger()));
                        break;
                    case "E": System.out.println("Bye!");
                        break;
                    default: System.out.println("Error. That's not an available action.");
                        break;
                }

            } while(!action.equals("E"));
        } catch (NotBoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int askForInteger() throws IOException {
        System.out.println("Perfect, give me an integer.");
        Integer userInt;
        do {
            System.out.print("Your answer: ");
            try {
                userInt = Integer.parseInt(userReader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Error. You've not introduced an integer!");
                userInt = null;
            }
        }
        while(userInt == null);
        return userInt;
    }
}