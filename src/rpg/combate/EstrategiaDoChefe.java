package rpg.combate;

import rpg.combatentes.Inimigo;
import rpg.combatentes.Personagem;

import java.util.List;

// Como a agressiva, mas passa a usar Golpe Brutal quando está com metade da vida ou menos.
public class EstrategiaDoChefe extends EstrategiaAgressiva {

    @Override
    public Acao escolher(Inimigo inimigo, List<Personagem> heroisVivos) {
        if (inimigo.getVida() * 2 <= inimigo.getVidaMaxima()) {
            return new GolpeBrutal(inimigo, escolherAlvo(heroisVivos));
        }
        return super.escolher(inimigo, heroisVivos);
    }
}
