package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import objects.Port;
import objects.LinkAbstract;
import objects.BasicAbstract;
import objects.Composite;
import objCreate.*;

import mode.ModeInterface;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener{
    
    private ArrayList<BasicAbstract> objList;
    private ArrayList<LinkAbstract> lineList;
    private ArrayList<BasicAbstract> selectedObjs;

    private int currentDepth = 0;
    private BasicAbstract hoveredObj = null;
    private Rectangle selectRect;

    private ModeInterface mode;

    private final Color SELECT_BOUND = new Color(0, 120, 215);
    private final Color SELECT_COLOR = new Color(0, 120, 215, 50);

    public Canvas() {
        addMouseListener(this);
        addMouseMotionListener(this);
        initializeList();
    }

    private void initializeList(){
        objList = new ArrayList<>();
        lineList = new ArrayList<>();
        selectedObjs = new ArrayList<>();
    }

    public ArrayList<BasicAbstract> getObjs(){
        return objList;
    }
    
    public ArrayList<BasicAbstract> getSelectedObjs(){
        return selectedObjs;
    }

    public void setHoveredObj(BasicAbstract hoveredObj){
        this.hoveredObj = hoveredObj;
    }

    public BasicAbstract getHoveredObj(){
        return hoveredObj;
    }

    public void setSelectRect(Rectangle newRect){
        this.selectRect = newRect;
        repaint();
    }

    public void setMode(ModeInterface mode){
        this.mode = mode;
    }

    public void addLink(LinkAbstract newLink){
        lineList.add(newLink);
        repaint();
    }

    public void addShape(BasicCreateInterface strategy, int dropX, int dropY){
        BasicAbstract newObj = strategy.createBasic(dropX, dropY, currentDepth++);
        objList.add(newObj);
        repaint();
    }

    // mouse action
    @Override
    public void mousePressed(MouseEvent e) {
        mode.onPressed(e, this);
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mode.onDragged(e, this);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mode.onReleased(e, this);
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e){
        mode.onHovered(e, this);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (BasicAbstract s : objList) {
            s.draw(g);
        }
        for (LinkAbstract l: lineList) {
            l.draw(g);
        }
        if (selectRect != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(SELECT_COLOR);
            g2d.fill(selectRect);
            g2d.setColor(SELECT_BOUND);
            g2d.draw(selectRect);
        }
    }

    public Port findPortAt(Point p) {
        for (int i = objList.size() - 1; i >= 0; i--) {
            BasicAbstract s = objList.get(i);
            if(s.getPorts() == null){
                continue;
            }
            for (Port port : s.getPorts()) {
                if (port.isInside(p)) {
                    return port;
                }
            }
        }
        return null;
    }

    public BasicAbstract findObjAt(Point p){
        for (int i = objList.size() - 1; i >= 0; i--){
            BasicAbstract s = objList.get(i);
            if(s.isInside(p)){
                return s;
            }
        }
        return null;
    }

    public void resetObjs(){
        for (BasicAbstract s : objList) {
            s.setIsSelected(false);
        }
    }

    public void selectObjectAt(Point p) {
        resetObjs();
        for (int i = objList.size() - 1; i >= 0; i--) {
            BasicAbstract s = objList.get(i);          
            if (s.isInside(p)) {
                s.setIsSelected(true);
                objList.remove(i);
                objList.add(s);
                break; 
            }
        }
        repaint();
    }

    public void updateSelectedObject() {
        selectedObjs.clear();
        for (BasicAbstract s : objList) {
            if (s.getIsSelected()) {
                selectedObjs.add(s);
            }
        }
    }

    public void groupObjects() {
        List<BasicAbstract> selectedItems = new ArrayList<>();

        for (int i = objList.size() - 1; i >= 0; i--) {
            BasicAbstract s = objList.get(i);
            if (s.getIsSelected()) {
                selectedItems.add(s);
                objList.remove(i);
            }
        }

        if (selectedItems.size() > 1) {
            Composite group = new Composite();
            for (BasicAbstract s : selectedItems) {
                s.setIsSelected(false);
                group.addMember(s);
            }
            group.setIsSelected(true);
            objList.add(group);
        }
        else {
            objList.addAll(selectedItems);
        }
        repaint();
    }

    public void ungroupObjects() {
        for (int i = objList.size() - 1; i >= 0; i--) {
            BasicAbstract s = objList.get(i);
            if (s.getIsSelected()){
                for (BasicAbstract member : s.getMembers()){
                    member.setIsSelected(true);
                    objList.add(member);
                }
                objList.remove(i);
                break;
            }
        }
        repaint();
    }
}