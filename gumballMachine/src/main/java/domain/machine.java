package domain;

import java.io.Serializable;


public class machine implements Serializable {
    private String sweet;
    private Double cost;
    private int quantity;

    public machine(machineBuilder machineBuilder){
        this.sweet = machineBuilder.getSweet();
        this.cost = machineBuilder.getCost();
        this.quantity = machineBuilder.getQuantity();
    }

    public String getSweet() {
        return sweet;
    }

    public void setSweet(String sweet) {
        this.sweet = sweet;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
