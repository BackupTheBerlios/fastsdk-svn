/**
 * 
 */
package org.fast.fastsdk.ui.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.AlignmentRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.MatchHeightRetargetAction;
import org.eclipse.gef.ui.actions.MatchWidthRetargetAction;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.fast.fastsdk.core.editors.FastGraphicalEditor;

/**
 * @author …Ú»›÷€
 */
public class FastMultiPageActionBarContributor extends
        MultiPageEditorActionBarContributor {
    private ActionRegistry registry         = new ActionRegistry();

    /**
     * Contains the {@link RetargetAction}s that are registered as global
     * action handlers. We need to hold on to these so that we can remove them
     * as PartListeners in dispose().
     */
    private List           retargetActions  = new ArrayList();

    private List           globalActionKeys = new ArrayList();

    /**
     * Adds the given action to the action registry.
     * 
     * @param action
     *            the action to add
     */
    protected void addAction(IAction action) {
        getActionRegistry().registerAction(action);
    }

    /**
     * Indicates the existence of a global action identified by the specified
     * key. This global action is defined outside the scope of this contributor,
     * such as the Workbench's undo action, or an action provided by a workbench
     * ActionSet. The list of global action keys is used whenever the active
     * editor is changed ({@link #setActiveEditor(IEditorPart)}). Keys
     * provided here will result in corresponding actions being obtained from
     * the active editor's <code>ActionRegistry</code>, and those actions
     * will be registered with the ActionBars for this contributor. The editor's
     * action handler and the global action must have the same key.
     * 
     * @param key
     *            the key identifying the global action
     */
    protected void addGlobalActionKey(String key) {
        globalActionKeys.add(key);
    }

    /**
     * Adds the specified RetargetAction to this contributors
     * <code>ActionRegistry</code>. The RetargetAction is also added as a
     * <code>IPartListener</code> of the contributor's page. Also, the
     * retarget action's ID is flagged as a global action key, by calling {@link
     * #addGlobalActionKey(String)}.
     * 
     * @param action
     *            the retarget action being added
     */
    protected void addRetargetAction(RetargetAction action) {
        addAction(action);
        retargetActions.add(action);
        getPage().addPartListener(action);
        addGlobalActionKey(action.getId());
    }

    /**
     * Creates and initializes Actions managed by this contributor.
     */
    protected void buildActions() {
        addRetargetAction(new UndoRetargetAction());
        addRetargetAction(new RedoRetargetAction());

        addRetargetAction(new AlignmentRetargetAction(PositionConstants.LEFT));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.CENTER));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.RIGHT));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.TOP));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.MIDDLE));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.BOTTOM));

        addRetargetAction(new ZoomInRetargetAction());
        addRetargetAction(new ZoomOutRetargetAction());

        addRetargetAction(new MatchWidthRetargetAction());
        addRetargetAction(new MatchHeightRetargetAction());

        addRetargetAction(new RetargetAction(
                GEFActionConstants.TOGGLE_RULER_VISIBILITY,
                GEFMessages.ToggleRulerVisibility_Label, IAction.AS_CHECK_BOX));

        addRetargetAction(new RetargetAction(
                GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY,
                GEFMessages.ToggleSnapToGeometry_Label, IAction.AS_CHECK_BOX));

        addRetargetAction(new RetargetAction(
                GEFActionConstants.TOGGLE_GRID_VISIBILITY,
                GEFMessages.ToggleGrid_Label, IAction.AS_CHECK_BOX));
    }

    /**
     * @see org.eclipse.gef.ui.actions.ActionBarContributor#declareGlobalActionKeys()
     */
    protected void declareGlobalActionKeys() {
        addGlobalActionKey(ActionFactory.PRINT.getId());
        addGlobalActionKey(ActionFactory.SELECT_ALL.getId());
        addGlobalActionKey(ActionFactory.PASTE.getId());
        addGlobalActionKey(ActionFactory.DELETE.getId());
    }

    /**
     * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToMenu(org.eclipse.jface.action.IMenuManager)
     */
    public void contributeToMenu(IMenuManager menubar) {
        super.contributeToMenu(menubar);
        MenuManager editMenu = new MenuManager("&Edit",
                IWorkbenchActionConstants.M_EDIT);
        editMenu.add(getAction(ActionFactory.UNDO.getId()));
        editMenu.add(getAction(ActionFactory.REDO.getId()));
        menubar.insertAfter(IWorkbenchActionConstants.M_FILE, editMenu);

        MenuManager viewMenu = new MenuManager("&View");
        viewMenu.add(getAction(GEFActionConstants.ZOOM_IN));
        viewMenu.add(getAction(GEFActionConstants.ZOOM_OUT));

        viewMenu.add(new Separator());
        viewMenu.add(getAction(GEFActionConstants.TOGGLE_RULER_VISIBILITY));
        viewMenu.add(getAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY));
        viewMenu.add(getAction(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY));
        viewMenu.add(new Separator());

        viewMenu.add(getAction(GEFActionConstants.MATCH_WIDTH));
        viewMenu.add(getAction(GEFActionConstants.MATCH_HEIGHT));
        menubar.insertAfter(IWorkbenchActionConstants.M_EDIT, viewMenu);
    }

    /**
     * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToToolBar(org.eclipse.jface.action.IToolBarManager)
     */
    public void contributeToToolBar(IToolBarManager tbm) {
        tbm.add(getAction(ActionFactory.UNDO.getId()));
        tbm.add(getAction(ActionFactory.REDO.getId()));

        tbm.add(new Separator());
        tbm.add(getAction(GEFActionConstants.ALIGN_LEFT));
        tbm.add(getAction(GEFActionConstants.ALIGN_CENTER));
        tbm.add(getAction(GEFActionConstants.ALIGN_RIGHT));

        tbm.add(new Separator());
        tbm.add(getAction(GEFActionConstants.ALIGN_TOP));
        tbm.add(getAction(GEFActionConstants.ALIGN_MIDDLE));
        tbm.add(getAction(GEFActionConstants.ALIGN_BOTTOM));

        tbm.add(new Separator());
        tbm.add(getAction(GEFActionConstants.MATCH_WIDTH));
        tbm.add(getAction(GEFActionConstants.MATCH_HEIGHT));

        tbm.add(new Separator());

        String[] zoomStrings = new String[] { ZoomManager.FIT_ALL,
                ZoomManager.FIT_HEIGHT, ZoomManager.FIT_WIDTH };
        tbm.add(new ZoomComboContributionItem(getPage(), zoomStrings));
    }

    /**
     * Disposes the contributor. Removes all {@link RetargetAction}s that were
     * {@link org.eclipse.ui.IPartListener}s on the
     * {@link org.eclipse.ui.IWorkbenchPage} and disposes them. Also disposes
     * the action registry.
     * <P>
     * Subclasses may extend this method to perform additional cleanup.
     * 
     * @see org.eclipse.ui.part.EditorActionBarContributor#dispose()
     */
    public void dispose() {
        for (int i = 0; i < retargetActions.size(); i++) {
            RetargetAction action = (RetargetAction) retargetActions.get(i);
            getPage().removePartListener(action);
            action.dispose();
        }
        registry.dispose();
        retargetActions = null;
        registry = null;
    }

    /**
     * Retrieves an action from the action registry using the given ID.
     * 
     * @param id
     *            the ID of the sought action
     * @return <code>null</code> or the action if found
     */
    protected IAction getAction(String id) {
        return getActionRegistry().getAction(id);
    }

    /**
     * returns this contributor's ActionRegsitry.
     * 
     * @return the ActionRegistry
     */
    protected ActionRegistry getActionRegistry() {
        return registry;
    }

    /**
     * @see org.eclipse.ui.part.EditorActionBarContributor#init(IActionBars)
     */
    public void init(IActionBars bars) {
        buildActions();
        declareGlobalActionKeys();
        super.init(bars);
    }

    public void setActivePage(IEditorPart editor) {
        if (editor instanceof FastGraphicalEditor) {
            ActionRegistry registry = (ActionRegistry) editor
                    .getAdapter(ActionRegistry.class);
            IActionBars bars = getActionBars();
            for (int i = 0; i < globalActionKeys.size(); i++) {
                String id = (String) globalActionKeys.get(i);
                bars.setGlobalActionHandler(id, registry.getAction(id));
            }
            getActionBars().updateActionBars();
        }
    }
}
