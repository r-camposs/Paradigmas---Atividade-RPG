package rpg.habilidades;

import rpg.combatentes.Combatente;

/**
  Reaproveita a regra comum a todas as habilidades do jogo: um número
  limitado de usos por batalha (Template Method). As subclasses só dizem
  QUAL é o efeito, em {@link #aplicarEfeito}.
 */
public abstract class HabilidadeLimitada implements Habilidade {

    private final String nome;
    private int usosRestantes;

    protected HabilidadeLimitada(String nome, int usos) {
        if (usos <= 0) {
            throw new IllegalArgumentException("Uma habilidade precisa ter ao menos 1 uso.");
        }
        this.nome = nome;
        this.usosRestantes = usos;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getUsosRestantes() {
        return usosRestantes;
    }

    @Override
    public boolean podeUsar() {
        return usosRestantes > 0;
    }

    // Passo fixo do algoritmo: valida, gasta um uso e delega o efeito.
    @Override
    public final String usar(Combatente usuario, Combatente oponente) {
        if (!podeUsar()) {
            throw new IllegalStateException(nome + " não tem mais usos.");
        }
        usosRestantes--;
        return aplicarEfeito(usuario, oponente);
    }

    // Passo variável: cada habilidade concreta define seu efeito.
    protected abstract String aplicarEfeito(Combatente usuario, Combatente oponente);
}
