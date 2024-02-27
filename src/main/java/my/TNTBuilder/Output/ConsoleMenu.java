package my.TNTBuilder.Output;

import my.TNTBuilder.Exceptions.TNTException;
import my.TNTBuilder.Models.Unit;
import my.TNTBuilder.Team;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private Scanner console = new Scanner(System.in);

    public void printWelcomeMessage(){
        System.out.println();
        System.out.println("********************************");
        System.out.println("Welcome to the TNT Team Builder!");
        System.out.println("********************************");
        System.out.println();
    }

    public String mainMenu(){
        System.out.println("(1) New Warband");
        System.out.println("(2) Load Warband");
        System.out.println("(3) Lookup Rule");
        System.out.println("(4) Exit Program");
        System.out.println();
        System.out.print("Make Selection: ");
        return console.nextLine();
    }

    public String editTeamMenu(){
        System.out.println();

        System.out.println("(1) Edit Unit");
        System.out.println("(2) Add New Unit");
        System.out.println("(3) Manage Inventory");
        System.out.println("(4) Finish Editing Team");
        System.out.println();
        System.out.print("Make Selection: ");

        return console.nextLine();
    }

    public TeamInputHelper initializeNewTeam(List<String> teamOptions) throws TNTException{
        int money = getStartingMoney();
        String faction = getFaction(teamOptions);

        System.out.print("Name your warband: ");
        String name = console.nextLine();
        return new TeamInputHelper(name, faction, money);
    }

    private String getFaction(List<String> teamOptions) throws TNTException {
        System.out.println("Faction Options:");
        for (int i = 0; i < teamOptions.size(); i++){
            System.out.println("("+ (i + 1) + ") " + teamOptions.get(i));
        }
        System.out.println();
        System.out.print("Please select your faction: ");
        String faction = null;
        try {
            int teamNumber = Integer.parseInt(console.nextLine());
            faction = teamOptions.get(teamNumber - 1);
        } catch (NumberFormatException e){
            throw new TNTException("Enter team selection as an integer.", e);
        }
        return faction;
    }

    private int getStartingMoney() throws TNTException {
        System.out.println();
        System.out.print("Starting Barter Scrip: ");
        int money = 0;
        try {
            money = Integer.parseInt(console.nextLine());
        } catch (NumberFormatException e){
            throw new TNTException("Enter money as an integer.", e);
        }
        System.out.println();
        return money;
    }




    public NewUnitInput getNewUnitInformationFromUser(List<Unit> unitOptions) throws TNTException{
        System.out.println();
        NewUnitInput newUnit = new NewUnitInput();

        for (int i = 0; i < unitOptions.size(); i++){
            System.out.println("(" + (i + 1) + ") " + unitOptions.get(i).getName() + " - Cost: "
                    + unitOptions.get(i).getBaseCost() + " BS");
        }
        System.out.print("Please select your unit class: ");
                try {
            int unitChoice = Integer.parseInt(console.nextLine());
            newUnit.setUnit(unitOptions.get(unitChoice - 1));
        } catch (NumberFormatException e){
            throw new TNTException("Enter unit selection as an integer.", e);
        }

        System.out.print("Name Your Unit: ");
        newUnit.setName(console.nextLine());

        return newUnit;
    }


    public void displayTeam(Team team){

        int boxWidth = 71;
        int padding = (boxWidth - team.getName().length()) / 2;
        int padding2 = padding;
        if (team.getName().length() % 2 == 0 ){
            padding2 += 1;
        }

        System.out.println();
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %" + padding + "s%S%"+ (padding2) + "s |%n", "", team.getName(), "");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("| %-12s           |  %7s  |  %21s  |  %6s  |%n",
                "FACTION", "BS COST", "UNSPENT BARTER SCRIP", "UPKEEP");
        System.out.printf("| %-22s |   %4d    |          %5d          |    %2d    |%n",
                team.getFaction(), team.getBSCost(), team.getMoney(), team.getUpkeep());
        System.out.println("---------------------------------------------------------------------------");
        printTeamBox(team, boxWidth);
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-71s |%n", "INVENTORY");
        System.out.printf("| %-71s |%n", "- Admittedly, I can't print the inventory right now.");
        System.out.println("---------------------------------------------------------------------------");

    }

    private static void printTeamBox(Team team, int boxWidth) {
        System.out.printf("| %-71s |%n", "MEMBERS");
        if (team.getUnitList().isEmpty()){
            System.out.printf("| %-71s |%n", "- How sad, this warband has no members :(");
        } else {
            for (int i = 0; i < team.getUnitList().size(); i++){
                Unit unit = team.getUnitList().get(i);
                String unitInfo = "(" + (i + 1) + ") " + unit.getUnitNickname() + " - " + unit.getName() + " - " +
                        unit.getBSCost() + " BS";
                int paddingAmount = boxWidth - unitInfo.length();
                System.out.printf("| %s%" + paddingAmount + "s |%n", unitInfo, "");
            }

        }
    }


    public void goodbyeMessage(){
        System.out.println();
        System.out.println("--- Exiting Program, Goodbye ---");
    }


    public void printErrorMessage(Exception e){
        System.out.println();
        System.out.println("--- ERROR: " + e.getMessage() + " ---");
        System.out.println();
    }
}
