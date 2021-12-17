package domain;

public class machineBuilder {
    private String sweet;
    private Double cost;
    private int quantity;
    static machine machine;



    public machineBuilder withSweet(String sweet){
        this.sweet=sweet;
        return this;
    }

    public machineBuilder withCost(Double cost){
        this.cost=cost;
        return this;
    }

    public machineBuilder withQuantity(int quantity){
        this.quantity=quantity;
        return this;
    }

    public String getSweet() {
        return sweet;
    }

    public Double getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public machine build() {
        return new machine(this);
    }
}
