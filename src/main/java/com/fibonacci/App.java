package com.fibonacci;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
                System.out.println("How many numbers would you like to calculate? Please input 45 or less for now, or simply type -1 to leave.");
                Scanner userInput = new Scanner(System.in);
                boolean error = false;
                int userChoiceInt = 0;
                do{
                String userChoice = userInput.nextLine();
                
                try{
                    userChoiceInt = Integer.parseInt(userChoice);
                    if(userChoiceInt == -1){
                        System.exit(0);
                    }
                }catch(Exception e){
                    System.out.println("Please only input numbers!");
                    error = true;
                }
                }while(!error);
                userInput.close();
                System.out.println("Calculating ");
                for(int i =0 ; i<4; i++){
                    System.out.print(".");
                }
        CharCreator ex = new CharCreator(userChoiceInt,true);
            ex.setVisible(true);
        CharCreator ex2 = new CharCreator(userChoiceInt, false);
            ex2.setVisible(true);
      }

    public static long fibrecl(int numberOfSteps) { // recersive
        double n = numberOfSteps;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return (fibrecl(numberOfSteps - 1) + fibrecl(numberOfSteps - 2));
        }
    }

    public static long fibiter(int numberOfSteps) { // iterative
        int i, x = 0, y = 1, z;
        if (numberOfSteps == 0) {
            return x;
        }
        for (i = 2; i <= numberOfSteps; i++) {
            z = x + y;
            x = y;
            y = z;
        }
        return y;
    }
}
