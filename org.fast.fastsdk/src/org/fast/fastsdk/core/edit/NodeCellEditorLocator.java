/**
 * 
 */
package org.fast.fastsdk.core.edit;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Text;
import org.fast.fastsdk.core.figures.FastNodeFigure;

/**
 * @author …Ú»›÷€
 */
public class NodeCellEditorLocator implements CellEditorLocator {
    private FastNodeFigure fastNode;

    public NodeCellEditorLocator(FastNodeFigure fastNode) {
        setName(fastNode);
    }

    public void relocate(CellEditor celleditor) {
        Text text = (Text) celleditor.getControl();
        Rectangle rect = fastNode.getClientArea();
        fastNode.translateToAbsolute(rect);
        org.eclipse.swt.graphics.Rectangle trim = text.computeTrim(0, 0, 0, 0);
        rect.translate(trim.x, trim.y);
        rect.width += trim.width;
        rect.height += trim.height;
        text.setBounds(rect.x, rect.y, rect.width, rect.height);
    }

    /**
     * Returns the FastNode figure.
     */
    public FastNodeFigure getLabel() {
        return fastNode;
    }

    /**
     * Sets the Sticky note figure.
     * 
     * @param FastNode
     *            The FastNode to set
     */
    public void setName(FastNodeFigure fastNode) {
        this.fastNode = fastNode;
    }
}
