import java.rmi.RemoteException;

public class CalculatorImpl implements ICalculator {
    @Override
    public String getMenu() throws RemoteException {
        return  "\nChoose the operation to perform:"+
                "\n (B)inary    - Convert the given integer number to binary"+
                "\n (P)rime     - Assert if the given integer is prime or odd"+
                "\n (F)actorial - Calculate the factorial of the given integer"+
                "\n (S)um       - Calculates the result of the sum among all the numbers between 1 and the given integer"+
                "\n (D)ivisors  - Get all possible divisors from 1 to the given integer"+
                "\n (E)nd       - Closes the connection with the server"
                ;
    }

    @Override
    public String getIntToBin(int num) throws RemoteException {
        return " -> The integer "+num+" in binary equals to "+Integer.toBinaryString(num);
    }

    @Override
    public String getIntIsPrimeOrOdd(int num) throws RemoteException {
        return " -> The integer "+num+" is an "+(num % 2 == 0 ? "odd" : "prime" )+" number";
    }

    @Override
    public String getIntToFactorial(int num) throws RemoteException {
        int factorial = 1;
        for (int i = 1; i <= num; i++) factorial *= i;
        return " -> The factorial of the integer "+num+" is "+factorial;
    }

    @Override
    public String getIntSumStartingBy1(int num) throws RemoteException {
        int accumulated = 0;
        for (int i = 1; i <= num; i++) accumulated += i;
        return " -> The result of the sum of all the numbers between 1 and "+num+" is "+accumulated;
    }

    @Override
    public String getIntAllPossibleDivisors(int num) throws RemoteException {
        String result = " -> All possible divisors of the integer are:";
        for (int i = 1; i <= num; i++) if(num % i == 0) result += " "+i;
        return result;
    }
}