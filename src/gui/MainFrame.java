package gui;

import java.awt.*;
import javax.swing.JFrame;
import menu.MenuBar;

public class MainFrame extends JFrame{
    private Canvas canvas;
    private ToolBar toolBar;
    private MenuBar menuBar;
    private final String WINDOW_TITLE = "UML Editor";
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 700;

    public MainFrame() {
        setTitle(WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        initializeFrame();
    }

    private void initializeFrame(){
        canvas = new Canvas();
        toolBar = new ToolBar(canvas);
        menuBar = new MenuBar(canvas);

        canvas.setCanvasListener(new CanvasListener() {
            @Override
            public void onActionCompleted(){
                toolBar.selectDefaultButton();
            }
        });

        this.setLayout(new BorderLayout());
        this.setJMenuBar(menuBar);
        this.add(toolBar, BorderLayout.WEST);
        this.add(canvas, BorderLayout.CENTER);
    }
}
