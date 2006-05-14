/**
 * 
 */
package org.fast.fastsdk.ui.providers;

import org.dom4j.Element;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author …Ú»›÷€
 */
public class FastFilesContentProvider implements ITreeContentProvider {
    public Object[] getChildren(Object parentElement) {
        return ((Element) parentElement).elements().toArray();
    }

    public Object getParent(Object element) {
        return ((Element) element).getParent();
    }

    public boolean hasChildren(Object element) {
        return !((Element) element).elements().isEmpty();
    }

    public Object[] getElements(Object inputElement) {
        return ((Element) inputElement).elements().toArray();
    }

    public void dispose() {
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }
}
