package rpg.combatentes;

import rpg.combate.EstrategiaDeAcao;

// Chefão final: Madoc, com atributos fixos e muito acima dos inimigos comuns.
public class Madoc extends Inimigo {

    public Madoc(EstrategiaDeAcao estrategia) {
        super("Madoc", 160, 21, 6, estrategia);
    }

    @Override
    public String falar() {
        return "Veremos se aprendeu o que eu ensinei.";
    }
}
