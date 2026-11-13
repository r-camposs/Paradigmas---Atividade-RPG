package rpg.itens;

// Papel de um item que pode ser vestido: ocupa um espaço e dá bônus de atributos.
public interface Equipavel extends Item {

    SlotEquipamento getSlot();

    int getBonusAtaque();

    int getBonusDefesa();
}
