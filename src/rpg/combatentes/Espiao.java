package rpg.combatentes;

import rpg.habilidades.Habilidade;

// Herói furtivo da Corte das Sombras (perfil do Fantasma).
public class Espiao extends Personagem {

    public Espiao(String nome, Habilidade habilidade) {
        super(nome, 90, 16, 6, habilidade);
    }

    @Override
    public String falar() {
        return "Ninguém me viu chegar.";
    }
}
