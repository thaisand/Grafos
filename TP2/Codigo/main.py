import os
from time import strftime
from grafo import Grafo
from brute_force_solver import BruteForceSolver
from mst_solver import MSTSolver


class Main:
    executor = "Thais"

    @staticmethod
    def main():
        f = "results.csv"
        if not os.path.exists(f):
            with open(f, "w") as csvWriter:
                csvWriter.write(
                    "Grafo #, Algoritmo, Centros, Raio, Tempo (ms), Calculador\n"
                )

        print("MST")
        for i in range(1, 2):
            algorithm = "MST"
            print(f"Pmed{i}: StartTime: {strftime('%Y-%m-%d %H:%M:%S')}")
            graph = Grafo.from_file(f"instances/pmed{i}.txt")
            k_centers = graph.get_k_centers()
            solver = MSTSolver(graph, k_centers)
            result = solver.find_best_centers_v1()
            run_time = solver.get_run_time()

            with open(f, "a") as csvWriter:
                csvWriter.write(
                    f"{i}, {algorithm}, {len(result[0])}: {str(result[0]).replace(', ', '|')}, {result[1]}, {run_time}, {Main.executor}\n"
                )

            print(f"BestRadius '{i}': {result[1]}")

        print("Forca Bruta")
        for i in range(1, 41):
            algorithm = "Brute Force"
            print(f"Pmed{i}: StartTime: {strftime('%Y-%m-%d %H:%M:%S')}")
            graph = Grafo.from_file(f"instances/pmed{i}.txt")
            k_centers = graph.get_k_centers()
            solver = BruteForceSolver(graph, k_centers)
            result = solver.find_best_centers()
            run_time = solver.get_run_time()

            with open(f, "a") as csvWriter:
                if run_time is None or run_time > BruteForceSolver.TIMEOUT:
                    csvWriter.write(
                        f"{i}, {algorithm}, {len(result[0])}: {str(result[0]).replace(', ', '|')}, {result[1]}, {run_time} (TIMEOUT), {Main.executor}\n"
                    )
                else:
                    csvWriter.write(
                        f" , {i}, {algorithm}, {len(result[0])}: {str(result[0]).replace(', ', '|')}, {result[1]}, {run_time}, {Main.executor}\n"
                    )

            print(f"BestRadius '{i}': {result[1]}")
            
        return


if __name__ == "__main__":
    Main.main()
