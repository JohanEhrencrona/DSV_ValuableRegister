// Johan Ehrencrona joeh2789

public class Stock extends Valuable {
    private int quantity;
    private double rate;
    private double vat;

    public Stock(String name, int quantity, double rate)  {
        super(name);
        this.quantity=quantity;
        this.rate=rate;
    }



    public void setRate(double newRate){
        this.rate=newRate;
    }

    public double getRate(){
        return rate;
    }



    public double getValue(){
        return quantity*rate;
    }

    public int getQuantity(){
        return quantity;
    }

    public String toString(){
        return getName() + " värde: "+ getValue() + " med moms: "+ getValuePlusVAT()+ " antal: " + getQuantity() + " kurs: " + getRate();
    }
}
