package com.kludsa.b12;
public class SudokuProblem {
  public static boolean sudokuAutomation(int[][] board, int n) {
    int row = -1;
    int col = -1;    
    boolean isEmpty = true;    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 0) {
          row = i;
          col = j;
          isEmpty = false;
          break;
        }
      }
      if (!isEmpty) {
        break;
      }
    }
    if (isEmpty) {
      return true;
    }
    for (int num = 1; num <= n; num++) {
      if (isSafePlace(board, row, col, num)) {        
        board[row][col] = num;        
        if (sudokuAutomation(board, n)) {
          return true;
        } else {
          board[row][col] = 0; 
        }
      }
    }
    return false;
  }

  public static boolean isSafePlace(int[][] board, int row, int col, int num) {
    for (int j = 0; j < board.length; j++) {
      if (board[row][j] == num) {
        return false;
      }
    }
    for (int i = 0; i < board.length; i++) {
      if (board[i][col] == num) {
        return false;
      }
    }
    int sqrt = (int) Math.sqrt(board.length);
    int rowStart = row - row % sqrt;
    int colStart = col - col % sqrt;
    for (int r = rowStart; r < rowStart + sqrt; r++) {
      for (int d = colStart; d < colStart + sqrt; d++) {
        if (board[r][d] == num) {
          return false;
        }
      }
    }
    return true;
  }  
  public static void printSudokuBoard(int[][] board, int N) { 
      for (int row = 0; row < N; row++) { 
          for (int col = 0; col < N; col++) { 
              System.out.print(board[row][col]+" "); 
          } 
          System.out.println(); 
      } 
  } 
  public static void main(String[] args) {
    int[][] board2 = new int[][] {{ 0, 2, 3, 0},
                                 { 0, 0, 0, 1 },
                                 { 0, 1, 0, 0 },
                                 { 2, 4, 1, 0 }};
        int[][] board = {{6,3,7,1,5,9,2,4,8},
                         {2,0,1,3,0,7,9,0,6},
                         {5,9,4,0,6,8,1,7,3},
                         {8,1,6,5,9,2,7,0,4},
                         {4,0,9,7,0,3,6,0,5},
                         {3,7,5,6,1,4,8,2,9},
                         {7,0,2,9,0,6,5,0,1},
                         {9,5,3,8,2,1,4,6,7},
                         {0,6,8,4,0,5,3,9,0}};

    int N = board.length;

    if (sudokuAutomation(board, N)) {
      printSudokuBoard(board, N);
    } else {
      System.out.println("No Solution Exists");
    }
  }
}
