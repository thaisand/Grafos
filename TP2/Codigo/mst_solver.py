from grafo import Grafo
import heapq
import time
import sys


class MSTSolver:
    def __init__(self, grafo, k_centros):
        self._grafo = grafo
        self._k_centros = k_centros
        self._start_time = 0

    def get_run_time(self):
        return int(round(time.time() * 1000)) - self._start_time


    def build_ms_forest_(self):
        g = Grafo(self._grafo.get_num_nodes(), self._grafo.is_directional())
        edges = [(e, e.weight) for e in self._grafo.get_all_edges()]
        heapq.heapify(edges)

        while edges and g.get_num_edges() < (g.get_num_nodes() - self._k_centros):
            tuple_ = heapq.heappop(edges)
            e = tuple_[0]
            # print(e)

            if not g.is_reachable_from(e.from_node, e.to_node):
                g.set_edge(e.from_node, e.to_node, e.weight)
                ex_from = g.calculate_eccentricity(e.from_node)
                ex_to = g.calculate_eccentricity(e.to_node)
                ex = min(ex_from, ex_to)
                if ex > edges[0][1] and ex > tuple_[1]:
                    g.set_edge(e.from_node, e.to_node, sys.maxsize)
                    tuple_ = (tuple_[0], ex)
                    heapq.heappush(edges, tuple_)

        return g

 

    def find_best_centers_v1(self):
        self._start_time = int(round(time.time() * 1000))
        # print(self.build_ms_forest_())
        return self.find_best_centers(
            self.build_ms_forest_().get_min_distance()
        )

    def find_best_centers(self):
        self._start_time = int(round(time.time() * 1000))
        return self.find_best_centers(
            self.build_ms_forest_().get_min_distance()
        )
    
    def find_best_centers(self, subgraphs):
        original_graph = self._grafo.get_min_distance()
        visited = [False] * len(subgraphs)

        vertices_by_subgraph = [[] for _ in range(self._k_centros)]
        centres = []
        radius = 0
        k = 0
        for i in range(len(vertices_by_subgraph)):
            vertices_by_subgraph[i] = []

        for i in range(len(visited)):
            visited[i] = False

        for i in range(len(subgraphs)):
            if not visited[i]:
                for j in range(len(subgraphs[i])):
                    if subgraphs[i][j] != sys.maxsize:
                        vertices_by_subgraph[k].append(j)
                        visited[j] = True
                k += 1

        for i in range(len(vertices_by_subgraph)):
            subgraph_radius = sys.maxsize
            subgraph_centre = 0
            for n in vertices_by_subgraph[i]:
                excentricity = 0
                for j in vertices_by_subgraph[i]:
                    excentricity = (
                        original_graph[n][j]
                        if original_graph[n][j] != sys.maxsize
                        and excentricity < original_graph[n][j]
                        else excentricity
                    )

                if excentricity < subgraph_radius:
                    subgraph_radius = excentricity
                    subgraph_centre = n
            centres.append(subgraph_centre)
            radius = max(radius, subgraph_radius)

        entry = (centres, radius)
        # print(entry)
        return entry

    @staticmethod
    def calculate_component_radius(graph_matrix, node):
        vertices = []
        subgraph_radius = sys.maxsize

        for i in range(len(graph_matrix[node])):
            if graph_matrix[node][i] != sys.maxsize:
                vertices.append(i)

        for n in vertices:
            excentricity = 0
            for j in vertices:
                excentricity = max(excentricity, graph_matrix[n][j])

            if excentricity < subgraph_radius:
                subgraph_radius = excentricity

        return subgraph_radius

    
