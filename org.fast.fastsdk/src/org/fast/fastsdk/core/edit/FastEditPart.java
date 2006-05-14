/**
 *
 */
package org.fast.fastsdk.core.edit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.DropRequest;
import org.fast.fastsdk.core.figures.FastNodeFigure;
import org.fast.fastsdk.core.model.FastConnection;
import org.fast.fastsdk.core.model.FastSubpart;

/**
 * @author …Ú»›÷€
 */
public abstract class FastEditPart extends AbstractGraphicalEditPart implements
        NodeEditPart, PropertyChangeListener {

    private AccessibleEditPart acc;

    public void activate() {
        if (isActive())
            return;
        super.activate();
        getFastSubpart().addPropertyChangeListener(this);
    }

    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE,
                new FastElementEditPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
                new FastNodeEditPolicy());
    }

    abstract protected AccessibleEditPart createAccessible();

    /**
     * Makes the EditPart insensible to changes in the model by removing itself
     * from the model's list of listeners.
     */
    public void deactivate() {
        if (!isActive())
            return;
        super.deactivate();
        getFastSubpart().removePropertyChangeListener(this);
    }

    protected AccessibleEditPart getAccessibleEditPart() {
        if (acc == null)
            acc = createAccessible();
        return acc;
    }

    /**
     * Returns the model associated with this as a FastSubPart.
     * 
     * @return The model of this as a FastSubPart.
     */
    protected FastSubpart getFastSubpart() {
        return (FastSubpart) getModel();
    }

    /**
     * Returns a list of connections for which this is the source.
     * 
     * @return List of connections.
     */
    protected List getModelSourceConnections() {
        return getFastSubpart().getSourceConnections();
    }

    /**
     * Returns a list of connections for which this is the target.
     * 
     * @return List of connections.
     */
    protected List getModelTargetConnections() {
        return getFastSubpart().getTargetConnections();
    }

    /**
     * Returns the Figure of this, as a node type figure.
     * 
     * @return Figure as a NodeFigure.
     */
    protected FastNodeFigure getNodeFigure() {
        return (FastNodeFigure) getFigure();
    }

    /**
     * Returns the connection anchor for the given ConnectionEditPart's source.
     * 
     * @return ConnectionAnchor.
     */
    public ConnectionAnchor getSourceConnectionAnchor(
            ConnectionEditPart connEditPart) {
        FastConnection wire = (FastConnection) connEditPart.getModel();
        return getNodeFigure().getConnectionAnchor(wire.getSourceTerminal());
    }

    /**
     * Returns the connection anchor of a source connection which is at the
     * given point.
     * 
     * @return ConnectionAnchor.
     */
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        Point pt = new Point(((DropRequest) request).getLocation());
        return getNodeFigure().getSourceConnectionAnchorAt(pt);
    }

    /**
     * Returns the connection anchor for the given ConnectionEditPart's target.
     * 
     * @return ConnectionAnchor.
     */
    public ConnectionAnchor getTargetConnectionAnchor(
            ConnectionEditPart connEditPart) {
        FastConnection wire = (FastConnection) connEditPart.getModel();
        return getNodeFigure().getConnectionAnchor(wire.getTargetTerminal());
    }

    /**
     * Returns the connection anchor of a terget connection which is at the
     * given point.
     * 
     * @return ConnectionAnchor.
     */
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        Point pt = new Point(((DropRequest) request).getLocation());
        return getNodeFigure().getTargetConnectionAnchorAt(pt);
    }

    /**
     * Returns the name of the given connection anchor.
     * 
     * @return The name of the ConnectionAnchor as a String.
     */
    final protected String mapConnectionAnchorToTerminal(ConnectionAnchor c) {
        return getNodeFigure().getConnectionAnchorName(c);
    }

    /**
     * Handles changes in properties of this. It is activated through the
     * PropertyChangeListener. It updates children, source and target
     * connections, and the visuals of this based on the property changed.
     * 
     * @param evt
     *            Event which details the property change.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        String prop = evt.getPropertyName();
        if (FastSubpart.CHILDREN.equals(prop)) {
            if (evt.getOldValue() instanceof Integer)
                // new child
                addChild(createChild(evt.getNewValue()), ((Integer) evt
                        .getOldValue()).intValue());
            else
                // remove child
                removeChild((EditPart) getViewer().getEditPartRegistry().get(
                        evt.getOldValue()));
        } else if (FastSubpart.INPUTS.equals(prop))
            refreshTargetConnections();
        else if (FastSubpart.OUTPUTS.equals(prop))
            refreshSourceConnections();
        else if (prop.equals(FastSubpart.SIZE_PROP)
                || prop.equals(FastSubpart.LOCATION_PROP))
            refreshVisuals();
    }

    /**
     * Updates the visual aspect of this.
     */
    protected void refreshVisuals() {
        Point loc = getFastSubpart().getLocation();
        Dimension size = getFastSubpart().getSize();
        Rectangle r = new Rectangle(loc, size);

        ((GraphicalEditPart) getParent()).setLayoutConstraint(this,
                getFigure(), r);
    }
}
