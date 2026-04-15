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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener{
    
    public ArrayList<ShapeAbstract> shapeList;
    public ArrayList<LinkAbstract> lineList;

    private BasicDrawInterface drawBasic = null;
    private LinkDrawInterface drawLink = null;
    private int currentDepth = 0;
    private Port startPort = null;
    private Port resizePort = null;
    private Point selectStart;
    private Point lastMousePoint;
    private ShapeAbstract draggingObj;
    private CanvasListener cListener;
    private boolean showAllPorts = false;
    private Rectangle selectRect;

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

            if(getSelectedObject() == null){
                selectStart = e.getPoint();
                selectRect = new Rectangle(selectStart);
            }

            ShapeAbstract top = shapeList.get(shapeList.size() - 1);
            if (top.getIsSelected()) {
                draggingObj = top;
                lastMousePoint = e.getPoint();
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

        if (selectStart != null) {
            int x = Math.min(e.getX(), selectStart.x);
            int y = Math.min(e.getY(), selectStart.y);
            int width = Math.abs(e.getX() - selectStart.x);
            int height = Math.abs(e.getY() - selectStart.y);
            
            selectRect = new Rectangle(x, y, width, height);
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
            resetToSelectMode();
        }

        if (draggingObj != null) {
            draggingObj = null;
            lastMousePoint = null;
        }

        if (selectRect != null){
            for (ShapeAbstract s : shapeList) {
                Rectangle objRect = new Rectangle(s.getX(), s.getY(), s.getWidth(), s.getHeight());
                if (selectRect.contains(objRect)) {
                    s.setIsSelected(true);
                }
            }
            selectRect = null;
            selectStart = null;
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
        this.showAllPorts = false;
        if(strategy != null) {
            System.out.println("Mode Switched: Basic Object Create");
        }
    }

    public void setLinkDraw(LinkDrawInterface strategy) {
        this.drawLink = strategy;
        this.showAllPorts = true;
        if(strategy != null) {
            System.out.println("Mode Switched: Link Connection");
        }
    }

    public boolean isShowAllPorts(){
        return this.showAllPorts;
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
        this.showAllPorts = false;
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
        for (LinkAbstract l: lineList) {
            l.draw(g);
        }
        if (selectRect != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(0, 120, 215, 50)); // 半透明藍色背景
            g2d.fill(selectRect);
            g2d.setColor(new Color(0, 120, 215));     // 藍色邊框
            g2d.draw(selectRect);
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
            System.out.println("Objects Grouped");
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