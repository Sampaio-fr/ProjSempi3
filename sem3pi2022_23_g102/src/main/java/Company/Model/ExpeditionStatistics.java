package Company.Model;

import java.util.List;

/**
 * The type Expedition statistics.
 */
public class ExpeditionStatistics {

    private int dia;
    private int cabazSatisfeitos;
    private int cabazParcialmenteSatisfeito;
    private int cabazNaoSatisfeito;
    private int size;
    private List<String> produtoresNaoRepetidos;

    /**
     * Instantiates a new Expedition statistics.
     *
     * @param dia                         the dia
     * @param cabazSatisfeitos            the cabaz satisfeitos
     * @param cabazParcialmenteSatisfeito the cabaz parcialmente satisfeito
     * @param cabazNaoSatisfeito          the cabaz nao satisfeito
     * @param size                        the size
     * @param produtoresNaoRepetidos      the produtores nao repetidos
     */
    public ExpeditionStatistics(int dia, int cabazSatisfeitos, int cabazParcialmenteSatisfeito, int cabazNaoSatisfeito, int size, List<String> produtoresNaoRepetidos) {
        this.dia = dia;
        this.cabazSatisfeitos = cabazSatisfeitos;
        this.cabazParcialmenteSatisfeito = cabazParcialmenteSatisfeito;
        this.cabazNaoSatisfeito = cabazNaoSatisfeito;
        this.size = size;
        this.produtoresNaoRepetidos = produtoresNaoRepetidos;
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
     * @return the dia
     */
    public ExpeditionStatistics setDia(int dia) {
        this.dia = dia;
        return this;
    }

    /**
     * Gets cabaz satisfeitos.
     *
     * @return the cabaz satisfeitos
     */
    public int getCabazSatisfeitos() {
        return cabazSatisfeitos;
    }

    /**
     * Sets cabaz satisfeitos.
     *
     * @param cabazSatisfeitos the cabaz satisfeitos
     */
    public void setCabazSatisfeitos(int cabazSatisfeitos) {
        this.cabazSatisfeitos = cabazSatisfeitos;
    }

    /**
     * Gets cabaz parcialmente satisfeito.
     *
     * @return the cabaz parcialmente satisfeito
     */
    public int getCabazParcialmenteSatisfeito() {
        return cabazParcialmenteSatisfeito;
    }

    /**
     * Sets cabaz parcialmente satisfeito.
     *
     * @param cabazParcialmenteSatisfeito the cabaz parcialmente satisfeito
     */
    public void setCabazParcialmenteSatisfeito(int cabazParcialmenteSatisfeito) {
        this.cabazParcialmenteSatisfeito = cabazParcialmenteSatisfeito;
    }

    /**
     * Gets cabaz nao satisfeito.
     *
     * @return the cabaz nao satisfeito
     */
    public int getCabazNaoSatisfeito() {
        return cabazNaoSatisfeito;
    }

    /**
     * Sets cabaz nao satisfeito.
     *
     * @param cabazNaoSatisfeito the cabaz nao satisfeito
     */
    public void setCabazNaoSatisfeito(int cabazNaoSatisfeito) {
        this.cabazNaoSatisfeito = cabazNaoSatisfeito;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets produtores nao repetidos.
     *
     * @return the produtores nao repetidos
     */
    public List<String> getProdutoresNaoRepetidos() {
        return produtoresNaoRepetidos;
    }

    /**
     * Sets produtores nao repetidos.
     *
     * @param produtoresNaoRepetidos the produtores nao repetidos
     */
    public void setProdutoresNaoRepetidos(List<String> produtoresNaoRepetidos) {
        this.produtoresNaoRepetidos = produtoresNaoRepetidos;
    }

    @Override
    public String toString() {
        if (produtoresNaoRepetidos.contains("Produtor Inexistente")) {
            size = size - 1;
        }
        return "ExpeditionStatistics: " + dia +
                ", cabaz Satisfeitos=" + cabazSatisfeitos +
                ", cabaz parcialmente Satisfeito=" + cabazParcialmenteSatisfeito +
                ", cabaz não Satisfeito=" + cabazNaoSatisfeito +
                ", Produtores usados =" + produtoresNaoRepetidos +
                ", Tamanho = " + size + "\n";
    }
}
