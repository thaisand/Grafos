import sys
from comparable_tuple import ComparableTuple
from edge import Edge


class Grafo:
    def __init__(self, num_nodes, is_directional=False):
        self._is_directional = is_directional
        self._num_edges = 0
        self._num_nodes = num_nodes
        self._k_centers = 0
        self._edges_weights = [[sys.maxsize] * num_nodes for _ in range(num_nodes)]

        for i in range(num_nodes):
            self._edges_weights[i][i] = 0

    @classmethod
    def from_file(cls, path):
        with open(path, "r") as file:
            row = file.readline().strip().split(" ")
            num_nodes = int(row[0])
            graph = cls(num_nodes)
            graph._num_edges = int(row[1])
            graph._k_centers = int(row[2])

            for index, line in enumerate(file):
                from_node, to_node, weight = map(int, line.strip().split())
                # print(from_node, to_node, weight)
                graph._set_edge_1_indexed(from_node, to_node, weight)

        # print(graph)
        return graph

    def get_min_distance(self):
        min_path = [row[:] for row in self._edges_weights]

        for k in range(self._num_nodes):
            for i in range(self._num_nodes):
                for j in range(self._num_nodes):
                    if min_path[i][k] == sys.maxsize or min_path[k][j] == sys.maxsize:
                        continue
                    elif min_path[i][j] == sys.maxsize:
                        min_path[i][j] = min_path[i][k] + min_path[k][j]
                    elif min_path[i][j] > min_path[i][k] + min_path[k][j]:
                        min_path[i][j] = min_path[i][k] + min_path[k][j]

        return min_path

    def set_edge(self, from_node, to_node, weight):
        if (
            weight != sys.maxsize
            and self._edges_weights[from_node][to_node] == sys.maxsize
        ):
            self._num_edges += 1
        elif (
            weight == sys.maxsize
            and self._edges_weights[from_node][to_node] != sys.maxsize
        ):
            self._num_edges -= 1

        self._edges_weights[from_node][to_node] = weight
        if not self._is_directional:
            self._edges_weights[to_node][from_node] = weight

    def _set_edge_1_indexed(self, from_node, to_node, weight):
        self.set_edge(from_node - 1, to_node - 1, weight)

    def get_edge_weight(self, from_node, to_node):
        return self._edges_weights[from_node][to_node]

    def get_edge_weight_1_indexed(self, from_node, to_node):
        return self.get_edge_weight(from_node - 1, to_node - 1)

    def get_num_edges(self):
        return self._num_edges

    def get_num_nodes(self):
        return self._num_nodes

    def is_directional(self):
        return self._is_directional

    def get_k_centers(self):
        return self._k_centers

    def set_k_centers(self, k_centers):
        self._k_centers = k_centers

    def get_all_edges(self):
        edges = []

        for i in range(self._num_nodes):
            start = 0 if self._is_directional else i + 1
            for j in range(start, self._num_nodes):
                if i != j and self._edges_weights[i][j] != sys.maxsize:
                    edges.append(Edge(i, j, self._edges_weights[i][j]))

        return edges

    def get_all_edges_from_node(self, node):
        edges = []

        for i in range(self._num_nodes):
            if i != node and self._edges_weights[node][i] != sys.maxsize:
                edges.append(Edge(node, i, self._edges_weights[node][i]))

        return edges

    def calculate_eccentricity(self, node):
        border = []
        dist = [sys.maxsize] * self._num_nodes
        excentricity = 0

        dist[node] = 0

        for e in self.get_all_edges_from_node(node):
            border.append(ComparableTuple(e, e.weight))

        while border:
            border.sort(key=lambda x: x.value)
            _tuple = border.pop(0)
            edge = _tuple.get_key()
            distance = _tuple.get_value()

            if dist[edge.to_node] == sys.maxsize:
                dist[edge.to_node] = distance

                for ed in self.get_all_edges_from_node(edge.to_node):
                    border.append(ComparableTuple(ed, ed.weight + distance))

        # print(_tuple)
        # print(edge)
        # print(distance)

        for i in range(self._num_nodes):
            if dist[i] != sys.maxsize and excentricity < dist[i]:
                excentricity = dist[i]

        return excentricity

    def get_reachable_nodes(self, start):
        visited = []
        visit_queue = [start]

        while visit_queue:
            node = visit_queue.pop(0)

            if node in visited:
                continue

            for i in range(self._num_nodes):
                if (
                    self._edges_weights[node][i] != sys.maxsize
                    and i != node
                    and i not in visited
                ):
                    visit_queue.append(i)

            visited.append(node)

        return visited

    def __str__(self):
        str_representation = ""
        for i in range(self._num_nodes):
            for j in range(self._num_nodes):
                num = self._edges_weights[i][j]
                if num == sys.maxsize:
                    str_representation += "inf"
                else:
                    str_representation += str(num)
                str_representation += "\t"
            str_representation += "\n"
        return (
            "Grafo [num_edges="
            + str(self._num_edges)
            + ", num_nodes="
            + str(self._num_nodes)
            + "]\n"
            + str_representation
        )

    def is_reachable_from(self, origin, objective):
        visited = [False] * self._num_nodes
        visit_queue = [origin]

        while visit_queue:
            node = visit_queue.pop(0)

            if visited[node]:
                continue

            for i in range(self._num_nodes):
                if self._edges_weights[node][i] != sys.maxsize and i == objective:
                    return True
                elif (
                    self._edges_weights[node][i] != sys.maxsize
                    and i != node
                    and not visited[i]
                ):
                    visit_queue.append(i)

            visited[node] = True

        return visited[objective]

    @staticmethod
    def graph_matrix_to_string(weights):
        str_representation = ""
        for i in range(len(weights)):
            for j in range(len(weights[i])):
                num = weights[i][j]
                if num == sys.maxsize:
                    str_representation += "inf"
                else:
                    str_representation += str(num)
                str_representation += "\t"
            str_representation += "\n"
        return str_representation
