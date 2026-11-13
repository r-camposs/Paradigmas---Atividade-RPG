package rpg.jogo;

import rpg.combate.Batalha;
import rpg.combate.FabricaDeBatalha;
import rpg.combate.ResultadoCombate;
import rpg.combate.SaidaDoJogo;
import rpg.combatentes.Personagem;
import rpg.itens.Item;

import java.util.List;
import java.util.Objects;

/**
  Conduz uma partida: escolha do herói, fases em sequência, recompensas e descanso.
  Responsabilidade única: o fluxo do jogo. O conteúdo da história vem pronto na
  {@link Campanha}, a conversa com o jogador passa por interfaces e as batalhas
  são pedidas a uma {@link FabricaDeBatalha} (o Jogo nunca faz "new Combate").
 */
public class Jogo {

    private final DecisoesDaJornada decisoes;
    private final SaidaDoJogo saida;
    private final FabricaDeBatalha batalhas;

    public Jogo(DecisoesDaJornada decisoes, SaidaDoJogo saida, FabricaDeBatalha batalhas) {
        this.decisoes = Objects.requireNonNull(decisoes);
        this.saida = Objects.requireNonNull(saida);
        this.batalhas = Objects.requireNonNull(batalhas);
    }

    public void jogar(List<Personagem> herois, Campanha campanha) {
        saida.exibir("\n" + campanha.getTitulo() + "\n");
        saida.exibir(campanha.getPrologo());

        Personagem heroi = decisoes.escolherHeroi(herois);
        saida.exibir("\nVocê joga como " + heroi.getNome() + ". \"" + heroi.falar() + "\"");

        List<Fase> fases = campanha.getFases();
        for (int i = 0; i < fases.size(); i++) {
            Fase fase = fases.get(i);
            saida.exibir("\n Fase " + (i + 1) + "/" + fases.size() + ": " + fase.getTitulo() );
            saida.exibir(fase.getIntroducao());

            decisoes.prepararParaBatalha(heroi);

            Batalha batalha = batalhas.criar(List.of(heroi), fase.getInimigos());
            if (batalha.executar() == ResultadoCombate.DERROTA) {
                saida.exibir("\n" + campanha.getFinalDeDerrota());
                return;
            }

            saida.exibir("\n" + fase.getConclusao());
            entregarRecompensas(heroi, fase);
            if (i < fases.size() - 1) {
                descansar(heroi);
            }
        }
        saida.exibir("\n" + campanha.getFinalDeVitoria());
    }

    private void entregarRecompensas(Personagem heroi, Fase fase) {
        for (Item recompensa : fase.getRecompensas()) {
            heroi.getInventario().adicionar(recompensa);
            saida.exibir("Você recebeu: " + recompensa.getNome() + " (" + recompensa.getDescricao() + ")");
        }
    }

    // Entre uma fase e outra, o herói recupera metade da vida máxima.
    private void descansar(Personagem heroi) {
        int antes = heroi.getVida();
        heroi.curar(heroi.getVidaMaxima() / 2);
        saida.exibir(heroi.getNome() + " descansa e recupera " + (heroi.getVida() - antes)
                + " de vida (" + heroi.getVida() + "/" + heroi.getVidaMaxima() + ").");
    }
}
