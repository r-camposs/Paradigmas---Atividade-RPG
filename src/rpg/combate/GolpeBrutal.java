package rpg.combate;

import rpg.combatentes.Combatente;

import java.util.Objects;

// Ataque forte (150% do ataque), usado por inimigos mais perigosos.
public class GolpeBrutal implements Acao {

    private final Combatente autor;
    private final Combatente alvo;

    public GolpeBrutal(Combatente autor, Combatente alvo) {
        this.autor = Objects.requireNonNull(autor);
        this.alvo = Objects.requireNonNull(alvo);
    }

    @Override
    public String executar() {
        int sofrido = alvo.receberDano(autor.getAtaque() * 3 / 2);
        return autor.getNome() + " desfere um Golpe Brutal em " + alvo.getNome()
                + " e causa " + sofrido + " de dano.";
    }
}
