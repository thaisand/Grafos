def get_combs(combinations, data, start, end, index):
    if index == len(data):
        combination = data.copy()
        combinations.append(combination)
    elif start <= end:
        data[index] = start
        get_combs(combinations, data, start + 1, end, index + 1)
        get_combs(combinations, data, start + 1, end, index)


def generate_combinations(n, r):
    combinations = []
    get_combs(combinations, [0] * r, 0, n - 1, 0)
    return combinations
