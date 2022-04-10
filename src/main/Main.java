package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
//      P1: This lets the window close properly when user clicks the close ("x") button.
        window.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        window.setResizable(false);
        window.setTitle("Legend of Normie");
//      P1: is set to null so that window is centered of the screen.
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}
