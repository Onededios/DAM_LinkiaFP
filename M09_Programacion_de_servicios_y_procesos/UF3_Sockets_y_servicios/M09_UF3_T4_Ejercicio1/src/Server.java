import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static java.rmi.registry.LocateRegistry.createRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            ICalculator calculator = new CalculatorImpl();
            ICalculator stub = (ICalculator) UnicastRemoteObject.exportObject(calculator, 8069);
            Registry registry = createRegistry(8069);
            registry.rebind("Calculator", stub);
            System.err.println("Server online!");
        } catch (Exception e) {
            System.err.println("Server error: " + e);
            e.printStackTrace();
        }
    }
}
