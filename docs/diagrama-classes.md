# Diagrama de classes

O diagrama UML foi dividido em **duas partes** para ficar legível. Clique na imagem para ampliar. Cada parte também existe em SVG (imagem vetorial, que amplia sem perder qualidade): [parte 1](img/diagrama-parte1.svg) e [parte 2](img/diagrama-parte2.svg).

As caixas cinzas, nomeadas como "definida em outra parte", são classes que aparecem completas na outra parte do diagrama.

## Parte 1: modelo do jogo (combatentes, habilidades e itens)

![Diagrama de classes, parte 1: modelo do jogo](img/diagrama-parte1.png)

## Parte 2: combate, fluxo do jogo e interface de console

![Diagrama de classes, parte 2: combate, fluxo do jogo e interface](img/diagrama-parte2.png)

## Legenda

| Símbolo | Significado |
|---|---|
| Seta com ponta vazada e linha cheia | Herança (a seta aponta para a classe-pai) |
| Seta com ponta vazada e linha tracejada | Implementação de interface |
| Linha com losango preenchido | Composição (o todo é dono da parte) |
| Linha com losango vazio | Agregação (o todo guarda a parte) |
| Seta simples com linha cheia | Associação (usa por atributo) |
| Seta simples com linha tracejada | Dependência (usa em um método) |
| Nome em itálico | Classe abstrata |

## Versão em texto (Mermaid)

O código abaixo gera os mesmos diagramas no GitHub (o desenho automático é menos organizado que as imagens acima).

<details>
<summary>Parte 1 em Mermaid</summary>

```mermaid
classDiagram
    direction TB

    class Combatente {
        <<abstract>>
        -String nome
        -int vidaMaxima
        -int vida
        -int ataque
        -int defesa
        +receberDano(int dano) int
        +curar(int quantidade) void
        +estaVivo() boolean
        +falar() String
        +getAtaque() int
        +getDefesa() int
    }
    class Personagem {
        <<abstract>>
        +usarItem(Usavel item) String
        +equipar(Equipavel item) String
    }
    class Inimigo {
        <<abstract>>
        +escolherAcao(List~Personagem~ herois) Acao
    }
    class Duelista
    class Feiticeira
    class Espiao
    class Soldado
    class Madoc

    Combatente <|-- Personagem
    Combatente <|-- Inimigo
    Personagem <|-- Duelista
    Personagem <|-- Feiticeira
    Personagem <|-- Espiao
    Inimigo <|-- Soldado
    Inimigo <|-- Madoc

    class Habilidade {
        <<interface>>
        +getNome() String
        +getUsosRestantes() int
        +podeUsar() boolean
        +precisaDeAlvo() boolean
        +usar(Combatente usuario, Combatente oponente) String
    }
    class HabilidadeLimitada {
        <<abstract>>
        -String nome
        -int usosRestantes
        +usar(Combatente usuario, Combatente oponente) String
        #aplicarEfeito(Combatente usuario, Combatente oponente) String
    }
    class GolpeCerteiro
    class GlamourRestaurador
    class GolpeFurtivo

    Habilidade <|.. HabilidadeLimitada
    HabilidadeLimitada <|-- GolpeCerteiro
    HabilidadeLimitada <|-- GlamourRestaurador
    HabilidadeLimitada <|-- GolpeFurtivo
    Personagem --> Habilidade : possui 1

    class Item {
        <<interface>>
        +getNome() String
        +getDescricao() String
    }
    class Usavel {
        <<interface>>
        +usar(Combatente alvo) String
    }
    class Equipavel {
        <<interface>>
        +getSlot() SlotEquipamento
        +getBonusAtaque() int
        +getBonusDefesa() int
    }
    class SlotEquipamento {
        <<enumeration>>
        ARMA
        ARMADURA
    }
    class ItemBase {
        <<abstract>>
        -String nome
        -String descricao
    }
    class PocaoDeCura
    class Arma
    class Armadura
    class Inventario {
        +adicionar(Item item) void
        +remover(Item item) boolean
        +getItens() List~Item~
        +getUsaveis() List~Usavel~
        +getEquipaveis() List~Equipavel~
    }
    class Equipamentos {
        +equipar(Equipavel e) Optional~Equipavel~
        +getBonusAtaque() int
        +getBonusDefesa() int
    }
    class EstrategiaDeAcao["EstrategiaDeAcao (parte 2)"] {
        <<interface>>
    }

    Item <|-- Usavel
    Item <|-- Equipavel
    Item <|.. ItemBase
    ItemBase <|-- PocaoDeCura
    ItemBase <|-- Arma
    ItemBase <|-- Armadura
    Usavel <|.. PocaoDeCura
    Equipavel <|.. Arma
    Equipavel <|.. Armadura
    Equipavel --> SlotEquipamento
    Personagem *-- Inventario
    Personagem *-- Equipamentos
    Inventario o-- Item : guarda 0..*
    Equipamentos o-- Equipavel : veste 0..*
    Inimigo --> EstrategiaDeAcao : usa 1
```

</details>

<details>
<summary>Parte 2 em Mermaid</summary>

```mermaid
classDiagram
    direction LR

    class Personagem["Personagem (parte 1)"]
    class Inimigo["Inimigo (parte 1)"]
    class Item["Item (parte 1)"]

    class Acao {
        <<interface>>
        +executar() String
    }
    class AtaqueBasico
    class GolpeBrutal
    class UsarHabilidade
    class UsarItem
    class EquiparItem
    class EstrategiaDeAcao {
        <<interface>>
        +escolher(Inimigo inimigo, List~Personagem~ heroisVivos) Acao
    }
    class EstrategiaAgressiva
    class EstrategiaDoChefe
    class EntradaDoJogador {
        <<interface>>
        +escolherAcao(Personagem heroi, List~Inimigo~ inimigosVivos) Acao
    }
    class SaidaDoJogo {
        <<interface>>
        +exibir(String mensagem) void
        +exibirEstado(List~Personagem~ herois, List~Inimigo~ inimigos) void
    }
    class DecisoesDaJornada {
        <<interface>>
        +escolherHeroi(List~Personagem~ opcoes) Personagem
        +prepararParaBatalha(Personagem heroi) void
    }
    class Batalha {
        <<interface>>
        +executar() ResultadoCombate
    }
    class FabricaDeBatalha {
        <<interface>>
        +criar(List~Personagem~ herois, List~Inimigo~ inimigos) Batalha
    }
    class Combate
    class FabricaDeCombate
    class ResultadoCombate {
        <<enumeration>>
        VITORIA
        DERROTA
    }
    class Jogo {
        +jogar(List~Personagem~ herois, Campanha campanha) void
    }
    class Campanha
    class Fase
    class FabricaDeHerois
    class RoteiroDoPrincipeCruel
    class Console

    Acao <|.. AtaqueBasico
    Acao <|.. GolpeBrutal
    Acao <|.. UsarHabilidade
    Acao <|.. UsarItem
    Acao <|.. EquiparItem
    EstrategiaDeAcao <|.. EstrategiaAgressiva
    EstrategiaAgressiva <|-- EstrategiaDoChefe
    EstrategiaDeAcao ..> Acao : cria
    EntradaDoJogador ..> Acao : devolve
    Inimigo --> EstrategiaDeAcao : usa 1
    Batalha <|.. Combate
    FabricaDeBatalha <|.. FabricaDeCombate
    FabricaDeBatalha ..> Batalha : cria
    FabricaDeCombate ..> Combate : cria
    FabricaDeCombate --> EntradaDoJogador
    FabricaDeCombate --> SaidaDoJogo
    Combate --> EntradaDoJogador
    Combate --> SaidaDoJogo
    Combate ..> Acao : executa
    Combate ..> ResultadoCombate
    EntradaDoJogador <|.. Console
    SaidaDoJogo <|.. Console
    DecisoesDaJornada <|.. Console
    Jogo --> DecisoesDaJornada
    Jogo --> SaidaDoJogo
    Jogo --> FabricaDeBatalha
    Jogo ..> Campanha : conduz
    Campanha *-- Fase
    Fase o-- Inimigo : enfrenta 1..*
    Fase o-- Item : recompensas 0..*
    FabricaDeHerois ..> Personagem : cria
    RoteiroDoPrincipeCruel ..> Campanha : cria
```

</details>
