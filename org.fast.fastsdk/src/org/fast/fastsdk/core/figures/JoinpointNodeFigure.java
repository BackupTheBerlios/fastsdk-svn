/**
 *
 */
package org.fast.fastsdk.core.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.fast.fastsdk.core.model.JoinpointNode;

/**
 * @author …Ú»›÷€
 */
public class JoinpointNodeFigure extends FastNodeFigure {
    public JoinpointNodeFigure() {
        offsetH = 19;
        offsetV = 19;
        FixedConnectionAnchor c = new FixedConnectionAnchor(this);
        c.offsetH = offsetH;
        connectionAnchors.put(JoinpointNode.IN_1, c);
        inputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetH = offsetH;
        c.topDown = false;
        connectionAnchors.put(JoinpointNode.IN_2, c);
        inputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetV = offsetV;
        c.leftToRight = false;
        connectionAnchors.put(JoinpointNode.OUT_2, c);
        outputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetV = offsetV;
        connectionAnchors.put(JoinpointNode.OUT_1, c);
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
        g.setForegroundColor(ColorConstants.black);
        r.width = r.width - 2;
        r.height = 38;
        g.drawOval(r);
        r.resize(-16, -16);
        r.translate(8, 8);
        g.setForegroundColor(ColorConstants.blue);
        g.drawOval(r);
    }

    public String toString() {
        return "JoinpointFigure";
    }
}
