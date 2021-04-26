import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

class SelectedPoint {
    private int x;
    private int y;
    public SelectedPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}

public class MyPanel extends JPanel implements ActionListener {

    private SelectedPoint firstPoint = null;
    private SelectedPoint secondPoint = null;
    private JButton[][] btn;
    private char[][] tempMatrix;
    private int[][] matrix;
    public MyPanel() {
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(9, 16, 2, 2));
        this.setPreferredSize(new Dimension(832, 468));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setAlignmentY(JPanel.CENTER_ALIGNMENT);

        btn = new JButton[9][16];
        matrix = getMatrix();
        tempMatrix = new char[11][18];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 18; j++) {
                if (i == 0) {
                    tempMatrix[i][j] = '.';
                } else if (i == 10) {
                    tempMatrix[i][j] = '.';
                } else if (j == 0) {
                    tempMatrix[i][j] = '.';
                } else if (j == 17) {
                    tempMatrix[i][j] = '.';
                } else {
                    tempMatrix[i][j] = '#';
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 16; j++) {
                btn[i][j] = new JButton();
                btn[i][j].setActionCommand(i + " " + j);
                btn[i][j].setBorder(null);
                Image image = new ImageIcon(getClass().getResource(
                        "/icon/" + matrix[i + 1][j + 1] + ".png")).getImage();
                btn[i][j].setIcon(new ImageIcon(image.getScaledInstance(48, 48, Image.SCALE_SMOOTH)));
                btn[i][j].addActionListener(this);
                this.add(btn[i][j]);
            }
        }
    }



    public int[][] getMatrix() {
        Random random = new Random();
        int[][] matrix = new int[11][18];
        ArrayList<String> arrayPosition = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 17; j++) {
                arrayPosition.add(i + " " + j);
            }
        }
        while (arrayPosition.size() > 1) {
            int res = random.nextInt(21) + 1;
            int position = random.nextInt(arrayPosition.size());
            String[] coordinate = arrayPosition.get(position).split(" ");
            matrix[Integer.parseInt(coordinate[0])][Integer.parseInt(coordinate[1])] = res;
            arrayPosition.remove(position);

            position = random.nextInt(arrayPosition.size());
            coordinate = arrayPosition.get(position).split(" ");
            matrix[Integer.parseInt(coordinate[0])][Integer.parseInt(coordinate[1])] = res;
            arrayPosition.remove(position);
        }
        return matrix;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] position = e.getActionCommand().split(" ");
        int x = Integer.parseInt(position[0]);
        int y = Integer.parseInt(position[1]);
        if (firstPoint == null) {
            firstPoint = new SelectedPoint(x, y);
            btn[x][y].setBorder(new LineBorder(Color.RED));
        } else {
            secondPoint = new SelectedPoint(x, y);
            tempMatrix[firstPoint.getX() + 1][firstPoint.getY() + 1] = 's';
            tempMatrix[secondPoint.getX() + 1][secondPoint.getY() + 1] = 'g';
            int result = Rook.minDistance(tempMatrix);
            boolean matchOrNot = result <= 3 && result != -1;
            if (matrix[firstPoint.getX() + 1][firstPoint.getY() + 1] == matrix[secondPoint.getX() + 1][secondPoint.getY() + 1] && matchOrNot) {
                clearButton(firstPoint);
                clearButton(secondPoint);
                System.out.println("true " + result);
                for (int i = 0; i < 11; i++) {
                    for (int j = 0; j < 18; j++) {
                        System.out.print(tempMatrix[i][j]);
                    }
                    System.out.println();
                }
            } else {
                tempMatrix[firstPoint.getX() + 1][firstPoint.getY() + 1] = '#';
                tempMatrix[secondPoint.getX() + 1][secondPoint.getY() + 1] = '#';
                btn[firstPoint.getX()][firstPoint.getY()].setBorder(null);
                System.out.println("false " + result);
                for (int i = 0; i < 11; i++) {
                    for (int j = 0; j < 18; j++) {
                        System.out.print(tempMatrix[i][j]);
                    }
                    System.out.println();
                }
            }

            firstPoint = null;
            secondPoint = null;
        }
    }

    public void clearButton(SelectedPoint point) {
        tempMatrix[point.getX() + 1][point.getY() + 1] = '.';
        btn[point.getX()][point.getY()].setBorder(null);
        btn[point.getX()][point.getY()].setIcon(null);
        btn[point.getX()][point.getY()].setBackground(Color.WHITE);
        btn[point.getX()][point.getY()].setEnabled(false);
    }


}
