/* --------------------------------------------------------------------------------
   Zero Striping (“Set Matrix Zeroes”)

   ─ Problem statement ─
   Given an m × n integer matrix, if any cell contains 0 set **its entire row and
   column** to 0.  Do the update **in-place** and as efficiently as possible.

   Example (4 × 5):
           1  0  3  4  5
           6  8  9 10 11
          12 13  0 15 16
          17 18 19  0 20
   → 0’s in (0,1) (2,2) (3,3) ⇒
           0  0  0  0  0
           6  0  0 10 11
           0  0  0  0  0
          17 18  0  0 20

   m and n ≤ 200 (or any reasonable size).

   ---------------------------------------------------------------------------
   Intuition – two ways
   ---------------------------------------------------------------------------
   1) **Hash-set version (easy, O(m + n) extra space)**  
      • First pass: record every row index that has a 0 in a set
                    record every column index that has a 0 in another set  
      • Second pass: zero every cell whose row is in the row-set OR
                     whose column is in the column-set.

      Time O(m n), space O(m + n).

   2) **In-place version (clever, O(1) extra space)**  
      Key observation: once a row/col contains a 0 its original non-zero
      values will be wiped out eventually—we can *reuse* those dead cells
      as **markers**.

      – Use **row 0** to mark which columns must be zeroed.  
      – Use **col 0** to mark which rows must be zeroed.  
      – BUT this overloads (0,0); also we may lose the information that row 0
        or col 0 themselves originally had a 0.  Keep two boolean flags
        `firstRowHasZero`, `firstColHasZero` before marking.

      Steps
      1. Scan first row … set `firstRowHasZero = true` if any 0.
      2. Scan first col … set `firstColHasZero = true` if any 0.
      3. Traverse the *sub-matrix* (r = 1..m-1, c = 1..n-1);  
         whenever matrix[r][c]==0 put 0 into matrix[r][0] **and** matrix[0][c]
         (mark its row and column).
      4. Second pass over the sub-matrix:  
            if matrix[r][0]==0 **or** matrix[0][c]==0 → set matrix[r][c]=0.
      5. Finally zero the *entire* first row if `firstRowHasZero` is true,
         and the *entire* first column if `firstColHasZero` is true.

   -------------------------------------------------------------------------------- */



import java.util.*;

public class Main
{
    
    public static void setZeroes(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) return;
        int m = M.length, n = M[0].length;

        // 1) does first row / first col already contain a 0?
        boolean firstRowHasZero = false, firstColHasZero = false;
        for (int c = 0; c < n; c++)
            if (M[0][c] == 0) { firstRowHasZero = true; break; }

        for (int r = 0; r < m; r++)
            if (M[r][0] == 0) { firstColHasZero = true; break; }

        // 2) use row0 / col0 as markers for the rest of the matrix
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (M[r][c] == 0) {
                    M[r][0] = 0;   // mark this row
                    M[0][c] = 0;   // mark this column
                }
            }
        }

        // 3) zero the sub-matrix based on those markers
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (M[r][0] == 0 || M[0][c] == 0)
                    M[r][c] = 0;
            }
        }

        // 4) zero first row/column if they originally had zeros
        if (firstRowHasZero) {
            for (int c = 0; c < n; c++) M[0][c] = 0;
        }
        if (firstColHasZero) {
            for (int r = 0; r < m; r++) M[r][0] = 0;
        }

        
    }
    

	public static void main(String[] args) {
        int[][] mat = {
            {1, 0, 3, 4, 5},
            {6, 8, 9,10,11},
            {12,13, 0,15,16},
            {17,18,19, 0,20}
        };
        setZeroes(mat);

        for (int[] row : mat) {
            for (int v : row) System.out.printf("%3d", v);
            System.out.println();
        }
    }

}