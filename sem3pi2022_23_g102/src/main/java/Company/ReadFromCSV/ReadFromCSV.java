package Company.ReadFromCSV;

import Company.Model.*;
import Company.Store.StoreLists;
import Methods.CreateMap;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Company.Constants.Constants.*;

/**
 * The type Read from csv.
 */
public class ReadFromCSV {

    private StoreLists storeLists;
    private CreateMap createMap;

    /**
     * Instantiates a new Read from csv.
     *
     * @param storeLists the store lists
     * @param createMap  the create map
     */
    public ReadFromCSV(StoreLists storeLists, CreateMap createMap) {
        this.storeLists = storeLists;
        this.createMap = createMap;
    }


    /**
     * It reads a file and creates a list of objects of type Pessoa, which is a superclass of Cliente, Produtor and Empresa
     *
     * @param caminho the path to the file
     * @return A list of Pessoa objects.
     * @throws FileNotFoundException the file not found exception
     */
    public List<Pessoa> lerClienteProdutor(String caminho) throws FileNotFoundException {
        Scanner read = new Scanner(new File(caminho));
        read.nextLine();
        while (read.hasNextLine()) {
            String[] line = read.nextLine().split(",");
            if (line[CLIENTEPRODUTOR_NOME].contains("E")) {// O(n)
                Empresa empresaAux = new Empresa(line[CLIENTEPRODUTOR_LOC], line[CLIENTEPRODUTOR_NOME], new Point2D.Double(Double.parseDouble(line[CLIENTEPRODUTOR_LAT]), Double.parseDouble(line[CLIENTEPRODUTOR_LONG])));
                if (!storeLists.getPessoaList().contains(empresaAux)) {// O(n)
                    storeLists.addPessoaList(empresaAux);
                }
            }
            if (line[CLIENTEPRODUTOR_NOME].contains("P")) {// O(n)
                Produtor produtorAux = new Produtor(line[CLIENTEPRODUTOR_LOC], line[CLIENTEPRODUTOR_NOME], new Point2D.Double(Double.parseDouble(line[CLIENTEPRODUTOR_LAT]), Double.parseDouble(line[CLIENTEPRODUTOR_LONG])));
                if (!storeLists.getPessoaList().contains(produtorAux)) {// O(n)
                    storeLists.addPessoaList(produtorAux);
                }
            }
            if (line[CLIENTEPRODUTOR_NOME].contains("C")) {// O(n)
                Cliente clienteAux = new Cliente(line[CLIENTEPRODUTOR_LOC], line[CLIENTEPRODUTOR_NOME], new Point2D.Double(Double.parseDouble(line[CLIENTEPRODUTOR_LAT]), Double.parseDouble(line[CLIENTEPRODUTOR_LONG])));
                if (!storeLists.getPessoaList().contains(clienteAux)) {// O(n)
                    storeLists.addPessoaList(clienteAux);
                }
            }
        }
        return storeLists.getPessoaList();
    }// O(n^2)

    /**
     * It reads a file and returns a list of objects
     *
     * @param caminho the path to the file
     * @return A list of objects of the class Helper.
     * @throws FileNotFoundException the file not found exception
     */
    public List<Helper> lerDistancias(String caminho) throws FileNotFoundException {
        Scanner read = new Scanner(new File(caminho));
        read.nextLine();
        while (read.hasNextLine()) {
            String[] line = read.nextLine().split(",");
            Helper helperaux = new Helper(line[DISTANCIAS_UM], line[DISTANCIAS_DOIS], Integer.parseInt(line[DISTANCIAS_TAMANHO]));
            if (!storeLists.getLocalidadeList().contains(helperaux)) {// O(n)
                storeLists.addLocalidadeList(helperaux);
            }

        }

        return storeLists.getLocalidadeList();
    }//O(n)

    /**
     * It reads a file, creates a list of Cabaz objects, and returns the list
     *
     * @param path the path to the file
     * @return A list of cabazes
     * @throws Exception the exception
     */
    public void lerCabaz(String path) throws Exception {
        String data;
        BufferedReader br = new BufferedReader(new FileReader(path));
        String header = br.readLine();
        String[] headerSplit = header.split(",");

        while ((data = br.readLine()) != null) {
            String[] values = data.split(",");
            createMap.guardarProducao(values, headerSplit);
        }

    }//O(n)


    /**
     * Ler planos de rega list.
     *
     * @param path the path
     * @return the list
     * @throws Exception the exception
     */
    public List<Setor> lerPlanosDeRega(String path) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");
        List<Setor> setorList = new ArrayList<>();
        List<PlanoDeRega> planoDeRegaList = new ArrayList<>(), planoDeRegaListFinal;
        LocalTime aux1, aux2;
        LocalDate localDateAux = LocalDate.now();
        String[] auxTimer, auxSetorString;
        String auxString;
        InfoRega auxInfoRega = null;
        Setor auxSetor = null;
        PlanoDeRega planoDeRegaAux = null;
        Scanner br = new Scanner(new FileReader(path));
        auxString = br.nextLine();
        auxTimer = auxString.split(",");
        aux1 = LocalTime.parse(auxTimer[0], dtf);
        aux2 = LocalTime.parse(auxTimer[1], dtf);

        while (br.hasNextLine()) {
            auxString = br.nextLine();
            auxSetorString = auxString.split(",");
            auxInfoRega = new InfoRega(aux1, aux2, Integer.parseInt(auxSetorString[1]), auxSetorString[2]);
            for (int i = 0; i < 30; i++) {
                planoDeRegaAux = new PlanoDeRega(localDateAux.plusDays(i), Integer.parseInt(auxSetorString[1]), aux1, aux2);
                planoDeRegaList.add(planoDeRegaAux);
            }
            planoDeRegaListFinal = regularidade(planoDeRegaList, auxSetorString[2]);// O(n)
            auxSetor = new Setor(auxSetorString[0], auxInfoRega, planoDeRegaListFinal);
            setorList.add(auxSetor);
        }


        return setorList;
    }// O(n)

    private List<PlanoDeRega> regularidade(List<PlanoDeRega> planoDeRega, String regularidade) {
        int index = 1;
        List<PlanoDeRega> planoDeRegaAux = new ArrayList<>();
        if (regularidade.startsWith("t")) {
            return planoDeRega;
        }
        if (regularidade.startsWith("p")) {
            for (PlanoDeRega rega : planoDeRega) {// O(n)
                if (index % 2 == 0) {
                    planoDeRegaAux.add(rega);
                }
                index++;
            }
        } else {
            for (PlanoDeRega rega : planoDeRega) {// O(n)
                if (index % 2 != 0) {
                    planoDeRegaAux.add(rega);
                }
                index++;
            }
        }
        return planoDeRegaAux;
    }// O(n)

}