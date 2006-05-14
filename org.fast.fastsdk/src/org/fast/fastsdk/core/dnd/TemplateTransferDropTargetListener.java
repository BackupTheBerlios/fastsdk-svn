/**
 * 
 */
package org.fast.fastsdk.core.dnd;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.swt.dnd.DND;

/**
 * @author …Ú»›÷€
 */
public abstract class TemplateTransferDropTargetListener extends
        AbstractTransferDropTargetListener {

    /**
     * Constructs a listener on the specified viewer.
     * 
     * @param viewer
     *            the EditPartViewer
     */
    public TemplateTransferDropTargetListener(EditPartViewer viewer) {
        super(viewer, TemplateTransfer.getInstance());
    }

    /**
     * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#createTargetRequest()
     */
    protected Request createTargetRequest() {
        // Look at the data on templatetransfer.
        // Create factory
        CreateRequest request = new CreateRequest();
        request.setFactory(getFactory(TemplateTransfer.getInstance()
                .getTemplate()));
        return request;
    }

    /**
     * A helper method that casts the target Request to a CreateRequest.
     * 
     * @return CreateRequest
     */
    protected final CreateRequest getCreateRequest() {
        return ((CreateRequest) getTargetRequest());
    }

    /**
     * Returns the appropriate Factory object to be used for the specified
     * template. This Factory is used on the CreateRequest that is sent to the
     * target EditPart.
     * 
     * @param template
     *            the template Object
     * @return a Factory
     */
    protected abstract CreationFactory getFactory(Object template);

    /**
     * The purpose of a template is to be copied. Therefore, the drop operation
     * can't be anything but <code>DND.DROP_COPY</code>.
     * 
     * @see AbstractTransferDropTargetListener#handleDragOperationChanged()
     */
    protected void handleDragOperationChanged() {
        getCurrentEvent().detail = DND.DROP_COPY;
        super.handleDragOperationChanged();
    }

    /**
     * The purpose of a template is to be copied. Therefore, the Drop operation
     * is set to <code>DND.DROP_COPY</code> by default.
     * 
     * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#handleDragOver()
     */
    protected void handleDragOver() {
        getCurrentEvent().detail = DND.DROP_COPY;
        getCurrentEvent().feedback = DND.FEEDBACK_SCROLL | DND.FEEDBACK_EXPAND;
        super.handleDragOver();
    }

    /**
     * Overridden to select the created object.
     * 
     * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#handleDrop()
     */
    protected void handleDrop() {
        super.handleDrop();
        selectAddedObject();
    }

    private void selectAddedObject() {
        Object model = getCreateRequest().getNewObject();
        if (model == null)
            return;
        EditPartViewer viewer = getViewer();
        viewer.getControl().forceFocus();
        Object editpart = viewer.getEditPartRegistry().get(model);
        if (editpart instanceof EditPart) {
            // Force a layout first.
            getViewer().flush();
            viewer.select((EditPart) editpart);
        }
    }

    /**
     * Assumes that the target request is a {@link CreateRequest}.
     */
    protected void updateTargetRequest() {
        CreateRequest request = getCreateRequest();
        request.setLocation(getDropLocation());
    }
}
