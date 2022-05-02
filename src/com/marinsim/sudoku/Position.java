package com.marinsim.sudoku;

import com.sun.jdi.connect.IllegalConnectorArgumentsException;

import java.util.ArrayList;
import java.util.List;

public class Position {
    public final int x;
    public final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static List<Position> getSharedNeighbours(List<Position> lfs, List<Position> rhs) {
        List<Position> res = new ArrayList<>();

        for (var el : lfs) {
            for (var el2 : rhs) {
                if (el.x == el2.x && el.y == el2.y) {
                    res.add(el);
                }
            }
        }
        return res;
    }

    public static List<Position> getSharedNeighbours(Position pos, Position pos2) {
        List<Position> res = new ArrayList<>();

        for (var el : getNeighbours(pos.x, pos.y)) {
            for (var el2 : getNeighbours(pos2.x, pos.y)) {
                if (el.x == el2.x && el.y == el2.y) {
                    res.add(el);
                }
            }
        }
        return res;
    }


    public static List<Position> getNeighbours(int x, int y) {
        List<Position> res = new ArrayList<>(22);

        for (int i = 0; i < 9; i++) {
            if (i == y) continue;
            res.add(new Position(x, i));
        }

        for (int i = 0; i < 9; i++) {
            if (i == x) continue;
            res.add(new Position(i, y));
        }

        var x0 = (x / 3) * 3;
        var y0 = (y / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((i == x % 3) || (j == y % 3)) continue;
                res.add(new Position(x0 + i, y0 + j));
            }
        }

        if (res.size() != 22) throw new IllegalStateException("kket neznas robci");
        return res;
    }

    public List<Position> getNeighbours() {
        List<Position> res = new ArrayList<>(22);

        for (int i = 0; i < 9; i++) {
            if (i == y) continue;
            res.add(new Position(x, i));
        }

        for (int i = 0; i < 9; i++) {
            if (i == x) continue;
            res.add(new Position(i, y));
        }

        var x0 = (x / 3) * 3;
        var y0 = (y / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((i == x % 3) || (j == y % 3)) continue;
                res.add(new Position(x0 + i, y0 + j));
            }
        }

        if (res.size() != 22) throw new IllegalStateException("kket neznas robci");
        return res;
    }

}
