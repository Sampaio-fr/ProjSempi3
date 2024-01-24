package Company.Model;

import java.util.List;
import java.util.Objects;

/**
 * The type Cabaz.
 */
public class Cabaz {
    private String cliente_Id;
    private int dia;
    private List<InfoProduto> list;

    /**
     * Instantiates a new Cabaz.
     *
     * @param cliente_Id the cliente id
     * @param dia        the dia
     * @param list       the list
     */
// A constructor.
    public Cabaz(String cliente_Id, int dia, List<InfoProduto> list) {
        this.cliente_Id = cliente_Id;
        this.dia = dia;
        this.list = list;
    }

    /**
     * Gets cliente id.
     *
     * @return the cliente id
     */
    public String getCliente_Id() {
        return cliente_Id;
    }

    /**
     * Sets cliente id.
     *
     * @param cliente_Id the cliente id
     */
    public void setCliente_Id(String cliente_Id) {
        this.cliente_Id = cliente_Id;
    }

    /**
     * Gets dia.
     *
     * @return the dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * Sets dia.
     *
     * @param dia the dia
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Gets list.
     *
     * @return the list
     */
    public List<InfoProduto> getList() {
        return list;
    }

    /**
     * Sets list.
     *
     * @param list the list
     */
    public void setList(List<InfoProduto> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cabaz cabaz)) return false;
        return dia == cabaz.dia && Objects.equals(cliente_Id, cabaz.cliente_Id) && Objects.equals(list, cabaz.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente_Id, dia, list);
    }

    @Override
    public String toString() {
        return "Cliente Id =" + cliente_Id + ", dia = " + dia + "\n" + list + "\n";
    }
}
