"""
Convert rmat format to metis format
- Edges are made undirected
- Adjacency lists are sorted
- Loops get removed
- Duplicate entries in adjacency lists get removed
"""
import sys

if len(sys.argv) != 2:
    print('usage: python {0} infile'.format(sys.argv[0]))
    quit()

inf = sys.argv[1]
outf = inf.rsplit('.')[0] + '.metis'

# build adjacency list
with open(inf) as f:
    line = f.readline()
    while line.startswith('c'):
        line = f.readline()

    n_vertices = int(line.split()[2])
    n_edges = int(line.split()[3])
    print('vertices: ' + str(n_vertices) + ', edges: ' + str(n_edges))
    adjlist = [set() for i in range(n_vertices)]

    for line in f:
        if line.startswith('a'):
            source = int(line.split()[1])
            sink = int(line.split()[2])
            if source != sink:
                adjlist[source - 1].add(sink)
                adjlist[sink - 1].add(source)

# save
e = sum([len(s) for s in adjlist])/2
with open(outf, "w") as f:
    f.write(str(len(adjlist)) + " " + str(int(e)) + "\n")
    for s in adjlist:
        sorted_s = sorted(s)
        for i in sorted_s[:-1]:
            f.write(str(i) + " ")
        f.write(str(sorted_s[-1]) + "\n")
