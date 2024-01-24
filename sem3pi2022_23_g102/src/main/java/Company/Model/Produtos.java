package Company.Model;

import java.util.Objects;

/**
 * The type Produtos.
 */
public class Produtos {

    private String nomeFruta;


    /**
     * Instantiates a new Produtos.
     *
     * @param nomeFruta the nome fruta
     */
    public Produtos(String nomeFruta) {
        this.nomeFruta = nomeFruta;
    }

    /**
     * Gets nome fruta.
     *
     * @return the nome fruta
     */
    public String getNomeFruta() {
        return nomeFruta;
    }

    /**
     * Sets nome fruta.
     *
     * @param nomeFruta the nome fruta
     */
    public void setNomeFruta(String nomeFruta) {
        this.nomeFruta = nomeFruta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produtos produtos)) return false;
        return Objects.equals(nomeFruta, produtos.nomeFruta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeFruta);
    }

    @Override
    public String toString() {
        return nomeFruta + ":";
    }
}
