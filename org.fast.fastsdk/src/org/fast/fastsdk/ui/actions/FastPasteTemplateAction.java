/**
 *
 */
package org.fast.fastsdk.ui.actions;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.ui.IEditorPart;
import org.fast.fastsdk.core.model.FastElementFactory;

/**
 * @author …Ú»›÷€
 */
public class FastPasteTemplateAction extends PasteTemplateAction {

    /**
     * Constructor for LogicPasteTemplateAction.
     * 
     * @param editor
     */
    public FastPasteTemplateAction(IEditorPart editor) {
        super(editor);
    }

    /**
     * @see org.eclipse.gef.examples.logicdesigner.actions.PasteTemplateAction#getFactory(java.lang.Object)
     */
    protected CreationFactory getFactory(Object template) {
        if (template instanceof String)
            return new FastElementFactory((String) template);
        return null;
    }

    /**
     * @see org.eclipse.gef.examples.logicdesigner.actions.PasteTemplateAction#getPasteLocation(GraphicalEditPart)
     */
    protected Point getPasteLocation(GraphicalEditPart container) {
        Point result = new Point(10, 10);
        IFigure fig = container.getContentPane();
        result.translate(fig.getClientArea(Rectangle.SINGLETON).getLocation());
        fig.translateToAbsolute(result);
        return result;
    }
}
