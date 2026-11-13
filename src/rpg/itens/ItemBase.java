package rpg.itens;

// Reaproveita nome e descrição, comuns a todos os itens concretos.
public abstract class ItemBase implements Item {

    private final String nome;
    private final String descricao;

    protected ItemBase(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }
}
