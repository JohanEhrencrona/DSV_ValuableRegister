// Johan Ehrencrona joeh2789

public class Appliance extends Valuable {
    private double price;
    private int wear;
    private double vat;

    public Appliance(String name, double price, int wear)  {
        super(name);
        this.price = price;
        this.wear = wear;
    }


    public double getPrice(){
        return price;
    }

    public int getWear(){
        return wear;
    }

    public double getValue(){
        return price * ((double)wear/10);
    }

    public String toString(){
        return getName() + " värde: "+ getValue() + " med moms: "+ getValuePlusVAT()+ " Inköpspris: " + getPrice() + " Slitage: " + wear;
    }
}
