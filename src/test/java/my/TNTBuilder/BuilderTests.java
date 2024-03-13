package my.TNTBuilder;

import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Exceptions.FailedMoneyTransaction;
import my.TNTBuilder.Exceptions.InvalidUnitPurchaseException;
import my.TNTBuilder.Exceptions.TNTException;
import my.TNTBuilder.Models.Unit;
import org.junit.*;

import java.util.ArrayList;

public class BuilderTests {
    Builder builder = null;

    @Before
    public void setBuilder() throws TNTException {
        builder = new Builder();
    }

    @Test
    public void newTeam_makes_new_team_empty_team() throws TNTException{
        String name = "Test Name";
        String faction = "Caravanners";
        int money = 500;
        Team testTeam = builder.newTeam(name, faction, money);
        Assert.assertEquals(name, testTeam.getName());
        Assert.assertEquals(faction, testTeam.getFaction());
        Assert.assertEquals(money, testTeam.getMoney());
        Assert.assertEquals(0, testTeam.getUnitList().size());
        //TODO deal with inventory when that's implemented
    }

    @Test
    public void newTeam_will_fail_with_negative_starting_money(){
        String name = "Test Name";
        String faction = "Caravanners";
        int money = -100;
        try {
            Team testTeam = builder.newTeam(name, faction, money);
            Assert.fail();
        } catch (TNTException e){        }
    }

    @Test
    public void newUnit_makes_currentUnit_equal_new_unit () throws TNTException{
        String name = "Unit Name";
        Unit unit = builder.getReference().getUnits().get(1);
        Team team = builder.newTeam("Team Name", "Caravanners", 500);
        builder.newUnit(name, unit);
        Assert.assertEquals(unit, builder.getCurrentUnit());
    }

    @Test
    public void newUnit_clones_unit_successfully() throws TNTException{
        String name = "Unit Name";
        Unit unit = builder.getReference().getUnits().get(1);
        Team team = builder.newTeam("Team Name", "Caravanners", 500);
        builder.newUnit(name, unit);
        Assert.assertEquals(builder.getReference().getUnits().get(1), builder.getCurrentUnit());
        Assert.assertNotSame(unit.getInventory(), builder.getCurrentUnit().getInventory());
        Assert.assertNotSame(unit.getSkillList(), builder.getCurrentUnit().getSkillList());
    }

    @Test
    public void newUnit_added_unit_to_team_unitList() throws TNTException{
        String name = "Unit Name";
        Unit unit = builder.getReference().getUnits().get(1);
        Team team = builder.newTeam("Team Name", "Caravanners", 500);
        builder.newUnit(name, unit);
        Assert.assertEquals(1, builder.getCurrentTeam().getUnitList().size());
        Assert.assertTrue(builder.getCurrentTeam().getUnitList().contains(builder.getCurrentUnit()));
    }

    @Test
    public void newUnit_fails_if_not_enough_money() throws TNTException{
        String name = "Unit Name";
        Unit unit = builder.getReference().getUnits().get(1);
        Team team = builder.newTeam("Team Name", "Caravanners", 30);

        try {
            builder.newUnit(name, unit);
            Assert.fail();
        } catch (TNTException e){
            
        }

    }

    @Test
    public void add_unit_throws_exception_with_invalid_unit() throws TNTException{
        Team team = builder.newTeam("Team Name", "Caravanners", 30);

        Unit unit = new Unit(1, "Raiders", "Title", "Rank", "Type", 50,
                "Note", 1, 1, 1, 1, 1, 1, 1, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        try {
            builder.newUnit("Bob", unit);
            Assert.fail();
        } catch (InvalidUnitPurchaseException e) {

        }
    }

    @Test
    public void spend_money_reduces_team_money() throws FailedMoneyTransaction, TNTException {
        Team team = builder.newTeam("Team Name", "Caravanners", 200);

        builder.spendMoney(150);
        Assert.assertEquals(50, builder.getCurrentTeam().getMoney());
    }

    @Test
    public void spend_money_fails_when_not_enough_money() throws TNTException{
        Team team = builder.newTeam("Team Name", "Caravanners", 30);

        try{
            builder.spendMoney(201);
            Assert.fail();
        } catch (FailedMoneyTransaction e){

        }
    }

    @Test
    public void spend_money_fails_with_negative_parameter() throws TNTException{
        Team team = builder.newTeam("Team Name", "Caravanners", 30);
        try{
            builder.spendMoney(-1);
            Assert.fail();
        } catch (FailedMoneyTransaction e){

        }
    }

    @Test
    public void gain_money_adds_correct_amount() throws TNTException{
        Team team = builder.newTeam("Team Name", "Caravanners", 30);
        builder.gainMoney(50);
        Assert.assertEquals(80, builder.getCurrentTeam().getMoney());
    }

    @Test
    public void gain_money_adds_correct_amount_multiple_calls() throws TNTException{
        Team team = builder.newTeam("Team Name", "Caravanners", 30);
        builder.gainMoney(50);
        builder.gainMoney(50);
        Assert.assertEquals(130, builder.getCurrentTeam().getMoney());
    }

    @Test
    public void gain_money_fails_with_negative_values() throws TNTException{
        Team team = builder.newTeam("Team Name", "Caravanners", 30);
        try {
            builder.gainMoney(-50);
            Assert.fail();
        } catch (FailedMoneyTransaction e) {

        }
    }

    @Test
    public void gain_experience_adds_to_unused_experience() throws TNTException{
        String name = "Unit Name";
        Unit unit = builder.getReference().getUnits().get(1);
        Team team = builder.newTeam("Team Name", "Caravanners", 500);
        builder.newUnit(name, unit);
        builder.gainExp(10);
        Assert.assertEquals(10, builder.getCurrentUnit().getUnspentExperience());
    }


    @Test
    public void gain_experience_will_not_add_negative_experience() throws TNTException{
        String name = "Unit Name";
        Unit unit = builder.getReference().getUnits().get(1);
        Team team = builder.newTeam("Team Name", "Caravanners", 500);
        builder.newUnit(name, unit);
        try {
            builder.gainExp(-10);
            Assert.fail();
        } catch (TNTException e){
            Assert.assertEquals(0, builder.getCurrentUnit().getUnspentExperience());
        }
    }


    @Test
    public void spend_experience_subtracts_from_unused_experience() throws TNTException {
        String name = "Unit Name";
        Unit unit = builder.getReference().getUnits().get(1);
        Team team = builder.newTeam("Team Name", "Caravanners", 500);
        builder.newUnit(name, unit);
        builder.getCurrentUnit().setUnspentExperience(50);
        builder.spendExp(10);
        Assert.assertEquals(40, builder.getCurrentUnit().getUnspentExperience());
    }

    @Test
    public void spend_experience_fails_with_negative_exp() throws TNTException {
        String name = "Unit Name";
        Unit unit = builder.getReference().getUnits().get(1);
        Team team = builder.newTeam("Team Name", "Caravanners", 500);
        builder.newUnit(name, unit);
        builder.getCurrentUnit().setUnspentExperience(50);
        try {
            builder.spendExp(-10);
            Assert.fail();
        } catch (TNTException e){
            Assert.assertEquals(50, builder.getCurrentUnit().getUnspentExperience());
        }
    }

    @Test
    public void spend_experience_fails_when_not_enough_exp() throws TNTException {
        String name = "Unit Name";
        Unit unit = builder.getReference().getUnits().get(1);
        Team team = builder.newTeam("Team Name", "Caravanners", 500);
        builder.newUnit(name, unit);
        builder.getCurrentUnit().setUnspentExperience(10);
        try {
            builder.spendExp(20);
            Assert.fail();
        } catch (TNTException e){
            Assert.assertEquals(10, builder.getCurrentUnit().getUnspentExperience());
        }
    }

}
