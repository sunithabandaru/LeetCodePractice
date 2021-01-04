/*

Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
*/ 

class Solution 
{
    private static void bfs(char[][] grid, boolean[][] visited, int rows, int cols, int r, int c)
    {
        if(r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == '1' && !visited[r][c])
        {
            visited[r][c] = true; // mark as visited
            bfs(grid, visited, rows, cols, r+1, c);
            bfs(grid, visited, rows, cols, r-1, c);
            bfs(grid, visited, rows, cols, r, c+1);
            bfs(grid, visited, rows, cols, r, c-1);
            
        }
    }
	
    public int numIslands(char[][] grid) 
    {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int islandCount =0;
		
		// traverse through the given grid 
        for(int r=0; r < rows; r++)
        {
            for(int c=0; c < cols; c++)
            {
                if(grid[r][c] == '1' && !visited[r][c])
                {
                    islandCount++;
                    bfs(grid, visited, rows, cols, r, c);
                }
            }
        }
        return islandCount;
    }
}

/*
Complexity Analysis

Time complexity : O(M×N) where M is the number of rows and N is the number of columns.

Space complexity : O(min(M, N)) because in worst case where the grid is filled with lands, the size of queue can grow up to min(M,N*M,N)
*/