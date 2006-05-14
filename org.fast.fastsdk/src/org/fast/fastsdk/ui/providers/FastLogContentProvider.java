/**
 * 
 */
package org.fast.fastsdk.ui.providers;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author …Ú»›÷€
 */
public class FastLogContentProvider implements IStructuredContentProvider {

    public Object[] getElements(Object inputElement) {
        List logEntries = (List) inputElement;
        if (!logEntries.isEmpty()) {
            return logEntries.toArray();
        } else {
            return new Object[0];
        }
    }

    public void dispose() {

    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

    }
}
