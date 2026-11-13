package rpg.combate;

import rpg.combatentes.Combatente;

import java.util.Objects;

//Ataque comum: o dano é o ataque do autor (a defesa do alvo é descontada em receberDano).
public class AtaqueBasico implements Acao {

    private final Combatente autor;
    private final Combatente alvo;

    public AtaqueBasico(Combatente autor, Combatente alvo) {
        this.autor = Objects.requireNonNull(autor);
        this.alvo = Objects.requireNonNull(alvo);
    }

    @Override
    public String executar() {
        int sofrido = alvo.receberDano(autor.getAtaque());
        return autor.getNome() + " ataca " + alvo.getNome() + " e causa " + sofrido + " de dano.";
    }
}
