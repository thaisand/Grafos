from time import strftime, time


class Solver:
    def __init__(self, graph, k_centers):
        self.graph = graph
        self.k_centers = k_centers
        self.start_time = time()
        self.end_time = None

    def get_run_time(self):
        if self.end_time:
            return (self.end_time - self.start_time) * 1000
        else:
            return None
