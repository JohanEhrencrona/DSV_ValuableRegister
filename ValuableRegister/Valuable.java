// Johan Ehrencrona joeh2789


public abstract class Valuable {
    private static final double FINAL_VAT = 0.25;
    private String name;
    private int value;
    private double vat;
    private int vatchecker;


    Valuable(String name){
        this.name = name;
    }

    public void setVAT(double newVAT){
        this.vat = newVAT;
        this.vatchecker++;
    }

    public String getName(){
        return name;
    }

    public double getValue(){
        return value;
    }

    public double getValuePlusVAT(){
        if (vatchecker==0){
            return getValue() * (FINAL_VAT+1);
        }
        else return getValue() * (vat+1);
    }

    public String toString(){
        return name + value;
    }
}
