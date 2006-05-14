/**
 *
 */
package org.fast.fastsdk.core.model.commands;

import org.eclipse.draw2d.Bendpoint;

/**
 * @author …Ú»›÷€
 */
public class DeleteBendpointCommand extends BendpointCommand {
    private Bendpoint bendpoint;

    public void execute() {
        bendpoint = (Bendpoint) getConnectionNode().getBendpoints().get(
                getIndex());
        getConnectionNode().removeBendpoint(getIndex());
        super.execute();
    }

    public void undo() {
        super.undo();
        getConnectionNode().insertBendpoint(getIndex(), bendpoint);
    }
}
