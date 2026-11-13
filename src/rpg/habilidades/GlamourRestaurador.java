package rpg.habilidades;

import rpg.combatentes.Combatente;

// Habilidade da Feiticeira: cura o próprio usuário.
public class GlamourRestaurador extends HabilidadeLimitada {

    private static final int CURA = 30;

    public GlamourRestaurador() {
        super("Glamour Restaurador", 2);
    }

    @Override
    public boolean precisaDeAlvo() {
        return false;
    }

    @Override
    protected String aplicarEfeito(Combatente usuario, Combatente oponente) {
        int antes = usuario.getVida();
        usuario.curar(CURA);
        int curado = usuario.getVida() - antes;
        return usuario.getNome() + " usa " + getNome() + " e recupera " + curado + " de vida.";
    }
}
