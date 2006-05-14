/**
 *
 */
package org.fast.fastsdk.core.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.fast.fastsdk.core.model.FunctionNode;

/**
 * @author …Ú»›÷€
 */
public class FunctionNodeFigure extends FastNodeFigure {
    public FunctionNodeFigure() {
        offsetH = 19;
        offsetV = 19;
        FixedConnectionAnchor c = new FixedConnectionAnchor(this);
        c.offsetV = offsetV;
        connectionAnchors.put(FunctionNode.IN_1, c);
        inputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetV = offsetV;
        c.leftToRight = false;
        connectionAnchors.put(FunctionNode.IN_2, c);
        outputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetH = offsetH;
        c.topDown = false;
        connectionAnchors.put(FunctionNode.IN_3, c);
        outputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetH = offsetH;
        connectionAnchors.put(FunctionNode.IN_4, c);
        outputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetV = offsetV;
        c.leftToRight = false;
        connectionAnchors.put(FunctionNode.OUT_1, c);
        outputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetV = offsetV;
        connectionAnchors.put(FunctionNode.OUT_2, c);
        outputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetH = offsetH;
        c.topDown = false;
        connectionAnchors.put(FunctionNode.OUT_3, c);
        outputConnectionAnchors.addElement(c);

        c = new FixedConnectionAnchor(this);
        c.offsetH = offsetH;
        connectionAnchors.put(FunctionNode.OUT_4, c);
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
        g.drawRectangle(r);
        r.resize(-16, -16);
        r.translate(8, 8);
        g.setForegroundColor(ColorConstants.blue);
        g.drawRectangle(r);
    }

    public String toString() {
        return "FunctionFigure";
    }
}
