import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int LOG = 17; // 2^17 > 1e5

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;

        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] depth = new int[n + 1];
        int[][] parent = new int[LOG][n + 1];

        for (int i = 0; i < LOG; i++) {
            Arrays.fill(parent[i], -1);
        }

        dfs(1, -1, graph, depth, parent);

        for (int k = 1; k < LOG; k++) {
            for (int v = 1; v <= n; v++) {
                if (parent[k - 1][v] != -1) {
                    parent[k][v] = parent[k - 1][parent[k - 1][v]];
                }
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            if (u == v) {
                ans[i] = 0;
                continue;
            }

            int lca = lca(u, v, parent, depth);
            int dist = depth[u] + depth[v] - 2 * depth[lca];

            ans[i] = modPow(2, dist - 1);
        }

        return ans;
    }

    private void dfs(int u, int p, List<Integer>[] graph,
                     int[] depth, int[][] parent) {
        parent[0][u] = p;

        for (int v : graph[u]) {
            if (v == p) continue;
            depth[v] = depth[u] + 1;
            dfs(v, u, graph, depth, parent);
        }
    }

    private int lca(int u, int v, int[][] parent, int[] depth) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++) {
            if (((diff >> k) & 1) == 1) {
                u = parent[k][u];
            }
        }

        if (u == v) return u;

        for (int k = LOG - 1; k >= 0; k--) {
            if (parent[k][u] != parent[k][v]) {
                u = parent[k][u];
                v = parent[k][v];
            }
        }

        return parent[0][u];
    }

    private int modPow(long base, int exp) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return (int) res;
    }
}
