// Johan Ehrencrona joeh2789

public class Jewellery extends Valuable {
    private static final int JEWEL_VALUE = 500;
    private int numberOfJewels;
    private Material material;

    public Jewellery(String name, int numberOfJewels, String material){
        super(name);
        this.numberOfJewels = numberOfJewels;
        this.material = Material.valueOf(material);
    }

    public int getJewels(){
        return numberOfJewels;
    }

    public Material getMaterial(){
        return material;
    }

    public double getValue(){
        return material.getValue() + (JEWEL_VALUE*this.getJewels());
    }

    public String toString(){
        return getName() + " värde: "+ getValue() + " med moms: "+ getValuePlusVAT()+ " antalet juveler: " + numberOfJewels + " material: " + getMaterial();
    }

}
