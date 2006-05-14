/**
 *
 */
package org.fast.fastsdk.core.edit;

import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.fast.fastsdk.core.model.FastDiagram;

/**
 * @author …Ú»›÷€
 */
public class FastContainerTreeEditPart extends FastTreeEditPart {
    /**
     * Constructor, which initializes this using the model given as input.
     */
    public FastContainerTreeEditPart(Object model) {
        super(model);
    }

    /**
     * Creates and installs pertinent EditPolicies.
     */
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.CONTAINER_ROLE,
                new FastContainerEditPolicy());
        installEditPolicy(EditPolicy.TREE_CONTAINER_ROLE,
                new FastTreeContainerEditPolicy());
        // If this editpart is the contents of the viewer, then it is not
        // deletable!
        if (getParent() instanceof RootEditPart)
            installEditPolicy(EditPolicy.COMPONENT_ROLE,
                    new RootComponentEditPolicy());
    }

    /**
     * Returns the model of this as a FastDiagram.
     * 
     * @return Model of this.
     */
    protected FastDiagram getFastDiagram() {
        return (FastDiagram) getModel();
    }

    /**
     * Returns the children of this from the model, as this is capable enough of
     * holding EditParts.
     * 
     * @return List of children.
     */
    protected List getModelChildren() {
        return getFastDiagram().getChildren();
    }
}
