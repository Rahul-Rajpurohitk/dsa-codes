/* --------------------------------------------------------------------------------
   Verify Sudoku Board
   ------------------------------------
   Given a *partially-filled* 9 × 9 Sudoku board, determine whether its **current
   state** is valid:

     1.  Every row contains each digit 1-9 at most once (0 = blank is ignored).
     2.  Every column contains each digit 1-9 at most once.
     3.  Every 3 × 3 sub-grid (there are nine of them) contains each digit 1-9 at most once.

   You are **NOT** asked to solve the puzzle, only to tell whether the filled-in
   cells violate any rule.

   Return `true`  → the board is currently valid  
   Return `false` → some rule is broken
   
   •  We must detect *duplicates* in three “buckets” simultaneously:
                – row  r
                – column c
                – 3×3 block (r/3, c/3)

       •  HashSet gives O(1) “have we seen this number already?” queries.

       •  Create:
            rowSets[9]     – one set per row
            colSets[9]     – one set per column
            boxSets[3][3]  – one set for every 3×3 sub-grid

       •  Traverse every cell once.
            If board[r][c] == 0  → skip (blank)
            Else:
               num = board[r][c]
               if num already in rowSets[r]    → invalid
               if num already in colSets[c]    → invalid
               if num already in boxSets[r/3][c/3] → invalid
               otherwise add num to all three sets
   -------------------------------------------------------------------------------- */



import java.util.*;

public class Main
{
    
    public static boolean isValidSudoku(int[][] board) {
        HashSet<Integer>[] rowSets = new HashSet[9]; //  9 HashSet for each row
        HashSet<Integer>[] colSets = new HashSet[9]; // 9 HashSet for each col
        
        HashSet<Integer>[][] boxSets = new HashSet[3][3]; // 3 x 3 grid  total 9
        
        
        for(int i = 0; i< 9; i++){
            rowSets[i] = new HashSet<>();
            colSets[i] = new HashSet<>();
        }
        
        for(int br = 0; br < 3; br++)
            for(int bc = 0; bc < 3; bc++)
                boxSets[br][bc] = new HashSet<>();

        
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                int num = board[r][c];
                if(num == 0) continue;
                
                //check rowSets
                // .add(num) if first time then return true, !true -> false; hence if doesn't execute
                // .add(num) if not first time then return false, !false -> true; hence body execute.
                if(!rowSets[r].add(num)) return false; 
                
                // check colSets
                if(!colSets[c].add(num)) return false;
                
                //check the boxSets
                if(!boxSets[r / 3][c / 3].add(num)) return false;
            }
        }
        
        return true;   // no duplicates anywhere

        
    }
    

	public static void main(String[] args) {
        int[][] board = {
            
            {3, 0, 6, 5, 8, 0, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 3, 1, 0},
            {0, 0, 0, 0, 9, 0, 0, 0, 0},
            {9, 0, 0, 6, 0, 3, 0, 0, 5},
            {0, 0, 0, 0, 6, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 2, 0, 0, 0, 0, 0}
        };

        System.out.println(isValidSudoku(board)); // false
    }

}