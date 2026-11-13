import rpg.combate.FabricaDeBatalha;
import rpg.combate.FabricaDeCombate;
import rpg.historia.FabricaDeHerois;
import rpg.historia.RoteiroDoPrincipeCruel;
import rpg.jogo.Jogo;
import rpg.ui.Console;

import java.util.Random;
import java.util.Scanner;

//Ponto de entrada e de montagem (composition root): cria as peças concretas e as entrega ao Jogo.

public class Main {

    public static void main(String[] args) {
        Console console = new Console(new Scanner(System.in), System.out);
        FabricaDeBatalha batalhas = new FabricaDeCombate(console, console, new Random());
        Jogo jogo = new Jogo(console, console, batalhas);

        jogo.jogar(new FabricaDeHerois().criarHerois(),
                new RoteiroDoPrincipeCruel().criarCampanha());
    }
}
