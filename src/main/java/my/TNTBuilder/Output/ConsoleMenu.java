package my.TNTBuilder.Output;

import my.TNTBuilder.Exceptions.TNTException;
import my.TNTBuilder.Models.Skill;
import my.TNTBuilder.Models.Skillset;
import my.TNTBuilder.Models.Unit;
import my.TNTBuilder.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private Scanner console = new Scanner(System.in);
    private final int BOX_WIDTH = 71;

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



    public NewUnitInputHelper getNewUnitInformationFromUser(List<Unit> unitOptions) throws TNTException{
        System.out.println();
        NewUnitInputHelper newUnit = new NewUnitInputHelper();

        selectUnitFromUnitOptions(unitOptions, newUnit);

        System.out.print("Name Your Unit: ");
        newUnit.setName(console.nextLine());

        return newUnit;
    }

    private void selectUnitFromUnitOptions(List<Unit> unitOptions, NewUnitInputHelper newUnit) throws TNTException {
        for (int i = 0; i < unitOptions.size(); i++){
            System.out.println("(" + (i + 1) + ") " + unitOptions.get(i).getName() + " - Cost: "
                    + unitOptions.get(i).getBaseCost() + " BS");
        }
        System.out.println();
        System.out.print("Please select your unit class: ");
        try {
            int unitChoice = Integer.parseInt(console.nextLine());
            newUnit.setUnit(unitOptions.get(unitChoice - 1));
        } catch (NumberFormatException e){
            throw new TNTException("Enter unit selection as an integer.", e);
        }
    }


    public void displayTeam(Team team){

        int[] padding = calculatePaddingForCenteredText(team.getName());


        System.out.println();
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %" + padding[0] + "s%S%"+ (padding[1]) + "s |%n", "", team.getName(), "");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("| %-12s           |  %7s  |  %21s  |  %6s  |%n",
                "FACTION", "BS COST", "UNSPENT BARTER SCRIP", "UPKEEP");
        System.out.printf("| %-22s |   %4d    |          %5d          |    %2d    |%n",
                team.getFaction(), team.getBSCost(), team.getMoney(), team.getUpkeep());
        System.out.println("---------------------------------------------------------------------------");
        printTeamBox(team);
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-71s |%n", "INVENTORY");
        System.out.printf("| %-71s |%n", "- Admittedly, inventory isn't implemented, so I can't print it.");
        System.out.println("---------------------------------------------------------------------------");

    }

    private int[] calculatePaddingForCenteredText(String string){
        int[] padding = new int[2];
        padding[0] = (BOX_WIDTH - string.length()) / 2;
        padding[1] = padding[0];
        if (string.length() % 2 == 0 ){
            padding[1] += 1;
        }
        return padding;
    }

    private void printTeamBox(Team team) {
        System.out.printf("| %-71s |%n", "MEMBERS");
        if (team.getUnitList().isEmpty()){
            System.out.printf("| %-71s |%n", "- How sad, this warband has no members :(");
        } else {
            for (int i = 0; i < team.getUnitList().size(); i++){
                Unit unit = team.getUnitList().get(i);
                String unitInfo = "(" + (i + 1) + ") " + unit.getUnitNickname() + " - " + unit.getName() + " - " +
                        unit.getBSCost() + " BS";
                int paddingAmount = BOX_WIDTH - unitInfo.length();
                System.out.printf("| %s%" + paddingAmount + "s |%n", unitInfo, "");
            }

        }
    }
    
    
    public void displayUnit(Unit unit) {

        int[] padding = calculatePaddingForCenteredText(unit.getUnitNickname());

        System.out.println();
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %" + padding[0] + "s%S%"+ (padding[1]) + "s |%n", "", unit.getUnitNickname(), "");
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("|         %-5s          |          %-4s          |          %-4s         |%n",
                "TITLE", "RANK", "TYPE");
        System.out.printf("|        %-15s |         %-13s  |         %-6s        |%n", unit.getName(),
                unit.getRank(), unit.getType());
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("|        %-6s          |         %-6s         |        %-7s        |%n",
                "METTLE", "WOUNDS", "BS COST");
        System.out.printf("|           %1d            |           %1d            |          %-3d          |%n",
                unit.getMettle(), unit.getWounds(), unit.getBSCost());
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("|     %4s     |     %5s    |    %6s    |   %8s  |    %7s   |%n", "MOVE", "MELEE", "RANGED",
                "STRENGTH", "DEFENSE");
        System.out.printf("|      %1d       |      %-2d      |       %1s      |      %-2d     |      %-2d      |%n",
                unit.getMove(), unit.getMelee(), unit.getRanged(), unit.getStrength(), unit.getDefense());
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-71s |%n", "INVENTORY");
        System.out.printf("| %-71s |%n", "Inventory isn't implemented");
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-71s |%n", "SPECIAL ABILITIES");
        for (Skill skill : unit.getSkillList()){
            printSkill(skill);
        }
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("|    %-9s     |   %-11s   | %-15s |      %-8s    |%n", "Spent EXP", "Unspent EXP",
                "Cost to Advance", "Upkeep");
        System.out.printf("|       %-3d        |        %-1d        |        %-1d        |         %-1d        |%n",
                unit.getSpentExperience(), unit.getUnspentExperience(), unit.costToAdvance(), unit.getUnitUpkeep());
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-71s |%n", "Available Skillsets:");
        System.out.printf("| %-71s |%n", concatonateSkillsets(unit.getAvailableSkillsets()));
        System.out.println("---------------------------------------------------------------------------");

    }

    private void printSkill(Skill skill) {
        if (skill.getDescription().length() <= 54){
            System.out.printf("| %-15s | %-53s |%n", skill.getName(), skill.getDescription());
        } else {
            List<String> brokenDesc = new ArrayList<>();
            for (int i = 0; i < skill.getDescription().length() - 53; i += 53){
                String substring = skill.getDescription().substring(i, i + 53);
                brokenDesc.add(substring);
            }
            int brokenLength = brokenDesc.size() * 53;
            brokenDesc.add(skill.getDescription().substring(brokenLength));
            for (int i = 0; i < brokenDesc.size(); i++){
                if (i == 0){
                    System.out.printf("| %-15s | %-53s |%n", skill.getName(), brokenDesc.get(i));
                } else {
                    System.out.printf("| %-15s | %-53s |%n", "", brokenDesc.get(i));
                }
            }
        }
    }

    private String concatonateSkillsets(List<Skillset> skillsets){
        StringBuilder returnString = new StringBuilder(new StringBuilder());
        for (Skillset skillset : skillsets){
            returnString.append(skillset.getName()).append(" ");
        }
        return returnString.toString();
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
