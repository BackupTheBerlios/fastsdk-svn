/**
 * 
 */
package org.fast.fastsdk.ui.providers;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author …Ú»›÷€
 */
public class FastXmlContentProvider implements ITreeContentProvider {

    public void dispose() {
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

    public Object[] getChildren(Object parentElement) {
        Element parent = (Element) parentElement;
        List all = parent.attributes();
        all.addAll(parent.elements());
        return all.toArray();
    }

    public Object getParent(Object element) {
        if (element instanceof Element) {
            return ((Element) element).getParent();
        }
        return null;
    }

    public boolean hasChildren(Object element) {
        if (element instanceof Attribute)
            return false;
        else if (element instanceof Element) {
            return !((Element) element).elements().isEmpty();
        } else {
            return false;
        }
    }

    public Object[] getElements(Object inputElement) {
        return ((Element) inputElement).elements().toArray();
    }
}
