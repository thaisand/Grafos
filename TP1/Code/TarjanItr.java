import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TarjanItr {

    // Declaração de variáveis
    private Grafo grafo;
    private int[] descoberto;
    private int[] menor;
    private boolean[] visitados;
    private int[] pai;
    private int tempo;
    private LinkedList<Integer> pilha;
    private List<LinkedList<Integer>> componentesBiconexos;

    // Construtor da classe Tarjan
    public TarjanItr(Grafo grafo) {
        // Inicialização das variáveis
        this.grafo = grafo;
        this.descoberto = new int[grafo.getNumVertices()];
        this.menor = new int[grafo.getNumVertices()];
        this.visitados = new boolean[grafo.getNumVertices()];
        this.pai = new int[grafo.getNumVertices()];
        this.tempo = 0;
        this.pilha = new LinkedList<>();
        this.componentesBiconexos = new ArrayList<>();
        for (int i = 0; i < grafo.getNumVertices(); i++) {
            pai[i] = -1;
        }
    }
   
    // Obtem os componentes biconexos do grafo
    public List<LinkedList<Integer>> getComponentesBiconexos() {
        for (int i = 0; i < grafo.getNumVertices(); i++) {
            if (!visitados[i]) {
                buscaProfundidade(i);
                if (!pilha.isEmpty()) {
                    LinkedList<Integer> componentes = new LinkedList<>();
                    int w;
                    // Encontra os componentes biconexos da pilha
                    do {
                        w = pilha.pop();
                        componentes.add(w);
                    } while (w != i);
                    componentesBiconexos.add(componentes);
                }
            }
        }
        return componentesBiconexos;
    }

    // Busca em profundidade no grafo
    private void buscaProfundidade(int inicio) {
        Stack<Integer> stack = new Stack<>();
        stack.push(inicio);

        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visitados[v]) {
                visitados[v] = true;
                descoberto[v] = tempo;
                menor[v] = tempo;
                tempo++;
                int filhos = 0;
                for (int u : grafo.getVerticesAdj(v)) {
                    if (!visitados[u]) {
                        filhos++;
                        pai[u] = v;
                        pilha.push(v);
                        pilha.push(u);
                        stack.push(u);
                    } else if (u != pai[v] && descoberto[u] < menor[v]) {
                        menor[v] = descoberto[u];
                        pilha.push(v);
                        pilha.push(u);
                    }
                }
                // Verifica se é um componente biconexo
                if ((pai[v] == -1 && filhos > 1) || (pai[v] != -1 && menor[v] >= descoberto[pai[v]])) {
                    LinkedList<Integer> componentes = new LinkedList<>();
                    int w;
                    // Encontra os componentes biconexos da pilha
                    do {
                        w = pilha.pop();
                        componentes.add(w);
                    } while (w != v);
                    componentes.add(v);
                    componentesBiconexos.add(componentes);
                }
            } else {
                while (!pilha.isEmpty() && descoberto[pilha.peek()] > descoberto[v]) {
                    pilha.push(v);
                    pilha.push(pilha.pop());
                }
            }
        }
    }
}
