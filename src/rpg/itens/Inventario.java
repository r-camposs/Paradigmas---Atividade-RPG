package rpg.itens;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Guarda os itens carregados por um personagem. Só cuida de guardar, listar e remover.
public class Inventario {

    private final List<Item> itens = new ArrayList<>();

    public void adicionar(Item item) {
        itens.add(Objects.requireNonNull(item, "O item não pode ser nulo."));
    }

    // Remove o item (se estiver aqui) e informa se conseguiu.
    public boolean remover(Item item) {
        return itens.remove(item);
    }

    public List<Item> getItens() {
        return List.copyOf(itens);
    }

    public List<Usavel> getUsaveis() {
        return filtrar(Usavel.class);
    }

    public List<Equipavel> getEquipaveis() {
        return filtrar(Equipavel.class);
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    private <T> List<T> filtrar(Class<T> tipo) {
        return itens.stream().filter(tipo::isInstance).map(tipo::cast).toList();
    }
}
