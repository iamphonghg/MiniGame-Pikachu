import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class QItem {
    int row;
    int col;
    int direc;
    int dist;
    public QItem(int row, int col, int direc, int dist)
    {
        this.row = row;
        this.col = col;
        this.direc = direc;
        this.dist = dist;
    }
}

public class Rook {
    static final int LEFT = 1;
    static final int RIGHT = 2;
    static final int UP = 3;
    static final int DOWN = 4;
    public static int minDistance(char[][] grid) {
        int sourceRow = 0, sourceCol = 0;
        firstLoop:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 's') {
                    sourceRow = i;
                    sourceCol = j;
                    break firstLoop;
                }
            }
        }

        Queue<QItem> queue = new LinkedList<>();
        ArrayList<String> visited = new ArrayList<>();
        queue.add(new QItem(sourceRow, sourceCol + 1, RIGHT, 1));
        queue.add(new QItem(sourceRow, sourceCol - 1, LEFT, 1));
        queue.add(new QItem(sourceRow + 1, sourceCol, DOWN, 1));
        queue.add(new QItem(sourceRow - 1, sourceCol, UP, 1));

        while (!queue.isEmpty()) {
            QItem p = queue.remove();
            if (visited.contains(p.row + "" + p.col + "" + p.direc)) {
                continue;
            }
            visited.add(p.row + "" + p.col + "" + p.direc);
            int temp = 0;
            if (p.direc == LEFT) {
                temp = p.col;
                while (temp >= 0 && isValid(p.row, temp, grid)) {
                    if (grid[p.row][temp] == 'g') {
                        return p.dist;
                    }
                    if (p.row - 1 >= 0) {
                        queue.add(new QItem(p.row - 1, temp, UP, p.dist + 1));
                    }
                    if (p.row + 1< grid.length) {
                        queue.add(new QItem(p.row + 1, temp, DOWN, p.dist + 1));
                    }
                    temp--;
                }
            }
            if (p.direc == RIGHT) {
                temp = p.col;
                while (temp < grid[0].length && isValid(p.row, temp, grid)) {
                    if (grid[p.row][temp] == 'g') {
                        return p.dist;
                    }
                    if (p.row - 1 >= 0) {
                        queue.add(new QItem(p.row - 1, temp, UP, p.dist + 1));
                    }
                    if (p.row + 1 < grid.length) {
                        queue.add(new QItem(p.row + 1, temp, DOWN, p.dist + 1));
                    }
                    temp++;
                }
            }
            if (p.direc == UP) {
                temp = p.row;
                while (temp >= 0 && isValid(temp, p.col, grid)) {
                    if (grid[temp][p.col] == 'g') {
                        return p.dist;
                    }
                    if (p.col - 1 >= 0) {
                        queue.add(new QItem(temp, p.col - 1, LEFT, p.dist + 1));
                    }
                    if (p.col + 1 < grid[0].length) {
                        queue.add(new QItem(temp, p.col + 1, RIGHT, p.dist + 1));
                    }
                    temp--;
                }
            }
            if (p.direc == DOWN) {
                temp = p.row;
                while (temp <= grid.length - 1 && isValid(temp, p.col, grid)) {
                    if (grid[temp][p.col] == 'g') {
                        return p.dist;
                    }
                    if (p.col - 1>= 0) {
                        queue.add(new QItem(temp, p.col - 1, LEFT, p.dist + 1));
                    }
                    if (p.col + 1 < grid[0].length) {
                        queue.add(new QItem(temp, p.col + 1, RIGHT, p.dist + 1));
                    }
                    temp++;
                }
            }
        }
        return -1;
    }

    private static boolean isValid(int x, int y, char[][] grid) {
        return (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] != '#');
    }

//    public static void main(String[] args) {
//        String[] lines = {"..................",
//                "...s.#####.######.",
//                ".#.#######g######.",
//                ".################.",
//                ".################.",
//                ".################.",
//                ".################.",
//                ".################.",
//                ".################.",
//                ".################.",
//                ".................."};
//        char[][] input = new char[lines.length][lines[1].length()];
//        for (int i = 0; i < lines.length; i++) {
//            String[] temp = lines[i].split("");
//            for (int j = 0; j < lines[1].length(); j++) {
//                input[i][j] = temp[j].charAt(0);
//            }
//        }
//    }
}
