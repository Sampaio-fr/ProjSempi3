package Company.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * The type Plano de rega.
 */
public class PlanoDeRega {

    private LocalDate dia;
    private LocalDateTime inicioPrimeiraRega;
    private LocalDateTime finalPrimeiraRega;
    private LocalDateTime inicioSegundaRega;
    private LocalDateTime finalSegundaRega;


    /**
     * Instantiates a new Plano de rega.
     *
     * @param dia          the dia
     * @param minutos      the minutos
     * @param primeiraRega the primeira rega
     * @param segundaRega  the segunda rega
     */
    public PlanoDeRega(LocalDate dia, long minutos, LocalTime primeiraRega, LocalTime segundaRega) {
        this.dia = dia;
        this.inicioPrimeiraRega = LocalDateTime.of(dia, primeiraRega);
        this.inicioSegundaRega = LocalDateTime.of(dia, segundaRega);
        this.finalPrimeiraRega = inicioPrimeiraRega.plusMinutes(minutos);
        this.finalSegundaRega = inicioSegundaRega.plusMinutes(minutos);
    }

    /**
     * Gets dia.
     *
     * @return the dia
     */
    public LocalDate getDia() {
        return dia;
    }

    /**
     * Sets dia.
     *
     * @param dia the dia
     */
    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    /**
     * Gets inicio primeira rega.
     *
     * @return the inicio primeira rega
     */
    public LocalDateTime getInicioPrimeiraRega() {
        return inicioPrimeiraRega;
    }

    /**
     * Sets inicio primeira rega.
     *
     * @param inicioPrimeiraRega the inicio primeira rega
     */
    public void setInicioPrimeiraRega(LocalDateTime inicioPrimeiraRega) {
        this.inicioPrimeiraRega = inicioPrimeiraRega;
    }

    /**
     * Gets final primeira rega.
     *
     * @return the final primeira rega
     */
    public LocalDateTime getFinalPrimeiraRega() {
        return finalPrimeiraRega;
    }

    /**
     * Sets final primeira rega.
     *
     * @param finalPrimeiraRega the final primeira rega
     */
    public void setFinalPrimeiraRega(LocalDateTime finalPrimeiraRega) {
        this.finalPrimeiraRega = finalPrimeiraRega;
    }

    /**
     * Gets inicio segunda rega.
     *
     * @return the inicio segunda rega
     */
    public LocalDateTime getInicioSegundaRega() {
        return inicioSegundaRega;
    }

    /**
     * Sets inicio segunda rega.
     *
     * @param inicioSegundaRega the inicio segunda rega
     */
    public void setInicioSegundaRega(LocalDateTime inicioSegundaRega) {
        this.inicioSegundaRega = inicioSegundaRega;
    }

    /**
     * Gets final segunda rega.
     *
     * @return the final segunda rega
     */
    public LocalDateTime getFinalSegundaRega() {
        return finalSegundaRega;
    }

    /**
     * Sets final segunda rega.
     *
     * @param finalSegundaRega the final segunda rega
     */
    public void setFinalSegundaRega(LocalDateTime finalSegundaRega) {
        this.finalSegundaRega = finalSegundaRega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanoDeRega that = (PlanoDeRega) o;
        return Objects.equals(dia, that.dia) && Objects.equals(inicioPrimeiraRega, that.inicioPrimeiraRega) && Objects.equals(finalPrimeiraRega, that.finalPrimeiraRega) && Objects.equals(inicioSegundaRega, that.inicioSegundaRega) && Objects.equals(finalSegundaRega, that.finalSegundaRega);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dia, inicioPrimeiraRega, finalPrimeiraRega, inicioSegundaRega, finalSegundaRega);
    }

    @Override
    public String toString() {
        return "Plano de Rega-> " + "Dia = " + dia + ", " + "InicioPrimeiraRega = " + inicioPrimeiraRega + ", " + "FinalPrimeiraRega = " + finalPrimeiraRega + ", " + "InicioSegundaRega = " + inicioSegundaRega + ", " + "FinalSegundaRega=" + finalSegundaRega + "\n";
    }
}
