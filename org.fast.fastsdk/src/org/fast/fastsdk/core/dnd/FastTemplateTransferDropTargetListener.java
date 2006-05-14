/**
 * 
 */
package org.fast.fastsdk.core.dnd;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.requests.CreationFactory;
import org.fast.fastsdk.core.model.FastElementFactory;

/**
 * @author …Ú»›÷€
 */
public class FastTemplateTransferDropTargetListener extends
        TemplateTransferDropTargetListener {
    public FastTemplateTransferDropTargetListener(EditPartViewer viewer) {
        super(viewer);
    }

    protected CreationFactory getFactory(Object template) {
        if (template instanceof String)
            return new FastElementFactory((String) template);
        return null;
    }
}
