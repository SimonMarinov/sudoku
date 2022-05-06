package com.marinsim.sudoku;

public class Test {
    private static int MAX_GEN = 72;
    private static int NOF_SOLVERS = 3; // copied code for generating sudoku table is very slow with big number of cels to

    public static void main(String[] args) {

        SudokuSolver solvers[] = new SudokuSolver[NOF_SOLVERS];
        SudokuSolution solutions[] = new SudokuSolution[NOF_SOLVERS];
        for (int i = 0; i < solutions.length; i++) {
            solutions[i] = new SudokuSolution();
        }
        int start = 1;

        boolean solved[] = new boolean[NOF_SOLVERS];

        // solvable
        for (int n = 0; n < 100; n++) {
            for (int j = 0; j < 82; j++) {
                var sudokuGen = new RandomSudokuGen(j);
                SudokuTable tableToSolve = new SudokuTable(sudokuGen.randFulfillableTable());


                solvers[0] = new NaiveSudokuSolver(tableToSolve);
                solvers[1] = new BacktrakingSudokuSolver(tableToSolve);
                solvers[2] = new CsbSudokuSolver(tableToSolve);

                if (j < 8) {
                    start = 0;
                } else start = 1;

                System.out.println();
                sudokuGen.printSudoku();
                System.out.println();


                for (int s = start; s < solvers.length; s++) {
                    solved[s] = solvers[s].solve(solutions[s]);
                }

                boolean sol = solved[start];
                for (int i = start; i < solutions.length; i++) {
                   //if (!solved[i] && j < MAX_GEN ) throw new IllegalStateException();
                    if (sol != solved[i]) {
                        throw new IllegalStateException();
                    }
                    if (solved[i] != solutions[i].isSolutionOk()) {
                        throw new IllegalStateException();
                    }
                }


                System.out.println("number of cells to fill =" + j);
                if (start== 0) {
                    System.out.println("Naive solutions notes visited      :" + solvers[0].getNotesVisited());
                }
                System.out.println("Backtraikng solutions notes visited:" + solvers[1].getNotesVisited());
                System.out.println("Csp solutions notes visited        :" + solvers[2].getNotesVisited());
                System.out.println();

            }
        }

        System.out.println("RANDOM SUDOKU TO SOLVE");

        //rand sudkou
        for (int n = 0; n < 100; n++) {
            for (int j = 0; j < 81; j++) {
                var sudokuGen = new RandomSudokuGen(j);
                SudokuTable tableToSolve = new SudokuTable(sudokuGen.randTable());


                solvers[0] = new NaiveSudokuSolver(tableToSolve);
                solvers[1] = new BacktrakingSudokuSolver(tableToSolve);
                solvers[2] = new CsbSudokuSolver(tableToSolve);

                if (j < 8) {
                    start = 0;
                } else start = 1;

                System.out.println();
                sudokuGen.printSudoku();
                System.out.println();


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


                System.out.println("number of cells to fill =" + j);
                if (start== 0) {
                    System.out.println("Naive solutions notes visited      :" + solvers[0].getNotesVisited());
                }
                System.out.println("Backtraikng solutions notes visited:" + solvers[1].getNotesVisited());
                System.out.println("Csp solutions notes visited        :" + solvers[2].getNotesVisited());
                System.out.println();
            }
        }
    }

}
