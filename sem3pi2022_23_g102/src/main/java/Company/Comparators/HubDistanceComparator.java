package Company.Comparators;

import Company.Model.Empresa;
import Company.Model.Pair;

import java.util.Comparator;

/**
 * The type Hub distance comparator.
 */
public class HubDistanceComparator implements Comparator<Pair<Empresa, Integer>> {
    @Override
    public int compare(Pair<Empresa, Integer> h1, Pair<Empresa, Integer> h2) {
        if (h1.getValue() < h2.getValue()) {
            return -1;
        } else if (h1.getValue() > h2.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}