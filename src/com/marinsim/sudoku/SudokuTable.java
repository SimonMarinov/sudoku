package com.marinsim.sudoku;

import java.io.IOException;
import java.util.Scanner;

public class SudokuTable {
    public int[][] table;

    public SudokuTable(int[][] table) {
        this.table = table;
    }

    public SudokuTable() {

    }

    void loadClasicTable() throws IOException {
        table = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int val = 0;
                char c = (char) System.in.read();
                while (c < '0' || c > '9') {
                    c = (char) System.in.read();
                }
                val = Integer.parseInt(String.valueOf(c));

                if (val < 0 || val > 9) {
                    throw new IllegalArgumentException();
                }

                table[i][j] = val;

            }
        }
    }

    public static boolean isTableSolved(int table[][]) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] < 1 || table[i][j] > 9) return false;
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

    public boolean isTableSolved() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == 0) return false;
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

    void print() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }
}
