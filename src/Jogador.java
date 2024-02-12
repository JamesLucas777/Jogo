public class Jogador {
    private String nome;
    private int idade;
    private int pontuacao;
    private int numeroTentativas;

    public Jogador(String nome) {
        this.nome = nome;
        this.numeroTentativas = 0;
        this.pontuacao = 0;
    }

    public Jogador(String nome, int idade, int pontuacao, int numeroTentativas) {
        this.nome = nome;
        this.idade = idade;
        this.pontuacao = pontuacao;
        this.numeroTentativas = numeroTentativas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getNumeroTentativas() {
        return numeroTentativas;
    }

    public void setNumeroTentativas(int numeroTentativas) {
        this.numeroTentativas = numeroTentativas;
    }

    public void adicionarPontos(int pontos) {
        setPontuacao(getPontuacao() + pontos);
    }

    public void perderPontos(int pontos) {
        setPontuacao(getPontuacao() - pontos);
    }

    // Se precisar, depois altero o método para só incrementar o numero de tentativas, sem parametro
    public void adicionarTentativas(int numeroTentativas) {
        setNumeroTentativas(getNumeroTentativas() + numeroTentativas);
    }

    public void mostrarInformacoesJogador() {
        System.out.println("\nPontuação do jogador " + getNome() + ": " + getPontuacao());
        // System.out.println("O jogador " + getNome() + " tem " + getIdade() + " anos.");
        System.out.println("Tentativas do jogador " + getNome() + ": " + getNumeroTentativas());
    }

}
