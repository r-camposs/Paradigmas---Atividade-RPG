package rpg.combate;

import rpg.combatentes.Inimigo;
import rpg.combatentes.Personagem;

import java.util.List;


public interface EntradaDoJogador {

    Acao escolherAcao(Personagem heroi, List<Inimigo> inimigosVivos);
}
