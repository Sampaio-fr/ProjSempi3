package Company.Model;

public class StatisticsByHub {

    private Pessoa hub;
    private int clientesDistintos;
    private int produtoresDistintos;

    public StatisticsByHub(Pessoa hub, int clientes, int produtores) {
        this.hub = hub;
        this.clientesDistintos = clientes;
        this.produtoresDistintos = produtores;
    }

    public Pessoa getHub() {
        return hub;
    }

    public StatisticsByHub setHub(Pessoa hub) {
        this.hub = hub;
        return this;
    }

    public int getClientesDistintos() {
        return clientesDistintos;
    }

    public StatisticsByHub setClientesDistintos(int clientesDistintos) {
        this.clientesDistintos = clientesDistintos;
        return this;
    }

    public int getProdutoresDistintos() {
        return produtoresDistintos;
    }

    public void setProdutoresDistintos(int produtoresDistintos) {
        this.produtoresDistintos = produtoresDistintos;
    }

    @Override
    public String toString() {
        return "StatisticsByHub " +
                "hub = " + hub.getIdPessoa() +
                " , pessoas Distintas = " + clientesDistintos + " , produtores Distintos = " + produtoresDistintos;
    }
}
