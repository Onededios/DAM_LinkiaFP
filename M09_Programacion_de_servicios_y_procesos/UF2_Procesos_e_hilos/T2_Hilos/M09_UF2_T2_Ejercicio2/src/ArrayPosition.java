import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ThreadReader extends Thread {
    private final int[][] array;
    private final int numberToFind;
    ThreadReader(String threadName, int[][] array, int numberToFind) {
        this.array = array;
        this.numberToFind = numberToFind;
        this.setName(threadName);
        this.start();
    }

    public void run() {
        int column = Integer.parseInt(this.getName());
        for (int j = 0; j < array.length; j++) {
            if (array[j][column] == numberToFind) {
                System.out.println("Hilo:"+this.getName()+" Posición: ["+(j+1)+"]["+(column+1)+"]");
            }
        }
    }
}

public class ArrayPosition {
    public static void main(String[] args) {
        int arrayX = 10;
        int arrayY = 20;
        int minNum = 0;
        int maxNum = 99;
        int numberToFind;

        int[][] array = new int[arrayY][arrayX];
        ThreadReader[] threads = new ThreadReader[arrayX];

        arrayFiller(array, minNum, maxNum);

        printFullArray(array, String.valueOf(maxNum).length());

        numberToFind = getUserNumber(minNum, maxNum);

        for (int i = 0; i < arrayX; i++) {
            threads[i] = new ThreadReader(String.valueOf(i), array, numberToFind);
        }
    }

    public static void arrayFiller(int[][] array, int minNum, int maxNum) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = getRandomNumberBetween(minNum, maxNum);
            }
        }
    }

    public static void printFullArray(int[][] array, int maxNumLength) {
        System.out.println("Here goes the array filled:");

        for (int[] ints : array) {
            for (int j = 0; j < array[0].length; j++) {
                int spacesToAdd = maxNumLength - String.valueOf(ints[j]).length();
                System.out.print("[");
                for (int s = 0; s < spacesToAdd; s++) System.out.print(" ");
                System.out.print(ints[j]+"]");
            }
            System.out.println();
        }
    }




    public static int getRandomNumberBetween(int min, int max) {
        return  (int)(Math.random()*(max-min+1)+min);
    }

    private static int getUserNumber(int min, int max) {
        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("\nEscribe el número a buscar ("+min+"-"+max+"): ");
            try {
                int num = Integer.parseInt(userIn.readLine());
                if (num >= min && num <= max) return num;
                else System.out.print("No es un número comprendido entre "+min+" y "+max+". Introduce un entero válido.");
            } catch (NumberFormatException e) {
                System.out.print("No es un número válido. Introduce un número entero.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
} 