package rpg.itens;

// Equipamento que aumenta a defesa.
public class Armadura extends ItemBase implements Equipavel {

    private final int bonusDefesa;

    public Armadura(String nome, int bonusDefesa) {
        super(nome, "+" + bonusDefesa + " de defesa.");
        this.bonusDefesa = bonusDefesa;
    }

    @Override
    public SlotEquipamento getSlot() {
        return SlotEquipamento.ARMADURA;
    }

    @Override
    public int getBonusAtaque() {
        return 0;
    }

    @Override
    public int getBonusDefesa() {
        return bonusDefesa;
    }
}
