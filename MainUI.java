import javax.swing.*;

public class MainUI extends JFrame {


    public MainUI(String title) {
        super(title);

        MyPanel pnlMain = new MyPanel();
        this.add(pnlMain);

        this.setSize(1200, 675);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        MainUI runGame = new MainUI("Pikachu");
    }
}

