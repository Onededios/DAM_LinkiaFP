import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sumador {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int first = Integer.parseInt(reader.readLine());
            int second = Integer.parseInt(reader.readLine());

            int result = 0;

            for (int i = first; i <= second; i++) result += i;

            System.out.println(result);

            reader.close();
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
} 