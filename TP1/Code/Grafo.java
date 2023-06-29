import java.util.*;

public class Grafo {
    private int vertices;
    private int arestas;
    private Map<Integer, List<Integer>> listaAdj;

    // construtor da classe Grafo que inicializa os atributos da classe
    public Grafo(int vertices) {
        this.vertices = vertices;
        this.arestas = 0; 
        this.listaAdj = new HashMap<>();

        // inicializa a lista de adjacência de cada vértice
        for (int i = 0; i < vertices; i++) {
            listaAdj.put(i, new ArrayList<>());
        }
    }

    // retorna o número de vértices do grafo
    public int getNumVertices() {
        return vertices;
    }

    // retorna o número de arestas do grafo
    public int getNumArestas() {
        return arestas;
    }

    // adiciona uma aresta entre os vértices v e w
    public void addAresta(int v, int w) {
        listaAdj.get(v).add(w);
        listaAdj.get(w).add(v);
        arestas++;
    }

    // retorna os vértices adjacentes ao vértice v
    public Iterable<Integer> getVerticesAdj(int v) {
        return listaAdj.get(v);
    }

    // retorna todos os vértices do grafo
    public Iterable<Integer> getVertices() {
        return listaAdj.keySet();
    }
}
