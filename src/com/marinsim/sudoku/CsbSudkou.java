package com.marinsim.sudoku;

import com.sun.jdi.connect.IllegalConnectorArgumentsException;

import java.util.ArrayList;
import java.util.List;

public class CsbSudkou {
    private CsbSudokuTableVal table[][] = new CsbSudokuTableVal[][];

    public CsbSudkou(SudokuTable tableToSolve) {

    }

    boolean solve() {
        while (true) {
            var varibleWithSmallestDomain = getVarWithSmallestDomain();
            if (varibleWithSmallestDomain == null) {
                break;
            }
            for (var posibleVal : varibleWithSmallestDomain.getDomain()) {
                var x = varibleWithSmallestDomain.getX();
                var y =varibleWithSmallestDomain.getY();
                if (exactFromNeighboursDomains(x,y,posibleVal)){

                } else

            }
        }
    }

    /**
     * implements forward chaining if retruns false if any varible has empty domain
     * @param x
     * @param y
     * @param valToExtract
     * @return
     */
    private boolean exactFromNeighboursDomains(int x, int y, int valToExtract) {
        var neighbours = getNeighbours(x,y);
        for (var neighbour: neighbours) {
            if (!neighbour.eraseFromDomain(valToExtract)){
                return false;
            }
        }
        return true;
    }

    private List<CsbSudokuTableVal> getNeighbours(int x, int y) {
        List<CsbSudokuTableVal> res = new ArrayList<>(22);
        for (int i = 0; i < 9; i++) {
            res.add(table[x][i]);
        }

        for (int i = 0; i < 9; i++) {
            if (i == x) continue;
            res.add(table[i][y]);
        }
        var x0 = (x/3)*3;
        var y0 = (y/3)*3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((i == x%3) || (j == y%3)) continue;
                res.add(table[x0+i][y0+j]);
            }
        }
        if (res.size()=!22) throw new IllegalConnectorArgumentsException("kket neznas robci");
        return res;
    }

    private CSbSudokuVarible getVarWithSmallestDomain() {
        int smallestSize = Integer.MAX_VALUE;
        CSbSudokuVarible ret = null;
        for (var row : table) {
            for (var el : row) {
                if (el instanceof CSbSudokuVarible) {
                    if (((CSbSudokuVarible) el).getDomain().size() < smallestSize) {
                        smallestSize = ((CSbSudokuVarible) el).getDomain().size();
                        ret = ((CSbSudokuVarible) el);
                    }
                }
            }
        }
        return ret;
    }

    private boolean isSolved() {
        for (var row : table) {
            for (var el : row) {
                if (el instanceof CSbSudokuVarible) {
                    return false;
                }
            }
        }
        return true;
    }


}
