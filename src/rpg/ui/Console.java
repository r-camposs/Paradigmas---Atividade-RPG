package rpg.ui;

import rpg.combate.Acao;
import rpg.combate.AtaqueBasico;
import rpg.combate.EntradaDoJogador;
import rpg.combate.EquiparItem;
import rpg.combate.SaidaDoJogo;
import rpg.combate.UsarHabilidade;
import rpg.combate.UsarItem;
import rpg.combatentes.Combatente;
import rpg.combatentes.Inimigo;
import rpg.combatentes.Personagem;
import rpg.habilidades.Habilidade;
import rpg.itens.Equipavel;
import rpg.itens.Item;
import rpg.itens.Usavel;
import rpg.jogo.DecisoesDaJornada;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
  Interface de texto do jogo. Responsabilidade única: conversar com o jogador
  (mostrar informações e ler decisões). Implementa as interfaces pequenas
  que o combate e o jogo esperam e recebe o teclado e a tela por construtor,
  então também funciona com entradas simuladas.
 */
public class Console implements EntradaDoJogador, SaidaDoJogo, DecisoesDaJornada {

    private final Scanner teclado;
    private final PrintStream tela;

    public Console(Scanner teclado, PrintStream tela) {
        this.teclado = teclado;
        this.tela = tela;
    }

    // SaidaDoJogo

    @Override
    public void exibir(String mensagem) {
        tela.println(mensagem);
    }

    @Override
    public void exibirEstado(List<Personagem> herois, List<Inimigo> inimigos) {
        tela.println("Heróis:");
        for (Combatente heroi : herois) {
            tela.println("  " + linhaDe(heroi));
        }
        tela.println("Inimigos:");
        for (Combatente inimigo : inimigos) {
            tela.println("  " + linhaDe(inimigo));
        }
    }

    //  EntradaDoJogador

    @Override
    public Acao escolherAcao(Personagem heroi, List<Inimigo> inimigosVivos) {
        while (true) {
            Habilidade habilidade = heroi.getHabilidade();
            tela.println("\nVez de " + heroi.getNome()
                    + " (ataque " + heroi.getAtaque() + ", defesa " + heroi.getDefesa() + ")");
            tela.println("1) Atacar");
            tela.println("2) " + habilidade.getNome() + " (" + habilidade.getUsosRestantes() + " usos)");
            tela.println("3) Usar item");
            tela.println("4) Equipar item");

            Acao acao = switch (lerNumero(1, 4)) {
                case 1 -> atacar(heroi, inimigosVivos);
                case 2 -> usarHabilidade(heroi, inimigosVivos);
                case 3 -> usarItem(heroi);
                default -> equiparItem(heroi);
            };
            if (acao != null) {
                return acao;
            }
        }
    }

    //  DecisoesDaJornada

    @Override
    public Personagem escolherHeroi(List<Personagem> opcoes) {
        tela.println("\nEscolha o seu herói:");
        for (int i = 0; i < opcoes.size(); i++) {
            Personagem heroi = opcoes.get(i);
            tela.println((i + 1) + ") " + heroi.getNome() + " (" + heroi.getClass().getSimpleName() + ")"
                    + " - vida " + heroi.getVidaMaxima() + ", ataque " + heroi.getAtaque()
                    + ", defesa " + heroi.getDefesa()
                    + " | habilidade: " + heroi.getHabilidade().getNome());
        }
        return opcoes.get(lerNumero(1, opcoes.size()) - 1);
    }

    @Override
    public void prepararParaBatalha(Personagem heroi) {
        while (true) {
            tela.println("\nPreparação de " + heroi.getNome() + " - vida " + heroi.getVida() + "/"
                    + heroi.getVidaMaxima() + ", ataque " + heroi.getAtaque() + ", defesa " + heroi.getDefesa());
            tela.println("Equipado: " + descreverEquipados(heroi));
            tela.println("1) Equipar item");
            tela.println("2) Usar item");
            tela.println("3) Seguir para a batalha");
            switch (lerNumero(1, 3)) {
                case 1 -> {
                    Equipavel item = escolherEquipavel(heroi);
                    if (item != null) {
                        tela.println(heroi.equipar(item));
                    }
                }
                case 2 -> {
                    Usavel item = escolherUsavel(heroi);
                    if (item != null) {
                        tela.println(heroi.usarItem(item));
                    }
                }
                default -> {
                    return;
                }
            }
        }
    }

    private String descreverEquipados(Personagem heroi) {
        List<Equipavel> equipados = heroi.getEquipamentos().getEquipados();
        if (equipados.isEmpty()) {
            return "nada";
        }
        List<String> nomes = new ArrayList<>();
        for (Equipavel item : equipados) {
            nomes.add(item.getNome());
        }
        return String.join(", ", nomes);
    }

    // submenus (retornam null quando o jogador volta)

    private Acao atacar(Personagem heroi, List<Inimigo> inimigosVivos) {
        Inimigo alvo = escolherAlvo(inimigosVivos);
        return alvo == null ? null : new AtaqueBasico(heroi, alvo);
    }

    private Acao usarHabilidade(Personagem heroi, List<Inimigo> inimigosVivos) {
        Habilidade habilidade = heroi.getHabilidade();
        if (!habilidade.podeUsar()) {
            tela.println("Essa habilidade não tem mais usos.");
            return null;
        }
        if (!habilidade.precisaDeAlvo()) {
            return new UsarHabilidade(heroi, heroi);
        }
        Inimigo alvo = escolherAlvo(inimigosVivos);
        return alvo == null ? null : new UsarHabilidade(heroi, alvo);
    }

    private Acao usarItem(Personagem heroi) {
        Usavel item = escolherUsavel(heroi);
        return item == null ? null : new UsarItem(heroi, item);
    }

    private Acao equiparItem(Personagem heroi) {
        Equipavel item = escolherEquipavel(heroi);
        return item == null ? null : new EquiparItem(heroi, item);
    }

    private Usavel escolherUsavel(Personagem heroi) {
        List<Usavel> usaveis = heroi.getInventario().getUsaveis();
        int escolha = escolherNaLista("Qual item usar?", nomesDos(usaveis));
        return escolha < 0 ? null : usaveis.get(escolha);
    }

    private Equipavel escolherEquipavel(Personagem heroi) {
        List<Equipavel> equipaveis = heroi.getInventario().getEquipaveis();
        int escolha = escolherNaLista("Qual equipamento vestir?", nomesDos(equipaveis));
        return escolha < 0 ? null : equipaveis.get(escolha);
    }

    private Inimigo escolherAlvo(List<Inimigo> inimigosVivos) {
        if (inimigosVivos.size() == 1) {
            return inimigosVivos.get(0);
        }
        List<String> opcoes = new ArrayList<>();
        for (Inimigo inimigo : inimigosVivos) {
            opcoes.add(inimigo.getNome() + " (" + inimigo.getVida() + "/" + inimigo.getVidaMaxima() + ")");
        }
        int escolha = escolherNaLista("Qual alvo?", opcoes);
        return escolha < 0 ? null : inimigosVivos.get(escolha);
    }

    // utilidades de leitura e formatação

    // Mostra uma lista numerada (mais "0) Voltar") e retorna o índice escolhido, ou -1 para voltar.
    private int escolherNaLista(String titulo, List<String> opcoes) {
        if (opcoes.isEmpty()) {
            tela.println("Nada disponível.");
            return -1;
        }
        tela.println(titulo);
        for (int i = 0; i < opcoes.size(); i++) {
            tela.println((i + 1) + ") " + opcoes.get(i));
        }
        tela.println("0) Voltar");
        return lerNumero(0, opcoes.size()) - 1;
    }

    private int lerNumero(int minimo, int maximo) {
        while (true) {
            tela.print("> ");
            if (!teclado.hasNextLine()) {
                throw new IllegalStateException("A entrada foi encerrada.");
            }
            String linha = teclado.nextLine().trim();
            try {
                int numero = Integer.parseInt(linha);
                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }
            } catch (NumberFormatException e) {
                // cai na mensagem abaixo
            }
            tela.println("Opção inválida. Digite um número de " + minimo + " a " + maximo + ".");
        }
    }

    private List<String> nomesDos(List<? extends Item> itens) {
        List<String> nomes = new ArrayList<>();
        for (Item item : itens) {
            nomes.add(item.getNome() + " - " + item.getDescricao());
        }
        return nomes;
    }

    private String linhaDe(Combatente combatente) {
        int cheios = (int) Math.ceil(10.0 * combatente.getVida() / combatente.getVidaMaxima());
        String barra = "[" + "#".repeat(cheios) + "-".repeat(10 - cheios) + "]";
        String linha = String.format("%-18s %s %3d/%d", combatente.getNome(), barra,
                combatente.getVida(), combatente.getVidaMaxima());
        return combatente.estaVivo() ? linha : linha + " (derrotado)";
    }
}
