package domain;

import java.io.Serializable;


public class Inventory implements Serializable {

    public machine kitkat = machineFactory.getMachine("KitKat");
    public machine ecclairs = machineFactory.getMachine("Ecclairs");
    public machine milkybar = machineFactory.getMachine("MilkyBar");
    public machine fivestar = machineFactory.getMachine("5star");

    public denomination twentyfive = denFactory.getDenomination("25 paise");
    public denomination fifty = denFactory.getDenomination("50 paise");
    public denomination oneRs = denFactory.getDenomination("1 Rs.");
    public denomination fiveRs = denFactory.getDenomination("5 Rs.");

}
