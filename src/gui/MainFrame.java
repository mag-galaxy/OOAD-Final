package gui;

import java.awt.*;
import javax.swing.JFrame;
import menu.MenuBar;

public class MainFrame extends JFrame{
    private Canvas canvas;
    private ToolBar toolBar;
    private MenuBar menuBar;

    public MainFrame() {
        // 1. 設定視窗基本屬性
        setTitle("UML Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null); // 視窗置中

        // 2. 實例化核心元件 (順序很重要，因為 ToolBar 需要 Canvas 的引用)
        canvas = new Canvas();
        toolBar = new ToolBar(canvas);
        menuBar = new MenuBar(canvas);

        // 3. 佈局擺放
        // 使用 BorderLayout 是最符合 UML Editor 結構的選擇
        this.setLayout(new BorderLayout());

        // 擺放選單 (JMenuBar 是特殊的，使用 setJMenuBar 而非 add)
        this.setJMenuBar(menuBar);

        // 擺放工具欄 (放在左側)
        this.add(toolBar, BorderLayout.WEST);

        // 擺放畫布 (放在中間，會自動填滿剩餘空間)
        this.add(canvas, BorderLayout.CENTER);
    }
}
