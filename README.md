# Spanner Study
Comparison of different approximative spanner algorithms based on their stretch factor.

![Spannbaum](https://media.giphy.com/media/l378b59fSuMV12tzO/giphy-downsized.gif "Spannbaum")

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
