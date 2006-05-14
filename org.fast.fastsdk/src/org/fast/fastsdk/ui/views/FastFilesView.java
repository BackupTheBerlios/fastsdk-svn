package org.fast.fastsdk.ui.views;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.fast.fastsdk.ui.listeners.OpenFileListener;
import org.fast.fastsdk.ui.providers.FastFilesContentProvider;
import org.fast.fastsdk.ui.providers.FastFilesLabelProvider;

public class FastFilesView extends AbstractFastViewPart {
    private TreeViewer filesView;

    private List       fileList;

    public FastFilesView() {
        setPartName("Files");
        fileList = new ArrayList();
    }

    public void createPartControl(Composite parent) {
        super.createPartControl(parent);
        filesView = new TreeViewer(parent, SWT.NONE);
        filesView.addDoubleClickListener(new OpenFileListener(getSite()
                .getWorkbenchWindow()));
        filesView.setContentProvider(new FastFilesContentProvider());
        filesView.setLabelProvider(new FastFilesLabelProvider());
        buildTree();
    }

    public void setFocus() {

    }

    public void addFile(File file, IEditorInput input) {
        if (!fileList.contains(file)) {
            fileList.add(file);
        }
        buildTree();
    }

    public void removeFile(File file) {

    }

    public List getFileList() {
        return fileList;
    }

    private void buildTree() {
        filesView.getTree().removeAll();
        Document doc = DocumentHelper.createDocument();
        Element mock = doc.addElement("mock");
        Element files = mock.addElement("Files");
        Iterator iter = fileList.iterator();
        while (iter.hasNext()) {
            File file = (File) iter.next();
            Element fileElement = files.addElement("file");
            fileElement.setText(file.getName());
            fileElement.addAttribute("absPath", file.getAbsolutePath());
        }
        filesView.setInput(mock);
    }
}
