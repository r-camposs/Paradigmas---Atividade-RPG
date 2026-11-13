package rpg.historia;

import rpg.combatentes.Duelista;
import rpg.combatentes.Espiao;
import rpg.combatentes.Feiticeira;
import rpg.combatentes.Personagem;
import rpg.habilidades.GlamourRestaurador;
import rpg.habilidades.GolpeCerteiro;
import rpg.habilidades.GolpeFurtivo;
import rpg.itens.PocaoDeCura;

import java.util.List;

// Cria os heróis que o jogador pode escolher, cada um com sua habilidade e uma poção inicial.
public class FabricaDeHerois {

    public List<Personagem> criarHerois() {
        Personagem jude = new Duelista("Jude", new GolpeCerteiro());
        Personagem vivi = new Feiticeira("Vivi", new GlamourRestaurador());
        Personagem fantasma = new Espiao("Fantasma", new GolpeFurtivo());

        for (Personagem heroi : List.of(jude, vivi, fantasma)) {
            heroi.getInventario().adicionar(new PocaoDeCura("Poção de Cura", 40));
        }
        return List.of(jude, vivi, fantasma);
    }
}
