package domain;

public class machineFactory {
    public static machine getMachine(String type){
        if(type.equals("KitKat")){
            machine kitkatMachine = (new machineBuilder()).withSweet("KitKat")
                    .withCost(10.0)
                    .withQuantity(10)
                    .build();
            return kitkatMachine;
        }
        else if(type.equals("Ecclairs")) {
            machine ecclairsMachine = (new machineBuilder()).withSweet("Ecclairs")
                    .withCost(1.25)
                    .withQuantity(10)
                    .build();
            return ecclairsMachine;
        }
        else if(type.equals("MilkyBar")) {
            machine milkybarMachine = (new machineBuilder()).withSweet("MilkyBar")
                    .withCost(5.00)
                    .withQuantity(10)
                    .build();
            return milkybarMachine;
        }
        else if(type.equals("5star")) {
            machine fivestarMachine = (new machineBuilder()).withSweet("5star")
                    .withCost(5.50)
                    .withQuantity(10)
                    .build();
            return fivestarMachine;
        }
        else{
            return null;
        }
    }
}
