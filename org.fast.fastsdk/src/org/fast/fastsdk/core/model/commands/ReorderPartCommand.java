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
public class ReorderPartCommand extends Command {
    private int         oldIndex, newIndex;

    private FastSubpart child;

    private FastDiagram parent;

    public ReorderPartCommand(FastSubpart child, FastDiagram parent,
            int newIndex) {
        super("reorder part");
        this.child = child;
        this.parent = parent;
        this.newIndex = newIndex;
    }

    public void execute() {
        oldIndex = parent.getChildren().indexOf(child);
        parent.removeChild(child);
        parent.addChild(child, newIndex);
    }

    public void undo() {
        parent.removeChild(child);
        parent.addChild(child, oldIndex);
    }
}
