# Spanner Study
An experimental comparison of graph spanner algorithms based on the quality of the resulting spanner.
A *t*-spanner of graph *G* is a subgraph *S*, such that for each pair of vertices the distance in *S* is at most *t* times the distance in *G*.
We assess the quality by the average stretch factor and the total number of edges in the spanner.
The stretch factor of pair of vertices is the distance in *S* relative to the distance in *G*. The average stretch factor is the average over all pairs of vertices.
We compare our results with the study of [Ausiello et al.](https://link.springer.com/article/10.1007/s00453-008-9216-9) and use it as a reference.

#### Members
- Florian Lugstein
- Simon Baier
- Gregor Bachinger


## TODO List
- ~~Implement Base Framework~~
    - ~~Graph input / output~~
    - ~~Stretch factor computation~~
- ~~Generate Test Graphs~~
    - ~~Generate a R-MAT based powerlaw graph, like the one used in the paper~~
- Implement Spanner algorithms
    - Greedy spanner ([lecture slides](https://avs.cs.sbg.ac.at/slides/slides05.pdf) page 5-14, Algorithm on page 10)
    - Baswana/Sen unweighted ([lecture slides](https://avs.cs.sbg.ac.at/slides/slides05.pdf) page 15-24, Algorithm on page 20)
    - Spanner from [exercise 6](https://avs.cs.sbg.ac.at/exercises/ex06.pdf)
    - (optional: Elkin [paper](https://dl.acm.org/doi/10.1145/3274651))
- Compute and prepare figures like in the paper
- Write documentation


## Run
### Generate Test Graphs
- Download [GTgraph](https://github.com/dhruvbird/GTgraph)
- Generate R-MAT based Graph `GTgraph/R-MAT/GTgraph-rmat -n 8192 -m 13003600 -o rmat.txt`
- Convert to metis format `python rmat2metis.py rmat.txt`

### Generate Spanner
- TODO

### Compute Stretch Factor
- `StretchFactor.java`
- TODO

![Spannbaum](https://media.giphy.com/media/l378b59fSuMV12tzO/giphy-downsized.gif "Spannbaum")
