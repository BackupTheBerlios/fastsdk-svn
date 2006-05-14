/**
 * 
 */
package org.fast.fastsdk.ui.views;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/**
 * @author …Ú»›÷€
 */
public abstract class AbstractFastViewPart extends ViewPart {

    public void createPartControl(Composite parent) {
        buildActions();
    }

    public void buildActions() {

    }

    /**
     * Convenience method for adding an action to the view part's toolbar
     * 
     * @param action
     */
    protected void addAction(IAction action) {
        IToolBarManager toolBar = getViewSite().getActionBars()
                .getToolBarManager();
        toolBar.add(action);
    }
}
