package rpg.jogo;

import rpg.combatentes.Inimigo;
import rpg.itens.Item;

import java.util.List;

// Uma etapa da história: texto de abertura, inimigos da batalha, texto de conclusão e recompensas.
public class Fase {

    private final String titulo;
    private final String introducao;
    private final List<Inimigo> inimigos;
    private final String conclusao;
    private final List<Item> recompensas;

    public Fase(String titulo, String introducao, List<Inimigo> inimigos,
                String conclusao, List<Item> recompensas) {
        if (inimigos.isEmpty()) {
            throw new IllegalArgumentException("Uma fase precisa de ao menos um inimigo.");
        }
        this.titulo = titulo;
        this.introducao = introducao;
        this.inimigos = List.copyOf(inimigos);
        this.conclusao = conclusao;
        this.recompensas = List.copyOf(recompensas);
    }

    public String getTitulo() { return titulo; }
    public String getIntroducao() { return introducao; }
    public List<Inimigo> getInimigos() { return inimigos; }
    public String getConclusao() { return conclusao; }
    public List<Item> getRecompensas() { return recompensas; }
}
