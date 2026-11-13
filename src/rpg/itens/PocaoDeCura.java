package rpg.itens;

import rpg.combatentes.Combatente;

// Item consumível que recupera vida.
public class PocaoDeCura extends ItemBase implements Usavel {

    private final int cura;

    public PocaoDeCura(String nome, int cura) {
        super(nome, "Recupera " + cura + " de vida.");
        if (cura <= 0) {
            throw new IllegalArgumentException("A cura deve ser positiva.");
        }
        this.cura = cura;
    }

    @Override
    public String usar(Combatente alvo) {
        int antes = alvo.getVida();
        alvo.curar(cura);
        int curado = alvo.getVida() - antes;
        return alvo.getNome() + " usa " + getNome() + " e recupera " + curado + " de vida.";
    }
}
