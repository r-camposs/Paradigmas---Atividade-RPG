package rpg.combate;

import rpg.combatentes.Inimigo;
import rpg.combatentes.Personagem;

import java.util.List;

/**
 * Decide o que um inimigo faz no seu turno (padrão Strategy). Cada inimigo
 * recebe uma estratégia por construtor, então o comportamento muda sem
 * alterar a classe do inimigo nem o combate.
 */
public interface EstrategiaDeAcao {

    // Escolhe a ação do inimigo. A lista recebida só tem heróis vivos (e não é vazia).
    Acao escolher(Inimigo inimigo, List<Personagem> heroisVivos);
}
