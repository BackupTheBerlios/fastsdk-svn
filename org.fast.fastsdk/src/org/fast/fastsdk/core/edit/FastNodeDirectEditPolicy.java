package org.fast.fastsdk.core.edit;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.fast.fastsdk.core.figures.FastNodeFigure;
import org.fast.fastsdk.core.model.FastNode;
import org.fast.fastsdk.core.model.commands.SetFastNodeNameCommand;

public class FastNodeDirectEditPolicy extends DirectEditPolicy {

    protected Command getDirectEditCommand(DirectEditRequest request) {
        // TODO Auto-generated method stub
        String name = (String) request.getCellEditor().getValue();
        FastNodeEditPart part = (FastNodeEditPart) getHost();
        SetFastNodeNameCommand command = new SetFastNodeNameCommand(
                (FastNode) part.getModel(), name);
        return command;
    }

    protected void showCurrentEditValue(DirectEditRequest request) {
        // TODO Auto-generated method stub
        String name = (String) request.getCellEditor().getValue();
        ((FastNodeFigure) getHostFigure()).setName(name);
        getHostFigure().getUpdateManager().performUpdate();
    }
}
