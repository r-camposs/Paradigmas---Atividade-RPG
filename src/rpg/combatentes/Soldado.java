package rpg.combatentes;

import rpg.combate.EstrategiaDeAcao;

// Inimigo simples e configurável: serve para Valerian, guardas etc.
public class Soldado extends Inimigo {

    public Soldado(String nome, int vidaMaxima, int ataque, int defesa, EstrategiaDeAcao estrategia) {
        super(nome, vidaMaxima, ataque, defesa, estrategia);
    }

    @Override
    public String falar() {
        return "Em nome do reino!";
    }
}
