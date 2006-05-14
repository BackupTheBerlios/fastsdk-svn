package org.fast.fastsdk.core.edit;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.fast.fastsdk.core.model.FastNode;
import org.fast.fastsdk.core.model.commands.SetFastNodeNameCommand;

public class FastNameEditPolicy extends FastElementEditPolicy {
    public Command getCommand(Request request) {
        if (NativeDropRequest.ID.equals(request.getType()))
            return getDropTextCommand((NativeDropRequest) request);
        return super.getCommand(request);
    }

    protected Command getDropTextCommand(NativeDropRequest request) {
        SetFastNodeNameCommand command = new SetFastNodeNameCommand(
                (FastNode) getHost().getModel(), (String) request.getData());
        return command;
    }

    public EditPart getTargetEditPart(Request request) {
        if (NativeDropRequest.ID.equals(request.getType()))
            return getHost();
        return super.getTargetEditPart(request);
    }
}
