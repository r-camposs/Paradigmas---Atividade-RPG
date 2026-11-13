package rpg.combatentes;

/**
  Abstração base de qualquer ser que luta (herói ou inimigo).
  Responsabilidade única: guardar e proteger o estado de combate
  (vida, ataque, defesa). Não sabe COMO o combate acontece.
 */
public abstract class Combatente {

    private final String nome;
    private final int vidaMaxima;
    private int vida;
    private final int ataque;
    private final int defesa;

    protected Combatente(String nome, int vidaMaxima, int ataque, int defesa) {
        if (vidaMaxima <= 0) {
            throw new IllegalArgumentException("A vida máxima deve ser positiva.");
        }
        if (ataque < 0 || defesa < 0) {
            throw new IllegalArgumentException("Ataque e defesa não podem ser negativos.");
        }
        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        this.vida = vidaMaxima;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    // Aplica o dano já descontando a defesa (mínimo 1). Retorna o dano realmente sofrido.
    public int receberDano(int dano) {
        if (dano < 0) {
            throw new IllegalArgumentException("O dano não pode ser negativo.");
        }
        int danoReal = Math.max(1, dano - getDefesa());
        vida = Math.max(0, vida - danoReal);
        return danoReal;
    }

    // Recupera vida sem ultrapassar a vida máxima.
    public void curar(int quantidade) {
        if (quantidade <= 0) {
            return;
        }
        vida = Math.min(vidaMaxima, vida + quantidade);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    // Cada combatente tem sua própria fala (polimorfismo).
    public abstract String falar();

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
}
