package com.marinsim.sudoku;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class CSbSudokuVarible {
    public final int x;
    public final int y;
    private Set<Integer> domain;

    public CSbSudokuVarible(int x, int y, int val) {
        this.x = x;
        this.y = y;

        if (val == 0){
            domain = getDefaultDomain();
        } else {
            domain = new TreeSet<>() {{
                add(val);
            }};
        }
    }

    public CSbSudokuVarible(CSbSudokuVarible copy) {
        this.x = copy.x;
        this.y = copy.y;
        domain = new TreeSet<>(copy.domain);
    }

    public Set<Integer> getDomain() {
        return domain;
    }

    /**
     *
     * @return 0 if domain size is bigger than 1 that means that domain doenst have restricitve value
     */
    public int getRestrictedVal() {
        if (domain.size() == 1){
            return domain.iterator().next();
        }
        else return 0;
    }

    private Set<Integer >getDefaultDomain(){
        return new TreeSet<>(){{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
            add(9);
        }};
    }


}
