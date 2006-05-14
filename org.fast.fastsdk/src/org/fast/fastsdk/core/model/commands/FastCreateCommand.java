/**
 *
 */
package org.fast.fastsdk.core.model.commands;

import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.fast.fastsdk.core.model.FastDiagram;
import org.fast.fastsdk.core.model.FastSubpart;

/**
 * @author …Ú»›÷€
 */
public class FastCreateCommand extends Command {
    private FastSubpart child;

    private Rectangle   rect;

    private FastDiagram parent;

    private int         index = -1;

    public FastCreateCommand() {
        super("create node");
    }

    public boolean canExecute() {
        return child != null && parent != null;
    }

    public void execute() {
        if (rect != null) {
            Insets expansion = getInsets();
            if (!rect.isEmpty())
                rect.expand(expansion);
            else {
                rect.x -= expansion.left;
                rect.y -= expansion.top;
            }
            child.setLocation(rect.getLocation());
            if (!rect.isEmpty())
                child.setSize(rect.getSize());
        }
        redo();
    }

    private Insets getInsets() {
        return new Insets();
    }

    public void redo() {
        parent.addChild(child, index);
    }

    public void setChild(FastSubpart subpart) {
        child = subpart;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setLocation(Rectangle r) {
        rect = r;
    }

    public void setParent(FastDiagram newParent) {
        parent = newParent;
    }

    public void undo() {
        parent.removeChild(child);
    }
}
