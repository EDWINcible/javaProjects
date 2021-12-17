package domain;

public class denBuilder {
    private String money;
    private Double value;
    private int quantity;
    static denomination denomination;

    public denBuilder withMoney(String money){
        this.money=money;
        return this;
    }

    public denBuilder withValue(Double value){
        this.value=value;
        return this;
    }

    public denBuilder withQuantity(int quantity){
        this.quantity=quantity;
        return this;
    }

    public String getMoney() {
        return money;
    }

    public Double getValue() {
        return value;
    }

    public int getQuantity() {
        return quantity;
    }

    public denomination build() {
        return new denomination(this);
    }
}
