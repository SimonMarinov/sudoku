package com.marinsim.sudoku;

import com.sun.jdi.connect.IllegalConnectorArgumentsException;

import java.util.ArrayList;
import java.util.List;

public class BacktrakingSudokuSolver implements SudokuSolver {
    private int table[][];
    private static long notesVisited;

    public BacktrakingSudokuSolver(SudokuTable sudokuTable) {
        notesVisited = 0;
        this.table = sudokuTable.table;
    }


    @Override
    public boolean solve(SudokuSolution solution) {
        notesVisited++;

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == 0) {
                    for (int k = 1; k < 10; k++) {
                        if (posible(i, j, k)) {
                            table[i][j] = k;
                            if (solve(solution)){
                                return true;
                            }
                        }
                    }
                    table[i][j] = 0;

                    return false;
                }
            }
        }

        solution.saveSolution(table);
        return SudokuTable.isTableSolved(table);
    }

    private boolean posible(int x, int y, int val) {
        for (int i = 0; i < 9; i++) {
            if (table[x][i] == val) return false;
        }

        for (int i = 0; i < 9; i++) {
            if (table[i][y] == val) return false;
        }

        var x0 = (x / 3) * 3;
        var y0 = (y / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[x0 + i][y0 + j] == val) return false;
            }
        }

        return true;
    }

    private void printTable() {
        for (var row : table) {
            for (var el : row) {
                System.out.print(el);
            }
            System.out.println();
        }
    }


    @Override
    public long getNotesVisited() {
        return notesVisited;
    }
}
