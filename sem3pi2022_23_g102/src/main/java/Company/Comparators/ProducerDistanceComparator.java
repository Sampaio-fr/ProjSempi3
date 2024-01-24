package Company.Comparators;

import Company.Model.Pair;
import Company.Model.Produtor;

import java.util.Comparator;

/**
 * The type Hub distance comparator.
 */
public class ProducerDistanceComparator implements Comparator<Pair<Produtor, Integer>> {
    @Override
    public int compare(Pair<Produtor, Integer> h1, Pair<Produtor, Integer> h2) {
        if (h1.getValue() < h2.getValue()) {
            return -1;
        } else if (h1.getValue() > h2.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}

