package rpg.combatentes;

import rpg.habilidades.Habilidade;

// Herói de espada: equilibrado, com boa defesa (perfil da Jude).
public class Duelista extends Personagem {

    public Duelista(String nome, Habilidade habilidade) {
        super(nome, 100, 18, 8, habilidade);
    }

    @Override
    public String falar() {
        return "Minha lâmina fala por mim.";
    }
}
