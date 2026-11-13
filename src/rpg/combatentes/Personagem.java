package rpg.combatentes;

import rpg.habilidades.Habilidade;
import rpg.itens.Equipamentos;
import rpg.itens.Equipavel;
import rpg.itens.Inventario;
import rpg.itens.Item;
import rpg.itens.Usavel;

import java.util.Objects;

/**
  Combatente controlado pelo jogador. Recebe sua habilidade de fora (injeção
  por construtor) e carrega um inventário e equipamentos, que são objetos
  próprios (composição): Personagem coordena, cada classe cuida da sua parte.
 */
public abstract class Personagem extends Combatente {

    private final Habilidade habilidade;
    private final Inventario inventario = new Inventario();
    private final Equipamentos equipamentos = new Equipamentos();

    protected Personagem(String nome, int vidaMaxima, int ataque, int defesa, Habilidade habilidade) {
        super(nome, vidaMaxima, ataque, defesa);
        this.habilidade = Objects.requireNonNull(habilidade, "A habilidade não pode ser nula.");
    }

    public Habilidade getHabilidade() {
        return habilidade;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public Equipamentos getEquipamentos() {
        return equipamentos;
    }

    // Consome um item do inventário sobre este personagem.
    public String usarItem(Usavel item) {
        exigirNoInventario(item);
        return item.usar(this);
    }

    // Veste um item do inventário; o que estava no mesmo espaço volta para o inventário.
    public String equipar(Equipavel item) {
        exigirNoInventario(item);
        equipamentos.equipar(item).ifPresent(inventario::adicionar);
        return getNome() + " equipou " + item.getNome() + ".";
    }

    // O ataque total inclui o bônus dos equipamentos.
    @Override
    public int getAtaque() {
        return super.getAtaque() + equipamentos.getBonusAtaque();
    }

    // A defesa total inclui o bônus dos equipamentos.
    @Override
    public int getDefesa() {
        return super.getDefesa() + equipamentos.getBonusDefesa();
    }

    private void exigirNoInventario(Item item) {
        if (!inventario.remover(item)) {
            throw new IllegalArgumentException("O item não está no inventário.");
        }
    }
}
