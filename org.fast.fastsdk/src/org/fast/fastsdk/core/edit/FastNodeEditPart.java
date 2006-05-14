/**
 *
 */
package org.fast.fastsdk.core.edit;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.AccessibleAnchorProvider;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.fast.fastsdk.core.figures.EndNodeFigure;
import org.fast.fastsdk.core.figures.FastNodeFigure;
import org.fast.fastsdk.core.figures.FunctionNodeFigure;
import org.fast.fastsdk.core.figures.JoinpointNodeFigure;
import org.fast.fastsdk.core.figures.PredicateNodeFigure;
import org.fast.fastsdk.core.figures.StartNodeFigure;
import org.fast.fastsdk.core.model.FastNode;
import org.fast.fastsdk.core.model.FunctionNode;
import org.fast.fastsdk.core.model.JoinpointNode;
import org.fast.fastsdk.core.model.PredicateNode;
import org.fast.fastsdk.core.model.StartNode;

/**
 * @author …Ú»›÷€
 */
public class FastNodeEditPart extends FastEditPart {
    private FastNodeNameEditManager manager;

    /**
     * @see org.fastsdk.core.edit.FastEditPart#createAccessible()
     */
    protected AccessibleEditPart createAccessible() {
        // TODO Auto-generated method stub
        return new AccessibleGraphicalEditPart() {
            public void getValue(AccessibleControlEvent e) {
                // TODO Auto-generated method stub
                e.result = getFastNode().getName();
            }

            public void getName(AccessibleEvent e) {
                e.result = getFastNode().toString();
            }
        };
    }

    protected void createEditPolicies() {
        // TODO Auto-generated method stub
        super.createEditPolicies();
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
                new FastNodeDirectEditPolicy());
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        // TODO Auto-generated method stub
        if (getModel() instanceof FunctionNode)
            return new FunctionNodeFigure();
        else if (getModel() instanceof PredicateNode)
            return new PredicateNodeFigure();
        else if (getModel() instanceof JoinpointNode)
            return new JoinpointNodeFigure();
        else if (getModel() instanceof StartNode)
            return new StartNodeFigure();
        else
            return new EndNodeFigure();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class key) {
        // TODO Auto-generated method stub
        if (key == AccessibleAnchorProvider.class)
            return new DefaultAccessibleAnchorProvider() {
                public List getSourceAnchorLocations() {
                    List list = new ArrayList();
                    Vector sourceAnchors = getNodeFigure()
                            .getSourceConnectionAnchors();
                    for (int i = 0; i < sourceAnchors.size(); i++) {
                        ConnectionAnchor anchor = (ConnectionAnchor) sourceAnchors
                                .get(i);
                        list.add(anchor.getReferencePoint()
                                .getTranslated(0, -3));
                    }
                    return list;
                }

                public List getTargetAnchorLocations() {
                    List list = new ArrayList();
                    Vector targetAnchors = getNodeFigure()
                            .getTargetConnectionAnchors();
                    for (int i = 0; i < targetAnchors.size(); i++) {
                        ConnectionAnchor anchor = (ConnectionAnchor) targetAnchors
                                .get(i);
                        list.add(anchor.getReferencePoint());
                    }
                    return list;
                }
            };
        return super.getAdapter(key);
    }

    public FastNode getFastNode() {
        return (FastNode) getModel();
    }

    private void performDirectEdit() {
        if (manager == null)
            manager = new FastNodeNameEditManager(this,
                    new NodeCellEditorLocator((FastNodeFigure) getFigure()));
        manager.show();
    }

    public void performRequest(Request request) {
        if (request.getType() == RequestConstants.REQ_DIRECT_EDIT)
            performDirectEdit();
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equalsIgnoreCase(FastNode.NAME_PROP))
            refreshVisuals();
        else
            super.propertyChange(evt);
    }

    protected void refreshVisuals() {
        ((FastNodeFigure) getFigure()).setName(getFastNode().getName());
        super.refreshVisuals();
    }
}
