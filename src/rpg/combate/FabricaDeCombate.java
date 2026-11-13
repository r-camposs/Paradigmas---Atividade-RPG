package rpg.combate;

import rpg.combatentes.Inimigo;
import rpg.combatentes.Personagem;

import java.util.List;
import java.util.Objects;
import java.util.Random;

// Cria combates em turnos, guardando as dependências que todos eles compartilham.
public class FabricaDeCombate implements FabricaDeBatalha {

    private final EntradaDoJogador entrada;
    private final SaidaDoJogo saida;
    private final Random sorteio;

    public FabricaDeCombate(EntradaDoJogador entrada, SaidaDoJogo saida, Random sorteio) {
        this.entrada = Objects.requireNonNull(entrada);
        this.saida = Objects.requireNonNull(saida);
        this.sorteio = Objects.requireNonNull(sorteio);
    }

    @Override
    public Batalha criar(List<Personagem> herois, List<Inimigo> inimigos) {
        return new Combate(herois, inimigos, entrada, saida, sorteio);
    }
}
