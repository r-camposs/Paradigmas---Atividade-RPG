package rpg.combate;

import rpg.combatentes.Personagem;
import rpg.itens.Usavel;

import java.util.Objects;

// Faz o personagem consumir um item do inventário.
public class UsarItem implements Acao {

    private final Personagem autor;
    private final Usavel item;

    public UsarItem(Personagem autor, Usavel item) {
        this.autor = Objects.requireNonNull(autor);
        this.item = Objects.requireNonNull(item);
    }

    @Override
    public String executar() {
        return autor.usarItem(item);
    }
}
