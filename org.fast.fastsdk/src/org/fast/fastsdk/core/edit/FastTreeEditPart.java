/**
 *
 */
package org.fast.fastsdk.core.edit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.fast.fastsdk.core.model.FastDiagram;
import org.fast.fastsdk.core.model.FastSubpart;

/**
 * @author …Ú»›÷€
 */
public class FastTreeEditPart extends AbstractTreeEditPart implements
        PropertyChangeListener {

    /**
     * Constructor initializes this with the given model.
     * 
     * @param model
     *            Model for this.
     */
    public FastTreeEditPart(Object model) {
        super(model);
    }

    public void activate() {
        super.activate();
        getFastSubpart().addPropertyChangeListener(this);
    }

    /**
     * Creates and installs pertinent EditPolicies for this.
     */
    protected void createEditPolicies() {
        EditPolicy component;
        component = new FastElementEditPolicy();
        installEditPolicy(EditPolicy.COMPONENT_ROLE, component);
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
                new FastTreeEditPolicy());
    }

    public void deactivate() {
        getFastSubpart().removePropertyChangeListener(this);
        super.deactivate();
    }

    /**
     * Returns the model of this as a FastSubPart.
     * 
     * @return Model of this.
     */
    protected FastSubpart getFastSubpart() {
        return (FastSubpart) getModel();
    }

    /**
     * Returns <code>null</code> as a Tree EditPart holds no children under
     * it.
     * 
     * @return <code>null</code>
     */
    protected List getModelChildren() {
        return Collections.EMPTY_LIST;
    }

    public void propertyChange(PropertyChangeEvent change) {
        if (change.getPropertyName().equals(FastDiagram.CHILDREN)) {
            if (change.getOldValue() instanceof Integer)
                // new child
                addChild(createChild(change.getNewValue()), ((Integer) change
                        .getOldValue()).intValue());
            else
                // remove child
                removeChild((EditPart) getViewer().getEditPartRegistry().get(
                        change.getOldValue()));
        } else
            refreshVisuals();
    }

    /**
     * Refreshes the Widget of this based on the property given to update. All
     * major properties are updated irrespective of the property input.
     * 
     * @param property
     *            Property to be refreshed.
     */
    protected void refreshVisuals() {
        if (getWidget() instanceof Tree)
            return;
        Image image = getFastSubpart().getIcon();
        TreeItem item = (TreeItem) getWidget();
        if (image != null)
            image.setBackground(item.getParent().getBackground());
        setWidgetImage(image);
        setWidgetText(getFastSubpart().toString());
    }
}
