package rpg.combate;

import rpg.combatentes.Combatente;
import rpg.combatentes.Inimigo;
import rpg.combatentes.Personagem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
  Controla uma batalha em turnos entre N heróis e M inimigos.
  Responsabilidade única: ordenar os turnos e executar ações até um lado cair.
  Depende só de abstrações (EntradaDoJogador, SaidaDoJogo, Acao): não lê o
  teclado, não imprime e não conhece ataques ou itens concretos.
 */
public class Combate implements Batalha {

    private final List<Personagem> herois;
    private final List<Inimigo> inimigos;
    private final EntradaDoJogador entrada;
    private final SaidaDoJogo saida;
    private final Random sorteio;
    private final Set<Combatente> derrotasAnunciadas = new HashSet<>();

    public Combate(List<Personagem> herois, List<Inimigo> inimigos,
                   EntradaDoJogador entrada, SaidaDoJogo saida, Random sorteio) {
        if (herois.isEmpty() || inimigos.isEmpty()) {
            throw new IllegalArgumentException("A batalha precisa de ao menos um herói e um inimigo.");
        }
        this.herois = List.copyOf(herois);
        this.inimigos = List.copyOf(inimigos);
        this.entrada = Objects.requireNonNull(entrada);
        this.saida = Objects.requireNonNull(saida);
        this.sorteio = Objects.requireNonNull(sorteio);
    }

    @Override
    public ResultadoCombate executar() {
        boolean heroisComecam = sorteio.nextBoolean();
        saida.exibir("Sorteio: " + (heroisComecam ? "os heróis" : "os inimigos") + " agem primeiro!");

        int rodada = 1;
        while (existeVivo(herois) && existeVivo(inimigos)) {
            saida.exibir("\n=== Rodada " + rodada + " ===");
            saida.exibirEstado(herois, inimigos);
            if (heroisComecam) {
                turnoDosHerois();
                turnoDosInimigos();
            } else {
                turnoDosInimigos();
                turnoDosHerois();
            }
            rodada++;
        }
        return existeVivo(herois) ? ResultadoCombate.VITORIA : ResultadoCombate.DERROTA;
    }

    private void turnoDosHerois() {
        for (Personagem heroi : herois) {
            if (!heroi.estaVivo()) {
                continue;
            }
            if (!existeVivo(inimigos)) {
                return;
            }
            executar(entrada.escolherAcao(heroi, vivos(inimigos)));
        }
    }

    private void turnoDosInimigos() {
        for (Inimigo inimigo : inimigos) {
            if (!inimigo.estaVivo()) {
                continue;
            }
            if (!existeVivo(herois)) {
                return;
            }
            executar(inimigo.escolherAcao(vivos(herois)));
        }
    }

    private void executar(Acao acao) {
        saida.exibir(acao.executar());
        anunciarDerrotas(herois);
        anunciarDerrotas(inimigos);
    }

    private void anunciarDerrotas(List<? extends Combatente> lado) {
        for (Combatente combatente : lado) {
            if (!combatente.estaVivo() && derrotasAnunciadas.add(combatente)) {
                saida.exibir(combatente.getNome() + " foi derrotado!");
            }
        }
    }

    private static boolean existeVivo(List<? extends Combatente> lado) {
        for (Combatente combatente : lado) {
            if (combatente.estaVivo()) {
                return true;
            }
        }
        return false;
    }

    private static <T extends Combatente> List<T> vivos(List<T> lado) {
        List<T> vivos = new ArrayList<>();
        for (T combatente : lado) {
            if (combatente.estaVivo()) {
                vivos.add(combatente);
            }
        }
        return vivos;
    }
}
