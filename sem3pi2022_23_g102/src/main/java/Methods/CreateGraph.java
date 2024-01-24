package Methods;

import Company.Graph.map.MapGraph;
import Company.Model.Helper;
import Company.Model.Pessoa;
import Company.Store.StoreLists;

import java.util.List;

/**
 * The type Create graph.
 */
public class CreateGraph {

    private static StoreLists storeLists;
    private MapGraph<Pessoa, Integer> graph = new MapGraph<>(false);


    /**
     * Instantiates a new Create graph.
     *
     * @param storeLists the store lists
     */
    public CreateGraph(StoreLists storeLists) {
        this.storeLists = storeLists;

    }


    /**
     * "Find a person by their location."
     * <p>
     * The function is called findPessoaByLocal, and it takes a String as a parameter. The function returns a Pessoa object
     *
     * @param local The local where the person is.
     * @return A list of Pessoa objects.
     */
    public static Pessoa findPessoaByLocal(String local) {
        List<Pessoa> listPessoa = storeLists.getPessoaList();

        for (Pessoa pessoa : listPessoa) {// O(n)
            if (pessoa.getLocalidade().equals(local)) {
                return pessoa;
            }
        }
        return null;
    }// O(n)


    /**
     * It creates a graph with the people as vertices and the distance between them as edges
     *
     * @return A graph with all the vertices and edges.
     */
    public MapGraph<Pessoa, Integer> createGraph() {
        List<Pessoa> listPessoa = storeLists.getPessoaList();
        List<Helper> listHelper = storeLists.getLocalidadeList();

        for (Pessoa pessoa : listPessoa) {// O(n)
            graph.addVertex(pessoa); // O(n)
        }

        // O(n^3)
        // O(n)
        for (Helper helper : listHelper) {
            // O(m)
            Pessoa pessoa = findPessoaByLocal(helper.getLocalidade1());
            Pessoa pessoa1 = findPessoaByLocal(helper.getLocalidade2());// O(n)
            if (pessoa != null & pessoa1 != null) {
                graph.addEdge(pessoa, pessoa1, helper.getDistancia());// O(1)
            }
        }

        return graph;
    }// O(n^3)
}
