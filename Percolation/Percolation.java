import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final boolean[][] grid;
    private int openSitesCount;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufFullCheck;
    private final int virtualTop;
    private final int virtualBottom;

    // creates n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be > 0");

        this.n = n;
        grid = new boolean[n][n];
        openSitesCount = 0;

        // n*n + 2 for virtual top and bottom
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufFullCheck = new WeightedQuickUnionUF(n * n + 1);

        virtualTop = n * n;
        virtualBottom = n * n + 1;
    }

    // maps (row, col) to UF index
    private int index(int row, int col) {
        return (row * n) + col;
    }

    // validates 1-based indices
    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("Index out of bounds");
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int r = row - 1;
        int c = col - 1;

        if (grid[r][c]) return;

        grid[r][c] = true;
        openSitesCount++;

        int current = index(r, c);

        if (r == 0) {
            uf.union(current, virtualTop);
            ufFullCheck.union(current, virtualTop);
        }

        if (r == n - 1) {
            uf.union(current, virtualBottom);
        }

        // directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc]) {
                int neighbor = index(nr, nc);
                uf.union(current, neighbor);
                ufFullCheck.union(current, neighbor);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int r = row - 1;
        int c = col - 1;
        return isOpen(row, col) && ufFullCheck.find(index(r, c)) == ufFullCheck.find(virtualTop);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    // optional test
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 2);
        p.open(2, 2);
        p.open(3, 2);
        System.out.println("Percolates: " + p.percolates());
    }
}
