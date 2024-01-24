package Company.Model;

import java.awt.geom.Point2D;
import java.util.Objects;

/**
 * The type Pessoa.
 */
public class Pessoa {

    // Declaring a variable.
    private String localidade;

    private Point2D.Double coordenadas;

    private String idPessoa;

    // It's a constructor.


    /**
     * Instantiates a new Pessoa.
     *
     * @param localidade  the localidade
     * @param coordenadas the coordenadas
     * @param id          the id
     */
    public Pessoa(String localidade, Point2D.Double coordenadas, String id) {
        this.localidade = localidade;
        this.coordenadas = coordenadas;
        this.idPessoa = id;
    }


    /**
     * Gets id pessoa.
     *
     * @return the id pessoa
     */
    public String getIdPessoa() {
        return idPessoa;
    }

    /**
     * Sets id pessoa.
     *
     * @param idPessoa the id pessoa
     */
    public void setIdPessoa(String idPessoa) {
        this.idPessoa = idPessoa;
    }

    /**
     * > This function returns the coordinates of the point
     *
     * @return The coordinates of the point.
     */
    public Point2D.Double getCoordenadas() {
        return coordenadas;
    }

    /**
     * This function sets the coordinates of the object
     *
     * @param coordenadas The coordinates of the point.
     */
    public void setCoordenadas(Point2D.Double coordenadas) {
        this.coordenadas = coordenadas;
    }

    /**
     * This function returns the localidade of the object
     *
     * @return The localidade variable is being returned.
     */
    public String getLocalidade() {
        return localidade;
    }

    /**
     * This function sets the localidade of the object
     *
     * @param localidade The city name
     */
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }


    /**
     * If the object is the same as the one being compared, return true. If the object is null or of a different class,
     * return false. If the object is of the same class, compare the localidade and coordenadas fields
     *
     * @param o The object to compare to.
     * @return The hashCode() method is returning the hash code of the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(localidade, pessoa.localidade) && Objects.equals(coordenadas, pessoa.coordenadas);
    }

    /**
     * The hashCode() method returns a hash code value for the object
     *
     * @return The hashcode of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(localidade, coordenadas, idPessoa);
    }

    /**
     * The toString() method returns a string representation of the object
     *
     * @return The localidade and coordenadas.
     */
    @Override
    public String toString() {
        return "Pessoa: \n" + "Localidade = " + localidade + "; Coordenadas = " + coordenadas.getX() + ", " + coordenadas.getY();

    }
}
