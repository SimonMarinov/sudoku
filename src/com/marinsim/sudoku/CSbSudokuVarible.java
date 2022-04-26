package com.marinsim.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CSbSudokuVarible extends CsbSudokuTableVal {
    private int x;
    private int y;
    private Set<Integer> domain;

    protected CSbSudokuVarible(int x, int y, Set<Integer> domain) {
        super(x, y);
        this.domain = domain;
    }


    public final Set<Integer> getDomain() {
        return domain;
    }

    @Override
    public int getRestrictedVal() {
        return 0;
    }

    /**
     * @param erase element to erase
     * @return false if domain is empty after
     */
    @Override
    public boolean eraseFromDomain(Integer erase) {
        domain.remove(erase);
        return !domain.isEmpty();
    }

    @Override
    public void insertIntoDomain(Integer insert) {
        domain.add(insert);
    }
}
