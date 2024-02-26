package my.TNTBuilder.Output;

import jdk.swing.interop.SwingInterOpUtils;
import my.TNTBuilder.Exceptions.TNTException;
import my.TNTBuilder.Rulebook;
import my.TNTBuilder.Team;

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

    public TeamInputHelper initializeNewTeam() throws TNTException{
        System.out.println();
        System.out.print("Starting Barter Scrip: ");
        int money = 0;
        try {
            money = Integer.parseInt(console.nextLine());
        } catch (NumberFormatException e){
            throw new TNTException("Enter money as an integer.", e);
        }
        System.out.print("New warband's faction: ");
        String faction = console.nextLine();
        System.out.print("Name your warband: ");
        String name = console.nextLine();
        return new TeamInputHelper(name, faction, money);
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

    public String[] getNewUnitInformationFromUser(){
        System.out.println();
        String[] userInput = new String[2];
        System.out.print("Name Your Unit: ");
        userInput[0] = console.nextLine();
        System.out.print("New Unit Title: "); //TODO validate titles
        userInput[1] = console.nextLine();

        return userInput;
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
        System.out.printf("| %-71s |%n", "MEMBERS");
        if (team.getUnitMap().isEmpty()){
            System.out.printf("| %-71s |%n", "- How sad, this warband has no members :(");
        } else {
            //at some point loop through the member map
        }
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-71s |%n", "INVENTORY");
        System.out.printf("| %-71s |%n", "- Admittedly, I can't print the inventory right now.");
        System.out.println("---------------------------------------------------------------------------");

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
