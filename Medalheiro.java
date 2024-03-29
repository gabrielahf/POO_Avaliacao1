import java.util.ArrayList;

//GERENCIA O CADASTRO DE MEDALHAS
public class Medalheiro{

    private ArrayList<Medalha> medalhas;


    public Medalheiro() {
        medalhas = new ArrayList<>();
    }


    /*
    recebe como parâmetro uma nova medalha e a
    cadastra no sistema. Não pode haver medalhas com o mesmo código. Retorna
    true se o cadastro teve sucesso; ou false em caso contrário.
     */
    public boolean cadastraMedalha(Medalha m) {
        if (consultaMedalha(m.getCodigo()) != null){
            return false;
        }
         medalhas.add(m);
        return true;
    }

    /*
    consultaMedalha(int): retorna a medalha com o código indicado. Se não
    houver medalha com este código retorna null.
     */
    public Medalha consultaMedalha(int codigo){
        for(Medalha m : medalhas){
            if(m.getCodigo() == codigo){
                return m;
            }
        }
        return null;
    }



    /*
    retorna uma coleção de medalhas com a
    modalidade indicada. Se não houver nenhuma medalha com esta modalidade
    retorna null.
     */


    public ArrayList<Medalha> consultaMedalhas(String modalidade){
        ArrayList<Medalha> consultaMedalha = new ArrayList<>();

        if (!medalhas.isEmpty()){
            for(int i = 0; i < this.medalhas.size(); i++){
                Medalha m = this.medalhas.get(i);
                if(m.getModalidade().equalsIgnoreCase(modalidade)){
                    consultaMedalha.add(m);
                }
            }
        }
        if (!consultaMedalha.isEmpty()){
            return consultaMedalha;
        }
        return null;
    }

    //consultaMedalhasTodosPaises
    public ArrayList<Medalha> consultaMedalhasPaises(){
        ArrayList<Medalha> totalMedalhas = new ArrayList<>();
        for (Medalha m : medalhas){
           totalMedalhas.add(m);
            }
        if (totalMedalhas.isEmpty()){
            return null;
        } else{
            return totalMedalhas;
        }
    }


    public ArrayList<Medalha> consultaMedalhaPorPais(String pais){
        ArrayList<Medalha> medalhaPais = new ArrayList<>();
        for (Medalha medalha : medalhas){
            for (Atleta atleta : medalha.getAtletas()){
                if (atleta.getPais().equals(pais)){
                    medalhaPais.add(medalha);
                    break;
                }
            }
        }
        return medalhaPais;
    }
}
