package gui;

import java.awt.*;
import javax.swing.JFrame;
import menu.MenuBar;

public class MainFrame extends JFrame{

    private static MainFrame instance;

    private Canvas canvas;
    private ToolBar toolBar;
    private MenuBar menuBar;
    private final String WINDOW_TITLE = "UML Editor";
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 700;

    private MainFrame() {
        setTitle(WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        initializeFrame();
    }

    public static MainFrame getInstance(){
        if (instance == null) instance = new MainFrame();
        return instance;
    }

    private void initializeFrame(){
        canvas = Canvas.getInstance();
        toolBar = ToolBar.getInstance();
        menuBar = MenuBar.getInstance();

        this.setLayout(new BorderLayout());
        this.setJMenuBar(menuBar);
        this.add(toolBar, BorderLayout.WEST);
        this.add(canvas, BorderLayout.CENTER);
    }
}
