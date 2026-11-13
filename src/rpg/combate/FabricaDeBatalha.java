package rpg.combate;

import rpg.combatentes.Inimigo;
import rpg.combatentes.Personagem;

import java.util.List;

//Cria batalhas entre heróis e inimigos. Permite ao Jogo pedir uma batalha sem conhecer a classe concreta.
public interface FabricaDeBatalha {

    Batalha criar(List<Personagem> herois, List<Inimigo> inimigos);
}
