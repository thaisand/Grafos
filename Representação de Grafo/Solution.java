
import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo: ");
        String entrada = in.nextLine();
        Grafo grafo = new Grafo();

        grafo = leArquivo(entrada);
        int v = -1;
        while (v != 0) {
            System.out.println("----------------------------");
            System.out.println("Digite o valor do vértice: ");
            v = in.nextInt();
            in.nextLine();
            Vertice vertice = new Vertice(v);
            checaEntrada(grafo, vertice);  
        }
        in.close();

    }

    private static void checaEntrada(Grafo grafo, Vertice vertice) {
        if (grafo.containsVertice(vertice)) {
            vertice = grafo.cloneVertice(vertice);
            // Grau de saída do vértice == numero de arestas que saem do vertice 
            System.out.println("Grau de saída: " + vertice.getVizinhos().size()); 

            // Grau de entrada == numero de arestas que entram no vertice
            System.out.println("Grau de entrada: " + grafo.countVizinhos(vertice)); 

            // Sucessores == vertices que saem do vertice
            vertice.printVizinhos(vertice); 

            // Predecessores == vertices que chegam ao vertice
            grafo.printVizinhos(vertice);

        } else {
            System.out.println("Vértice inválido!");
        }
    }

    private static Grafo leArquivo(String entrada) throws FileNotFoundException {
        File arquivo = new File(entrada);
        //File arquivo = new File("graph-test-100.txt");
        Scanner sc = new Scanner(arquivo);
        int vertices = sc.nextInt();
        int arestas = sc.nextInt();
        int v, a;

        Grafo grafo = new Grafo();
        while(sc.hasNext()) {
            v = sc.nextInt();
            a = sc.nextInt();
            Vertice vertice = new Vertice(v);
            Vertice aresta = new Vertice(a);

            if (grafo.containsVertice(vertice) == true) {
                vertice = grafo.cloneVertice(vertice);
                grafo.addVizinho(vertice, aresta);
                //System.out.println("aresta " + vertice.getId() + " " + aresta.getId());
            } else {
                grafo.addVertice(vertice, aresta);
                //System.out.println("vertice " + vertice.getId() + " " + aresta.getId());
            }
        }
        grafo.imprimirVertices();
        sc.close();
        return grafo; 
    }
}



