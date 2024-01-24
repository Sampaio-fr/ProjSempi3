package Methods;

import Company.Model.PlanoDeRega;
import Company.Model.Setor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Methods rega.
 */
public class MethodsRega {

    /**
     * Instantiates a new Methods rega.
     */
    public MethodsRega() {
    }


    /**
     * It checks if a given time is between the start and end of a given day's irrigation plan
     *
     * @param setorList List of all the sectors
     * @param setor     The name of the sector
     * @param hora      LocalDateTime
     * @return It is being returned a boolean value.
     */
    public boolean verificarRega(List<Setor> setorList, String setor, LocalDateTime hora) {
        Setor setorAux = null;
        for (Setor aux : setorList) {//O(n)
            if (aux.getParcela().equals(setor))
                setorAux = aux;
        }
        if (setorAux == null) {
            System.out.println("Não existe a parcela");
            return false;
        }
        LocalDate date = hora.toLocalDate();

        for (PlanoDeRega planoDeRegaAux : setorAux.getPlanoDeRegaList()) {//O(n)
            if (planoDeRegaAux.getDia().equals(date)) {
                if (hora.isAfter(planoDeRegaAux.getInicioPrimeiraRega()) && hora.isBefore(planoDeRegaAux.getFinalPrimeiraRega())) {
                    return true;
                }
                if (hora.isAfter(planoDeRegaAux.getInicioSegundaRega()) && hora.isBefore(planoDeRegaAux.getFinalSegundaRega())) {
                    return true;
                }
            }
        }
        return false;
    }//O(n^2)
}
