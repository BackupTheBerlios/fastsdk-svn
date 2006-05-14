/**
 *
 */
package org.fast.fastsdk.core.dnd;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.AbstractTransferDragSourceListener;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;

/**
 * @author …Ú»›÷€
 */
public class FileTransferDragSourceListener extends
        AbstractTransferDragSourceListener {

    public FileTransferDragSourceListener(EditPartViewer viewer) {
        super(viewer, TextTransfer.getInstance());
    }

    public FileTransferDragSourceListener(EditPartViewer viewer, Transfer xfer) {
        super(viewer, xfer);
    }

    public void dragSetData(DragSourceEvent event) {
        event.data = "Some text"; //$NON-NLS-1$
    }

    public void dragStart(DragSourceEvent event) {
        event.doit = false;
    }
}
