package Company.Model;

/**
 * The type Pair.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class Pair<K, V> {
    private K first; //first member of pair
    private V second; //second member of pair

    /**
     * Instantiates a new Pair.
     *
     * @param first  the first
     * @param second the second
     */
    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public K getKey() {
        return first;
    }

    /**
     * Sets key.
     *
     * @param first the first
     */
    public void setKey(K first) {
        this.first = first;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public V getValue() {
        return second;
    }

    /**
     * Sets value.
     *
     * @param second the second
     */
    public void setValue(V second) {
        this.second = second;
    }
}