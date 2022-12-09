import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            int[][] trees = scanRows();
            System.out.println(visibleTrees(trees));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static int[][] scanRows() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("input.txt");
        Scanner scan = new Scanner(fis);
        int trees[][] = new int[99][99];
        String[] rows = new String[99];
        for (int i = 0; i < 99; i++) {
            rows[i] = scan.nextLine();
        }
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[0].length(); j++) {
                int height = Integer.parseInt(String.valueOf(rows[i].charAt(j)));
                trees[i][j] = height;
            }
        }
        return trees;
    }

    public static int visibleTrees(int[][] trees) {
        int scenicScore = 0;
        for (int i = 0; i < trees.length; i++) {
            for (int j = 0; j < trees[0].length; j++) {
                int scoreRight = visibleRight(trees, i, j);
                int scoreLeft = visibleLeft(trees, i, j);
                int scoreUp = visibleUp(trees, i, j);
                int scoreDown = visibleDown(trees, i, j);
                int thisTree = scoreRight * scoreLeft * scoreUp * scoreDown;
                if (thisTree > scenicScore) {
                    scenicScore = thisTree;
                }
            }
        }

        return scenicScore;
    }

    public static int visibleRight(int[][] trees, int i, int j) {
        int view = 0;
        for (int k = j + 1; k < trees[0].length; k++) {
            view++;
            if (trees[i][j] <= trees[i][k]) {
                return view;
            }
        }
        return view;
    }
    public static int visibleLeft(int[][] trees, int i, int j) {
        int view = 0;
        for (int k = j - 1; k >= 0; k--) {
            view++;
            if (trees[i][j] == trees[i][k]) {
                return view;
            }
            if (trees[i][j] < trees[i][k]) {
                return view;
            }
        }
        return view;
    }
    public static int visibleUp(int[][] trees, int i, int j) {
        int view = 0;
        for (int k = i - 1; k >= 0; k--) {
            view ++;
            if (trees[i][j] <= trees[k][j]) {
                return view;
            }
        }
        return view;
    }
    public static int visibleDown(int[][] trees, int i, int j) {
        int view = 0;
        for (int k = i + 1; k < trees.length; k++) {
            view++;
            if (trees[i][j] <= trees[k][j]) {
                return view;
            }
        }
        return view;
    }
}