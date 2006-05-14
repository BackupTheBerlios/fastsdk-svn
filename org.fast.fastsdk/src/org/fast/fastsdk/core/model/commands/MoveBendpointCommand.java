/**
 *
 */
package org.fast.fastsdk.core.model.commands;

import org.eclipse.draw2d.Bendpoint;
import org.fast.fastsdk.core.model.FastBendpoint;

/**
 * @author …Ú»›÷€
 */
public class MoveBendpointCommand extends BendpointCommand {
    private Bendpoint oldBendpoint;

    public void execute() {
        FastBendpoint bp = new FastBendpoint();
        bp.setRelativeDimensions(getFirstRelativeDimension(),
                getSecondRelativeDimension());
        setOldBendpoint((Bendpoint) getConnectionNode().getBendpoints().get(
                getIndex()));
        getConnectionNode().setBendpoint(getIndex(), bp);
        super.execute();
    }

    protected Bendpoint getOldBendpoint() {
        return oldBendpoint;
    }

    public void setOldBendpoint(Bendpoint bp) {
        oldBendpoint = bp;
    }

    public void undo() {
        super.undo();
        getConnectionNode().setBendpoint(getIndex(), getOldBendpoint());
    }
}
