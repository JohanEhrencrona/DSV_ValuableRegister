// Johan Ehrencrona joeh2789

public enum Material {
    Guld(2000),
    Silver(700);

    private final int value;

    Material(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
