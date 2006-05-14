/**
 * 
 */
package org.fast.fastsdk.ui.views;

import java.io.File;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.fast.fastsdk.core.editors.FastGraphicalEditor;
import org.fast.fastsdk.ui.providers.FastLogContentProvider;
import org.fast.fastsdk.ui.providers.FastLogLabelProvider;
import org.fast.fastsdk.util.logging.FastLogUtil;

/**
 * @author …Ú»›÷€
 */
public class FastLogView extends AbstractFastViewPart {
    private TableViewer table;

    private IAction     refreshAction;

    private IAction     removeAllAction;

    public void createPartControl(Composite parent) {
        super.createPartControl(parent);
        table = new TableViewer(parent, SWT.NONE);

        initViewer();

        addColumn("Time");
        addColumn("Level");
        addColumn("Location");
        addColumn("Message");

        updateLogView();
    }

    public void buildActions() {
        super.buildActions();
        refreshAction = new Action() {
            public void run() {
                updateLogView();
            }
        };
        refreshAction.setImageDescriptor(ImageDescriptor.createFromFile(
                FastGraphicalEditor.class, "icons/refresh_obj.gif"));
        refreshAction.setToolTipText("Refresh Logs");
        addAction(refreshAction);
        removeAllAction = new Action() {
            public void run() {
                FastLogUtil.clearLogs(new File("fastide.log"));
                updateLogView();
            }
        };
        removeAllAction.setImageDescriptor(ImageDescriptor.createFromFile(
                FastGraphicalEditor.class, "icons/delete.gif"));
        removeAllAction.setToolTipText("Clear Logs");
        addAction(removeAllAction);
    }

    public void setFocus() {

    }

    private void initViewer() {
        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(20, 75, true));
        layout.addColumnData(new ColumnWeightData(20, 75, true));
        layout.addColumnData(new ColumnWeightData(20, 75, true));
        layout.addColumnData(new ColumnWeightData(40, 75, true));
        table.getTable().setLayout(layout);
        table.getTable().setLinesVisible(true);
        table.getTable().setHeaderVisible(true);
        table.setContentProvider(new FastLogContentProvider());
        table.setLabelProvider(new FastLogLabelProvider());
    }

    private void addColumn(String title) {
        TableColumn messageColumn = new TableColumn(table.getTable(), SWT.NONE);
        messageColumn.setText(title);
    }

    private void updateLogView() {
        List logs = FastLogUtil.parse(new File("fastide.log"));
        table.setInput(logs);
    }
}
