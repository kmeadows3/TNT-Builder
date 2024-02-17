package my.TNTBuilder.Input;

import my.TNTBuilder.DataClasses.Equipment;
import my.TNTBuilder.DataClasses.ItemTrait;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    private File equipmentFile = new File("Reference/testCSVs/testEquipment.csv");
    private List<String> inputLines = new ArrayList<>();

    public void readInventoryFile() throws FileNotFoundException {
        try(Scanner reader = new Scanner(equipmentFile)){
            while (reader.hasNextLine()){
                inputLines.add(reader.nextLine());
            }
        }
    }

    private Equipment stringToEquipment(String string){
        String[] equipmentParts = string.split(",");
        String type = equipmentParts[1];
        int cost = Integer.parseInt(equipmentParts[2]);
        String specialRules = equipmentParts[3];
        List<ItemTrait> traits = new ArrayList<>();  //Will need to fix to return List of Item Traits if traits exist
        String rarity = equipmentParts[5];
        Boolean isRelic = false;
        if (!rarity.equals("N/A")){
            isRelic = true;
        }
        Equipment currentItem = new Equipment(type, cost, specialRules, traits, rarity, isRelic);
        return currentItem;
    }


    public static void main(String[] args) throws FileNotFoundException{
        FileReader fr = new FileReader();
        fr.run();
    }


    private void run() throws FileNotFoundException{
        readInventoryFile();
        stringToEquipment(inputLines.get(1));
        System.out.println("Done!");
    }


}
