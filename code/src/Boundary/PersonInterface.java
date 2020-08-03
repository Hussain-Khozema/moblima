package Boundary;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * This class has methods that make getting user inputs easier.<br>
 * Uses Scanner and System.out.print.
 * @author SS3 Group 8
 *
 */
public abstract class PersonInterface {
    /**
     * Scanner shared by subclasses
     */
    public Scanner sc = new Scanner(System.in);
    /**
     * Abstract method to print options
     */
    public abstract void displayMenu();
    /**
     * Abstract method to interact with person
     */
    public abstract void interact();
    
    /**
     * Prints out message ending with newline
     * @param message String to be printed
     */
    public void displayLine(String message) { //prints out message with line
        System.out.println(message);
    }

    /**
     * Prints out message without newline
     * @param message String to be printed
     */
    public void display(String message) { //prints out message without going to the next line
        System.out.print(message);
    }

    /**
     * Prints out the message and gets user input (accepts integer)
     * @param message String to be printed
     * @return Integer (user input)
     */
    public int scanInteger(String message) {  //scans an integer and has error checking for input
        int result;

        while (true) {
            System.out.print(message);
            try {
                result = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, please try again");
            }
            sc.nextLine();
        }
        sc.nextLine();

        return result;
    }
    
    /**
     * Prints out the message and gets user input (accepts double)
     * @param message String to be printed
     * @return Double (user input)
     */
    public double scanDouble(String message) { //scans an double and has error checking for input
        double result;

        while (true) {
            System.out.print(message);
            try {
                result = sc.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, please try again");
            }
            sc.nextLine();
        }
        sc.nextLine();

        return result;
    }

    /**
     * Prints out the message and gets user input (accepts String)
     * @param message String to be printed
     * @return String (user input)
     */
    public String scanString(String message) {  //scans an String and has error checking for input
        String result;

        System.out.print(message);
        result = sc.next();
        sc.nextLine();

        return result;
    }

    /**
     * Prints out the message and gets user input (accepts String, reads entire line)
     * @param message String to be printed
     * @return String (user input)
     */
    public String scanLine(String message) {  //scans a string line and has error checking for input
        String result;

        System.out.print(message);
        result = sc.nextLine();

        return result;
    }

    /**
     * Prints out the message and gets user input (accepts boolean)
     * @param message String to be printed
     * @return Boolean (user input)
     */
    public boolean scanBoolean(String message) {  //scans a boolean and has error checking for input
        boolean result;

        while (true) {
            System.out.print(message);
            try {
                result = sc.nextBoolean();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, please try again");
            }
            sc.nextLine();
        }
        sc.nextLine();

        return result;
    }
}