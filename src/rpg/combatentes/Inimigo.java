package rpg.combatentes;

import rpg.combate.Acao;
import rpg.combate.EstrategiaDeAcao;

import java.util.List;
import java.util.Objects;

/**
  Combatente controlado pelo jogo. Não decide sozinho o que fazer: delega
  para uma estratégia recebida por construtor (depende da abstração
  {@link EstrategiaDeAcao}, nunca de uma estratégia concreta).
 */
public abstract class Inimigo extends Combatente {

    private final EstrategiaDeAcao estrategia;

    protected Inimigo(String nome, int vidaMaxima, int ataque, int defesa, EstrategiaDeAcao estrategia) {
        super(nome, vidaMaxima, ataque, defesa);
        this.estrategia = Objects.requireNonNull(estrategia, "A estratégia não pode ser nula.");
    }

    public Acao escolherAcao(List<Personagem> heroisVivos) {
        return estrategia.escolher(this, heroisVivos);
    }
}
