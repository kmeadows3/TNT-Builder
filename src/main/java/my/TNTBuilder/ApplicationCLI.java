package my.TNTBuilder;

import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Exceptions.TNTException;
import my.TNTBuilder.Output.ConsoleMenu;
import my.TNTBuilder.Output.NewUnitInput;
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
                    editTeamMenu();
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


    private void editTeamMenu(){
        while (true) {
            menu.displayTeam(builder.getCurrentTeam());
            try {
                String userSelection = menu.editTeamMenu();
                if (userSelection.equals("1")){
                    throw new TNTException("This functionality is not implemented");
                } else if (userSelection.equals("2")){
                    createUnit();
                } else if (userSelection.equals("3")){
                    throw new TNTException("This functionality is not implemented");
                } else if (userSelection.equals("4")){
                    // TODO implement save functionality
                    return;
                } else {
                    throw new TNTException("Please enter a valid selection");
                }
            } catch (TNTException e) {
                menu.printErrorMessage(e);
            }
        }
    }


    private void createTeam(){

        try {
            TeamInputHelper teamData = menu.initializeNewTeam(builder.getRulebook().getTeamOptions());
            builder.newTeam(teamData.getName(), teamData.getFaction(), teamData.getMoney());
        } catch (TNTException e){
            menu.printErrorMessage(e);
        }
    }

    private void createUnit() throws TNTException{
        try {
            NewUnitInput userInput = menu.getNewUnitInformationFromUser(builder.getRulebook().getUnitOptions(
                    builder.getCurrentTeam().getFaction()));
            builder.newUnit(userInput.getName(), userInput.getUnit());
        }  catch (NumberFormatException e) {
            throw new TNTException("Please enter a valid number", e);
        }catch (TNTException e) {
            menu.printErrorMessage(e);
        }
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