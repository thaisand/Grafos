import java.util.*;

class Grafo {
    private ArrayList<Vertice> vertices;
    private int numVertices = 0;

    public Grafo() {
        vertices = new ArrayList<Vertice>();
    }

    public void imprimirVertices() {
        for (Vertice v : vertices) {
            System.out.println(v.getId() + " " + v.getQuantVizinhos());
        }
    }

    public void addVertice(Vertice v, Vertice a) {
        vertices.add(v);
        v.addVizinho(a);
        numVertices++;
    }

    public void addVizinho(Vertice v1, Vertice v2) {

        v1.addVizinho(v2);
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public boolean containsVertice(Vertice v) {
        boolean resp = false;
        for (Vertice vertice : vertices) {
            if (vertice.getId() == v.getId()) {
                resp = true;
            }
        }
        return resp;
    }

    public Vertice cloneVertice(Vertice v) {
        for (Vertice vertice : vertices) {
            if (vertice.getId() == v.getId()) {
                return vertice;
            }
        }
        return v;
    }

    public int countVizinhos(Vertice v) {
        int resp = 0;
        ArrayList<Vertice> aux = new ArrayList<>();
        for(Vertice vertice: vertices) {
            aux = vertice.getVizinhos();
            for(Vertice ver: aux) {
                if(ver.getId() == v.getId()) {
                    resp++;
                }
            }
        }
        return resp;
    }

    public void printVizinhos (Vertice v) {
        ArrayList<Vertice> aux = new ArrayList<>();
        System.out.print("Predecessores: ");
        for(Vertice vertice: vertices) {
            aux = vertice.getVizinhos();
            for(Vertice ver: aux) {
                if(ver.getId() == v.getId()) {
                    System.out.print(vertice.getId() + " ");
                }
            }
        }
        System.out.println();
    }


}
