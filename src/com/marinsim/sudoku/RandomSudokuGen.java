package com.marinsim.sudoku;

import java.lang.*;
import java.util.Random;

/**
 * code copied and modified from: https://www.geeksforgeeks.org/program-sudoku-generator/
 */
public class RandomSudokuGen {
    int[] table[];
    final int sudokuLen = 9; // number of columns/rows.
    int SRN; // square root of N
    int digitsToFill; // No. Of missing digits

    // Constructor
    RandomSudokuGen(int K) {
        this.digitsToFill = K;

        // Compute square root of N
        Double SRNd = Math.sqrt(sudokuLen);
        SRN = SRNd.intValue();

        table = new int[sudokuLen][sudokuLen];
    }

    public int[][] randFulfillableTable(){
        fillValues();
        return table;
    }

    public int[][] randTable() {
        for (int i = 0; i < sudokuLen; i++) {
            for (int j = 0; j < sudokuLen; j++) {
                table[i][j] = 1 + randomGenerator(sudokuLen)%8;
            }
        }

        removeKDigits();

        return table;
    }



    // Sudoku Generator
    private void fillValues() {
        // Fill the diagonal of SRN x SRN matrices
        fillDiagonal();

        // Fill remaining blocks
        fillRemaining(0, SRN);

        // Remove Randomly K digits to make game
        removeKDigits();
    }



    // Fill the diagonal SRN number of SRN x SRN matrices
    private void fillDiagonal() {

        for (int i = 0; i < sudokuLen; i = i + SRN)

            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }

    // Returns false if given 3 x 3 block contains num.
    private boolean unUsedInBox(int rowStart, int colStart, int num) {
        for (int i = 0; i < SRN; i++)
            for (int j = 0; j < SRN; j++)
                if (table[rowStart + i][colStart + j] == num)
                    return false;

        return true;
    }

    // Fill a 3 x 3 matrix.
    private void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < SRN; i++) {
            for (int j = 0; j < SRN; j++) {
                do {
                    num = randomGenerator(sudokuLen);
                }
                while (!unUsedInBox(row, col, num));

                table[row + i][col + j] = num;
            }
        }
    }

    // Random generator
    private int randomGenerator(int num) {
        return (int) Math.floor((Math.random() * num + 1));
    }

    // Check if safe to put in cell
    private boolean CheckIfSafe(int i, int j, int num) {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i - i % SRN, j - j % SRN, num));
    }

    // check in the row for existence
    private boolean unUsedInRow(int i, int num) {
        for (int j = 0; j < sudokuLen; j++)
            if (table[i][j] == num)
                return false;
        return true;
    }

    // check in the row for existence
    private boolean unUsedInCol(int j, int num) {
        for (int i = 0; i < sudokuLen; i++)
            if (table[i][j] == num)
                return false;
        return true;
    }

    // A recursive function to fill remaining
    // matrix
    private boolean fillRemaining(int i, int j) {
        //  System.out.println(i+" "+j);
        if (j >= sudokuLen && i < sudokuLen - 1) {
            i = i + 1;
            j = 0;
        }
        if (i >= sudokuLen && j >= sudokuLen)
            return true;

        if (i < SRN) {
            if (j < SRN)
                j = SRN;
        } else if (i < sudokuLen - SRN) {
            if (j == (int) (i / SRN) * SRN)
                j = j + SRN;
        } else {
            if (j == sudokuLen - SRN) {
                i = i + 1;
                j = 0;
                if (i >= sudokuLen)
                    return true;
            }
        }

        for (int num = 1; num <= sudokuLen; num++) {
            if (CheckIfSafe(i, j, num)) {
                table[i][j] = num;
                if (fillRemaining(i, j + 1))
                    return true;

                table[i][j] = 0;
            }
        }
        return false;
    }

    // Remove the K no. of digits to
    // complete game
    private void removeKDigits() {
        int notFound = 0;
        int count = digitsToFill;
        while (count != 0) {
            int cellId = randomGenerator(sudokuLen * sudokuLen) - 1;

            // System.out.println(cellId);
            // extract coordinates i  and j
            int i = (cellId / sudokuLen);
            int j = cellId % 9;

            // System.out.println(i+" "+j);
            if (table[i][j] != 0) {
                count--;
                table[i][j] = 0;
            } else notFound++;

            if (notFound == 1000){
                notFound=0;
                count--;
                removeNextDigit();
            }

        }
    }

    private void removeNextDigit() {
        for (int i = 0; i < sudokuLen ; i++) {
            for (int j = 0; j < sudokuLen; j++) {
                if (table[i][j] != 0){
                    table[i][j] = 0;
                    return;
                }
            }
        }
        throw new IllegalStateException();
    }

    // Print sudoku
    public void printSudoku() {
        for (int i = 0; i < sudokuLen; i++) {
            for (int j = 0; j < sudokuLen; j++)
                System.out.print(table[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

}
