import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
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
        System.out.print("Digite a pontuação do jogador: ");
        int pontuacao = nextInt(scanner);
        System.out.print("Digite o número de tentativas do jogador: ");
        int numeroTentativas = nextInt(scanner);
        return new Jogador(nome, idade, pontuacao, numeroTentativas);
    }

    private static void ordenarMelhoresJogadores(List<Jogador> jogadores) {
        // ordena/organiza a lista de jogadores pela pontuacao usando sort
        // sort necesita um Comparator
        // Comparator -> classe utilitária de comparação
        jogadores.sort(Comparator.comparingInt(Jogador::getPontuacao).reversed()); // ordem decrescente (maior para menor)
    }
    private static void adicionarMelhoresJogadoresOrdenados(List<Jogador> jogadores, Jogador jogador) {
        jogadores.add(jogador);
        ordenarMelhoresJogadores(jogadores);
    }

    private static void listarMelhoresJogadores(List<Jogador> jogadores) {
        ordenarMelhoresJogadores(jogadores);
        System.out.println("\nLista dos Melhores Jogadores: ");
        for (int i = 0; i < jogadores.size() && i < 10; i++) {
            // System.out.println(i + 1 + "º " + jogadores.get(i).getNome() + " - " + jogadores.get(i).getPontuacao() + " pontos");
            System.out.println(jogadores.get(i).getNome() + " - " + (i + 1) + "º lugar");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Jogador> listaMelhoresJogadores = new ArrayList<>();


        // para não precisar poluir o main, crio o jogador separadamente
        Jogador jogador1 = criarJogador(scanner);
        Jogador jogador2 = criarJogador(scanner);

        adicionarMelhoresJogadoresOrdenados(listaMelhoresJogadores, jogador1);
        adicionarMelhoresJogadoresOrdenados(listaMelhoresJogadores, jogador2);


        // como não foi especificado, o pontuacao e o numero de tentativas ficará assim por enquanto
        // e pontos são adicionados e removidos manualmente passando um número como parametro
        jogador1.adicionarPontos(10);
        jogador1.adicionarTentativas(1);
        jogador1.perderPontos(5);
        jogador1.adicionarTentativas(1);

        jogador1.mostrarInformacoesJogador(); // mostra algumas informações sobre o jogador no console
        jogador2.mostrarInformacoesJogador();

        // mostra os jogadores na ordem de pontuação
        listarMelhoresJogadores(listaMelhoresJogadores);
    }
}