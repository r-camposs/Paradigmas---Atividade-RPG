# RPG O Príncipe Cruel (console)

Trabalho prático de **Paradigmas de Programação**: um RPG em modo console, feito em **Java**, aplicando **Programação Orientada a Objetos** e os **5 princípios SOLID**. A ambientação é inspirada no livro *O Príncipe Cruel*, de Holly Black.

**Autor:** Rebeca Mota Campos

**Turma:** CC2NB

## Status do projeto

- [x] Etapa 1: domínio (combatentes, personagens e inimigos)
- [x] Etapa 2: habilidades
- [x] Etapa 3: itens e inventário
- [x] Etapa 4: combate em turnos e interface de console
- [x] Etapa 5: fluxo do jogo (escolha do herói e sequência de batalhas até o chefão)
- [x] Etapa 6: revisão final do código, do UML e do README
- [x] Capturas de tela e publicação no GitHub

> Um exemplo de execução em texto está em [docs/exemplo-execucao.md](docs/exemplo-execucao.md). As capturas de tela foram adicionadas em `docs/img/`.

## Como o jogo funciona

O jogador escolhe um dos três heróis e enfrenta, em sequência, quatro batalhas por turnos até o chefão final, Madoc. O motor de combate aceita qualquer formação (1 contra 1, 1 contra N ou N contra N).

**Fluxo da partida:** prólogo, escolha do herói, e para cada fase: texto de abertura, preparação (equipar e usar itens), batalha, texto de conclusão, recompensas e descanso (recupera metade da vida máxima). Ao vencer o Madoc, o jogo termina com a coroação de Cardan; se o herói cair, a partida acaba.

| Fase | Inimigos | Recompensa |
|---|---|---|
| 1. O duelo com Valerian | Valerian| Espada de Ferro |
| 2. A missão do príncipe Dain | 2 Capangas de Balekin | Armadura de Couro, Poção de Cura |
| 3. A festa de Balekin | Cavaleiro de Balekin, Guarda de Balekin | 2 Poções de Cura |
| 4. O confronto com Madoc | Madoc (chefão) | (fim do jogo) |

**Turnos:** no início da batalha, um sorteio define se os heróis ou os inimigos agem primeiro. A cada rodada, um lado inteiro age e depois o outro. A batalha termina quando um dos lados não tem mais ninguém vivo.

**Ações do herói:** atacar, usar a habilidade especial, usar um item consumível ou equipar um item (equipar gasta o turno).

**Ações do inimigo:** cada inimigo recebe uma estratégia. A agressiva sempre ataca o herói com menos vida; a do chefe faz o mesmo, mas usa o Golpe Brutal quando está com metade da vida ou menos.

| Herói | Tipo | Vida | Ataque | Defesa | Habilidade |
|---|---|---|---|---|---|
| Jude | Duelista | 100 | 18 | 8 | Golpe Certeiro: 150% do ataque (3 usos) |
| Vivi | Feiticeira | 80 | 22 | 4 | Glamour Restaurador: cura 30 de vida (2 usos) |
| Fantasma | Espião | 90 | 16 | 6 | Golpe Furtivo: dois golpes de 75% do ataque (2 usos) |

| Inimigo | Tipo | Vida | Ataque | Defesa |
|---|---|---|---|---|
| Valerian (exemplo) | Soldado | 60 | 12 | 4 |
| Madoc | Chefão | 160 | 21 | 6 |

| Item | Tipo | Efeito |
|---|---|---|
| Poção de Cura | Consumível | Recupera vida |
| Arma | Equipamento (espaço ARMA) | Aumenta o ataque |
| Armadura | Equipamento (espaço ARMADURA) | Aumenta a defesa |

Cada espaço de equipamento comporta um item: ao equipar outro no mesmo espaço, o anterior volta para o inventário. Os valores ainda serão balanceados nas próximas etapas.

## Estrutura do repositório

```
.
├── README.md
├── docs/
│   ├── diagrama-classes.md
│   ├── exemplo-execucao.md
│   └── img/                  (capturas de tela)
└── src/
    ├── Main.java
    └── rpg/
        ├── combatentes/      (Combatente, Personagem, Duelista, Feiticeira, Espiao,
        │                      Inimigo, Soldado, Madoc)
        ├── habilidades/      (Habilidade, HabilidadeLimitada, GolpeCerteiro,
        │                      GlamourRestaurador, GolpeFurtivo)
        ├── itens/            (Item, Usavel, Equipavel, SlotEquipamento, ItemBase,
        │                      PocaoDeCura, Arma, Armadura, Inventario, Equipamentos)
        ├── jogo/             (Jogo, Campanha, Fase, DecisoesDaJornada)
        ├── historia/         (FabricaDeHerois, RoteiroDoPrincipeCruel)
        ├── combate/          (Combate, ResultadoCombate, Acao, AtaqueBasico, GolpeBrutal,
        │                      UsarHabilidade, UsarItem, EquiparItem, EstrategiaDeAcao,
        │                      EstrategiaAgressiva, EstrategiaDoChefe,
        │                      EntradaDoJogador, SaidaDoJogo,
        │                      Batalha, FabricaDeBatalha, FabricaDeCombate)
        └── ui/               (Console)
```

## Como compilar e rodar

Requisito: **JDK 17 ou superior** instalado.

Na raiz do repositório:

```bash
javac -encoding UTF-8 -d out src/Main.java src/rpg/*/*.java
java -Dfile.encoding=UTF-8 -cp out Main
```

No Windows, se os acentos aparecerem quebrados, rode `chcp 65001` antes de executar.

**Pelo IntelliJ IDEA:** abra a pasta do projeto, confirme que `src` está marcada como *Sources Root* e execute a classe `Main`. A interação é feita digitando o número da opção no console da IDE.

## Diagrama de classes

O diagrama UML está em [docs/diagrama-classes.md](docs/diagrama-classes.md).

## Princípios SOLID

Esta seção mostra onde e como cada princípio foi aplicado.

| Princípio | Onde | Status |
|---|---|---|
| **S**: Responsabilidade Única | `Combatente`, `Inventario`, `Equipamentos`, `Combate`, `Jogo`, `Console` | ✅ |
| **O**: Aberto/Fechado | Habilidades, itens, ações, estratégias, inimigos e campanhas entram por extensão | ✅ |
| **L**: Substituição de Liskov | Subclasses de `Combatente`, `Habilidade`, `Usavel`, `Acao`, `EstrategiaDeAcao` | ✅ |
| **I**: Segregação de Interfaces | `Item`, `Usavel`, `Equipavel`, `EntradaDoJogador`, `SaidaDoJogo`, `DecisoesDaJornada` | ✅ |
| **D**: Inversão de Dependência | `Jogo`, `Combate`, `Inimigo` e `Personagem` dependem de abstrações injetadas | ✅ |

### S: Responsabilidade Única

Cada classe tem uma única razão para mudar:

- `Combatente` guarda e protege o estado de combate (vida, ataque e defesa).
- `Inventario` só guarda, lista e remove itens.
- `Equipamentos` só controla o que está vestido e soma os bônus.
- `Combate` só ordena os turnos e executa ações até um lado cair.
- `Jogo` só conduz o fluxo da partida (fases, recompensas e descanso). O conteúdo da história fica em `Campanha`/`Fase` e em `RoteiroDoPrincipeCruel`.
- `Console` só conversa com o jogador (mostra informações e lê decisões).

`Combate` não lê o teclado, não imprime e não sabe como cada ataque funciona:

```java
private void turnoDosHerois() {
    for (Personagem heroi : herois) {
        if (!heroi.estaVivo()) continue;
        if (!existeVivo(inimigos)) return;
        executar(entrada.escolherAcao(heroi, vivos(inimigos)));
    }
}

private void executar(Acao acao) {
    saida.exibir(acao.executar());
    // ...
}
```

### O: Aberto/Fechado

Novos comportamentos entram criando classes, sem editar as existentes:

- Uma nova habilidade é uma nova subclasse de `HabilidadeLimitada`.
- Um novo item consumível implementa `Usavel`; um novo equipamento implementa `Equipavel`.
- Uma nova ação de combate é uma nova classe que implementa `Acao`, e o `Combate` continua igual.
- Um novo comportamento de inimigo é uma nova `EstrategiaDeAcao`.
- Uma nova história é uma nova `Campanha` (com outras `Fase`s), e o `Jogo` continua igual.

```java
public class EstrategiaDoChefe extends EstrategiaAgressiva {
    @Override
    public Acao escolher(Inimigo inimigo, List<Personagem> heroisVivos) {
        if (inimigo.getVida() * 2 <= inimigo.getVidaMaxima()) {
            return new GolpeBrutal(inimigo, escolherAlvo(heroisVivos));
        }
        return super.escolher(inimigo, heroisVivos);
    }
}
```

### L: Substituição de Liskov

Qualquer subtipo funciona no lugar do seu tipo base:

- Heróis e inimigos são tratados como `Combatente`.
- Qualquer `Acao` é executada pelo `Combate` do mesmo jeito.
- Qualquer `EstrategiaDeAcao` pode ser entregue a um `Inimigo`. `EstrategiaDoChefe` herda de `EstrategiaAgressiva` e respeita o mesmo contrato.

O método `usar` de `HabilidadeLimitada` é `final`, para que nenhuma subclasse quebre a regra de usos limitados. `Personagem` sobrescreve `getAtaque()` e `getDefesa()` apenas somando bônus, mantendo o contrato da classe base.

### I: Segregação de Interfaces

Em vez de uma interface grande, cada papel tem a sua:

- `Item`: nome e descrição. `Usavel` e `Equipavel` são papéis separados, então uma poção não implementa métodos de equipamento.
- `EntradaDoJogador`: só a decisão do jogador. `SaidaDoJogo`: só a exibição de informações. `DecisoesDaJornada`: só as escolhas fora do combate (herói e preparação). O `Console` implementa as três, mas quem precisa de apenas uma depende de apenas uma.

```java
public interface EntradaDoJogador {
    Acao escolherAcao(Personagem heroi, List<Inimigo> inimigosVivos);
}

public interface SaidaDoJogo {
    void exibir(String mensagem);
    void exibirEstado(List<Personagem> herois, List<Inimigo> inimigos);
}
```

### D: Inversão de Dependência

As classes de alto nível dependem de abstrações, e as implementações concretas são injetadas de fora:

- `Jogo` recebe `DecisoesDaJornada`, `SaidaDoJogo` e uma `FabricaDeBatalha`, não o `Console` nem o `Combate`. O `Jogo` nunca faz `new Combate(...)`: pede a batalha à fábrica.
- `Combate` recebe `EntradaDoJogador` e `SaidaDoJogo`, não o `Console`.
- `Inimigo` recebe uma `EstrategiaDeAcao`, não uma estratégia concreta.
- `Personagem` recebe uma `Habilidade`, não uma habilidade concreta.
- `Console` recebe `Scanner` e `PrintStream` no construtor, o que permite testá-lo com entradas simuladas.

O `Main` é o ponto onde tudo é montado:

```java
Console console = new Console(new Scanner(System.in), System.out);
FabricaDeBatalha batalhas = new FabricaDeCombate(console, console, new Random());
Jogo jogo = new Jogo(console, console, batalhas);

jogo.jogar(new FabricaDeHerois().criarHerois(),
        new RoteiroDoPrincipeCruel().criarCampanha());
```

## Conceitos de POO aplicados

- **Abstração:** `Combatente`, `Personagem`, `Inimigo`, `ItemBase` e `HabilidadeLimitada` são classes abstratas; `Acao`, `Habilidade` e `EstrategiaDeAcao` são interfaces.
- **Encapsulamento:** atributos `private`; a vida só muda por `receberDano` e `curar`; o inventário devolve cópias da lista.
- **Herança:** `Duelista`, `Feiticeira` e `Espiao` herdam de `Personagem`; `Soldado` e `Madoc` herdam de `Inimigo`.
- **Polimorfismo:** `falar()`, `usar(...)`, `executar()` e `escolher(...)` têm implementações diferentes em cada classe concreta.
- **Composição:** `Personagem` é composto por `Inventario` e `Equipamentos`.

## Referência

BLACK, Holly. *O príncipe cruel*. São Paulo: Galera Record, 2018.
