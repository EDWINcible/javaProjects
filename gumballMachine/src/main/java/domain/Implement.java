package domain;

import bootstrap.Driver;
import domain.Inventory;
import domain.denomination;
import domain.machine;
import org.apache.log4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static bootstrap.Driver.*;

public class Implement {


    static HashMap<denomination, Integer> depositMap = new HashMap<denomination, Integer>();
    static HashMap<machine, Integer> sweetMap = new HashMap<machine, Integer>();
//    public static Logger logger = LoggerFactory.getLogger(Driver.class);

    static Scanner sc = new Scanner(System.in);

    //        Inventory inventory = (Inventory) readInventory();
//    File sweetText = new File("/home/edwin/Documents/workspace/gumballMachine/sweetSave.txt");


//    static Inventory inventory= new Inventory();
    static Inventory inventory;

    static {
        try {
            inventory = (Inventory) readSweets();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    static double deposit = 0;

    public static void depositMoney() throws Exception {
//        inventory.moneyMaker();


        int a = 0, b = 0, c = 0, d = 0;
        boolean flag = true;
        while (flag == true) {
            System.out.println("GumBall Machine Please Enter your choice :");
            System.out.println("1. 25 paise \n2. 50 paise \n3. 1 Rs \n4. 5 Rs \n5.Done");
            int opt = sc.nextInt();

//            while(opt!=5){
            switch (opt) {
                case 1:
                    deposit += 0.25;
                    a = a + 1;
                    depositMap.put(inventory.twentyfive, a);
                    break;
                case 2:
                    deposit += 0.50;
                    b += 1;
                    depositMap.put(inventory.fifty, b);
                    break;
                case 3:
                    deposit += 1.00;
                    c += 1;
                    depositMap.put(inventory.oneRs, c);
                    break;
                case 4:
                    deposit += 5.00;
                    d += 1;
                    depositMap.put(inventory.fiveRs, d);
                    break;
                case 5:
                    System.out.println("Deposited :" + deposit);
//                    logger.info("Money deposited");
//                    buySweets(deposit, depositMap);
                    flag = false;
                    break;
                default:
                    System.out.println("Error");
            }
        }
    }

    public static void buySweets() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println(deposit);
        System.out.println(depositMap);
//        HashMap<machine, Integer> sweetMap = new HashMap<machine, Integer>();
        int a = 0, b = 0, c = 0, d = 0;
        boolean flag = true;
        while (flag == true) {
            System.out.println("Please select sweets :");
            System.out.println("1. KitKat Rs.10.00 \n2. Ecclairs Rs.1.25  \n3. MilkyBar Rs.5.00 \n4. 5 Star Rs.5.50 \n5.Done");
            int opt = sc.nextInt();

//            while(opt!=5){
            switch (opt) {
                case 1:
                    a += 1;
                    sweetMap.put(inventory.kitkat, a);
                    break;
                case 2:
                    b += 1;
                    sweetMap.put(inventory.ecclairs, b);
                    break;
                case 3:
                    c += 1;
                    sweetMap.put(inventory.milkybar, c);
                    break;
                case 4:
                    d += 1;
                    sweetMap.put(inventory.fivestar, d);
                    break;
                case 5:
                    System.out.println("Sweets Chosen :" + sweetMap);
//                    logger.info("Sweets Selected");
//                    transaction(sweetMap,depositMap);
                    flag = false;

                    break;
                default:
                    System.out.println("Error! Please choose again");
            }

        }
    }

    public static void transaction() throws Exception {
        Double sweetCost=0.0,deposit=0.0;
        int sweet;
        for (machine key : sweetMap.keySet()) {
            sweetCost+=key.getCost()*sweetMap.get(key);
            sweet=key.getQuantity();
        }
        for (denomination key1 : depositMap.keySet()) {
            deposit+=key1.getValue()*depositMap.get(key1);
            key1.setQuantity((int) key1.getQuantity()+depositMap.get(key1));
            if(key1.getQuantity()==0){
                System.out.println("Transaction Failed");
                key1.setQuantity((int) key1.getQuantity()-depositMap.get(key1));
                System.out.println("Money returned :");
                System.out.println(key1.getValue()+":"+depositMap.get(key1));
            }
        }
        System.out.println(sweetCost);
        System.out.println(deposit);
        if((double) deposit==(double) sweetCost){
            for (machine key : sweetMap.keySet()) {
                System.out.println(key.getSweet()+":"+sweetMap.get(key));
                key.setQuantity((int) (key.getQuantity()-sweetMap.get(key)));
//                System.out.println(key.getQuantity());
            }
            System.out.println("Sucessfully Purchased");
        }
        else if((double) deposit>(double) sweetCost){
            Double change=deposit-sweetCost;
            for (machine key : sweetMap.keySet()) {
                System.out.println("Successfully Purchased :"+key.getSweet()+"-"+sweetMap.get(key));
                key.setQuantity((int) (key.getQuantity()-sweetMap.get(key)));
            }
            while(change>0){
                if(change>=5.00 && inventory.fiveRs.getQuantity()!=0){
                    inventory.fiveRs.setQuantity((int) (inventory.fiveRs.getQuantity()-1));
                    change=change-5.00;
//                    System.out.println("in 5");
                }
                else if(change>=1.00 && inventory.fiveRs.getQuantity()!=0){
                    inventory.oneRs.setQuantity((int) (inventory.oneRs.getQuantity()-1));
                    change=change-1.00;
//                    System.out.println("in 1");
                }
                else if(change>=0.50 && inventory.fifty.getQuantity()!=0){
                    inventory.fifty.setQuantity((int) (inventory.fifty.getQuantity()-1));
                    change=change-0.50;
//                    System.out.println("in 0.50");
                }
                else if(change>=0.25 && inventory.twentyfive.getQuantity()!=0){
                    inventory.twentyfive.setQuantity((int) (inventory.twentyfive.getQuantity()-1));
                    change=change-0.25;
//                    System.out.println("in 0.25");
                }
                else{
                    System.out.println("Transaction Failed");
                }
            }
        }
        else if((double) deposit<(double) sweetCost){
            System.out.println("Transaction Failed due to insufficient deposit");
            for (denomination key1 : depositMap.keySet()) {
                key1.setQuantity((int) key1.getQuantity()-depositMap.get(key1));
                System.out.println("Money returned :");
                System.out.println(key1.getValue()+":"+depositMap.get(key1));
            }
        }
        else{
            System.out.println("Transaction Failed");
            for (denomination key1 : depositMap.keySet()) {
                key1.setQuantity((int) key1.getQuantity()-depositMap.get(key1));
                System.out.println("Money returned :");
                System.out.println(key1.getValue()+":"+depositMap.get(key1));
            }
        }
        for (denomination key1 : depositMap.keySet()) {
            System.out.println(key1.getMoney()+" Reamaining :"+key1.getQuantity());
        }
        for (machine key : sweetMap.keySet()) {
            System.out.println(key.getSweet()+" Remaining :"+key.getQuantity());
        }
//        logger.info("Transaction Completed");
//        logger.info("Inventory Saved");
        saveSweets(inventory);

    }


}
