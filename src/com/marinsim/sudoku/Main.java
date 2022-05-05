package com.marinsim.sudoku;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter table:");
        SudokuTable table = new SudokuTable();


        try {
            table.loadClasicTable();
            System.out.println();
            System.out.println("Do you want to use Naive solution this may take a long time ? Y/N");
            char c = 0;
            while (c != 'Y' && c != 'N'){
                c = (char) System.in.read();
            }

            int start = 0;
            if (c == 'Y'){
                start = 0;
            } else start = 2;

            SudokuSolver solvers[] = new SudokuSolver[3];
            SudokuSolution solutions[] = new SudokuSolution[3];
            for (int i = 0; i < solutions.length; i++) {
                solutions[i] = new SudokuSolution();
            }

            boolean solved[] = new boolean[3];

            solvers[0] = new NaiveSudokuSolver(table);
            solvers[1] = new BacktrakingSudokuSolver(table);
            solvers[2] = new CsbSudokuSolver(table);


//            for (int i = 0; i < solvers.length; i++) {
//                int finalI = i;
//                threads.add(new Thread(() -> {
//                    solved[finalI] = solvers[finalI].solve(solutions[finalI]);
//                }));
//            }
//
//            for (int i = 0; i < threads.size(); i++) {
//                try {
//                    threads.get(i).join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

            for (int s = start; s < solvers.length; s++) {
                solved[s] = solvers[s].solve(solutions[s]);
            }



            boolean sol = solved[start];
            for (int i = start; i < solutions.length; i++) {
                if (sol != solved[i]) {
                    throw new IllegalStateException();
                }
                if (solved[i] != solutions[i].isSolutionOk()) {
                    throw new IllegalStateException();
                }
            }

            if (start== 0) {
                System.out.println("Naive solutions notes visited:" + solvers[0].getNotesVisited());
            }
            System.out.println("Backtraikng solutions notes visited:" + solvers[1].getNotesVisited());
            System.out.println("Csp solutions notes visited:" + solvers[2].getNotesVisited());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
