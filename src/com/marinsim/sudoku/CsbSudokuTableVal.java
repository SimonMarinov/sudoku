package com.marinsim.sudoku;

public abstract class CsbSudokuTableVal {
   private final int x;
   private final int y;

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   protected CsbSudokuTableVal(int x, int y) {
      this.x = x;
      this.y = y;
   }

   abstract public int getRestrictedVal();

   abstract public boolean eraseFromDomain(Integer erase);

   abstract public void  insertIntoDomain(Integer insert);

}
