package com.marinsim.sudoku;

public class SudokuSolver {

   public static SudokuTable csbSolve(SudokuTable tableToSolve){
      CsbSudkou problem = new CsbSudkou(tableToSolve);
      problem.solve();
   }

}
