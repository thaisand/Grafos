import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Main {
    static int V; // Número de vértices no grafo dado

    /*
     * Retorna verdadeiro se houver um caminho a partir da origem 's' até o destino 't' no grafo residual.
     * Também preenche o array parent[] para armazenar o caminho.
     */
    static boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        // Cria um array de visitados e marca todos os vértices como não visitados
        boolean[] visitados = new boolean[V];

        // Cria uma fila, insere o vértice de origem e marca o vértice de origem como visitado
        Queue<Integer> fila = new LinkedList<>();
        fila.add(s);
        visitados[s] = true;
        parent[s] = -1;

        // Loop da BFS
        while (!fila.isEmpty()) {
            int u = fila.peek();
            fila.remove();

            for (int v = 0; v < V; v++) {
                if (!visitados[v] && rGraph[u][v] > 0) {
                    fila.add(v);
                    parent[v] = u;
                    visitados[v] = true;
                }
            }
        }

        // Se alcançamos o destino na BFS a partir da origem, retorna verdadeiro, caso contrário, falso
        return visitados[t];
    }

    // Retorna o número máximo de caminhos disjuntos de s para t 
    static List<List<Integer>> encontrarCaminhosDisjuntos(int grafo[][], int s, int t) {
        int u, v;

        // Cria um grafo residual e preenche-o com as capacidades residuais do grafo original
        int[][] rGraph = new int[V][V];
        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = grafo[u][v];

        // Este array é preenchido pelo BFS e armazena o caminho
        int[] parent = new int[V];

        List<List<Integer>> caminhosDisjuntos = new ArrayList<>();

        // Aumenta o fluxo enquanto houver um caminho da origem até o destino
        while (bfs(rGraph, s, t, parent)) {
            // Encontra a capacidade residual mínima das arestas ao longo do caminho preenchido pela BFS
            int fluxo_caminho = Integer.MAX_VALUE;

            List<Integer> caminho = new ArrayList<>();
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                fluxo_caminho = Math.min(fluxo_caminho, rGraph[u][v]);
                caminho.add(0, v);
            }
            caminho.add(0, s);
            caminhosDisjuntos.add(caminho);

            // Atualiza as capacidades residuais das arestas e inverte as arestas ao longo do caminho
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= fluxo_caminho;
                rGraph[v][u] += fluxo_caminho;
            }
        }

        // Retorna a lista de caminhos disjuntos
        return caminhosDisjuntos;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
    
        try {
            // Lê o arquivo de texto contendo o grafo
            BufferedReader leitor = new BufferedReader(new FileReader("grafo1.txt"));
            String linha;
            int linhas = 0;
            
            // Conta o número de linhas no arquivo para determinar o número de vértices
            while ((linha = leitor.readLine()) != null) {
                linhas++;
            }
            leitor.close();
    
            V = linhas;
    
            // Cria uma matriz para representar o grafo
            int[][] grafo = new int[V][V];
            leitor = new BufferedReader(new FileReader("grafo1.txt"));
            int linhaAtual = 0;
    
            // Preenche a matriz com os valores do grafo
            while ((linha = leitor.readLine()) != null) {
                linha = linha.replace("[", "").replace("]", "");
                String[] valores = linha.split(" ");
                for (int coluna = 0; coluna < V; coluna++) {
                    grafo[linhaAtual][coluna] = Integer.parseInt(valores[coluna]);
                }
                linhaAtual++;
            }
            leitor.close();
    
            int s = 13; // Vértice de origem
            int t = 37; // Vértice de destino
    
            // Encontra os caminhos disjuntos entre s e t no grafo
            List<List<Integer>> caminhosDisjuntos = encontrarCaminhosDisjuntos(grafo, s, t);
            
            // Exibe o número de caminhos disjuntos encontrados
            System.out.println("Número de caminhos disjuntos: " + caminhosDisjuntos.size());
            System.out.println("Caminhos disjuntos encontrados:");
            
            // Exibe os caminhos disjuntos encontrados
            for (List<Integer> caminho : caminhosDisjuntos) {
                for (int i = 0; i < caminho.size(); i++) {
                    System.out.print(caminho.get(i));
                    if (i != caminho.size() - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execução: " + executionTime + " ms");
    }
    
}