package poker.GUI;

import java.awt.*;
import javax.swing.*;

public class ClientGUI {

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static void main(String[] args) {


        LoginGUI loginWindow = new LoginGUI();
        loginWindow.setLocation(screenSize.width / 2 - loginWindow.getSize().width / 2, screenSize.height / 2 - loginWindow.getSize().height / 2);
        loginWindow.setResizable(false);
        loginWindow.setVisible(true);
        loginWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


}