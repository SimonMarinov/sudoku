package com.marinsim.sudoku;

import java.io.IOException;
import java.util.Scanner;

public class SudokuTable {
    public int [][] table;

    public SudokuTable(int[][] table) {
        this.table = table;
    }

    void loadClasicTable(){
        table = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int val=0;
                try {
                    char c = (char) System.in.read();
                    val = Integer.parseInt(String.valueOf(c));

                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (val<0 || val>9) {
                    throw new IllegalArgumentException();
                }

                table[i][j] = val;

            }
        }
    }

}
