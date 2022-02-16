// Johan Ehrencrona joeh2789

import java.util.Comparator;

class NameSort implements Comparator<Valuable> {
    public int compare(Valuable v1, Valuable v2)
    {return v1.getName().compareTo(v2.getName());}
}

        
