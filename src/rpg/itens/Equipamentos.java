package rpg.itens;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// Controla o que um personagem está vestindo e soma os bônus. Um item por espaço.
public class Equipamentos {

    private final Map<SlotEquipamento, Equipavel> equipados = new EnumMap<>(SlotEquipamento.class);

    // Veste o item e devolve o que estava no mesmo espaço, se havia algo.
    public Optional<Equipavel> equipar(Equipavel equipamento) {
        return Optional.ofNullable(equipados.put(equipamento.getSlot(), equipamento));
    }

    public List<Equipavel> getEquipados() {
        return List.copyOf(equipados.values());
    }

    public int getBonusAtaque() {
        return equipados.values().stream().mapToInt(Equipavel::getBonusAtaque).sum();
    }

    public int getBonusDefesa() {
        return equipados.values().stream().mapToInt(Equipavel::getBonusDefesa).sum();
    }
}
