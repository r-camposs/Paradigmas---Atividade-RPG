package rpg.jogo;

import java.util.List;

// Uma história completa: prólogo, fases em sequência e os dois finais possíveis.
public class Campanha {

    private final String titulo;
    private final String prologo;
    private final List<Fase> fases;
    private final String finalDeVitoria;
    private final String finalDeDerrota;

    public Campanha(String titulo, String prologo, List<Fase> fases,
                    String finalDeVitoria, String finalDeDerrota) {
        if (fases.isEmpty()) {
            throw new IllegalArgumentException("Uma campanha precisa de ao menos uma fase.");
        }
        this.titulo = titulo;
        this.prologo = prologo;
        this.fases = List.copyOf(fases);
        this.finalDeVitoria = finalDeVitoria;
        this.finalDeDerrota = finalDeDerrota;
    }

    public String getTitulo() { return titulo; }
    public String getPrologo() { return prologo; }
    public List<Fase> getFases() { return fases; }
    public String getFinalDeVitoria() { return finalDeVitoria; }
    public String getFinalDeDerrota() { return finalDeDerrota; }
}
