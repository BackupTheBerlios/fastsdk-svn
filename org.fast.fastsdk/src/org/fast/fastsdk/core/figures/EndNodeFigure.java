package org.fast.fastsdk.core.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.fast.fastsdk.core.model.EndNode;

public class EndNodeFigure extends FastNodeFigure {
    public EndNodeFigure() {
        offsetH = 15;
        offsetV = 15;
        FixedConnectionAnchor c = new FixedConnectionAnchor(this);
        c.offsetV = offsetV;
        connectionAnchors.put(EndNode.IN1, c);
        inputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetH = offsetH;
        connectionAnchors.put(EndNode.IN2, c);
        inputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetV = offsetV;
        c.leftToRight = false;
        connectionAnchors.put(EndNode.IN3, c);
        inputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetH = offsetH;
        c.topDown = false;
        connectionAnchors.put(EndNode.IN4, c);
        inputConnectionAnchors.addElement(c);
    }

    /**
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    protected void paintFigure(Graphics g) {
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
        return "EndFigure";
    }
}
