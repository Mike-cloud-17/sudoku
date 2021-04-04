package solver;

import algoritms.EnumerationAlg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solver {
    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>();
        List<Integer> sudoku = new ArrayList<>();
        try {
            data = readData(args);
        } catch (IllegalArgumentException ex) {
            System.out.println();
            System.out.println(ex.getMessage());
            System.out.println("The program has finished. To start again you have to rerun it with right parameters.");
            System.exit(1);
        }
        sudoku = data.subList(0, data.size() - 1);
        int version = data.get(data.size() - 1);
        solve(sudoku, version);
    }

    public static List<Integer> readData(String[] args) {
        System.out.println("Input 81 sudoku values divided by gap and 1 in the end to get a full solution " +
                "or 2 for the next step.");
        List<String> listData = new ArrayList<>(0);
        listData.addAll(Arrays.asList(args));
        if (args.length != 82 || !tryParseIntChecked(listData))
            throw new IllegalArgumentException("You have to input 81 integer numbers divided by gap.");
        List<Integer> listIntData = new ArrayList<>(0);
        for (int i = 0; i < 82; ++i) {
            listIntData.add(Integer.parseInt(listData.get(i)));
        }
        return listIntData;
    }

    public static boolean tryParseIntChecked(List<String> list) {
        try {
            for (String s : list) {
                Integer.parseInt(s);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void solve(List<Integer> data, int version) {
        Sudoku sudoku = new Sudoku(data);
        EnumerationAlg enAlg = new EnumerationAlg(sudoku);
        int[][] solvedSudoku = enAlg.getSudoku();
        if (!correctnessCheck(solvedSudoku))
            System.out.println("Incorrect sudoku was entered.");
        else {
            if (version == 1)
                enAlg.printSudoku(solvedSudoku);
            else {
                for (int i = 0; i < sudoku.size(); ++i) {
                    if (sudoku.getSudoku().get(i) == 0) {
                        sudoku.setFigure(i, solvedSudoku[i / 9][i % 9]);
                        break;
                    }
                }
                enAlg.printSudoku(enAlg.makeMatrixOfList(sudoku));
            }
        }
    }

    public static boolean correctnessCheck(int[][] sudoku) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (sudoku[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
}
