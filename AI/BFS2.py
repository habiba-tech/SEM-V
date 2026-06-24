graph = {
    'S':['A','B','C'],
    'A':['B','D'],
    'B':['D','H'],
    'C':['L'],
    'D':['F'],
    'H':['F','G'],
    'L':['I','J'],
    'I':['K'],
    'J':['K'],
    'G':['E'],
    'K':['E']
}

h = {
    'S':10,'A':9,'B':7,'C':8,
    'D':8,'F':6,'H':6,'G':3,
    'L':6,'I':4,'J':4,'K':3,
    'E':0
}

open = ['S']
closed = []

while open:

    best = open[0]

    for i in open:
        if h[i] < h[best]:
            best = i

    open.remove(best)

    print(best, end=" ")

    if best == 'E':
        print("\nGoal Found")
        break

    closed.append(best)

    if best in graph:
        for child in graph[best]:
            if child not in open and child not in closed:
                open.append(child)
