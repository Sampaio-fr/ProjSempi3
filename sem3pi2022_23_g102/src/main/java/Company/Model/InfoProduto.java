package Company.Model;

import java.util.Objects;

/**
 * The type Info produto.
 */
public class InfoProduto {

    private String nomeProduto;
    private Double expected;
    private Double received;
    private String nomeProdutor;
    private int estado;

    /**
     * Instantiates a new Info produto.
     *
     * @param nomeProduto  the nome produto
     * @param expected     the expected
     * @param received     the received
     * @param nomeProdutor the nome produtor
     */
    public InfoProduto(String nomeProduto, Double expected, Double received, String nomeProdutor) {
        this.nomeProduto = nomeProduto;
        this.expected = expected;
        this.received = received;
        this.nomeProdutor = nomeProdutor;
        this.estado = estadoInfo(expected, received);
    }

    private int estadoInfo(Double expected, Double received) {
        if (Objects.equals(expected, received)) return 2;

        if (received > 0) return 1;

        return 0;

    }

    /**
     * Gets nome produto.
     *
     * @return the nome produto
     */
    public String getNomeProduto() {
        return nomeProduto;
    }

    /**
     * Sets nome produto.
     *
     * @param nomeProduto the nome produto
     */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    /**
     * Gets expected.
     *
     * @return the expected
     */
    public Double getExpected() {
        return expected;
    }

    /**
     * Sets expected.
     *
     * @param expected the expected
     */
    public void setExpected(Double expected) {
        this.expected = expected;
    }

    /**
     * Gets received.
     *
     * @return the received
     */
    public Double getReceived() {
        return received;
    }

    /**
     * Sets received.
     *
     * @param received the received
     */
    public void setReceived(Double received) {
        this.received = received;
    }

    /**
     * Gets nome produtor.
     *
     * @return the nome produtor
     */
    public String getNomeProdutor() {
        return nomeProdutor;
    }

    /**
     * Sets nome produtor.
     *
     * @param nomeProdutor the nome produtor
     */
    public void setNomeProdutor(String nomeProdutor) {
        this.nomeProdutor = nomeProdutor;
    }

    /**
     * Gets estado.
     *
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Sets estado.
     *
     * @param estado the estado
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Produto= " + nomeProduto + ", Expected=" + expected + ", Received=" + received + ", Nome Produtor=" + nomeProdutor + ", Estado=" + estado + "\n";
    }
}
