package Company.Model;

import java.util.List;

/**
 * The type Statistics by produtor.
 */
public class StatisticsByProdutor {

    private Produtor produtor;
    private int cabazTotalmenteFornecido;
    private int cabazParcialmenteFornecidos;
    private int esgotadoProdutos;
    private List<Empresa> hubsFornecidos;
    private int hubsTamanho;

    /**
     * Instantiates a new Statistics by produtor.
     *
     * @param produtor                    the produtor
     * @param cabazTotalmenteFornecido    the cabaz totalmente fornecido
     * @param cabazParcialmenteFornecidos the cabaz parcialmente fornecidos
     * @param esgotadoProdutos            the esgotado produtos
     * @param hubsFornecidos              the hubs fornecidos
     */
    public StatisticsByProdutor(Produtor produtor, int cabazTotalmenteFornecido, int cabazParcialmenteFornecidos, int esgotadoProdutos, List<Empresa> hubsFornecidos) {
        this.produtor = produtor;
        this.cabazTotalmenteFornecido = cabazTotalmenteFornecido;
        this.cabazParcialmenteFornecidos = cabazParcialmenteFornecidos;
        this.esgotadoProdutos = esgotadoProdutos;
        this.hubsFornecidos = hubsFornecidos;
        this.hubsTamanho = hubsFornecidos.size();
    }

    /**
     * Gets produtor.
     *
     * @return the produtor
     */
    public Produtor getProdutor() {
        return produtor;
    }

    /**
     * Sets produtor.
     *
     * @param produtor the produtor
     * @return the produtor
     */
    public StatisticsByProdutor setProdutor(Produtor produtor) {
        this.produtor = produtor;
        return this;
    }

    /**
     * Gets cabaz totalmente fornecido.
     *
     * @return the cabaz totalmente fornecido
     */
    public int getCabazTotalmenteFornecido() {
        return cabazTotalmenteFornecido;
    }

    /**
     * Sets cabaz totalmente fornecido.
     *
     * @param cabazTotalmenteFornecido the cabaz totalmente fornecido
     */
    public void setCabazTotalmenteFornecido(int cabazTotalmenteFornecido) {
        this.cabazTotalmenteFornecido = cabazTotalmenteFornecido;
    }

    /**
     * Gets cabaz parcialmente fornecidos.
     *
     * @return the cabaz parcialmente fornecidos
     */
    public int getCabazParcialmenteFornecidos() {
        return cabazParcialmenteFornecidos;
    }

    /**
     * Sets cabaz parcialmente fornecidos.
     *
     * @param cabazParcialmenteFornecidos the cabaz parcialmente fornecidos
     */
    public void setCabazParcialmenteFornecidos(int cabazParcialmenteFornecidos) {
        this.cabazParcialmenteFornecidos = cabazParcialmenteFornecidos;
    }

    /**
     * Gets esgotado produtos.
     *
     * @return the esgotado produtos
     */
    public int getEsgotadoProdutos() {
        return esgotadoProdutos;
    }

    /**
     * Sets esgotado produtos.
     *
     * @param esgotadoProdutos the esgotado produtos
     */
    public void setEsgotadoProdutos(int esgotadoProdutos) {
        this.esgotadoProdutos = esgotadoProdutos;
    }

    /**
     * Gets hubs fornecidos.
     *
     * @return the hubs fornecidos
     */
    public List<Empresa> getHubsFornecidos() {
        return hubsFornecidos;
    }

    /**
     * Sets hubs fornecidos.
     *
     * @param hubsFornecidos the hubs fornecidos
     */
    public void setHubsFornecidos(List<Empresa> hubsFornecidos) {
        this.hubsFornecidos = hubsFornecidos;
    }

    /**
     * Gets hubs tamanho.
     *
     * @return the hubs tamanho
     */
    public int getHubsTamanho() {
        return hubsTamanho;
    }

    /**
     * Sets hubs tamanho.
     *
     * @param hubsTamanho the hubs tamanho
     */
    public void setHubsTamanho(int hubsTamanho) {
        this.hubsTamanho = hubsTamanho;
    }

    @Override
    public String toString() {
        return "Statistics By Produtor: " + produtor.getIdPessoa() +
                ", cabazTotalmenteFornecido =" + cabazTotalmenteFornecido +
                ", cabazParcialmenteFornecidos = " + cabazParcialmenteFornecidos +
                ", esgotadoProdutos = " + esgotadoProdutos +
                ", hubsFornecidos = " + hubsFornecidos +
                ", hubsTamanho = " + hubsTamanho + "\n";
    }
}
