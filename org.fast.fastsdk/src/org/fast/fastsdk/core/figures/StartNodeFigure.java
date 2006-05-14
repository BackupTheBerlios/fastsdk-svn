package org.fast.fastsdk.core.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.fast.fastsdk.core.model.StartNode;

public class StartNodeFigure extends FastNodeFigure {

    public StartNodeFigure() {
        offsetV = 15;
        offsetH = 15;
        FixedConnectionAnchor c = new FixedConnectionAnchor(this);
        c.offsetV = offsetV;
        connectionAnchors.put(StartNode.OUT1, c);
        outputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetH = offsetH;
        connectionAnchors.put(StartNode.OUT2, c);
        outputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetV = offsetV;
        c.leftToRight = false;
        connectionAnchors.put(StartNode.OUT3, c);
        outputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetH = offsetH;
        c.topDown = false;
        connectionAnchors.put(StartNode.OUT4, c);
        outputConnectionAnchors.addElement(c);
    }

    /**
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    protected void paintFigure(Graphics g) {
        // TODO Auto-generated method stub
        super.paintFigure(g);

        drawText(getName(), g, 20);
        Rectangle r = getBounds().getCopy();
        r.x = r.x + (r.width - 20) / 2;
        r.width = 18;
        r.height = 18;
        g.setForegroundColor(ColorConstants.blue);
        g.drawOval(r);
        r.resize(-6, -6);
        r.translate(3, 3);
        g.setForegroundColor(ColorConstants.black);
        g.drawOval(r);
    }

    public String toString() {
        return "StartFigure";
    }
}
