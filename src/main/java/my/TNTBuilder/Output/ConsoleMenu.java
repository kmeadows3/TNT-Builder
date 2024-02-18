package my.TNTBuilder.Output;

import java.util.Scanner;

public class ConsoleMenu {

    private Scanner console = new Scanner(System.in);

    public void inventoryFileNotFoundError(){
        System.out.println("*************************************************");
        System.out.println("One or more inventory files appear to be missing.");
        System.out.println("*************************************************");
    }

    public void invalidInventoryFile(){
        System.out.println("******************************");
        System.out.println("Error in reading inventory CSV");
        System.out.println("******************************");
    }

    public void genericErrorMessage(){
        System.out.println("**************************************");
        System.out.println("There has been an error in the program");
        System.out.println("**************************************");
    }
}
