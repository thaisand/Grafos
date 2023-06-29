import java.util.Random;

public class Main { 

    public static void main(String[] args) {
        // Cria uma instância da classe Random para gerar números aleatórios
        Random rand = new Random();
        // Define um array com os tamanhos dos grafos a serem gerados
        int[] tamanhos = {100000};
        // Define o número de vezes que cada tamanho de grafo será gerado e testado
        int tentativas = 1;

                // Loop for para percorrer cada tamanho de grafo
                for (int tamanho : tamanhos) {
                    System.out.println("----------------------------------------------------");
                    // Exibe uma mensagem informando o tamanho do grafo atual
                    System.out.println("Grafo tamanho " + tamanho);
                    // Inicializa a variável tempoTotal como 0
                    double tempoTotal = 0;
                    
                    // Loop for para gerar e testar o grafo aleatório "tentativas" vezes
                    for (int i = 0; i < tentativas; i++) {
                        // Gera um novo grafo aleatório com o tamanho especificado e o objeto Random
                        Grafo g = geraGrafo(tamanho, rand);
                        
                        // Executa o algoritmo de caminhos disjuntos
                        // Mede o tempo que leva para executar o algoritmo usando a classe System.nanoTime()
                        long inicio = System.nanoTime();
                        DisjointPaths dp = new DisjointPaths();
                        dp.randomGraph();
                        long fim = System.nanoTime();
                        // Calcula o tempo que levou para executar o algoritmo em milissegundos
                        double tempo = (fim - inicio) / 1000000.0;
                        // Adiciona o tempo atual ao tempo total
                        tempoTotal += tempo;
                    }           
                    // Calcula o tempo médio que levou para executar o algoritmo em cada grafo do tamanho atual
                    double tempoMedio = tempoTotal / tentativas;
                    // Exibe o tempo médio na tela
                    System.out.println("Tempo médio caminhos disjuntos: " + tempoMedio + " ms");
                }
        /*
        // Loop for para percorrer cada tamanho de grafo
        for (int tamanho : tamanhos) {
            System.out.println("----------------------------------------------------");
            // Exibe uma mensagem informando o tamanho do grafo atual
            System.out.println("Grafo tamanho " + tamanho);
            // Inicializa a variável tempoTotal como 0
            double tempoTotal = 0;
            
            // Loop for para gerar e testar o grafo aleatório "tentativas" vezes
            for (int i = 0; i < tentativas; i++) {
                // Gera um novo grafo aleatório com o tamanho especificado e o objeto Random
                Grafo g = geraGrafo(tamanho, rand);
                Biconexo bic = new Biconexo(g);
                // Executa o algoritmo de força bruta
                // Mede o tempo que leva para executar o algoritmo usando a classe System.nanoTime()
                long inicio = System.nanoTime();
                for (int v = 0; v < g.getNumVertices(); v++)
                    if (bic.isArticulacao(v))
                        System.out.println(v);
                long fim = System.nanoTime();
                // Calcula o tempo que levou para executar o algoritmo em milissegundos
                double tempo = (fim - inicio) / 1000000.0;
                // Adiciona o tempo atual ao tempo total
                tempoTotal += tempo;
            }           
            // Calcula o tempo médio que levou para executar o algoritmo em cada grafo do tamanho atual
            double tempoMedio = tempoTotal / tentativas;
            // Exibe o tempo médio na tela
            System.out.println("Tempo médio Força Bruta: " + tempoMedio + " ms");
        }

/*
        // Loop for para percorrer cada tamanho de grafo
        for (int tamanho : tamanhos) {
            System.out.println("----------------------------------------------------");
            // Exibe uma mensagem informando o tamanho do grafo atual
            System.out.println("Grafo tamanho " + tamanho);
            // Inicializa a variável tempoTotal como 0
            double tempoTotal = 0;
            
            // Loop for para gerar e testar o grafo aleatório "tentativas" vezes
            for (int i = 0; i < tentativas; i++) {
                // Gera um novo grafo aleatório com o tamanho especificado e o objeto Random
                Grafo g = geraGrafo(tamanho, rand);
                
                // Executa o algoritmo Tarjan para encontrar os componentes biconexos do grafo g
                // Mede o tempo que leva para executar o algoritmo usando a classe System.nanoTime()
                long inicio = System.nanoTime();
                TarjanRec tarjan = new TarjanRec(g);
                tarjan.getComponentesBiconexos();
                long fim = System.nanoTime();
                // Calcula o tempo que levou para executar o algoritmo em milissegundos
                double tempo = (fim - inicio) / 1000000.0;
                // Adiciona o tempo atual ao tempo total
                tempoTotal += tempo;
            }           
            // Calcula o tempo médio que levou para executar o algoritmo em cada grafo do tamanho atual
            double tempoMedio = tempoTotal / tentativas;
            // Exibe o tempo médio na tela
            System.out.println("Tempo médio Tarjan recursivo: " + tempoMedio + " ms");
        } */


        // Loop for para percorrer cada tamanho de grafo
        for (int tamanho : tamanhos) {
            System.out.println("----------------------------------------------------");
            // Exibe uma mensagem informando o tamanho do grafo atual
            System.out.println("Grafo tamanho " + tamanho);
            // Inicializa a variável tempoTotal como 0
            double tempoTotal = 0;
            
            // Loop for para gerar e testar o grafo aleatório "tentativas" vezes
            for (int i = 0; i < tentativas; i++) {
                // Gera um novo grafo aleatório com o tamanho especificado e o objeto Random
                Grafo g = geraGrafo(tamanho, rand);
                
                // Executa o algoritmo Tarjan para encontrar os componentes biconexos do grafo g
                // Mede o tempo que leva para executar o algoritmo usando a classe System.nanoTime()
                long inicio = System.nanoTime();
                TarjanItr tarjan = new TarjanItr(g);
                tarjan.getComponentesBiconexos();
                long fim = System.nanoTime();
                // Calcula o tempo que levou para executar o algoritmo em milissegundos
                double tempo = (fim - inicio) / 1000000.0;
                // Adiciona o tempo atual ao tempo total
                tempoTotal += tempo;
            }           
            // Calcula o tempo médio que levou para executar o algoritmo em cada grafo do tamanho atual
            double tempoMedio = tempoTotal / tentativas;
            // Exibe o tempo médio na tela
            System.out.println("Tempo médio Tarjan iterativo: " + tempoMedio + " ms");
        }



    }
    
    // Método para gerar um novo grafo aleatório com o tamanho especificado
    public static Grafo geraGrafo(int tamanho, Random rand) {
        // Cria uma nova instância da classe Grafo com o tamanho especificado
        Grafo g = new Grafo(tamanho);
        // Loop for para percorrer cada par de vértices do grafo
        for (int i = 0; i < tamanho; i++) {
            for (int j = i + 1; j < tamanho; j++) {
                // Adiciona uma aresta entre os vértices i e j com probabilidade de 50%
                if (rand.nextDouble() < 0.1) {
                    g.addAresta(i, j);
                }
            }
        }
        // Retorna o grafo gerado
        return g;
    }
}
