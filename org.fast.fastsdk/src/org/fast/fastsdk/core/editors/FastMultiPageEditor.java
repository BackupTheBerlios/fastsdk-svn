/**
 * 
 */
package org.fast.fastsdk.core.editors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.fast.fastsdk.core.model.FastDiagram;
import org.fast.fastsdk.ui.model.GenerationModel;
import org.fast.fastsdk.ui.providers.FastXmlContentProvider;
import org.fast.fastsdk.ui.providers.FastXmlLabelProvider;

/**
 * @author …Ú»›÷€
 */
public class FastMultiPageEditor extends MultiPageEditorPart implements
        PropertyChangeListener {

    private FastGraphicalEditor fastEditor;

    private TreeViewer          xmlView;

    private ImageFigure         unoptimized = new ImageFigure();

    private ImageFigure         optimized   = new ImageFigure();

    private FigureCanvas        unoptCanvas;

    private FigureCanvas        optCanvas;

    protected void createPages() {
        createFastGraphicalEditor();
        createFastXmlView();
        createUnoptimizedImage();
        createOptimizedImage();
    }

    private void createFastGraphicalEditor() {
        fastEditor = new FastGraphicalEditor();
        fastEditor.getGenerated().addPropertyChangeListener(this);
        try {
            int index = addPage(fastEditor, getEditorInput());
            setPageText(index, fastEditor.getTitle());
        } catch (PartInitException e) {
            e.printStackTrace();
        }
    }

    private void createFastXmlView() {
        xmlView = new TreeViewer(getContainer(), SWT.NONE);
        xmlView.getTree().setLinesVisible(true);
        xmlView.setContentProvider(new FastXmlContentProvider());
        xmlView.setLabelProvider(new FastXmlLabelProvider());
        TreeColumn column1 = new TreeColumn(xmlView.getTree(), SWT.LEFT);
        column1.setWidth(300);
        TreeColumn column2 = new TreeColumn(xmlView.getTree(), SWT.LEFT);
        column2.setWidth(300);
        updateTreeItems();
        int index = addPage(xmlView.getTree());
        setPageText(index, "Xml View");
    }

    private void createUnoptimizedImage() {
        unoptCanvas = new FigureCanvas(getContainer(), SWT.NONE);

        unoptCanvas.setContents(unoptimized);
        int index = addPage(unoptCanvas);
        setPageText(index, "Unoptimized Image");
    }

    private void createOptimizedImage() {
        optCanvas = new FigureCanvas(getContainer(), SWT.NONE);

        optCanvas.setContents(optimized);
        int index = addPage(optCanvas);
        setPageText(index, "Optimized Image");
    }

    private void updateTreeItems() {
        xmlView.getTree().removeAll();
        FastDiagram diagram = fastEditor.getDiagram();
        Document diagDoc = DocumentHelper.createDocument();
        Element mock = diagDoc.addElement("mock");
        Element root = mock.addElement("diagram");
        diagram.getXml(root);
        xmlView.setInput(mock);
    }

    public void doSave(IProgressMonitor monitor) {
        getEditor(0).doSave(monitor);
        updateTreeItems();
    }

    public void doSaveAs() {
        getEditor(0).doSaveAs();
        updateTreeItems();
    }

    public boolean isSaveAsAllowed() {
        return true;
    }

    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals(GenerationModel.GENERATED_PROP)) {
            IPath extensionRemoved = ((FastEditorInput) fastEditor
                    .getEditorInput()).getPath().removeFileExtension();
            String unoptimizedPath = extensionRemoved.addFileExtension(
                    "fss.unopt.png").toOSString();
            String optimizedPath = extensionRemoved.addFileExtension(
                    "fss.opt.png").toOSString();
            unoptimized.setImage(createImage(unoptimizedPath));
            optimized.setImage(createImage(optimizedPath));
            unoptCanvas.setContents(unoptimized);
            optCanvas.setContents(optimized);
        }
    }

    private Image createImage(String path) {
        FileInputStream input = null;
        try {
            input = new FileInputStream(path);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        Image image = new Image(null, input);
        return image;
    }

    public void setGenerated(boolean generated) {
        fastEditor.getGenerated().setGenerated(generated);
    }

    public Object getAdapter(Class adapter) {
        // TODO Auto-generated method stub
        return fastEditor.getAdapter(adapter);
    }
}
