package gui;

import objects.Port;
import objects.ShapeAbstract;
import objects.LinkAbstract;
import objects.BasicAbstract;
import objects.Composite;
import draw.BasicDrawInterface;
import draw.LinkDrawInterface;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener{
    private BasicDrawInterface drawBasic = null;
    private LinkDrawInterface drawLink = null;
    public ArrayList<ShapeAbstract> shapeList;
    public ArrayList<LinkAbstract> lineList;
    private int currentDepth = 0;
    private Port startPort = null;
    private Point lastMousePoint;
    private BasicAbstract draggingObj;
    private CanvasListener cListener;

    // constructor
    public Canvas() {
        shapeList = new ArrayList<>();
        lineList = new ArrayList<>();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    // mouse action
    @Override
    public void mousePressed(MouseEvent e){
        // A. create basic object
        if (drawBasic != null) {
            // call Interface to create Rect or Oval
            BasicAbstract newObj = drawBasic.createBasic(e.getX(), e.getY(), currentDepth++);
            shapeList.add(newObj);
            
            // 操作結束，跳回 Select 模式
            resetToSelectMode();
        } 
        // B. create link object
        else if (drawLink != null) {
            // chech if mouse is at a port
            startPort = findPortAt(e.getPoint());
        } 
        // C. select object
        else if(drawBasic == null && drawLink == null){
            selectObjectAt(e.getPoint());
            ShapeAbstract top = shapeList.get(shapeList.size() - 1);
            if (((BasicAbstract) top).getIsSelected()) {
                draggingObj = (BasicAbstract) top;
                lastMousePoint = e.getPoint(); // 紀錄起點
            }
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (draggingObj != null && lastMousePoint != null) {
            int dx = e.getX() - lastMousePoint.x;
            int dy = e.getY() - lastMousePoint.y;

            // update coordinate of object
            draggingObj.setX(draggingObj.getX() + dx);
            draggingObj.setY(draggingObj.getY() + dy);

            lastMousePoint = e.getPoint();
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e){
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // create link
        if (drawLink != null && startPort != null) {
            // find if mouse released at a port
            Port endPort = findPortAt(e.getPoint());

            // check if it is a valid link
            if (endPort != null && endPort != startPort) {
                if (startPort.getOwner() != endPort.getOwner()) {
                    
                    // call interface to create link object
                    LinkAbstract newLink = drawLink.createLink(startPort, endPort);

                    lineList.add(newLink);
                    System.out.println("Connection Created!");
                }
            }
            startPort = null;
            
            // 操作結束，跳回 Select 模式
            resetToSelectMode();
        }

        // 處理移動模式的收尾 (Use Case E)
        if (draggingObj != null) {
            // 清除正在拖拽的物件參照
            draggingObj = null;
            lastMousePoint = null;
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }

    // called by buttons for drawing setting
    public void setBasicDraw(BasicDrawInterface strategy) {
        this.drawBasic = strategy;
        System.out.println("Mode Switched: Basic Object Creation");
    }

    public void setLinkDraw(LinkDrawInterface strategy) {
        this.drawLink = strategy;
        System.out.println("Mode Switched: Link Connection");
    }

    private Port findPortAt(Point p) {
        // start from the last added object
        for (int i = shapeList.size() - 1; i >= 0; i--) {
            ShapeAbstract s = shapeList.get(i);
            BasicAbstract obj = (BasicAbstract) s;
            // check if mouse is in a port
            for (Port port : obj.getPorts()) {
                if (port.isInside(p)) {
                    return port;
                }
            }
        }
        return null;
    }

    private void selectObjectAt(Point p) {
        // 1. 先將所有物件設為未選取
        for (ShapeAbstract s : shapeList) {
            s.setIsSelected(false);
        }

        // 2. 從最後面 (視覺最上層) 開始找點中了誰
        for (int i = shapeList.size() - 1; i >= 0; i--) {
            ShapeAbstract s = shapeList.get(i);          
            if (s.isInside(p)) {
                s.setIsSelected(true);

                // 3. 置頂邏輯：將選中的物件移到 List 的最後面
                shapeList.remove(i);
                shapeList.add(s);
                break; 
            }
        }
        repaint();
    }

    public void setCanvasListener(CanvasListener l){
        this.cListener = l;
    }

    private void resetToSelectMode() {
        this.drawBasic = null;
        this.drawLink = null;
        if(cListener != null){
            cListener.onActionCompleted();;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (ShapeAbstract s : shapeList) {
            s.draw(g);
        }
    }

    public void groupObjects() {
        List<ShapeAbstract> selectedItems = new ArrayList<>();
        
        // 1. find selected objects
        for (int i = shapeList.size() - 1; i >= 0; i--) {
            ShapeAbstract s = shapeList.get(i);
            if (s.getIsSelected()) {
                selectedItems.add(s);
                shapeList.remove(i);
            }
        }

        // 2. selected objects > 1
        if (selectedItems.size() > 1) {
            Composite group = new Composite();
            for (ShapeAbstract s : selectedItems) {
                s.setIsSelected(false);
                group.addMember(s);
            }
            group.setIsSelected(true);
            shapeList.add(group);
        }
        else {
            shapeList.addAll(selectedItems);
        }
        repaint();
    }

    public void ungroupObjects() {
        // find group object
        for (int i = shapeList.size() - 1; i >= 0; i--) {
            ShapeAbstract s = shapeList.get(i);
            if (s.getIsSelected() && s instanceof Composite) {
                Composite group = (Composite) s;
                shapeList.remove(i);
                for (ShapeAbstract member : group.getMembers()) {
                    member.setIsSelected(true);
                    shapeList.add(member);
                }
                break;
            }
        }
        repaint();
    }

    public ShapeAbstract getSelectedObject() {
        ShapeAbstract selected = null;
        int count = 0;
        for (ShapeAbstract s : shapeList) {
            if (s.getIsSelected()) {
                selected = s;
                count++;
            }
        }
        return (count == 1) ? selected : null;
    }
}