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

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener{
    private BasicDrawInterface drawBasic = null;
    private LinkDrawInterface drawLink = null;
    public ArrayList<ShapeAbstract> shapeList = new ArrayList<>();
    public ArrayList<LinkAbstract> lineList;
    private int currentDepth = 0;
    private Port startPort = null;  // 紀錄連線起點
    private Point lastMousePoint;      // 紀錄上一次滑鼠的位置
    private BasicAbstract draggingObj; // 目前正在拖拽的物件

    public Canvas() {
        addMouseListener(this);
    }

    // ===================== mouse action =====================

    @Override
    public void mousePressed(MouseEvent e){
        // A. 建立物件模式 (Use Case A)
        if (drawBasic != null) {
            // 呼叫介面產生物件 (Rect 或 Oval)
            BasicAbstract newObj = drawBasic.createBasic(e.getX(), e.getY(), currentDepth++);
            shapeList.add(newObj);
            
            // 操作結束，自動跳回 Select 模式
            resetToSelectMode();
        } 
        // B. 連線模式 (Use Case B - 起點)
        else if (drawLink != null) {
            // 尋找滑鼠點擊位置是否有 Port
            startPort = findPortAt(e.getPoint());
        } 
        // C. 選取模式 (Use Case C)
        else {
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
            // 1. 計算這一次移動的位移量
            int dx = e.getX() - lastMousePoint.x;
            int dy = e.getY() - lastMousePoint.y;

            // 2. 更新物件座標 (Use Case E)
            draggingObj.setX(draggingObj.getX() + dx);
            draggingObj.setY(draggingObj.getY() + dy);

            // 3. 更新最後滑鼠位置，供下一次Dragged計算
            lastMousePoint = e.getPoint();

            // 4. 重繪
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e){
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // 1. 處理連線模式 (Use Case B)
        if (drawLink != null && startPort != null) {
            // 尋找滑鼠放開位置是否有另一個 Port
            Port endPort = findPortAt(e.getPoint());

            // 判定連線是否有效：
            // - 有抓到終點 Port
            // - 終點 Port 不等於起點 Port
            if (endPort != null && endPort != startPort) {
                if (startPort.getOwner() != endPort.getOwner()) {
                    
                    // 透過介面產生物件 (如 AssociationCreate)
                    LinkAbstract newLink = drawLink.createLink(startPort, endPort);
                    
                    // 將連線加入清單
                    shapeList.add(newLink);
                    System.out.println("Connection Created!");
                }
            }

            // 重置連線暫存狀態
            startPort = null;
            
            // 根據 Use Case A/B：操作完後自動跳回 Select 模式
            resetToSelectMode();
        }

        // 2. 處理移動模式的收尾 (Use Case E)
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

    // 提供給 Button 呼叫的模式設定
    public void setBasicDraw(BasicDrawInterface strategy) {
        this.drawBasic = strategy;
        this.drawLink = null; // 切換到畫物件模式時，自動關閉連線模式
        System.out.println("Mode Switched: Basic Object Creation");
    }

    public void setLinkDraw(LinkDrawInterface strategy) {
        this.drawLink = strategy;
        this.drawBasic = null; // 切換到連線模式時，自動關閉畫物件模式
        System.out.println("Mode Switched: Link Connection");
    }

    private Port findPortAt(java.awt.Point p) {
        // 從最上層物件開始找 (深度小的優先)
        for (int i = shapeList.size() - 1; i >= 0; i--) {
            ShapeAbstract s = shapeList.get(i);
            BasicAbstract obj = (BasicAbstract) s;
            // 取得該物件的所有 ports 並檢查 isInside
            for (Port port : obj.getPorts()) {
                if (port.isInside(p)) {
                    return port;
                }
            }
            
        }
        return null;
    }

    private void selectObjectAt(java.awt.Point p) {
        // 1. 先將所有物件設為未選取
        for (ShapeAbstract s : shapeList) {
            ((BasicAbstract) s).setIsSelected(false);
            
        }

        // 2. 從最後面 (視覺最上層) 開始找點中了誰
        for (int i = shapeList.size() - 1; i >= 0; i--) {
            ShapeAbstract s = shapeList.get(i);

            // 只有基本物件 (Rect, Oval) 或 Group 物件可以被選取
            if (s instanceof BasicAbstract) {
                BasicAbstract obj = (BasicAbstract) s;
                
                if (obj.isInside(p)) {
                    obj.setIsSelected(true);

                    // 3. 置頂邏輯：將選中的物件移到 List 的最後面
                    // 這樣下次 paintComponent 時它會最後畫，就在最前面
                    shapeList.remove(i);
                    shapeList.add(obj);

                    // 找到一個就跳出，避免同時選中重疊的多個物件
                    break; 
                }
            }
        }
        
        // 4. 每次選取狀態改變後都要重繪
        repaint();
    }

    private void resetToSelectMode() {
        this.drawBasic = null;
        this.drawLink = null;
        // 這裡需要透過 MainFrame 取得 ToolBar 的參考來呼叫 selectDefaultButton()
        // 例如：mainFrame.getToolBar().selectDefaultButton();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void groupObjects() {
        List<ShapeAbstract> selectedItems = new ArrayList<>();
        
        // 1. 找出所有目前被選取的物件
        for (int i = shapeList.size() - 1; i >= 0; i--) {
            ShapeAbstract s = shapeList.get(i);
            if (s.getIsSelected()) {
                selectedItems.add(s);
                shapeList.remove(i); // 從主清單移除，準備放進群組
            }
        }

        // 2. 如果選取數量大於 1，才進行群組
        if (selectedItems.size() > 1) {
            Composite group = new Composite();
            for (ShapeAbstract s : selectedItems) {
                s.setIsSelected(false); // 成員本身取消個別選取狀態
                group.addMember(s);
            }
            group.setIsSelected(true); // 群組設為選取狀態
            shapeList.add(group);      // 將群組物件放回畫布
        } else {
            // 如果只有一個，放回去原本的位置
            shapeList.addAll(selectedItems);
        }
        repaint();
    }

    public void ungroupObjects() {
        // 1. 找出目前選中的「單一」群組物件
        for (int i = shapeList.size() - 1; i >= 0; i--) {
            ShapeAbstract s = shapeList.get(i);
            if (s.getIsSelected() && s instanceof Composite) {
                Composite group = (Composite) s;
                
                // 2. 將成員釋放回主清單
                shapeList.remove(i);
                for (ShapeAbstract member : group.getMembers()) {
                    member.setIsSelected(true); // 釋放後預設維持選取
                    shapeList.add(member);
                }
                break; // 一次只解散一個
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
        // 根據需求，通常只有選取「一個」時才開放改名
        return (count == 1) ? selected : null;
    }

}
