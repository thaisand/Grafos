from utils import generate_combinations
import copy
import sys
import time


class BruteForceSolver:
    TIMEOUT = 3600000

    def __init__(self, grafo, k_centros):
        self.grafo = grafo
        self.k_centros = k_centros
        self.cost_matrix = grafo.get_min_distance()
        self.start_time = 0

    def find_best_centers(self):
        self.start_time = self.get_run_time()

        centers = None
        best_centers = None
        min_radius = sys.maxsize

        for i in range(1, self.k_centros + 1):
            if self.get_run_time() > BruteForceSolver.TIMEOUT:
                break
            print("Calculando n =", i)
            centers = self.find_best_center_ite(i)

            tmp = centers[1]

            if min_radius > centers[1]:
                best_centers = copy.deepcopy(centers[0])
                min_radius = tmp

        return [best_centers, min_radius]

    def get_run_time(self):
        return int(round(time.time() * 1000)) - self.start_time

    def find_best_center_ite(self, n):
        r = self.grafo.get_num_nodes()
        combination = [0] * n

        best_centers = None
        to_nodes = None
        min_radius = sys.maxsize

        for i in range(n):
            combination[i] = i

        while combination[n - 1] < r:
            if self.get_run_time() > BruteForceSolver.TIMEOUT:
                break

            to_nodes = combination[:]
            map = self.distribute_nodes_to_centers(to_nodes)
            map = self.get_distances_to_centers(map)

            tmp = self.find_max_radius_of_distribution(map)
            if min_radius > tmp:
                best_centers = copy.deepcopy(to_nodes)
                min_radius = tmp

            t = n - 1
            while t != 0 and combination[t] == r - n + t:
                t -= 1
            combination[t] += 1
            for i in range(t + 1, n):
                combination[i] = combination[i - 1] + 1

        return [best_centers, min_radius]

 

    def find_max_radius_of_distribution(self, distributed_nodes):
        max_entry = None
        max_radius = -sys.maxsize - 1

        for entry in distributed_nodes:
            distances = distributed_nodes[entry]
            if distances:
                tmp = max(distances)
                if max_entry is None or tmp > max_radius:
                    max_entry = entry
                    max_radius = tmp

        return max_radius

    def distribute_nodes_to_centers(self, centers):
        distributed_nodes = {}

        for i in range(len(centers)):
            distributed_nodes[centers[i]] = []

        for i in range(self.grafo.get_num_nodes()):
            cheapest_node = self.get_cheapest_within_from_node(i, centers)
            distributed_nodes[cheapest_node].append(i)

        return distributed_nodes

    def get_distances_to_centers(self, distributed_nodes):
        distances = {}

        for key in distributed_nodes:
            distances[key] = []
            for node in distributed_nodes[key]:
                distances[key].append(self.cost_matrix[key][node])

        return distances

    def get_cheapest_within_to_node(self, from_node, to_nodes):
        min_dist = sys.maxsize
        to_node = to_nodes[0]

        for i in range(len(to_nodes)):
            if min_dist > self.cost_matrix[from_node][to_nodes[i]]:
                min_dist = self.cost_matrix[from_node][to_nodes[i]]
                to_node = to_nodes[i]

        return to_node

    def get_cheapest_within_from_node(self, to_node, from_nodes):
        min_dist = sys.maxsize
        from_node = from_nodes[0]

        for i in range(len(from_nodes)):
            if min_dist > self.cost_matrix[from_nodes[i]][to_node]:
                min_dist = self.cost_matrix[from_nodes[i]][to_node]
                from_node = from_nodes[i]

        return from_node

    def get_cheapest_node(self, from_node):
        min_dist = self.cost_matrix[from_node][0]
        to_node = 0

        for i in range(1, self.grafo.get_num_nodes()):
            if min_dist > self.cost_matrix[from_node][i]:
                min_dist = self.cost_matrix[from_node][i]
                to_node = i

        return to_node
