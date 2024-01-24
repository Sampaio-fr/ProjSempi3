package Company.Model;

import java.util.Objects;

/**
 * The type Helper.
 */
public class Helper {

    private String localidade1;
    private String localidade2;
    private int distancia;


    /**
     * Instantiates a new Helper.
     *
     * @param localidade1 the localidade 1
     * @param localidade2 the localidade 2
     * @param distancia   the distancia
     */
// A constructor.
    public Helper(String localidade1, String localidade2, int distancia) {
        this.localidade1 = localidade1;
        this.localidade2 = localidade2;
        this.distancia = distancia;
    }

    /**
     * This function returns the value of the localidade1 variable
     *
     * @return The localidade1 variable is being returned.
     */
    public String getLocalidade1() {
        return localidade1;
    }

    /**
     * This function sets the value of the localidade1 variable
     *
     * @param localidade1 The name of the city you want to search for.
     */
    public void setLocalidade1(String localidade1) {
        this.localidade1 = localidade1;
    }

    /**
     * This function returns the localidade2 variable
     *
     * @return The localidade2 variable is being returned.
     */
    public String getLocalidade2() {
        return localidade2;
    }

    /**
     * This function sets the value of the localidade2 variable
     *
     * @param localidade2 The name of the city you want to search for.
     */
    public void setLocalidade2(String localidade2) {
        this.localidade2 = localidade2;
    }

    /**
     * This function returns the distance of the current node from the root node
     *
     * @return The distance between the two points.
     */
    public int getDistancia() {
        return distancia;
    }

    /**
     * It sets the distance of the object.
     *
     * @param distancia The distance from the origin to the destination.
     */
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    /**
     * If the object is the same object, or if the object is of the same class and the distance and the localities are the
     * same, then the objects are equal
     *
     * @param o The object to compare with.
     * @return The hashCode of the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Helper helper = (Helper) o;
        return Double.compare(helper.distancia, distancia) == 0 && Objects.equals(localidade1, helper.localidade1) && Objects.equals(localidade2, helper.localidade2);
    }

    /**
     * The hashCode() method returns a hash code value for the object
     *
     * @return The hash code of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(localidade1, localidade2, distancia);
    }

    /**
     * The toString() method returns a string representation of the object
     *
     * @return The toString method is being returned.
     */
    @Override
    public String toString() {
        return "Helper{" +
                "localidade1= " + localidade1 + "\n" +
                "Localidade2= " + localidade2 + "\n" +
                "Distancia= " + distancia + "\n";

    }
}
