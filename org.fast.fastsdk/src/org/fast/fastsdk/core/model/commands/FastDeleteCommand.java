/**
 *
 */
package org.fast.fastsdk.core.model.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.fast.fastsdk.core.model.FastConnection;
import org.fast.fastsdk.core.model.FastDiagram;
import org.fast.fastsdk.core.model.FastGuide;
import org.fast.fastsdk.core.model.FastSubpart;

/**
 * @author …Ú»›÷€
 */
public class FastDeleteCommand extends Command {
    private FastSubpart child;

    private FastDiagram parent;

    private FastGuide   vGuide, hGuide;

    private int         vAlign, hAlign;

    private int         index             = -1;

    private List        sourceConnections = new ArrayList();

    private List        targetConnections = new ArrayList();

    public FastDeleteCommand() {
        super("delete node");
    }

    private void deleteConnections(FastSubpart part) {
        if (part instanceof FastDiagram) {
            List children = ((FastDiagram) part).getChildren();
            for (int i = 0; i < children.size(); i++)
                deleteConnections((FastSubpart) children.get(i));
        }
        sourceConnections.addAll(part.getSourceConnections());
        for (int i = 0; i < sourceConnections.size(); i++) {
            FastConnection conn = (FastConnection) sourceConnections.get(i);
            conn.detachSource();
            conn.detachTarget();
        }
        targetConnections.addAll(part.getTargetConnections());
        for (int i = 0; i < targetConnections.size(); i++) {
            FastConnection conn = (FastConnection) targetConnections.get(i);
            conn.detachSource();
            conn.detachTarget();
        }
    }

    private void detachFromGuides(FastSubpart part) {
        if (part.getVerticalGuide() != null) {
            vGuide = part.getVerticalGuide();
            vAlign = vGuide.getAlignment(part);
            vGuide.detachPart(part);
        }
        if (part.getHorizontalGuide() != null) {
            hGuide = part.getHorizontalGuide();
            hAlign = hGuide.getAlignment(part);
            hGuide.detachPart(part);
        }

    }

    public void execute() {
        primExecute();
    }

    protected void primExecute() {
        deleteConnections(child);
        detachFromGuides(child);
        index = parent.getChildren().indexOf(child);
        parent.removeChild(child);
    }

    private void reattachToGuides(FastSubpart part) {
        if (vGuide != null)
            vGuide.attachPart(part, vAlign);
        if (hGuide != null)
            hGuide.attachPart(part, hAlign);
    }

    public void redo() {
        primExecute();
    }

    private void restoreConnections() {
        for (int i = 0; i < sourceConnections.size(); i++) {
            FastConnection conn = (FastConnection) sourceConnections.get(i);
            conn.attachSource();
            conn.attachTarget();
        }
        sourceConnections.clear();
        for (int i = 0; i < targetConnections.size(); i++) {
            FastConnection conn = (FastConnection) targetConnections.get(i);
            conn.attachSource();
            conn.attachTarget();
        }
        targetConnections.clear();
    }

    public void setChild(FastSubpart c) {
        child = c;
    }

    public void setParent(FastDiagram p) {
        parent = p;
    }

    public void undo() {
        parent.addChild(child, index);
        restoreConnections();
        reattachToGuides(child);
    }
}
