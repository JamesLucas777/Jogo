import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Função que le o proximo inteiro digitado pelo usuário e consome o ENTER (\n)
    public static int nextInt(Scanner scanner) {
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }

    private static Jogador criarJogador(Scanner scanner) {
        System.out.print("\nDigite o nome do jogador: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a idade do jogador: ");
        int idade = nextInt(scanner);
        // usei o construtor sem pontuação e tentativas porquue não achei que faria muito sentido usar ele com pontuação
        return new Jogador(nome, idade);
    }

    private static void ordenarMelhoresJogadores(List<Jogador> jogadores) {
        // ordena/organiza a lista de jogadores pela pontuacao usando sort
        // sort necesita um Comparator
        // Comparator -> classe utilitária de comparação
        jogadores.sort(Comparator.comparingInt(Jogador::getPontuacao).reversed()); // ordem decrescente (maior para menor)
    }

    // Sempre que um jogador for criado, adicione uma validação se o nome do jogador existe na lista de jogadores.
    //Se ele já existir, peça para ele colocar outro nome. Quando não existe, adiciona o jogador na lista
    private static void adicionarMelhoresJogadoresOrdenados(List<Jogador> jogadores, Jogador jogador) {
        while (verificarSeJogadorExiste(jogadores, jogador.getNome()) != null) {
            System.out.print("\nJogador " + jogador.getNome() + " já existente. Digite outro nome: ");
            mudarNomeJogador(jogador);
        }
        jogadores.add(jogador);
        ordenarMelhoresJogadores(jogadores);
    }

    // Verifica se o nome do jogador existe na lista de jogadores, se existir, retorna o jogador existente.
    // Se o jogador nao existir, retorna null.
    private static Jogador verificarSeJogadorExiste(List<Jogador> jogadores, String nome) {
        for (Jogador jogador : jogadores) {
            if (jogador.getNome().equals(nome)) {
                return jogador;
            }
        }
        return null;
    }

    /*
     Verifica se o nome do jogador existe na lista de jogadores. Se o jogador não existe,
     solicita ao usuário que crie um novo jogador e o adiciona à lista de jogadores.
     Se o jogador já existe, retorna o jogador existente.
     */
    private static Jogador verificarSeExisteOuCriarJogador(List<Jogador> listaMelhoresJogadores, String nome, Scanner scanner) {
        Jogador jogadorExistente = verificarSeJogadorExiste(listaMelhoresJogadores, nome);
        if (jogadorExistente == null) {
            System.out.println("\nJogador " + nome + " não existente. Crie um novo jogador: ");
            Jogador novoJogador = criarJogador(scanner);
            adicionarMelhoresJogadoresOrdenados(listaMelhoresJogadores, novoJogador);
            return novoJogador;
        } else {
            return jogadorExistente;
        }
    }

    private static void mudarNomeJogador(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        jogador.setNome(scanner.nextLine());
// scanner.close();  quando tenta fechar o scanner e ele rodar de novo (2º vez) pra por o novo nome ele lança a exceção:
// Exception in thread "main" java.util.NoSuchElementException: No line found          na linha do scanner
// então não dá pra fechar o scanner e tem que esperar que o garbage collector feche os scanners que abrirem
    }

    private static void listarDezMelhoresJogadores(List<Jogador> jogadores) {
        ordenarMelhoresJogadores(jogadores);
        System.out.println("\nLista dos 10 Melhores Jogadores: ");
        for (int i = 0; i < jogadores.size() && i < 10; i++) {
            System.out.println(jogadores.get(i).getNome() + " - " + (i + 1) + "º lugar com " +
                    jogadores.get(i).getPontuacao() + " pontos.");
        }
    }

    private static void listarJogadoresRankingGeral(List<Jogador> jogadores) {
        ordenarMelhoresJogadores(jogadores);
        System.out.println("\nLista Ranking Geral de Jogadores: ");
        for (int i = 0; i < jogadores.size(); i++) {
            System.out.println(jogadores.get(i).getNome() + " - " + (i + 1) + "º lugar com " +
                    jogadores.get(i).getPontuacao() + " pontos.");
        }
    }

    private static void listarJogadoresExistentes(List<Jogador> jogadores) {
        if (!jogadores.isEmpty()) {
            System.out.println("\nLista dos Jogadores Existentes: ");
        }
        for (Jogador jogador : jogadores) {
            System.out.println(jogador.getNome());
        }
    }

    private static int numeroMaximoAdivinhar(Scanner scanner) {
        System.out.print("\nEscolha um número para ser o limite máximo do jogo de adivinhação: ");
        return nextInt(scanner);
    }

    public static void mostrarMenuPrincipalJogos() {
        System.out.println("\nDigite 1 para selecionar o jogo de Pedra, Papel e Tesoura.");
        System.out.println("Digite 2 para selecionar o jogo de adivinhação.");
        System.out.println("Digite 0 para sair do programa.");
    }

    public static void mostrarMenuPosJogo() {
        System.out.println("\nDigite 1 para ver o ranking completo.");
        System.out.println("Digite 2 para ver os top 10 jogadores.");
        System.out.println("Digite 3 para jogar novamente.");
        System.out.println("Digite 0 para encerrar o programa. \n");
        System.out.print("Escolha uma das opções acima: ");
    }

    /*
    o método main lista continuamente os jogadores existentes, solicita o nome de um jogador, verifica se
    o jogador existe ou cria um novo jogador, joga um jogo com o jogador e, em seguida, processa as opções pós-jogo.
    O programa é executado indefinidamente até ser explicitamente encerrado.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Jogador> listaMelhoresJogadores = new ArrayList<>();

        while (true) {

            listarJogadoresExistentes(listaMelhoresJogadores);
            System.out.print("\nDigite o nome do jogador para verificar se o mesmo existe: ");
            String nome = scanner.nextLine();

            Jogador jogador = verificarSeExisteOuCriarJogador(listaMelhoresJogadores, nome, scanner);

            if (escolherJogoEJogar(scanner, jogador)) return;

            if (opcaoPosJogo(scanner, listaMelhoresJogadores, jogador)) return;
        }
    }

    /*
    O método que permite a um jogador escolher e jogar um jogo. Ele exibe um menu de opções de jogo, lê a escolha do
    usuário, cria um objeto de jogo com o jogador e, com base na escolha do usuário, joga um jogo ou sai do menu.
     */
    private static boolean escolherJogoEJogar(Scanner scanner, Jogador jogador) {
        mostrarMenuPrincipalJogos();
        int escolhaUsuario = nextInt(scanner);
        Jogo jogo = new Jogo(jogador);

        switch (escolhaUsuario) {
            case 1: // jogo de pedra, papel ou tesoura
                jogo.jogar();
                break;
            case 2: // jogo de adivinhação
                jogo.jogar(numeroMaximoAdivinhar(scanner));
                break;
            case 0: // sair do jogo
                System.out.println("\nPrograma encerrado. Obrigado por jogar!");
                return true;
            default:
                System.out.println("Opcão inválida. Tente novamente!");
                escolherJogoEJogar(scanner, jogador);
        }
        return false;
    }

    // O métoo exibe um menu ao jogador, lê a escolha do jogador e depois realiza diferentes ações com base na escolha.
    private static boolean opcaoPosJogo(Scanner scanner, List<Jogador> listaMelhoresJogadores, Jogador jogador) {
        mostrarMenuPosJogo();
        int escolhaPosJogo = nextInt(scanner);

        switch (escolhaPosJogo) {
            case 1: // ver ranking completo
                listarJogadoresRankingGeral(listaMelhoresJogadores);
                break;
            case 2: // ver o top 10 jogadores
                listarDezMelhoresJogadores(listaMelhoresJogadores);
                break;
            case 3: // jogar novamente
                escolherJogoEJogar(scanner, jogador);
                opcaoPosJogo(scanner, listaMelhoresJogadores, jogador);
                break;
            case 0: // encerrar o jogo
                System.out.println("\nPrograma encerrado. Obrigado por jogar!");
                return true;
            default:
                System.out.println("Opcão inválida. Tente novamente!");
                opcaoPosJogo(scanner, listaMelhoresJogadores, jogador);
        }
        return false;
    }
}