package domain;

public class denFactory {
    public static denomination getDenomination(String type){
        if(type.equals("25 paise")) {
            denomination twentyfive = (new denBuilder()).withMoney("25 paise")
                    .withValue(0.25)
                    .withQuantity(50)
                    .build();
            return twentyfive;
        }
        else if(type.equals("50 paise")) {
            denomination fifty = (new denBuilder()).withMoney("50 paise")
                    .withValue(0.50)
                    .withQuantity(50)
                    .build();
            return fifty;
        }
        else if(type.equals("1 Rs.")) {
            denomination oneRs = (new denBuilder()).withMoney("1 Rs.")
                    .withValue(1.00)
                    .withQuantity(50)
                    .build();
            return oneRs;
        }
        else if(type.equals("5 Rs.")) {
            denomination fiveRs = (new denBuilder()).withMoney("5 Rs.")
                    .withValue(5.00)
                    .withQuantity(50)
                    .build();
            return fiveRs;
        }
        else{
            return null;
        }
    }
}
