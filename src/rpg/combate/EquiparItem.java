package rpg.combate;

import rpg.combatentes.Personagem;
import rpg.itens.Equipavel;

import java.util.Objects;

// Faz o personagem vestir um equipamento do inventário (gasta o turno).
public class EquiparItem implements Acao {

    private final Personagem autor;
    private final Equipavel item;

    public EquiparItem(Personagem autor, Equipavel item) {
        this.autor = Objects.requireNonNull(autor);
        this.item = Objects.requireNonNull(item);
    }

    @Override
    public String executar() {
        return autor.equipar(item);
    }
}
