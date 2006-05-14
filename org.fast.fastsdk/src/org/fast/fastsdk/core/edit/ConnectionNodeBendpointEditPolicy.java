/**
 *
 */
package org.fast.fastsdk.core.edit;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;
import org.fast.fastsdk.core.model.FastConnection;
import org.fast.fastsdk.core.model.commands.BendpointCommand;
import org.fast.fastsdk.core.model.commands.CreateBendpointCommand;
import org.fast.fastsdk.core.model.commands.DeleteBendpointCommand;
import org.fast.fastsdk.core.model.commands.MoveBendpointCommand;

/**
 * @author …Ú»›÷€
 */
public class ConnectionNodeBendpointEditPolicy extends BendpointEditPolicy {

    protected Command getCreateBendpointCommand(BendpointRequest request) {
        CreateBendpointCommand com = new CreateBendpointCommand();
        Point p = request.getLocation();
        Connection conn = getConnection();

        conn.translateToRelative(p);

        com.setLocation(p);
        Point ref1 = getConnection().getSourceAnchor().getReferencePoint();
        Point ref2 = getConnection().getTargetAnchor().getReferencePoint();

        conn.translateToRelative(ref1);
        conn.translateToRelative(ref2);

        com.setRelativeDimensions(p.getDifference(ref1), p.getDifference(ref2));
        com.setConnectionNode((FastConnection) request.getSource().getModel());
        com.setIndex(request.getIndex());
        return com;
    }

    protected Command getMoveBendpointCommand(BendpointRequest request) {
        MoveBendpointCommand com = new MoveBendpointCommand();
        Point p = request.getLocation();
        Connection conn = getConnection();

        conn.translateToRelative(p);

        com.setLocation(p);

        Point ref1 = getConnection().getSourceAnchor().getReferencePoint();
        Point ref2 = getConnection().getTargetAnchor().getReferencePoint();

        conn.translateToRelative(ref1);
        conn.translateToRelative(ref2);

        com.setRelativeDimensions(p.getDifference(ref1), p.getDifference(ref2));
        com.setConnectionNode((FastConnection) request.getSource().getModel());
        com.setIndex(request.getIndex());
        return com;
    }

    protected Command getDeleteBendpointCommand(BendpointRequest request) {
        BendpointCommand com = new DeleteBendpointCommand();
        Point p = request.getLocation();
        com.setLocation(p);
        com.setConnectionNode((FastConnection) request.getSource().getModel());
        com.setIndex(request.getIndex());
        return com;
    }
}
