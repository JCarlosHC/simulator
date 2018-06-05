package net.dao;

import java.util.Comparator;
import net.model.relacionPiezaError;

public class horaLlegadaPiezaComparator implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        relacionPiezaError p1 = (relacionPiezaError)o1;
        relacionPiezaError p2 = (relacionPiezaError)o2;
        return p1.getHorainicio().
                compareTo(p2.getHorainicio());
    }
}
