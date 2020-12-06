package reversepolishcalculator;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        Scanner KBD = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("--------------------------------------");
            System.out.println("Enter RPN expression or <CR> to exit ");
            String expression = KBD.nextLine();

            if (expression.equalsIgnoreCase("exit") || expression.trim().isEmpty()) {
                running = false;
            } else {
                System.out.println(RPN.evaluate(expression));
            }
        }
    }
}
