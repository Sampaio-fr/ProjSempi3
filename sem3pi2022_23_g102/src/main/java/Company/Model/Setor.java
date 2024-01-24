package Company.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Setor.
 */
public class Setor {

    private String parcela;
    private InfoRega infoRega;
    private List<PlanoDeRega> planoDeRegaList;

    /**
     * Instantiates a new Setor.
     *
     * @param parcela  the parcela
     * @param infoRega the infoRega
     */
    public Setor(String parcela, InfoRega infoRega) {
        this.parcela = parcela;
        this.infoRega = infoRega;
        this.planoDeRegaList = new ArrayList<>();
    }

    /**
     * Instantiates a new Setor.
     *
     * @param parcela         the parcela
     * @param infoRega        the infoRega
     * @param planoDeRegaList the plano de rega list
     */
    public Setor(String parcela, InfoRega infoRega, List<PlanoDeRega> planoDeRegaList) {
        this.parcela = parcela;
        this.infoRega = infoRega;
        this.planoDeRegaList = planoDeRegaList;
    }

    /**
     * Gets parcela.
     *
     * @return the parcela
     */
    public String getParcela() {
        return parcela;
    }

    /**
     * Sets parcela.
     *
     * @param parcela the parcela
     */
    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    /**
     * Gets infoRega.
     *
     * @return the infoRega
     */
    public InfoRega getInfo() {
        return infoRega;
    }

    /**
     * Sets infoRega.
     *
     * @param infoRega the infoRega
     */
    public void setInfo(InfoRega infoRega) {
        this.infoRega = infoRega;
    }

    /**
     * Gets plano de rega list.
     *
     * @return the plano de rega list
     */
    public List<PlanoDeRega> getPlanoDeRegaList() {
        return planoDeRegaList;
    }

    /**
     * Sets plano de rega list.
     *
     * @param planoDeRegaList the plano de rega list
     */
    public void setPlanoDeRegaList(List<PlanoDeRega> planoDeRegaList) {
        this.planoDeRegaList = planoDeRegaList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Setor setor = (Setor) o;
        return Objects.equals(parcela, setor.parcela) && Objects.equals(infoRega, setor.infoRega) && Objects.equals(planoDeRegaList, setor.planoDeRegaList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parcela, infoRega, planoDeRegaList);
    }

    @Override
    public String toString() {
        return " Setor ->" + "parcela: " + parcela + ", planoDeRegaList: " + planoDeRegaList + "\n";
    }
}
