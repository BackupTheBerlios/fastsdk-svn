package org.fast.fastsdk.ui.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.fast.fastsdk.core.editors.FastEditorInput;
import org.fast.fastsdk.core.editors.FastGraphicalEditor;
import org.fast.fastsdk.core.model.FastDiagramFactory;
import org.fast.fastsdk.ui.views.FastFilesView;
import org.fast.fastsdk.util.FastSdkConstants;

public class NewFastDiagramAction extends Action {
    private IWorkbenchWindow window;

    public NewFastDiagramAction(IWorkbenchWindow window) {
        super("&New");

        this.window = window;
        setId("org.fast.fastsdk.actions.newAction");
        setActionDefinitionId("org.fast.fastsdk.actions.newAction");
        this.setImageDescriptor(ImageDescriptor.createFromFile(
                FastGraphicalEditor.class, "icons/newframe_wiz.gif"));
    }

    public void run() {
        String fileName = openDialog();
        if (fileName != null) {
            // Concat the file with a "fst" extension if one does not exist
            if (!fileName.endsWith(".fst"))
                fileName = fileName.concat(".fst");
            File file = new File(fileName);
            openEditor(file);
            writeObject(file);
        }
    }

    /**
     * <p>
     * Opens the file dialog and allow the user to enter a file name for the
     * file to be created.
     * </p>
     * 
     * @return The absolute path of the file selected.
     */
    private String openDialog() {
        FileDialog fileDialog = new FileDialog(window.getShell(), SWT.OPEN);
        fileDialog.setFilterExtensions(new String[] { "*.fst", "*.*" });
        fileDialog.setText("New...");
        return fileDialog.open();
    }

    /**
     * <p>
     * Writes an empty FAST diagram to the object output stream.
     * </p>
     * 
     * @param file
     *            The file that the diagram is to be written to.
     */
    private void writeObject(File file) {
        OutputStream fileStream = null;
        try {
            fileStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileStream);
            out.writeObject(FastDiagramFactory.getRootElement());
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * <p>
     * Opens the FAST editor for users to edit FAST diagrams.
     * </p>
     * 
     * @param file
     *            The file that will be opened in the editor.
     */
    private void openEditor(File file) {
        IWorkbenchPage page = window.getActivePage();
        Path path = new Path(file.getAbsolutePath());
        FastEditorInput input = new FastEditorInput(path);
        FastFilesView view = (FastFilesView) window.getActivePage().findView(
                FastSdkConstants.FASTFILESVIEW_ID);
        view.addFile(file, input);
        try {
            page.openEditor(input, FastSdkConstants.FASTEDITOR_ID, true);
        } catch (PartInitException e) {
            e.printStackTrace();
        }
    }
}
