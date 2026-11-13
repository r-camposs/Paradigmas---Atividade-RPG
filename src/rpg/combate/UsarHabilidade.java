package rpg.combate;

import rpg.combatentes.Combatente;
import rpg.combatentes.Personagem;

import java.util.Objects;

// Faz o personagem usar a habilidade especial dele.
public class UsarHabilidade implements Acao {

    private final Personagem autor;
    private final Combatente oponente;

    public UsarHabilidade(Personagem autor, Combatente oponente) {
        this.autor = Objects.requireNonNull(autor);
        this.oponente = Objects.requireNonNull(oponente);
    }

    @Override
    public String executar() {
        return autor.getHabilidade().usar(autor, oponente);
    }
}
