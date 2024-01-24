package Company.Comparators;

import Company.Model.Cabaz;

import java.util.Comparator;

/**
 * The type Comparator list cabaz.
 */
public class ComparatorListCabaz implements Comparator<Cabaz> {

    @Override
    public int compare(Cabaz e1, Cabaz e2) {
        return e1.getDia() - e2.getDia();
    }


}
