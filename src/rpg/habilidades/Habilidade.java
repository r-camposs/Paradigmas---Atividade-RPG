package rpg.habilidades;

import rpg.combatentes.Combatente;

/**
 Contrato de uma habilidade especial. Interface pequena: quem precisa
 USAR a habilidade (o combate) e quem precisa EXIBI-LA (a interface de
 console) dependem só destes métodos, nunca de uma habilidade concreta.
 */
public interface Habilidade {

    String getNome();

    int getUsosRestantes();

    boolean podeUsar();

    /**
      Aplica o efeito da habilidade. Pré-condição: {@link #podeUsar()} deve ser verdadeiro.
      Retorna um texto descrevendo o que aconteceu (quem imprime é a interface).
      Habilidades que afetam só o usuário (cura) ignoram o oponente.
     */
    String usar(Combatente usuario, Combatente oponente);

    // Informa se a habilidade precisa de um oponente como alvo (a cura em si mesmo não precisa).
    default boolean precisaDeAlvo() {
        return true;
    }
}
