package Company.Model;

import java.util.List;

/**
 * The type Statistics by cabaz.
 */
public class StatisticsByCabaz {

    private int satisfeitos;
    private int parcialmenteSatisfeitos;
    private int insatisfeitos;
    private int produtoresUsados;
    private double percentagemCabazSatisfeito;
    private List<String> producersByCabaz;
    private String cliente;
    private int dia;


    /**
     * Instantiates a new Statistics by cabaz.
     *
     * @param satisfiedProduct        the satisfied product
     * @param parcialSatisfiedProduct the parcial satisfied product
     * @param unsatisfiedProduct      the unsatisfied product
     * @param size                    the size
     * @param producersByCabaz        the producers by cabaz
     * @param clienteId               the cliente id
     * @param dia                     the dia
     */
    public StatisticsByCabaz(int satisfiedProduct, int parcialSatisfiedProduct, int unsatisfiedProduct, int size, List<String> producersByCabaz, String clienteId, int dia) {
        this.satisfeitos = satisfiedProduct;
        this.parcialmenteSatisfeitos = parcialSatisfiedProduct;
        this.insatisfeitos = unsatisfiedProduct;
        this.produtoresUsados = size;
        this.percentagemCabazSatisfeito = percentagemCabazSatisfeitocalculo(satisfeitos, parcialmenteSatisfeitos + satisfeitos + insatisfeitos);
        this.producersByCabaz = producersByCabaz;
        this.cliente = clienteId;
        this.dia = dia;
    }

    private double percentagemCabazSatisfeitocalculo(int satisfeitos, int total) {
        if (satisfeitos > 0) {
            double aux = (double) satisfeitos / total;
            return aux * 100;
        } else {
            return 0;
        }
    }

    /**
     * Gets satisfeitos.
     *
     * @return the satisfeitos
     */
    public int getSatisfeitos() {
        return satisfeitos;
    }

    /**
     * Sets satisfeitos.
     *
     * @param satisfeitos the satisfeitos
     */
    public void setSatisfeitos(int satisfeitos) {
        this.satisfeitos = satisfeitos;
    }

    /**
     * Gets parcialmente satisfeitos.
     *
     * @return the parcialmente satisfeitos
     */
    public int getParcialmenteSatisfeitos() {
        return parcialmenteSatisfeitos;
    }

    /**
     * Sets parcialmente satisfeitos.
     *
     * @param parcialmenteSatisfeitos the parcialmente satisfeitos
     */
    public void setParcialmenteSatisfeitos(int parcialmenteSatisfeitos) {
        this.parcialmenteSatisfeitos = parcialmenteSatisfeitos;
    }

    /**
     * Gets insatisfeitos.
     *
     * @return the insatisfeitos
     */
    public int getInsatisfeitos() {
        return insatisfeitos;
    }

    /**
     * Sets insatisfeitos.
     *
     * @param insatisfeitos the insatisfeitos
     */
    public void setInsatisfeitos(int insatisfeitos) {
        this.insatisfeitos = insatisfeitos;
    }

    /**
     * Gets produtores usados.
     *
     * @return the produtores usados
     */
    public int getProdutoresUsados() {
        return produtoresUsados;
    }

    /**
     * Sets produtores usados.
     *
     * @param produtoresUsados the produtores usados
     */
    public void setProdutoresUsados(int produtoresUsados) {
        this.produtoresUsados = produtoresUsados;
    }

    /**
     * Gets percentagem cabaz satisfeito.
     *
     * @return the percentagem cabaz satisfeito
     */
    public double getPercentagemCabazSatisfeito() {
        return percentagemCabazSatisfeito;
    }

    /**
     * Sets percentagem cabaz satisfeito.
     *
     * @param percentagemCabazSatisfeito the percentagem cabaz satisfeito
     */
    public void setPercentagemCabazSatisfeito(double percentagemCabazSatisfeito) {
        this.percentagemCabazSatisfeito = percentagemCabazSatisfeito;
    }

    /**
     * Gets producers by cabaz.
     *
     * @return the producers by cabaz
     */
    public List<String> getProducersByCabaz() {
        return producersByCabaz;
    }

    /**
     * Sets producers by cabaz.
     *
     * @param producersByCabaz the producers by cabaz
     */
    public void setProducersByCabaz(List<String> producersByCabaz) {
        this.producersByCabaz = producersByCabaz;
    }

    /**
     * Gets cliente.
     *
     * @return the cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Sets cliente.
     *
     * @param cliente the cliente
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
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

    @Override
    public String toString() {
        return "StatisticsByCabaz -> Cliente :" + cliente +
                ", Dia=" + dia +
                ", Satisfeitos = " + satisfeitos +
                ", Parcialmente Satisfeitos = " + parcialmenteSatisfeitos +
                ", Insatisfeitos = " + insatisfeitos +
                ", Produtores =" + produtoresUsados +
                ", Percentagem Cabaz Satisfeito = " + percentagemCabazSatisfeito + "%" +
                ", Produtores do Cabaz : " + producersByCabaz + "\n";


    }
}
