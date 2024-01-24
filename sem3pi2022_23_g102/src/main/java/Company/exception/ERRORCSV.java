package Company.exception;

/**
 * The type Errorcsv.
 */
public class ERRORCSV extends Exception {

    /**
     * throws an exception for the Storage class
     */
    public ERRORCSV() {
        this("Erro no Ficheiro CSV volte a tentar !");
    }

    /**
     * throws an exception for the Storage class with a costume message
     *
     * @param error the error
     */
    public ERRORCSV(String error) {
        super(error);
    }
}
