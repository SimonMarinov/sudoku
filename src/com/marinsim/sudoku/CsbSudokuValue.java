package com.marinsim.sudoku;

public class CsbSudokuValue extends CsbSudokuTableVal {
    private final int value;

    protected CsbSudokuValue(int x, int y, int value ) {
        super(x, y);
        this.value = value;
    }

    @Override
    public int getRestrictedVal() {
        return value;
    }

    @Override
    public boolean eraseFromDomain(Integer erase) {
        return true;
    }

    @Override
    public void insertIntoDomain(Integer insert) {

    }
}
