package my.TNTBuilder;

import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Exceptions.TNTException;
import my.TNTBuilder.Output.ConsoleMenu;
import my.TNTBuilder.Output.TeamInputHelper;

public class ApplicationCLI {
    ConsoleMenu menu = new ConsoleMenu();
    Builder builder;

    private void run() {
        menu.printWelcomeMessage();
        mainMenu();

        System.out.println("This line stops the debugger before program exits");
    }


    private void mainMenu(){
        while (true) {
            try {
                String selection = menu.mainMenu();
                if (selection.equals("1")) {
                    createTeam();
                } else if (selection.equals("2")) {
                    throw new TNTException("This functionality is not implemented");
                } else if (selection.equals("3")) {
                    throw new TNTException("This functionality is not implemented");
                } else if (selection.equals("4")) {
                    menu.goodbyeMessage();
                    break;
                } else {
                    throw new TNTException("That is not a valid selection");
                }
            } catch (TNTException e) {
                menu.printErrorMessage(e);
            }
        }
    }


    private void createTeam(){
        try {
            TeamInputHelper teamData = menu.initializeNewTeam();
            builder.newTeam(teamData.getName(), teamData.getFaction(), teamData.getMoney());
        } catch (TNTException e){
            menu.printErrorMessage(e);
        }
        menu.displayTeam(builder.getCurrentTeam());
    }





    public ApplicationCLI(){
        try {
            builder = new Builder();
        } catch (DaoException e){
            menu.printErrorMessage(e);
        }
    }



    //main method, exit ASAP
    public static void main(String[] args) {
        ApplicationCLI app = new ApplicationCLI();
        app.run();
    }
}