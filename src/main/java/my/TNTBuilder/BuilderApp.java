package my.TNTBuilder;

import my.TNTBuilder.Exceptions.InvalidInventoryFile;
import my.TNTBuilder.Exceptions.TNTException;
import my.TNTBuilder.Output.ConsoleMenu;

import java.io.FileNotFoundException;

public class BuilderApp {
    Rulebook rulebook;
    ConsoleMenu menu = new ConsoleMenu();

    private void run() {
        try {
            rulebook = new Rulebook();
        } catch (FileNotFoundException e){
            menu.inventoryFileNotFoundError();
        } catch (InvalidInventoryFile e){
            menu.invalidInventoryFile();
        } catch (TNTException e){
            menu.genericErrorMessage();
        }


        System.out.println("This line stops the debugger before program exits");
    }











    //main method, exit ASAP
    public static void main(String[] args) {
        BuilderApp builder = new BuilderApp();
        builder.run();
    }
}