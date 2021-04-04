package solver;

import java.util.List;

public class Sudoku {
    private List<Integer> sudoku;

    public int size() {
        return sudoku.size();
    }

    public Sudoku(List<Integer> sudoku) {
        this.sudoku = sudoku;
    }

    public List<Integer> getSudoku() {
        return sudoku;
    }

    public void setFigure(int ind, int num){
        sudoku.set(ind, num);
    }
}
