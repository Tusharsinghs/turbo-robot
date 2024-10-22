public class Sudoku {
    static boolean isSafe(int[][] grid, int row, int col,
            int num) {

        // Check if we find the same num
        // in the similar row , we
        // return false
        for (int x = 0; x <= 8; x++)
            if (grid[row][x] == num)
                return false;

        // Check if we find the same num
        // in the similar column ,
        // we return false
        for (int x = 0; x <= 8; x++)
            if (grid[x][col] == num)
                return false;

        // Check if we find the same num
        // in the particular 3*3
        // matrix, we return false
        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i + startRow][j + startCol] == num)
                    return false;

        return true;
    }

    static boolean f(int grid[][], int row, int col) {
        int N = grid.length;
        if (row == N - 1 && col == N)
            return true;

        // Check if column value becomes 9 ,
        // we move to next row
        // and column start from 0
        if (col == N) {
            row++;
            col = 0;
        }

        // Check if the current position
        // of the grid already
        // contains value >0, we iterate
        // for next column
        if (grid[row][col] != 0)
            return f(grid, row, col + 1);

        for (int num = 1; num < 10; num++) {

            // Check if it is safe to place
            // the num (1-9) in the
            // given row ,col ->we move to next column
            if (isSafe(grid, row, col, num)) {

                /*
                 * assigning the num in the current
                 * (row,col) position of the grid and
                 * assuming our assigned num in the position
                 * is correct
                 */
                grid[row][col] = num;

                // Checking for next
                // possibility with next column
                if (f(grid, row, col + 1))
                    return true;
            }
            /*
             * removing the assigned num , since our
             * assumption was wrong , and we go for next
             * assumption with diff num value
             */
            grid[row][col] = 0;
        }
        return false;
    }

    // Function to find a solved Sudoku.
    static boolean SolveSudoku(int grid[][]) {
        return f(grid, 0, 0);
    }

    // Function to print grids of the Sudoku.
    static void printGrid(int grid[][]) {
        int N = grid.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(grid[i][j] + " ");
            System.out.println();
        }
        // add your code here
    }

    public static void main(String[] args) {
        int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
        if (SolveSudoku(grid) == true) {
            printGrid(grid);
        } else {
            System.out.println("No solution Exists");
        }

    }

}
