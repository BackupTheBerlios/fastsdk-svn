/**
 *
 */
package org.fast.fastsdk.core.edit;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.fast.fastsdk.core.model.FastDiagram;
import org.fast.fastsdk.core.model.FastNode;

/**
 * @author …Ú»›÷€
 */
public class FastTreePartFactory implements EditPartFactory {

    /**
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
     *      java.lang.Object)
     */
    public EditPart createEditPart(EditPart context, Object model) {
        // TODO Auto-generated method stub
        if (model instanceof FastDiagram)
            return new FastContainerTreeEditPart(model);
        else if (model instanceof FastNode)
            return new FastTreeEditPart(model);
        return new FastTreeEditPart(model);
    }
}
