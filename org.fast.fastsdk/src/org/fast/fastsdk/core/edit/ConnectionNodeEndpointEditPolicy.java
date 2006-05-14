/**
 *
 */
package org.fast.fastsdk.core.edit;

import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

/**
 * @author …Ú»›÷€
 */
public class ConnectionNodeEndpointEditPolicy extends
        ConnectionEndpointEditPolicy {
    protected void addSelectionHandles() {
        super.addSelectionHandles();
        getConnectionFigure().setLineWidth(2);
    }

    protected PolylineConnection getConnectionFigure() {
        return (PolylineConnection) ((GraphicalEditPart) getHost()).getFigure();
    }

    protected void removeSelectionHandles() {
        super.removeSelectionHandles();
        getConnectionFigure().setLineWidth(0);
    }
}
