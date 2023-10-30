class ThreadFiller extends Thread {
    private final int[][] array;
    private final int minNum;
    private final int maxNum;

    ThreadFiller(String threadName, int[][] array, int minNum, int maxNum) {
        this.array = array;
        this.minNum = minNum;
        this.maxNum = maxNum;
        this.setName(threadName);
        this.start();
    }

    public void run() {
        int position = Integer.parseInt(this.getName());
        StringBuilder threadPhrase = new StringBuilder("Thread " + position + " filled the positions: ");
        for (int j = 0; j < array.length; j++) {
            array[j][position] = getRandomNumberBetween(minNum, maxNum);
            threadPhrase.append("[").append(position).append(",").append(j).append("] ");
        }
        System.out.println(threadPhrase);
    }

    public static void printFullArray(int[][] array, int maxNumLength) {
        System.out.println("\nHere goes the array filled:");

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

    private static int getRandomNumberBetween(int minNum, int maxNum) {
        return  (int)(Math.random()*(maxNum-minNum+1)+minNum);
    }
}

public class ArrayFiller {
    public static void main(String[] args) {
        int arrayX = 10;
        int arrayY = 25;
        int minNum = 1;
        int maxNum = 100;

        int[][] array = new int[arrayY][arrayX];
        ThreadFiller[] threads = new ThreadFiller[arrayX];

        for (int i = 0; i < arrayX; i++) {
            threads[i] = new ThreadFiller(String.valueOf(i), array, minNum, maxNum);
        }

        for (int i = 0; i < arrayX; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        ThreadFiller.printFullArray(array, String.valueOf(maxNum).length());
    }
}