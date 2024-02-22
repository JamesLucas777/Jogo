import java.util.Random;
import java.util.Scanner;

/* DESCRIÇÃO
Crie a Classe Jogo, essa classe vai ter o jogo em si, o jogo consiste em um pedra papel e tesoura,
que o jogador deve inserir a sua jogada e o sistema irá retorna se ele ganhou ou não.
O Jogo deve estar em um método jogar().
O Jogo deve ter o atributo, melhor jogador(melhorJogador) e deve ter o número de vezes que o jogo foi jogado (numeroJogadas).
Cada vez que um jogador ganha ele deve receber mais um ponto, cada tentativa adiciona ao numero de tentativas.*/
public class Jogo {

    private Jogador melhorJogador;
    private int numeroJogadas;

    public Jogo(Jogador jogador) {
        this.melhorJogador = jogador;
        this.numeroJogadas = 0;
    }

    public Jogador getMelhorJogador() {
        return melhorJogador;
    }

    public void setMelhorJogador(Jogador melhorJogador) {
        this.melhorJogador = melhorJogador;
    }

    public int getNumeroJogadas() {
        return numeroJogadas;
    }

    public void setNumeroJogadas(int numeroJogadas) {
        this.numeroJogadas = numeroJogadas;
    }

    public void incrementarNumeroJogadas() {
        setNumeroJogadas(getNumeroJogadas() + 1);
    }

    private void menuPedraPapelTesoura() {
        System.out.println("\n1 - Pedra");
        System.out.println("2 - Papel");
        System.out.println("3 - Tesoura");
        System.out.print("Jogador " + getMelhorJogador().getNome() + " escolha um número: ");
    }

    public void jogar() {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        menuPedraPapelTesoura();
        int escolhaJogador = nextInt(scan);
        int escolhaComputador = random.nextInt(3) + 1; // gera um numero entre 1 e 3

        determinarVencedor(escolhaJogador, escolhaComputador);
        incrementarNumeroJogadas();
        incrementarTentativasJogador();

    }

    private void incrementarTentativasJogador() {
        getMelhorJogador().adicionarTentativas(1);
    }

    private void determinarVencedor(int escolhaJogador, int escolhaComputador) {
        if (escolhaJogador == escolhaComputador) { // empate
            System.out.println("Empate! Ambos escolheram igual.");
        } else if (escolhaJogador == 1 && escolhaComputador == 3) { // pedra(1) ganha de tesoura(3)
            System.out.println("Jogador venceu! Jogador escolheu Pedra e o computador escolheu Tesoura.");
            incrementarPontosJogador();
        } else if (escolhaJogador == 2 && escolhaComputador == 1) { // papel(2) ganha de pedra(1)
            System.out.println("Jogador venceu! Jogador escolheu Papel e o computador escolheu Pedra.");
            incrementarPontosJogador();
        } else if (escolhaJogador == 3 && escolhaComputador == 2) { // tesoura(3) ganha de papel(2)
            System.out.println("Jogador venceu! Jogador escolheu Tesoura e o computador escolheu Papel.");
            incrementarPontosJogador();
        } else {  // qualquer outro caso, o computador vence
            System.out.println("Computador venceu! Mais sorte na próxima vez.");
            decrementarPontosJogador();
        }
    }

    private void decrementarPontosJogador() {
        // verifica se o jogador ainda tem pontos para perder. Se não tiver, não decrementa a pontuação
        if (getMelhorJogador().getPontuacao() >= 1) {
            getMelhorJogador().perderPontos(1);
        }
    }

    private void incrementarPontosJogador() {
        getMelhorJogador().adicionarPontos(1);
    }

    private int nextInt(Scanner scan) {
        int numero = scan.nextInt();
        scan.nextLine();
        return numero;
    }
}
