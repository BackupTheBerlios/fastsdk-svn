/**
 *
 */
package org.fast.fastsdk.core.model.commands;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.fast.fastsdk.core.model.FastDiagram;
import org.fast.fastsdk.core.model.FastSubpart;

/**
 * @author …Ú»›÷€
 */
public class OrphanChildCommand extends Command {
    private Point       oldLocation;

    private FastDiagram diagram;

    private FastSubpart child;

    private int         index;

    public OrphanChildCommand() {
        super("orphan child");
    }

    public void execute() {
        List children = diagram.getChildren();
        index = children.indexOf(child);
        oldLocation = child.getLocation();
        diagram.removeChild(child);
    }

    public void redo() {
        diagram.removeChild(child);
    }

    public void setChild(FastSubpart child) {
        this.child = child;
    }

    public void setParent(FastDiagram parent) {
        diagram = parent;
    }

    public void undo() {
        child.setLocation(oldLocation);
        diagram.addChild(child, index);
    }
}
