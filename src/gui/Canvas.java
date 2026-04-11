package gui;

import objects.Port;
import objects.ShapeAbstract;
import objects.LinkAbstract;
import objects.BasicAbstract;
import draw.BasicDrawInterface;
import draw.LinkDrawInterface;
import java.util.ArrayList;

public class Canvas {
    public BasicDrawInterface drawBasic;
    public LinkDrawInterface drawLink;
    public ArrayList<ShapeAbstract> shapeList;
    public ArrayList<LinkAbstract> lineList;
}
