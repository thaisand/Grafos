class Edge:
    def __init__(self, from_node, to_node, weight):
        self.from_node = from_node
        self.to_node = to_node
        self.weight = weight

    def __str__(self):
        return (
            "( "
            + str(self.from_node)
            + " , "
            + str(self.to_node)
            + " ) -> "
            + str(self.weight)
        )

    def __lt__(self, other):
        return self.weight < other.weight

    def __eq__(self, other):
        return self.weight == other.weight

    def __gt__(self, other):
        return self.weight > other.weight
