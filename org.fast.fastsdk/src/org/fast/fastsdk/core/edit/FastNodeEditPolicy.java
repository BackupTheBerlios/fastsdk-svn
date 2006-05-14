/**
 *
 */
package org.fast.fastsdk.core.edit;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.fast.fastsdk.core.figures.FastNodeFigure;
import org.fast.fastsdk.core.figures.FigureFactory;
import org.fast.fastsdk.core.model.FastConnection;
import org.fast.fastsdk.core.model.FastSubpart;
import org.fast.fastsdk.core.model.commands.ConnectionCommand;

/**
 * @author …Ú»›÷€
 */
public class FastNodeEditPolicy extends GraphicalNodeEditPolicy {

    protected Connection createDummyConnection(Request req) {
        PolylineConnection conn = FigureFactory.createNewWire(null);
        return conn;
    }

    protected Command getConnectionCompleteCommand(
            CreateConnectionRequest request) {
        ConnectionCommand command = (ConnectionCommand) request
                .getStartCommand();
        command.setTarget(getFastSubpart());
        ConnectionAnchor ctor = getFastEditPart().getTargetConnectionAnchor(
                request);
        if (ctor == null)
            return null;
        command.setTargetTerminal(getFastEditPart()
                .mapConnectionAnchorToTerminal(ctor));
        return command;
    }

    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        ConnectionCommand command = new ConnectionCommand();
        command.setConnectionNode(new FastConnection());
        command.setSource(getFastSubpart());
        ConnectionAnchor ctor = getFastEditPart().getSourceConnectionAnchor(
                request);
        command.setSourceTerminal(getFastEditPart()
                .mapConnectionAnchorToTerminal(ctor));
        request.setStartCommand(command);
        return command;
    }

    /**
     * Feedback should be added to the scaled feedback layer.
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalEditPolicy#getFeedbackLayer()
     */
    protected IFigure getFeedbackLayer() {
        /*
         * Fix for Bug# 66590 Feedback needs to be added to the scaled feedback
         * layer
         */
        return getLayer(LayerConstants.SCALED_FEEDBACK_LAYER);
    }

    protected FastEditPart getFastEditPart() {
        return (FastEditPart) getHost();
    }

    protected FastSubpart getFastSubpart() {
        return (FastSubpart) getHost().getModel();
    }

    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        ConnectionCommand cmd = new ConnectionCommand();
        cmd.setConnectionNode((FastConnection) request.getConnectionEditPart()
                .getModel());

        ConnectionAnchor ctor = getFastEditPart().getTargetConnectionAnchor(
                request);
        cmd.setTarget(getFastSubpart());
        cmd.setTargetTerminal(getFastEditPart().mapConnectionAnchorToTerminal(
                ctor));
        return cmd;
    }

    protected Command getReconnectSourceCommand(ReconnectRequest request) {
        ConnectionCommand cmd = new ConnectionCommand();
        cmd.setConnectionNode((FastConnection) request.getConnectionEditPart()
                .getModel());

        ConnectionAnchor ctor = getFastEditPart().getSourceConnectionAnchor(
                request);
        cmd.setSource(getFastSubpart());
        cmd.setSourceTerminal(getFastEditPart().mapConnectionAnchorToTerminal(
                ctor));
        return cmd;
    }

    protected FastNodeFigure getNodeFigure() {
        return (FastNodeFigure) ((GraphicalEditPart) getHost()).getFigure();
    }
}
