package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    site[] grid;
    int EdgeLenth;
    UnionFind uf;
    int nop = 0;

    private class site {
        int row;
        int col;
        String state;
        int code;

        public site(){
            state = "blocked";
        }
    }
    public int encode(int row, int col){
        int code = row + col * EdgeLenth;
        return code;
    }

    public int getCol(site s) {
        return s.code / EdgeLenth;
    }

    public int getRow(site s) {
        return s.code % EdgeLenth;
    }

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        EdgeLenth = N;
        grid = new site[N*N];
        uf = new UnionFind(N*N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int c = encode(i,j);
                grid[c].row = i;
                grid[c].col = j;
                grid[c].code = c;
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row >= EdgeLenth || col >= EdgeLenth){
            throw new java.lang.IndexOutOfBoundsException();
        }

        if (grid[encode(row,col)].state.equals("blocked") ) {
            if (row == 0) {
                grid[encode(row, col)].state = "full";
            } else {
                grid[encode(row, col)].state = "open";
            }
        }
        nop++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row >= EdgeLenth || col >= EdgeLenth){
            throw new java.lang.IndexOutOfBoundsException();
        }

        return !grid[encode(row,col)].state.equals("blocked");
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row >= EdgeLenth || col >= EdgeLenth){
            throw new java.lang.IndexOutOfBoundsException();
        }

        return grid[uf.find(encode(row,col))].state.equals("full");
    }

    // number of open sites
    public int numberOfOpenSites() {
        return nop;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = (EdgeLenth - 1) * EdgeLenth;i < EdgeLenth * EdgeLenth; i++) {
            if (uf.find(i) < EdgeLenth){
                return true;
            }
        }

        return false;
    }


    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {
    }
}
