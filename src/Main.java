import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do jogador: ");
        Jogador jogador1 = new Jogador(scanner.nextLine());
        System.out.print("Digite a idade do jogador: ");
        jogador1.setIdade(scanner.nextInt());

        // como não foi especificado, o pontuacao e o numero de tentativas são inicializados como 0
        // e pontos são adicionados e removidos manualmente passando um número como parametro
        jogador1.adicionarPontos(10);
        jogador1.adicionarTentativas(1);
        jogador1.perderPontos(5);
        jogador1.adicionarTentativas(1);

        System.out.println("Pontuação do jogador " + jogador1.getNome() + ": " + jogador1.getPontuacao());

        System.out.println("O jogador " + jogador1.getNome() + " tem " + jogador1.getIdade() + " anos.");

        System.out.println("Tentativas do jogador " + jogador1.getNome() + ": " + jogador1.getNumeroTentativas());

    }
}