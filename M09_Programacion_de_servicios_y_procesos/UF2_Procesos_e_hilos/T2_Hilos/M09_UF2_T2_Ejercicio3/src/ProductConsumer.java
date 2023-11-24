class Account {
    private int total;
    Account() {
        this.total = 0;
        System.out.println("Valor Inicial "+total);
    }
    private int getTotal() {return total;}
    private void setTotal(int total) {this.total = total;}
    public synchronized void updateTotal(String name, int value) {
        String chain = name+": valor "+value;
        if (getTotal() + value < 0) {
            chain += " hay " + getTotal() + " -->Duermo";
            System.out.println(chain);
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            updateTotal(name, value);
        }
        else {
            setTotal(getTotal() + value);
            System.out.println(chain+" Total ->: "+getTotal());
        }
        notifyAll();
    }
}

class ThreadAccountUpdater implements Runnable {
    private final int id;
    private int value;
    private final Account account;

    ThreadAccountUpdater(int id, int value, Account account) {
        this.id = id;
        this.value = value;
        this.account = account;
    }

    public void run() {
        account.updateTotal("hilo "+id, value);
    }
}

public class ProductConsumer {
    public static void main(String[] args) {
        Account account = new Account();
        int threadQTY = 20;
        int minNum = -5;
        int maxNum = 5;

        for (int i = 0; i < threadQTY; i++) new Thread( new ThreadAccountUpdater(i+1, getRandomNumberBetween(minNum, maxNum),account) ).start();
    }

    public static int getRandomNumberBetween(int min, int max) {
        int range = max - min + 1;
        int randomNumber = (int) (Math.random() * range) + min;
        return randomNumber;
    }

}
