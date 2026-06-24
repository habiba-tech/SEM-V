graph = {
    'S':[('A',7),('B',2),('C',8)],
    'B':[('H',1)],
    'H':[('G',2)],
    'G':[('E',2)]
}

h = {'S':10,'A':9,'B':7,'C':8,'H':6,'G':3,'E':0}

open = [('S',0)]

while open:

    best = open[0]

    for i in open:
        if i[1] + h[i[0]] < best[1] + h[best[0]]:
            best = i

    open.remove(best)

    node, g = best

    print(node, end=" ")

    if node == 'E':
        print("\nCost =", g)
        break

    if node in graph:
        for child, cost in graph[node]:
            open.append((child, g + cost))
