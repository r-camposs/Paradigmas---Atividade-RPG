package rpg.combate;

import rpg.combatentes.Combatente;
import rpg.combatentes.Inimigo;
import rpg.combatentes.Personagem;

import java.util.List;

// Sempre ataca o herói com menos vida, usando o ataque básico.
public class EstrategiaAgressiva implements EstrategiaDeAcao {

    @Override
    public Acao escolher(Inimigo inimigo, List<Personagem> heroisVivos) {
        return new AtaqueBasico(inimigo, escolherAlvo(heroisVivos));
    }

    protected Combatente escolherAlvo(List<Personagem> heroisVivos) {
        Combatente maisFerido = heroisVivos.get(0);
        for (Personagem heroi : heroisVivos) {
            if (heroi.getVida() < maisFerido.getVida()) {
                maisFerido = heroi;
            }
        }
        return maisFerido;
    }
}
