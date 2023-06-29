import java.util.ArrayList;

public class Vertice {
    private int id;
    private ArrayList<Vertice> vizinhos;
    private int quantVizinhos = 0;

    public Vertice(int id) {
        this.id = id;
        vizinhos = new ArrayList<Vertice>();
    }

    public void printVizinhos(Vertice v) {
        ArrayList<Vertice> aux = new ArrayList<>();
        aux = v.getVizinhos();
        System.out.print("Sucessores: ");
        for (Vertice ver : aux) {
            System.out.print(ver.getId() + " ");
        }
        System.out.println();
    }

    public void addVizinho(Vertice v) {
        vizinhos.add(v);
        quantVizinhos++;
    }

    public int getId() {
        return id;
    }

    public int getQuantVizinhos() {
        return quantVizinhos;
    }

    public ArrayList<Vertice> getVizinhos() {
        return vizinhos;
    }

}
