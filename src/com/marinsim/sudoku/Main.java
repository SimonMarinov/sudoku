package com.marinsim.sudoku;

public class Main {

    public static void main(String[] args) {
	// write your code here
        SudokuTable table = new SudokuTable();
        table.loadClasicTable();
        SudokuSolver.csbSolve(table);
    }
}
