package rpg.combate;

// Uma batalha que pode ser disputada até o fim. Quem a dispara não precisa saber como ela funciona.
public interface Batalha {

    ResultadoCombate executar();
}
