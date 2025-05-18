public class Solution 
{
    private static final int MOD = 1_000_000_007;

    public int colorTheGrid(int m, int n) 
    {
        // Step 1: Generate all valid column states (no same color in adjacent rows)
        List<int[]> states = new ArrayList<>();
        generateStates(m, new int[m], 0, states);

        // Step 2: Build transitions between states (no same color in same row)
        Map<String, List<String>> transitions = new HashMap<>();
        for (int[] a : states) 
        {
            String keyA = Arrays.toString(a);
            transitions.putIfAbsent(keyA, new ArrayList<>());
        
            for (int[] b : states) 
            {
                if (isCompatible(a, b)) 
                {
                    transitions.get(keyA).add(Arrays.toString(b));
                }
            }
        }

        // Step 3: Initialize DP for the first column (each valid state counts as 1)
        Map<String, Integer> dp = new HashMap<>();
        for (int[] state : states) 
        {
            dp.put(Arrays.toString(state), 1);
        }

        // Step 4: DP over columns - count ways to build columns sequentially
        for (int col = 1; col < n; col++) 
        {
            Map<String, Integer> newDp = new HashMap<>();
            for (String prev : dp.keySet()) 
            {
                int count = dp.get(prev);
                for (String next : transitions.get(prev)) 
                {
                    newDp.put(next, (newDp.getOrDefault(next, 0) + count) % MOD);
                }
            }

            dp = newDp;
        }

        // Step 5: Sum all possible final column configurations
        int result = 0;
        for (int val : dp.values()) 
        {
            result = (result + val) % MOD;
        }
        
        return result;
    }

    // Generate all valid columns with no adjacent cells of same color vertically
    private void generateStates(int m, int[] current, int row, List<int[]> states) 
    {
        if (row == m) 
        {
            states.add(Arrays.copyOf(current, m));
            return;
        }
        
        for (int color = 1; color <= 3; color++) 
        {
            if (row > 0 && current[row - 1] == color)
            {
                continue;  // avoid vertical conflict
            } 
            
            current[row] = color;
            generateStates(m, current, row + 1, states);
        }
    }

    // Check if two columns are compatible horizontally (no same color in any row)
    private boolean isCompatible(int[] a, int[] b) 
    {
        for (int i = 0; i < a.length; i++) 
        {
            if (a[i] == b[i])
            {
                return false;
            } 
        }

        return true;
    }
}