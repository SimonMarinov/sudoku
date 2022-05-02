package com.marinsim.sudoku;

public class SudokuSolution {
    private SudokuTable solution;
    private boolean solved;


    public SudokuSolution() {
        solved = false;
    }

    void saveSolution(CSbSudokuVarible[][] solution){
        int table[][] = new int[solution.length][solution.length];

        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution.length; j++) {
                table[i][j] = solution[i][j].getRestrictedVal();
            }
        }
        this.solution = new SudokuTable(table);
        solved = true;
    }


    void saveSolution(int[][] table){
        solved = true;
        solution = new SudokuTable(table);
    }

}
