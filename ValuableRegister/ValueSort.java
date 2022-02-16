// Johan Ehrencrona joeh2789

import java.util.Comparator;

class ValueSort implements Comparator<Valuable> {
    public int compare(Valuable v1, Valuable v2)
    {return Double.compare(v1.getValuePlusVAT(), v2.getValuePlusVAT());}
}
