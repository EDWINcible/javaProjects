package domain;

import java.io.Serializable;

public class denomination implements Serializable {
    private String money;
    private Double value;
    private  int quantity;

    public denomination(denBuilder denBuilder){
        this.money = denBuilder.getMoney();
        this.value = denBuilder.getValue();
        this.quantity = denBuilder.getQuantity();
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
