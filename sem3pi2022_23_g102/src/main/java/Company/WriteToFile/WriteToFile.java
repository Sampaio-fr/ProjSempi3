package Company.WriteToFile;

import Company.Graph.matrix.MatrixGraph;
import Company.Model.Pessoa;
import Company.Model.Setor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The type Write to file.
 */
public class WriteToFile {
    /**
     * Write to file matrix.
     *
     * @param matrixGraph the matrix graph
     * @param string      the string
     */
    public static void writeToFileMatrix(MatrixGraph<Pessoa, Integer> matrixGraph, String string) {
        try {
            FileWriter myWriter = new FileWriter(string);
            myWriter.write(matrixGraph.toString());
            myWriter.close();

        } catch (IOException e) {
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
            return;
        }
        System.out.println("Escreveu com sucesso.");
    }

    /**
     * Write to file matrix boolean.
     *
     * @param setorList the setor list
     * @param string    the string
     * @return the boolean
     */
    public static boolean writeToFileMatrix(List<Setor> setorList, String string) {
        boolean flag;

        try {
            FileWriter myWriter = new FileWriter(string);
            myWriter.write(setorList.toString());
            myWriter.close();

            flag = true;
        } catch (IOException e) {
            System.out.println("Ocorreu um erro.");
            flag = false;
            e.printStackTrace();
        }
        if (flag) System.out.println("Escreveu com sucesso.");
        return flag;
    }
}