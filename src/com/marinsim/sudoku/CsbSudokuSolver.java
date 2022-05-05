package com.marinsim.sudoku;

public class CsbSudokuSolver implements SudokuSolver {
    private CSbSudokuVarible table[][];
    private static long notesVisited;


    public CsbSudokuSolver(SudokuTable tableToSolve) {
        notesVisited = 0;
        table = new CSbSudokuVarible[tableToSolve.table.length][tableToSolve.table.length];

        for (int i = 0; i < tableToSolve.table.length; i++) {
            for (int j = 0; j < tableToSolve.table[i].length; j++) {
                table[i][j] = new CSbSudokuVarible(i, j, tableToSolve.table[i][j]);
            }
        }
    }

    public CsbSudokuSolver(CsbSudokuSolver copy) {
        table = new CSbSudokuVarible[copy.table.length][copy.table.length];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                var val = copy.table[i][j];
                table[i][j] = new CSbSudokuVarible(val);
            }
        }
    }

    @Override
    public boolean solve(SudokuSolution solution) {
        notesVisited++;


        if (!reduceTable()) {
            return false;
        }

        // choose moust restricte val
        // coul be done better
        var moustRestrictedVar = getMostRestrictedVariable();

        if (moustRestrictedVar == null) {
            solution.saveSolution(table);
            return true;
        }


        for (var val : moustRestrictedVar.getDomain()) {
            var newSolver = new CsbSudokuSolver(this);
            CSbSudokuVarible newVal = new CSbSudokuVarible(moustRestrictedVar.x, moustRestrictedVar.y, val);
            newSolver.table[moustRestrictedVar.x][moustRestrictedVar.y] = newVal;
            if (newSolver.solve(solution)) {
                return true;
            }
        }

        return false;

    }


    @Override
    public long getNotesVisited() {
        return notesVisited;
    }

    public void printTable() {
        for (var row : table) {
            for (var tableElement : row) {
                var val = tableElement.getRestrictedVal();
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }


    /**
     * makes domains of table elemnts smaler acoring to sudoku rules and rules which can be deducted from sudoku rules
     * could be done litle bit more eficintly
     * can solve the problem
     *
     * @return true if table can be solved otherwise returns false
     */
    private boolean reduceTable() {
        boolean changed = true;

        while (changed) {
            changed = false;
            // constraint domains from basic sudoku rules
            for (var row : table) {
                for (var tableElement : row) {
                    var restrictedVal = tableElement.getRestrictedVal();
                    if (restrictedVal != 0) {
                        var neighbours = Position.getNeighbours(tableElement.x, tableElement.y);
                        for (var neighbour : neighbours) {
                            if (table[neighbour.x][neighbour.y].getDomain().remove(restrictedVal)) {
                                changed = true;
                            }
                            if (table[neighbour.x][neighbour.y].getDomain().isEmpty()) {
                                return false;
                            }
                        }
                    }
                }
            }

            //constraint domains frot twin rule: If there are two neigbours with domain same domain which
            // size is two => shared neigbours need to have their domains restricted whith thouse two values
            for (var row : table) {
                for (var tableElement : row) {

                    var domain = tableElement.getDomain();

                    if (domain.size() == 2) {

                        var neighbours = Position.getNeighbours(tableElement.x, tableElement.y);

                        for (var neighbour : neighbours) {

                            var neighbourDomain = table[neighbour.x][neighbour.y].getDomain();

                            // if domains are equal
                            if (neighbourDomain.size() == 2 && neighbourDomain.containsAll(domain)) {

                                var sharedNeighbours = Position.getSharedNeighbours(neighbours, Position.getNeighbours(neighbour.x, neighbour.y));

                                for (var sharedNeighbour : sharedNeighbours) {

                                    if (table[sharedNeighbour.x][sharedNeighbour.y].getDomain().removeAll(domain)) {
                                        changed = true;
                                    }

                                    if (table[sharedNeighbour.x][sharedNeighbour.y].getDomain().isEmpty()) {
                                        return false;
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }


    /**
     *
     * @return null if table is solved after reduce table
     */
    private CSbSudokuVarible getMostRestrictedVariable() {
        int smallestSize = Integer.MAX_VALUE;
        CSbSudokuVarible ret = null;
        for (var row : table) {
            for (var el : row) {
                if (el.getDomain().size() > 1 && el.getDomain().size() < smallestSize) {
                    smallestSize = el.getDomain().size();
                    ret = el;
                }
            }
        }
        return ret;
    }

}
