package sddtc.library.java;

/**
 * Author sddtc
 * Date 16/3/28
 */
public class NQueens {
    private int queensNum = 4;
    private int[] queens = new int[queensNum + 1];
    private boolean[] rowExists = new boolean[queensNum + 1];
    private boolean[] a = new boolean[queensNum * 2];
    private boolean[] b = new boolean[queensNum * 2];

    private void init() {
        for (int i = 0; i < queensNum + 1; i++) {
            rowExists[i] = false;
        }

        for (int j = 0; j < queensNum * 2; j++) {
            a[j] = b[j] = false;
        }
    }

    private boolean isExists(int row, int col) {
        return (rowExists[row] || a[row + col - 1] || b[queensNum + col - row]);
    }

    public void step(int col) {
        for (int row = 1; row < queensNum + 1; row++) {
            if (!isExists(row, col)) {
                queens[col] = row;
                rowExists[row] = a[row + col - 1] = b[queensNum + col - row] = true;

                if (col == queensNum) {
                    for (int column = 1; column <= queensNum; column++) {
                        System.out.print("(" + column + "," + queens[column] + ")");
                    }
                    System.out.println();
                } else {
                    step(col + 1);
                }

                rowExists[row] = a[row + col - 1] = b[queensNum + col - row] = false;
            }
        }
    }

    public static void main(String[] args) {
        NQueens queens = new NQueens();
        queens.init();
        queens.step(1);
    }
}
