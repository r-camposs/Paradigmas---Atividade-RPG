package rpg.jogo;

import rpg.combatentes.Personagem;

import java.util.List;

// Decisões do jogador fora do combate: quem ele será e como se prepara antes de cada batalha.
public interface DecisoesDaJornada {

    Personagem escolherHeroi(List<Personagem> opcoes);

    // Momento de equipar e usar itens antes de uma batalha. Retorna quando o jogador estiver pronto.
    void prepararParaBatalha(Personagem heroi);
}
