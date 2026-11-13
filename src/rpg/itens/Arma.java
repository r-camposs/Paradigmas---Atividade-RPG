package rpg.itens;

// Equipamento que aumenta o ataque.
public class Arma extends ItemBase implements Equipavel {

    private final int bonusAtaque;

    public Arma(String nome, int bonusAtaque) {
        super(nome, "+" + bonusAtaque + " de ataque.");
        this.bonusAtaque = bonusAtaque;
    }

    @Override
    public SlotEquipamento getSlot() {
        return SlotEquipamento.ARMA;
    }

    @Override
    public int getBonusAtaque() {
        return bonusAtaque;
    }

    @Override
    public int getBonusDefesa() {
        return 0;
    }
}
