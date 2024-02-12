import java.util.Scanner;

public class Main {
    public static int nextInt(Scanner scanner) {
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }

    private static Jogador criarJogador(Scanner scanner) {
        System.out.print("Digite o nome do jogador: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a idade do jogador: ");
        int idade = nextInt(scanner);
        System.out.print("Digite a pontuação do jogador: ");
        int pontuacao = nextInt(scanner);
        System.out.print("Digite o número de tentativas do jogador: ");
        int numeroTentativas = nextInt(scanner);
        return new Jogador(nome, idade, pontuacao, numeroTentativas);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // para não precisar poluir o main, crio o jogador separadamente
        Jogador jogador= criarJogador(scanner);

        // como não foi especificado, o pontuacao e o numero de tentativas ficará assim por enquanto
        // e pontos são adicionados e removidos manualmente passando um número como parametro
        jogador.adicionarPontos(10);
        jogador.adicionarTentativas(1);
        jogador.perderPontos(5);
        jogador.adicionarTentativas(1);

        jogador.mostrarInformacoesJogador(); // mostra algumas informações sobre o jogador no console
    }
}