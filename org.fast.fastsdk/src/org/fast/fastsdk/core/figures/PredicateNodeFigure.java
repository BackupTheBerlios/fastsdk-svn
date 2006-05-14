/**
 *
 */
package org.fast.fastsdk.core.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.fast.fastsdk.core.model.PredicateNode;

/**
 * @author …Ú»›÷€
 */
public class PredicateNodeFigure extends FastNodeFigure {
    protected static PointList outerKite = null;

    protected static PointList innerKite = null;

    private static Point       p1        = new Point(19, 0);

    private static Point       p2        = new Point(0, 19);

    private static Point       p3        = new Point(19, 38);

    private static Point       p4        = new Point(38, 19);

    static {
        outerKite = new PointList();
        outerKite.addPoint(p1);
        outerKite.addPoint(p2);
        outerKite.addPoint(p3);
        outerKite.addPoint(p4);
        innerKite = new PointList();
        innerKite.addPoint(p1.x, p1.y + 8);
        innerKite.addPoint(p2.x + 8, p2.y);
        innerKite.addPoint(p3.x, p3.y - 8);
        innerKite.addPoint(p4.x - 8, p4.y);
    }

    public PredicateNodeFigure() {
        offsetV = 19;
        offsetH = 19;
        FixedConnectionAnchor c = new FixedConnectionAnchor(this);
        c.offsetV = offsetV;
        connectionAnchors.put(PredicateNode.IN_1, c);
        inputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetV = offsetV;
        c.leftToRight = false;
        connectionAnchors.put(PredicateNode.IN_2, c);
        inputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetH = offsetH;
        connectionAnchors.put(PredicateNode.OUT_1, c);
        outputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetH = offsetH;
        c.topDown = false;
        connectionAnchors.put(PredicateNode.OUT_2, c);
        outputConnectionAnchors.addElement(c);
    }

    /**
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    protected void paintFigure(Graphics g) {
        // TODO Auto-generated method stub
        super.paintFigure(g);
        drawText(getName(), g, 40);
        Rectangle r = getBounds().getCopy();
        g.translate(r.getLocation());
        g.setForegroundColor(ColorConstants.black);
        g.drawPolygon(outerKite);
        g.setForegroundColor(ColorConstants.blue);
        g.drawPolygon(innerKite);
    }

    public String toString() {
        return "PredicateFigure";
    }
}
