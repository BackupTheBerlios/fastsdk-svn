/**
 * 
 */
package org.fast.fastsdk.ui.listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Iterator;

import org.dom4j.Element;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.fast.fastsdk.core.editors.FastEditorInput;
import org.fast.fastsdk.core.model.FastDiagram;
import org.fast.fastsdk.util.FastSdkConstants;

/**
 * @author …Ú»›÷€
 */
public class OpenFileListener implements IDoubleClickListener {
    private IWorkbenchWindow window;

    public OpenFileListener(IWorkbenchWindow window) {
        this.window = window;
    }

    public void doubleClick(DoubleClickEvent event) {
        TreeViewer files = (TreeViewer) event.getSource();
        IStructuredSelection selection = (IStructuredSelection) files
                .getSelection();
        File file = null;
        Iterator iter = selection.iterator();
        if (iter.hasNext()) {
            Element fileElement = (Element) iter.next();
            String absPath = fileElement.attributeValue("absPath");
            if (absPath != null)
                file = new File(fileElement.attributeValue("absPath"));
        }
        try {
            FastDiagram diagram = getDiagram(file);
            openEditor(diagram, file);
        } catch (PartInitException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Obtain the FAST diagram from the specific file given.
     * 
     * @param file
     *            The file that the FAST diagram is obtained
     * @return The FAST Diagram obtained from the file
     * @throws IOException
     */
    private FastDiagram getDiagram(File file) throws IOException {
        FastDiagram diagram = null;
        if (file != null) {
            InputStream fileStream = null;
            fileStream = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileStream);
            try {
                diagram = (FastDiagram) in.readObject();
            } catch (ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        }
        return diagram;
    }

    private void openEditor(FastDiagram diagram, File file)
            throws PartInitException {
        if (file != null) {
            IWorkbenchPage page = window.getActivePage();
            Path path = new Path(file.getAbsolutePath());
            FastEditorInput input = new FastEditorInput(path);
            input.setDiagram(diagram);
            page.openEditor(input, FastSdkConstants.FASTEDITOR_ID);
        }
    }
}
