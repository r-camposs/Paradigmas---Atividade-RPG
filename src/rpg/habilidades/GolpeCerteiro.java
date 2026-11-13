package rpg.habilidades;

import rpg.combatentes.Combatente;

// Habilidade da Duelista: um golpe forte com 150% do ataque.
public class GolpeCerteiro extends HabilidadeLimitada {

    public GolpeCerteiro() {
        super("Golpe Certeiro", 3);
    }

    @Override
    protected String aplicarEfeito(Combatente usuario, Combatente oponente) {
        int dano = usuario.getAtaque() * 3 / 2;
        int sofrido = oponente.receberDano(dano);
        return usuario.getNome() + " usa " + getNome() + " e causa "
                + sofrido + " de dano a " + oponente.getNome() + ".";
    }
}
