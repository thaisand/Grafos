import java.util.*;

public class Biconexo {
    private int[] baixo;
    private int[] pre;
    private int cnt;
    private boolean[] articulacao;

    public Biconexo(Grafo G) {
        baixo = new int[G.getNumVertices()];
        pre = new int[G.getNumVertices()];
        articulacao = new boolean[G.getNumVertices()];
        for (int v = 0; v < G.getNumVertices(); v++)
            baixo[v] = -1;
        for (int v = 0; v < G.getNumVertices(); v++)
            pre[v] = -1;

        for (int v = 0; v < G.getNumVertices(); v++)
            if (pre[v] == -1)
                dfs(G, v, v);
    }

    private void dfs(Grafo G, int u, int v) {
        int filho = 0;
        pre[v] = cnt++;
        baixo[v] = pre[v];
        for (int w : G.getVerticesAdj(v)) {
            if (pre[w] == -1) {
                filho++;
                dfs(G, v, w);

                // update numero baixo
                baixo[v] = Math.min(baixo[v], baixo[w]);

                if (baixo[w] >= pre[v] && u != v)
                    articulacao[v] = true;
            }

            else if (w != u)
                baixo[v] = Math.min(baixo[v], pre[w]);
        }

        if (u == v && filho > 1)
            articulacao[v] = true;
    }

    public boolean isArticulacao(int v) {
        return articulacao[v];
    }

    // test client
}