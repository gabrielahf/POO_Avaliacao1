import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;



public class ACMESports {


    private Plantel plantel;
    private Medalheiro medalheiro;

    // Atributos para redirecionamento de E/S
    private Scanner entrada = new Scanner(System.in);  // Atributo para entrada de dados
    private PrintStream saidaPadrao = System.out;   // Guarda a saida padrao - tela (console)
    private final String nomeArquivoEntrada = "dadosin.txt";  // Nome do arquivo de entrada de dados
    private final String nomeArquivoSaida = "dadosout.txt";  // Nome do arquivo de saida de dados

    public ACMESports() {

        plantel = new Plantel();
        medalheiro = new Medalheiro();
        redirecionaES();    // Redireciona E/S para arquivos
    }

    public void executar() {
        cadastrarAtletas();
        cadastrarMedalhas();
        CadastrarMedalhaAtleta();
        MostrarDadosAtletaNumero();
        MostrarDadosAtletaNome();
        MostrarDadosMedalha();
        MostrarDadosAtletaPais();
        MostrarDadosAtletaTipoMedalha();
        MostrarDadosAtletaModalidade();
        MostrarDadosAtletaMaisMedalha();
        MostrarMedalhaPorPais();
    }


    private void redirecionaES() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("dadosin.txt"));
            entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
            PrintStream streamSaida = new PrintStream(new File("dadosout.txt"), Charset.forName("UTF-8"));
            System.setOut(streamSaida);             // Usa como saida um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
        entrada.useLocale(Locale.ENGLISH);   // Ajusta para leitura para ponto decimal
    }

    // Restaura E/S padrao de tela(console)/teclado
    // Chame este metodo para retornar a leitura e escrita de dados para o padrao
    private void restauraES() {
        System.setOut(saidaPadrao);
        entrada = new Scanner(System.in);
    }


     /*
    lê todos os dados de cada atleta e, se o número não for
    repetido, cadastra-o no sistema. Para cada atleta cadastrado com sucesso no
    sistema, mostra os dados do atleta no formato: 1:número,nome,país
     */

    private void cadastrarAtletas() {
        int numero = 0;
        String nome;
        String pais;

        //System.out.println("Digite o numero do atleta: ");
        numero = entrada.nextInt();
        entrada.nextLine();

        while (numero != -1) {
            //System.out.println("Digite o nome do atleta: ");
            nome = entrada.nextLine();
            //System.out.println("Digite o pais do atleta: ");
            pais = entrada.nextLine();

            Atleta a = new Atleta(numero, nome, pais);


            if (plantel.cadastraAtleta(a)) {
                System.out.println("1: " + a.getNumero() + " , " + a.getNome() + " , " + a.getPais());
                //System.out.println("Atleta cadastrado!");
            }

            numero = entrada.nextInt();
            entrada.nextLine();
        }


    }


    /*
     lê todos os dados de cada medalha e, se o código não for
    repetido, cadastra-a no sistema. Para cada medalha cadastrada com sucesso no
    sistema, mostra os dados da medalha no formato: 2:codigo,tipo,é
    individual?,modalidade
     */
    private void cadastrarMedalhas() {
        int codigo = 0;
        int tipo = 0;
        boolean individual;
        String modalidade;

        //System.out.println("Digite o codigo da medalha: ");
        codigo = entrada.nextInt();

        while (codigo != -1) {
            //System.out.println("Digite o tipo da medalha: ");
            tipo = entrada.nextInt();
            //System.out.println("Medalha é individual (true/false)? ");
            individual = entrada.nextBoolean();
            entrada.nextLine();
            //System.out.println("Digite a modalidade da medalha: ");
            modalidade = entrada.nextLine();

            Medalha med = new Medalha(codigo, tipo, individual, modalidade);


            if (medalheiro.cadastraMedalha(med)) {
                System.out.println("2:" + med.getCodigo() + "," + med.getTipo() + "," + med.isIndividual() + "," + med.getModalidade());
                //System.out.println("Medalha cadastrada!");
            }

            codigo = entrada.nextInt();

        }
    }


    //Cadastrar medalhas e atletas correspondentes
    /*
    adiciona uma medalha para cada atleta e vice-versa.
    Para cada cadastramento com sucesso mostra os dados
    no formato: 3:código,número
     */
    private void CadastrarMedalhaAtleta() {
        int codigo = 0;
        int numero = 0;

        //System.out.println("Digite o codigo da medalha: ");
        codigo = entrada.nextInt();
        entrada.nextLine();


        while (codigo != -1) {
            //System.out.println("Digite o numero do atleta: ");
            numero = entrada.nextInt();
            entrada.nextLine();

            Medalha medalha = medalheiro.consultaMedalha(codigo);
            Atleta atleta = plantel.consultaAtleta(numero);

            if (medalha != null && atleta != null) {
                medalha.adicionaAtleta(atleta);
                System.out.println("3: " + medalha.getCodigo() + "," + atleta.getNumero());
            } else {
                System.out.println("Nao encontrado");
            }
            codigo = entrada.nextInt();
            entrada.nextLine();
        }
    }


    //Mostrar os dados de um determinado atleta por número
        /*
         lê o número de um determinado atleta. Se não existir um atleta com o número indicado,
         mostra a mensagem de erro: “4:Nenhum atleta encontrado.”. Se existir, mostra os
        dados do atleta no formato: 4:número,nome,país
         */
    private void MostrarDadosAtletaNumero() {
        int numero = 0;

        //System.out.println("Digite o numero do atleta: ");
        numero = entrada.nextInt();
        entrada.nextLine();

        Atleta atleta = plantel.consultaAtleta(numero);

        if (atleta == null) {
            System.out.println("4: Nenhum atleta encontrado.");
        } else {
            System.out.println("4:" + atleta.getNumero() + "," + atleta.getNome() + "," + atleta.getPais());
        }
    }


    //Mostrar os dados de um determinado atleta por nome
        /*
        : lê o nome de um determinado atleta. Se não existir um atleta com o nome indicado, mostra a
        mensagem de erro: “5:Nenhum atleta encontrado.”. Se existir, mostra os
        dados do atleta no formato: 5:número,nome,país
         */
    private void MostrarDadosAtletaNome() {
        String nome;

        //System.out.println("Digite o nome do atleta: ");
        nome = entrada.nextLine();


        Atleta atleta = plantel.consultaAtleta(nome);

        if (atleta == null) {
            System.out.println("5: Nenhum atleta encontrado.");
        } else {
            System.out.println("5:" + atleta.getNumero() + "," + atleta.getNome() + "," + atleta.getPais());
        }
    }

    //Mostrar os dados de uma determinada medalha
        /*
        lê um código de medalha. Se não existir uma medalha com o código indicado, mostra a mensagem de erro:
        “6:Nenhuma medalha encontrada.”. Se existir, mostra os dados da medalha
        no formato: 6:codigo,tipo,é individual?,modalidade
         */
    private void MostrarDadosMedalha() {
        int codigo = 0;

        //System.out.println("Digite o codigo da medalha: ");
        codigo = entrada.nextInt();
        entrada.nextLine();

        Medalha medalha = medalheiro.consultaMedalha(codigo);

        if (medalha == null) {
            System.out.println("6: Nenhuma medalha encontrada.");
        } else {
            System.out.println("6:" + medalha.getCodigo() + "," + medalha.getTipo() + "," + medalha.isIndividual() + "," + medalha.getModalidade());
        }
    }

    //Mostrar os dados dos atletas de um determinado país
        /*
         lê o nome de um país. Se não existir nenhum país com o nome indicado, mostra a mensagem de erro:
        “7:Pais nao encontrado.”. Se existir, mostra os dados de cada atleta no
        formato: 7:número,nome,país
         */
    private void MostrarDadosAtletaPais() {
        ArrayList<Atleta> atletaPais;
        String pais;

        //System.out.println("Digite o pais do atleta: ");
        pais = entrada.nextLine();

        atletaPais = plantel.consultaAtletaPais(pais);
        //ArrayList<Atleta> atletas1 = plantel.consultaAtletaPais(pais);

        if (atletaPais.isEmpty()) {
            System.out.println("7: Pais nao encontrado.");
        } else {
            for (Atleta atleta : atletaPais) {
                System.out.println("7:" + atleta.getNumero() + "," + atleta.getNome() + "," + atleta.getPais());

            }
        }
    }


    //Mostrar os dados atletas de um determinado tipo de medalha
        /*
        lê o tipo de uma medalha. Se não houver nenhum atleta com o tipo de medalha indicado, mostra a
        mensagem de erro: “8:Nenhum atleta encontrado.”, caso contrário, mostra
        os dados de cada atleta no formato: 8:número,nome,país
         */
    private void MostrarDadosAtletaTipoMedalha() {
        ArrayList<Atleta> encontraAtleta;
        int tipo = 0;

        //System.out.println("Digite o tipo da medalha: ");
        tipo = entrada.nextInt();
        entrada.nextLine();

        encontraAtleta = plantel.atletaPorMedalha(tipo);

        if (encontraAtleta.isEmpty()) {
            System.out.println("8: Nenhum atleta encontrado.");
        } else {
            for (Atleta atleta : encontraAtleta)
                System.out.println("8:" + atleta.getNumero() + "," + atleta.getNome() + "," + atleta.getPais());
        }
    }

    //Mostrar os dados atletas de uma determinada modalidade
        /*
        : lê uma modalidade. Se não houver a modalidade no sistema, mostra a mensagem de erro:
        “9:Modalidade não encontrada.” Se uma medalha não tiver atleta, mostra
        uma mensagem no formato: 9:modalidade,tipo,Sem atletas com
        medalha. Caso contrário, mostra os dados de cada atleta da medalha no formato:
        9:modalidade,tipo,número,nome,país
         */
    private void MostrarDadosAtletaModalidade() {
        ArrayList<Medalha> medalhaPorModalidade;
        String modalidade;

        //System.out.println("Digite a modalidade do atleta: ");
        modalidade = entrada.nextLine();

        medalhaPorModalidade = medalheiro.consultaMedalhas(modalidade);


        if (medalheiro.consultaMedalhas(modalidade) == null) {
            System.out.println("9: Modalidade nao encontrada.");
        } else {
            for (Medalha medalha : medalhaPorModalidade) {
                if (medalha.getAtletas().isEmpty()) {
                    System.out.println("9:" + medalha.getModalidade() + "," + medalha.getTipo() + "," + "sem atletas com medalha");
                } else {
                    for (Atleta atleta : medalha.getAtletas()) {
                        System.out.println("9:" + medalha.getModalidade() + "," + medalha.getTipo() + "," + atleta.getNumero() + "," + atleta.getNome() + "," + atleta.getPais());
                    }
                }
            }

        }

    }


    //Mostrar os dados do atleta com mais medalhas
        /*
        localiza o atleta com maior número de medalhas. Se não houver atletas com medalhas, mostra a mensagem
        de erro: “10:Nenhum atleta com medalha.”. Caso contrário, mostra os dados
        do atleta e medalhas no formato: 10:número,nome,país,Ouro:quantidade,Prata:quantidade,Bronze: quantidade
         */
    private void MostrarDadosAtletaMaisMedalha() {
        Atleta maiorMedalhista = plantel.maiorMedalhista();
        int ouro = 0;
        int prata = 0;
        int bronze = 0;

        if (maiorMedalhista == null) {
            System.out.println("10: Nenhum atleta com medalha.");
        } else {
            for (Medalha medalhasAtleta : maiorMedalhista.getMedalhas()) {

                switch (medalhasAtleta.getTipo()) {
                    case 1:
                        ouro++;
                        break;

                    case 2:
                        prata++;
                        break;

                    case 3:
                        bronze++;
                        break;

                }
            }
            System.out.println("10: " + maiorMedalhista.getNumero() + "," + maiorMedalhista.getNome() + "," + maiorMedalhista.getPais() + ",Ouro:" + ouro + ",Prata:" + prata + ",Bronze:" + bronze);

        }

    }



        /*
        Mostrar o quadro geral de medalhas por país: nome de cada país, quantidade de
        medalhas de ouro, quantidade de medalhas de prata, quantidade de medalha de
        bronze.
        */

    public void MostrarMedalhaPorPais(){
        ArrayList<Medalha> totalMedalha = medalheiro.consultaMedalhasPaises();
        if (totalMedalha.isEmpty()){
            System.out.println("Nenhuma medalha encontrada.");
        }
        ArrayList<String> paises = new ArrayList<>();

        for (Medalha medalha : totalMedalha){
            for (Atleta atleta : medalha.getAtletas()){
                String pais = atleta.getPais();
                    if(!paises.contains(pais)){
                        paises.add(pais);
                    }
            }
        }
        for (String pais : paises){
            int ouro = 0;
            int prata = 0;
            int bronze = 0;

            for (Medalha medalha : medalheiro.consultaMedalhaPorPais(pais)){
                switch (medalha.getTipo()){
                    case 1:
                        ouro++;
                        break;

                    case 2:
                        prata++;
                        break;

                    case 3:
                        bronze++;
                        break;

                }
            }
            System.out.println("11:" + pais + "Ouro: " + ouro + ",Prata:" + prata + ",Bronze:" + bronze);
        }
    }



}