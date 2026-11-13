package rpg.historia;

import rpg.combate.EstrategiaAgressiva;
import rpg.combate.EstrategiaDeAcao;
import rpg.combate.EstrategiaDoChefe;
import rpg.combatentes.Madoc;
import rpg.combatentes.Soldado;
import rpg.itens.Arma;
import rpg.itens.Armadura;
import rpg.itens.PocaoDeCura;
import rpg.jogo.Campanha;
import rpg.jogo.Fase;

import java.util.List;

// Monta a campanha inspirada em O Príncipe Cruel: quatro fases até o confronto com Madoc.
public class RoteiroDoPrincipeCruel {

    public Campanha criarCampanha() {
        EstrategiaDeAcao agressiva = new EstrategiaAgressiva();

        Fase valerian = new Fase(
                "O duelo com Valerian",
                "Em Elfhame, humanos são vistos como inferiores. Na escola, as provocações viram desafio\n"
                        + "e Valerian bloqueia o seu caminho.",
                List.of(new Soldado("Valerian", 60, 12, 4, agressiva)),
                "Valerian cai. Você provou que não é tão fácil de derrubar quanto ele pensava.",
                List.of(new Arma("Espada de Ferro", 5)));

        Fase missao = new Fase(
                "A missão do príncipe Dain",
                "Você aceita espionar para o príncipe Dain. A primeira missão é infiltrar-se\n"
                        + "num encontro secreto, e dois capangas de Balekin descobrem você.",
                List.of(new Soldado("Capanga de Balekin", 50, 10, 5, agressiva),
                        new Soldado("Capanga de Balekin", 50, 10, 5, agressiva)),
                "Com os capangas fora do caminho, você leva as informações até Dain.",
                List.of(new Armadura("Armadura de Couro", 4), new PocaoDeCura("Poção de Cura", 40)));

        Fase festa = new Fase(
                "A festa de Balekin",
                "A crise da sucessão chegou ao ponto crítico. Na festa de Balekin, onde o seu plano\n"
                        + "precisa se completar, a guarda do príncipe tenta detê-lo.",
                List.of(new Soldado("Cavaleiro de Balekin", 70, 14, 6, agressiva),
                        new Soldado("Guarda de Balekin", 50, 10, 5, agressiva)),
                "A guarda recua. Resta enfrentar o último obstáculo do seu plano.",
                List.of(new PocaoDeCura("Poção de Cura", 40), new PocaoDeCura("Poção de Cura", 40)));

        Fase madoc = new Fase(
                "O confronto com Madoc",
                "Madoc, o general que criou você, quer o trono para si. Só um dos dois sai de pé.",
                List.of(new Madoc(new EstrategiaDoChefe())),
                "Madoc está derrotado, e o caminho para o trono está livre.",
                List.of());

        return new Campanha(
                "O PRÍNCIPE CRUEL",
                "Elfhame, o reino das fadas. Ali você cresceu, longe do mundo humano,\n"
                        + "sob o teto de Madoc, o general. Agora a sucessão do Grande Rei está em jogo\n"
                        + "e cada escolha sua pode mudar o destino do reino.",
                List.of(valerian, missao, festa, madoc),
                "Oak coroa Cardan como Grande Rei. Com o seu plano concluído, você garante poder\n"
                        + "sobre ele por meio de um juramento de obediência por um ano e um dia.\n\n"
                        + "FIM. Obrigado por jogar!",
                "Você foi derrotado. O trono de Elfhame ficará nas mãos de outro...\n\n"
                        + "FIM DE JOGO.");
    }
}
