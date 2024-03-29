import java.util.ArrayList;

public class Medalha {

    private int codigo;
    private int tipo;  //OURO, PRATA, BRONZE
    private String modalidade; // NOME DO ESPORTE
    private boolean individual;

    private ArrayList<Atleta> atletas;


    private boolean individual(){
        return false;
    } // INDICA SE A MEDALHA EH INDIVIDUAL OU EM EQUIPE


    public Medalha(int codigo, int tipo,boolean individual, String modalidade){
        this.codigo = codigo;
        this.tipo = tipo;
        this.individual = individual;
        this.modalidade = modalidade;
        atletas = new ArrayList<>();

    }

    public ArrayList<Atleta> getAtletas() {
        return atletas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public boolean isIndividual() {
        return individual;
    }

    public void setIndividual(boolean individual) {
        this.individual = individual;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    //adiciona um atleta à medalha.
    public void adicionaAtleta(Atleta atleta){
        atletas.add(atleta);
    }


}
