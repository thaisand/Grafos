class ComparableTuple:
    def __init__(self, key, value):
        self.key = key
        self.value = value

    def get_key(self):
        return self.key

    def set_key(self, key):
        self.key = key

    def get_value(self):
        return self.value

    def set_value(self, value):
        self.value = value

    def __str__(self):
        return "( " + str(self.get_key()) + " , " + str(self.get_value()) + " )"

    def __lt__(self, other):
        return self.get_value() < other.get_value()

    def __eq__(self, other):
        return self.get_value() == other.get_value()

    def __gt__(self, other):
        return self.get_value() > other.get_value()
