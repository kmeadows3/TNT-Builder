package my.TNTBuilder;

import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Exceptions.FailedMoneyTransaction;
import my.TNTBuilder.Exceptions.TNTException;
import my.TNTBuilder.Output.ConsoleMenu;
import my.TNTBuilder.Output.NewUnitInputHelper;
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


    private void editTeamMenu(){
        while (true) {
            menu.displayTeam(builder.getCurrentTeam());
            try {
                String userSelection = menu.editTeamMenu();
                if (userSelection.equals("1")){
                    renameTeam();
                } else if (userSelection.equals("2")){
                    editUnit();
                } else if (userSelection.equals("3")){
                    createUnit();
                } else if (userSelection.equals("4")){
                    manageMoneyMenu();
                } else if (userSelection.equals("5")){
                    throw new TNTException("This functionality is not implemented");
                } else if (userSelection.equals("6")){
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




    private void createTeam() throws TNTException{
        try {
            TeamInputHelper teamData = menu.initializeNewTeam(builder.getReference().getTeamOptions());
            builder.newTeam(teamData.getName(), teamData.getFaction(), teamData.getMoney());
            editTeamMenu();
        } catch (TNTException e) {
            menu.printErrorMessage(e);
        }
    }

    private void createUnit(){
        try {
            String faction = builder.getCurrentTeam().getFaction();
            NewUnitInputHelper userInput = menu.getNewUnitInformationFromUser(builder.getReference().getUnitOptions(
                    faction));
            builder.newUnit(userInput.getName(), userInput.getUnit());
            editUnitMenu();
        }  catch (NumberFormatException e) {
            TNTException ex = new TNTException("Please enter a valid number", e);
            menu.printErrorMessage(ex);
        } catch (TNTException e) {
            menu.printErrorMessage(e);
        }
    }

    private void editUnit() throws TNTException {
        if (builder.getCurrentTeam().getUnitList().isEmpty()){
            throw new TNTException("This team has no units to edit");
        }
        String userSelection = menu.selectUnitToEdit();
        try {
            int index = Integer.parseInt(userSelection) - 1;
            builder.setCurrentUnit(builder.getCurrentTeam().getUnitList().get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new TNTException("Please select a valid unit number", e);
        } catch (NumberFormatException e) {
            throw new TNTException("Please enter selection as a number");
        }
        editUnitMenu();
    }

    private void manageMoneyMenu(){
        while (true){
            String userSelection = menu.manageMoneyMenu();
            try {
                if (userSelection.equals("1")) {
                    gainMoney();
                } else if (userSelection.equals("2")) {
                    loseMoney();
                } else if (userSelection.equals("3")) {
                    payUpkeep();
                } else if (userSelection.equals("4")) {
                    return;
                } else {
                    throw new TNTException("Please enter a valid selection");
                }
            } catch (TNTException e) {
                menu.printErrorMessage(e);
            }
        }
    }

    private void gainMoney() throws TNTException {
        String userInput = menu.getMoneyGained();
        int startingFunds = builder.getCurrentTeam().getMoney();
        try {
            int gainedMoney = Integer.parseInt(userInput);
            builder.gainMoney(gainedMoney);
        } catch (FailedMoneyTransaction e) {
            throw new TNTException(e.getMessage(), e);
        }
        int endingFunds = builder.getCurrentTeam().getMoney();
        menu.moneyFeedback(startingFunds, endingFunds);
    }

    private void loseMoney () throws TNTException{
        String userInput = menu.getMoneyLost();
        int startingFunds = builder.getCurrentTeam().getMoney();
        try {
            int spentMoney = Integer.parseInt(userInput);
            builder.spendMoney(spentMoney);
        } catch (FailedMoneyTransaction e) {
            throw new TNTException(e.getMessage(), e);
        }
        int endingFunds = builder.getCurrentTeam().getMoney();
        menu.moneyFeedback(startingFunds, endingFunds);
    }

    private void payUpkeep() throws FailedMoneyTransaction {
        int startingFunds = builder.getCurrentTeam().getMoney();
        if (builder.getCurrentTeam().getUpkeep() > 0) {
            builder.spendMoney(builder.getCurrentTeam().getUpkeep());
        } else {
            throw new FailedMoneyTransaction("Your upkeep is " + builder.getCurrentTeam().getUpkeep());
        }
        int endingFunds = builder.getCurrentTeam().getMoney();
        menu.moneyFeedback(startingFunds, endingFunds);
    }


    private void editUnitMenu(){
        while (true) {
            menu.displayUnit(builder.getCurrentUnit());
            try {
                String userSelection = menu.editUnitMenu();
                if (userSelection.equals("1")) {
                    renameUnit();
                } else if (userSelection.equals("2")) {
                    throw new TNTException("This functionality is not implemented");
                } else if (userSelection.equals("3")) {
                    throw new TNTException("This functionality is not implemented");
                } else if (userSelection.equals("4")) {
                    throw new TNTException("This functionality is not implemented");
                } else if (userSelection.equals("5")) {
                    return;
                } else {
                    throw new TNTException("Please enter a valid selection");
                }
            } catch (TNTException e) {
                menu.printErrorMessage(e);
            }
        }
    }

    public void renameTeam(){
        String newName = menu.rename();
        builder.getCurrentTeam().setName(newName);
    }

    public void renameUnit(){
        String newName = menu.rename();
        builder.getCurrentUnit().setUnitNickname(newName);
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