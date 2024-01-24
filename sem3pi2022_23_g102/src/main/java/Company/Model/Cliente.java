package Company.Model;

import java.awt.geom.Point2D;
import java.util.Objects;

/**
 * The type Cliente.
 */
public class Cliente extends Pessoa {


    // A private variable that is only accessible within the class.
    private String id;
    // A private variable that is only accessible within the class.

    /**
     * Instantiates a new Cliente.
     *
     * @param localidade  the localidade
     * @param id          the id
     * @param coordenadas the coordenadas
     */
// A constructor.
    public Cliente(String localidade, String id, Point2D.Double coordenadas) {
        super(localidade, coordenadas, id);
        this.id = id;
    }

    /**
     * This function returns the id of the current object
     *
     * @return The id of the object.
     */
    public String getId() {
        return id;
    }

    /**
     * This function sets the id of the object to the id passed in as a parameter
     *
     * @param id The id of the user.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * If the object is not null, and the object is of the same class, and the superclass equals method returns true, then
     * return true if the id's are equal
     *
     * @param o The object to compare to.
     * @return The hashcode of the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    /**
     * The hashCode() method returns a hash code value for the object
     *
     * @return The hashcode of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    /**
     * The toString() method returns a string representation of the object
     *
     * @return The id of the client.
     */
    @Override
    public String toString() {
        return super.toString() + "; Id: " + id + "\n";

    }
}
