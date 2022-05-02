package com.marinsim.sudoku;

import com.sun.jdi.connect.IllegalConnectorArgumentsException;

import java.util.ArrayList;
import java.util.List;

public class NaiveSudoku implements SudokuSolver {
    private int table[][];
    private static long notesVisited;

    public NaiveSudoku(SudokuTable table) {
        notesVisited = 0;
        this.table = table.table;
    }


    public NaiveSudoku(NaiveSudoku copy) {
        table = new int[copy.table.length][copy.table.length];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                var val = copy.table[i][j];
                table[i][j] = val;
            }
        }
    }

    @Override
    public boolean solve(SudokuSolution solution) {
        notesVisited++;

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == 0) {
                    for (int k = 1; k < 10; k++) {
                        var newSolver = new NaiveSudoku(this);
                        newSolver.table[i][j] = k;
                        if (newSolver.solve(solution)) {
                            return true;
                        }
                    }
                    return false;
                }
            }
        }

        if (isTableOk()) {
            //print res
            System.out.println("Number of notes visited = " + notesVisited);
            printTable();
            return true;
        } else return false;

    }

    @Override
    public long notesVisited() {
        return notesVisited;
    }

    private void printTable() {
        for (var row : table) {
            for (var el : row) {
                System.out.print(el);
            }
            System.out.println();
        }
    }

    private boolean isTableOk() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                var neighbours = Position.getNeighbours(i, j);
                for (var neighbour : neighbours) {
                    if (table[i][j] == table[neighbour.x][neighbour.y]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isTableFiledIn() {
        for (var row : table) {
            for (var tableElement : row) {
                if (tableElement == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
