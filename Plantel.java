import java.util.ArrayList;

//GERENCIA O CADASTRO DE ATLETAS
public class Plantel {

    private ArrayList<Atleta> atletas;


    public Plantel() {
        atletas = new ArrayList<>();
    }


    /*
    recebe como parâmetro um novo Atleta e o cadastra no
    sistema. Não pode haver atletas com o mesmo número. Retorna true se o
    cadastro teve sucesso; ou false em caso contrário.
     */

    public boolean cadastraAtleta(Atleta atleta) {
        if (consultaAtleta(atleta.getNumero()) != null)
            return false;

        return atletas.add(atleta);
    }


    //Se não houver nenhum atleta com este NOME retorna null.

    public Atleta consultaAtleta(String nome) {
        for (Atleta atleta : atletas) {
            if (atleta.getNome().equalsIgnoreCase(nome)) {
                return atleta;
            }
        }
        return null;
    }




    //Se não houver nenhum atleta com este NUMERO retorna null.

    public Atleta consultaAtleta(int numero) {
        for (Atleta atleta : atletas) {
            if (atleta.getNumero() == numero) {
                return atleta;
            }
        }
        return null;
    }

    //atletasPorMedalhas

    public ArrayList<Atleta> atletaPorMedalha(int tipoMedalha){
        ArrayList<Atleta> atletaMedalhas = new ArrayList<>();

        for (Atleta atleta : atletas){
            for (Medalha medalha : atleta.getMedalhas()){
                if (medalha.getTipo() == tipoMedalha) {
                    atletaMedalhas.add(atleta);
                }
            }
        }
        return atletaMedalhas;
    }


    // consultaAtletaPorPais

    public ArrayList<Atleta> consultaAtletaPais(String pais){
        ArrayList<Atleta> atletas1 = new ArrayList<>();
        for (Atleta atleta : atletas){
            if (atleta.getPais().equalsIgnoreCase(pais)){
                atletas1.add(atleta);
            }
        }
        return atletas1;
    }

    //maiorMedalhista

    public Atleta maiorMedalhista(){
        ArrayList<Atleta> medalhista = new ArrayList<>();
        Atleta maiorMedalhista;

        for (Atleta atleta : atletas){
            if (atleta.consultaQuantidadeMedalhas() > 0){
                medalhista.add(atleta);
            }
        }
        if (medalhista.isEmpty()){
            return null;
        }else {
            maiorMedalhista = medalhista.get(0);

            for (Atleta atleta : medalhista){
                if (atleta.consultaQuantidadeMedalhas() > maiorMedalhista.consultaQuantidadeMedalhas()){
                    maiorMedalhista = atleta;
                }
            }
        }
        return maiorMedalhista;

    }






}