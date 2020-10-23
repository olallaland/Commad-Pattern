import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String input = in.nextLine();
            System.out.println(input);
            String[] strs = input.split("\"");
            for (int i = 0; i < strs.length; i++) {
                System.out.println(strs[i]);
            }
        }
    }
}
