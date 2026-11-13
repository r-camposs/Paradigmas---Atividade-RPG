package rpg.habilidades;

import rpg.combatentes.Combatente;

// Habilidade do Espião: dois golpes rápidos, cada um com 75% do ataque.
public class GolpeFurtivo extends HabilidadeLimitada {

    public GolpeFurtivo() {
        super("Golpe Furtivo", 2);
    }

    @Override
    protected String aplicarEfeito(Combatente usuario, Combatente oponente) {
        int golpe = usuario.getAtaque() * 3 / 4;
        int total = oponente.receberDano(golpe) + oponente.receberDano(golpe);
        return usuario.getNome() + " usa " + getNome() + " e acerta dois golpes rápidos, causando "
                + total + " de dano a " + oponente.getNome() + ".";
    }
}
