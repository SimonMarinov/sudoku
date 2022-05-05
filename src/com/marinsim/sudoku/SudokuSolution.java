package com.marinsim.sudoku;

public class SudokuSolution {
    private SudokuTable solution;


    public SudokuSolution() {}

    void saveSolution(CSbSudokuVarible[][] solution){
        int table[][] = new int[solution.length][solution.length];

        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution.length; j++) {
                table[i][j] = solution[i][j].getRestrictedVal();
            }
        }
        this.solution = new SudokuTable(table);
    }


    void saveSolution(int[][] solution){
        int table[][] = new int[solution.length][solution.length];

        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution.length; j++) {
                table[i][j] = solution[i][j];
            }
        }
        this.solution = new SudokuTable(table);
    }

    public boolean isSolutionOk() {
        if (solution == null){
            return false;
        }
        return solution.isTableSolved();
    }
}
