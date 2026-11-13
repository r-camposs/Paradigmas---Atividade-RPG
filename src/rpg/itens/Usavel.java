package rpg.itens;

import rpg.combatentes.Combatente;

// Papel de um item consumível: pode ser usado sobre um combatente.
public interface Usavel extends Item {

    // Aplica o efeito e retorna um texto descrevendo o que aconteceu.
    String usar(Combatente alvo);
}
