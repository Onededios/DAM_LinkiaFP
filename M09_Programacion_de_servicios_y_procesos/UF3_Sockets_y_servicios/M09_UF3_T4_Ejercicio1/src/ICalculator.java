import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculator extends Remote {
    String getMenu() throws RemoteException;
    String getIntToBin(int num) throws RemoteException;
    String getIntIsPrimeOrOdd(int num) throws RemoteException;
    String getIntToFactorial(int num) throws RemoteException;
    String getIntSumStartingBy1(int num) throws RemoteException;
    String getIntAllPossibleDivisors(int num) throws RemoteException;
}
