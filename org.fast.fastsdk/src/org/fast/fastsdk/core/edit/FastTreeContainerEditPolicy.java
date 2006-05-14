/**
 *
 */
package org.fast.fastsdk.core.edit;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.TreeContainerEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.fast.fastsdk.core.model.FastDiagram;
import org.fast.fastsdk.core.model.FastSubpart;
import org.fast.fastsdk.core.model.commands.FastCreateCommand;
import org.fast.fastsdk.core.model.commands.ReorderPartCommand;

/**
 * @author ÉòÈÝÖÛ
 */
public class FastTreeContainerEditPolicy extends TreeContainerEditPolicy {

    protected Command createCreateCommand(FastSubpart child, Rectangle r,
            int index, String label) {
        FastCreateCommand cmd = new FastCreateCommand();
        Rectangle rect;
        if (r == null) {
            rect = new Rectangle();
            rect.setSize(new Dimension(-1, -1));
        } else {
            rect = r;
        }
        cmd.setLocation(rect);
        cmd.setParent((FastDiagram) getHost().getModel());
        cmd.setChild(child);
        cmd.setLabel(label);
        if (index >= 0)
            cmd.setIndex(index);
        return cmd;
    }

    protected Command getAddCommand(ChangeBoundsRequest request) {
        CompoundCommand command = new CompoundCommand();
        command.setDebugLabel("Add in FastTreeContainerEditPolicy");//$NON-NLS-1$
        List editparts = request.getEditParts();
        int index = findIndexOfTreeItemAt(request.getLocation());

        for (int i = 0; i < editparts.size(); i++) {
            EditPart child = (EditPart) editparts.get(i);
            if (isAncestor(child, getHost()))
                command.add(UnexecutableCommand.INSTANCE);
            else {
                FastSubpart childModel = (FastSubpart) child.getModel();
                command.add(createCreateCommand(childModel, new Rectangle(
                        new org.eclipse.draw2d.geometry.Point(), childModel
                                .getSize()), index, "Reparent FastSubpart"));//$NON-NLS-1$
            }
        }
        return command;
    }

    protected Command getCreateCommand(CreateRequest request) {
        FastSubpart child = (FastSubpart) request.getNewObject();
        int index = findIndexOfTreeItemAt(request.getLocation());
        return createCreateCommand(child, null, index, "Create FastSubpart");//$NON-NLS-1$
    }

    protected Command getMoveChildrenCommand(ChangeBoundsRequest request) {
        CompoundCommand command = new CompoundCommand();
        List editparts = request.getEditParts();
        List children = getHost().getChildren();
        int newIndex = findIndexOfTreeItemAt(request.getLocation());

        for (int i = 0; i < editparts.size(); i++) {
            EditPart child = (EditPart) editparts.get(i);
            int tempIndex = newIndex;
            int oldIndex = children.indexOf(child);
            if (oldIndex == tempIndex || oldIndex + 1 == tempIndex) {
                command.add(UnexecutableCommand.INSTANCE);
                return command;
            } else if (oldIndex <= tempIndex) {
                tempIndex--;
            }
            command.add(new ReorderPartCommand((FastSubpart) child.getModel(),
                    (FastDiagram) getHost().getModel(), tempIndex));
        }
        return command;
    }

    protected boolean isAncestor(EditPart source, EditPart target) {
        if (source == target)
            return true;
        if (target.getParent() != null)
            return isAncestor(source, target.getParent());
        return false;
    }
}
