package sample;

public class Product {
    private String name;
    private String weight;
    private String profit;

    Product(){
        this.name = "";
        this.weight = "";
        this.profit = "";
    }

    Product(String name,String weight,String profit){
        this.name = name;
        this.weight = weight;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }
}
