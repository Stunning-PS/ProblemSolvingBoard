package greedy.leetcode;

public class Leetcode765 {

    public int minSwapsCouples(int[] row) {
        if (row == null || row.length < 2) return 0;

        int number = 0;
        DisjointSets disjointSets = new DisjointSets(row.length/2);
        for (int i = 0; i<row.length; i = i+2){
            if (!isCouple(row[i], row[i+1])){
                if (disjointSets.union(row[i]/2, row[i+1]/2)){
                    number++;
                }
            }
        }

        return number;
    }

    private boolean isCouple(int i, int j) {
        return (Math.min(i, j) & 1) == 0 && Math.abs(i - j) == 1;
    }

    public static class DisjointSets {
        public int[] parent;
        private int n;

        public DisjointSets(int n) {
            this.n = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = -1;
            }
        }

        public int find(int n) {
            if (parent[n] < 0) return n;
            parent[n] = find(parent[n]);
            return parent[n];
        }

        public boolean union(int x, int y) {
            int xp = find(x), yp = find(y);
            if (xp == yp) {
                return false;
            }

            if (Math.abs(parent[xp]) >= Math.abs(parent[yp])) {
                parent[xp] += parent[yp];
                parent[yp] = xp;
            } else {
                parent[yp] += parent[xp];
                parent[xp] = yp;
            }

            return true;
        }
    }
}
