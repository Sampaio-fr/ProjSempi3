package Company.Store;

import Company.Model.Cabaz;
import Company.Model.Helper;
import Company.Model.Pessoa;
import Company.Model.Setor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Store lists.
 */
public class StoreLists {

    private List<Pessoa> pessoaList = new ArrayList<>();

    private List<Helper> localidadeList = new ArrayList<>();

    private List<Cabaz> cabazList = new ArrayList<>();

    private List<Setor> setorList = new ArrayList<>();


    /**
     * Instantiates a new Store lists.
     */
    public StoreLists() {
    }


    /**
     * Gets cabaz list.
     *
     * @return the cabaz list
     */
    public List<Cabaz> getCabazList() {
        return this.cabazList;
    }

    /**
     * Sets cabaz list.
     *
     * @param cabazList the cabaz list
     */
    public void setCabazList(List<Cabaz> cabazList) {
        this.cabazList = cabazList;
    }

    /**
     * Add cabaz list.
     *
     * @param cabaz the cabaz
     */
    public void addCabazList(Cabaz cabaz) {
        this.cabazList.add(cabaz);
    }

    /**
     * Gets pessoa list.
     *
     * @return the pessoa list
     */
    public List<Pessoa> getPessoaList() {
        return this.pessoaList;
    }

    /**
     * Sets pessoa list.
     *
     * @param pessoaList the pessoa list
     */
    public void setPessoaList(List<Pessoa> pessoaList) {
        this.pessoaList = pessoaList;
    }

    /**
     * Add pessoa list.
     *
     * @param pessoa the pessoa
     */
    public void addPessoaList(Pessoa pessoa) {
        this.pessoaList.add(pessoa);
    }

    /**
     * Gets localidade list.
     *
     * @return the localidade list
     */
    public List<Helper> getLocalidadeList() {
        return this.localidadeList;
    }

    /**
     * Sets localidade list.
     *
     * @param localidadeList the localidade list
     */
    public void setLocalidadeList(List<Helper> localidadeList) {
        this.localidadeList = localidadeList;
    }

    /**
     * Add localidade list.
     *
     * @param localidade the localidade
     */
    public void addLocalidadeList(Helper localidade) {
        this.localidadeList.add(localidade);
    }


    /**
     * Gets setor list.
     *
     * @return the setor list
     */
    public List<Setor> getSetorList() {
        return setorList;
    }

    /**
     * Sets setor list.
     *
     * @param setorList the setor list
     */
    public void setSetorList(List<Setor> setorList) {
        this.setorList = setorList;
    }

    @Override
    public String toString() {
        return "StoreLists" + "\n" +
                "PessoaList: " + pessoaList + "\n" +
                "Localidade List: " + localidadeList + "\n";
    }
}
