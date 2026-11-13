package rpg.combate;

import rpg.combatentes.Inimigo;
import rpg.combatentes.Personagem;

import java.util.List;

// Como o combate mostra informações ao jogador. Não sabe se é console, janela ou arquivo.
public interface SaidaDoJogo {

    void exibir(String mensagem);

    void exibirEstado(List<Personagem> herois, List<Inimigo> inimigos);
}
