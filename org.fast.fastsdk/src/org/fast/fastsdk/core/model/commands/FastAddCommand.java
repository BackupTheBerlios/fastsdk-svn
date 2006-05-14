/**
 *
 */
package org.fast.fastsdk.core.model.commands;

import org.eclipse.gef.commands.Command;
import org.fast.fastsdk.core.model.FastDiagram;
import org.fast.fastsdk.core.model.FastSubpart;

/**
 * @author …Ú»›÷€
 */
public class FastAddCommand extends Command {
    private FastSubpart child;

    private FastDiagram parent;

    private int         index = -1;

    public FastAddCommand() {
        super("add node");
    }

    public void execute() {
        if (index < 0)
            parent.addChild(child);
        else
            parent.addChild(child, index);
    }

    public FastDiagram getParent() {
        return parent;
    }

    public void redo() {
        if (index < 0)
            parent.addChild(child);
        else
            parent.addChild(child, index);
    }

    public void setChild(FastSubpart subpart) {
        child = subpart;
    }

    public void setIndex(int i) {
        index = i;
    }

    public void setParent(FastDiagram newParent) {
        parent = newParent;
    }

    public void undo() {
        parent.removeChild(child);
    }
}
