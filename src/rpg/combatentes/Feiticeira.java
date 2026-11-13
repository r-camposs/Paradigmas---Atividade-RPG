package rpg.combatentes;

import rpg.habilidades.Habilidade;

// Heroína de glamour: ataque alto, defesa baixa (perfil da Vivi).
public class Feiticeira extends Personagem {

    public Feiticeira(String nome, Habilidade habilidade) {
        super(nome, 80, 22, 4, habilidade);
    }

    @Override
    public String falar() {
        return "Nem tudo é o que parece.";
    }
}
