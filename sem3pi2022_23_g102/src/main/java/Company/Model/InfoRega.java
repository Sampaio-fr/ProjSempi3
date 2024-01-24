package Company.Model;

import java.time.LocalTime;
import java.util.Objects;

/**
 * The type InfoRega.
 */
public class InfoRega {

    private LocalTime primeiraRega;
    private LocalTime segundaRega;
    private Integer duracao;
    private String regularidade;


    /**
     * Instantiates a new InfoRega.
     *
     * @param primeiraRega the primeira rega
     * @param segundaRega  the segunda rega
     * @param duracao      the duracao
     * @param regularidade the regularidade
     */
    public InfoRega(LocalTime primeiraRega, LocalTime segundaRega, Integer duracao, String regularidade) {
        this.primeiraRega = primeiraRega;
        this.segundaRega = segundaRega;
        this.duracao = duracao;
        this.regularidade = regularidade;
    }

    /**
     * Gets primeira rega.
     *
     * @return the primeira rega
     */
    public LocalTime getPrimeiraRega() {
        return primeiraRega;
    }

    /**
     * Sets primeira rega.
     *
     * @param primeiraRega the primeira rega
     */
    public void setPrimeiraRega(LocalTime primeiraRega) {
        this.primeiraRega = primeiraRega;
    }

    /**
     * Gets segunda rega.
     *
     * @return the segunda rega
     */
    public LocalTime getSegundaRega() {
        return segundaRega;
    }

    /**
     * Sets segunda rega.
     *
     * @param segundaRega the segunda rega
     */
    public void setSegundaRega(LocalTime segundaRega) {
        this.segundaRega = segundaRega;
    }

    /**
     * Gets duracao.
     *
     * @return the duracao
     */
    public Integer getDuracao() {
        return duracao;
    }

    /**
     * Sets duracao.
     *
     * @param duracao the duracao
     */
    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    /**
     * Gets regularidade.
     *
     * @return the regularidade
     */
    public String getRegularidade() {
        return regularidade;
    }

    /**
     * Sets regularidade.
     *
     * @param regularidade the regularidade
     */
    public void setRegularidade(String regularidade) {
        this.regularidade = regularidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoRega infoRega = (InfoRega) o;
        return Objects.equals(primeiraRega, infoRega.primeiraRega) && Objects.equals(segundaRega, infoRega.segundaRega) && Objects.equals(duracao, infoRega.duracao) && Objects.equals(regularidade, infoRega.regularidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primeiraRega, segundaRega, duracao, regularidade);
    }

    @Override
    public String toString() {
        return "InfoRega{" + "primeiraRega=" + primeiraRega + ", segundaRega=" + segundaRega + ", duracao=" + duracao + ", regularidade='" + regularidade + '\'' + '}';
    }
}
