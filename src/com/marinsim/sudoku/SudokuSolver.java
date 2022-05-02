package com.marinsim.sudoku;

public interface SudokuSolver {

   /**
    * implemantion should be recusive
    * after finding soulition solution should be saved into SudokuSolution
    * @return true if table can be solved
    */
   public abstract boolean solve(SudokuSolution solution);


   public long notesVisited();

}
