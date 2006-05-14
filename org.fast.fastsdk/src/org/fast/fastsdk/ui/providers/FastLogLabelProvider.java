/**
 * 
 */
package org.fast.fastsdk.ui.providers;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.fast.fastsdk.util.logging.FastLogEntry;

/**
 * @author …Ú»›÷€
 */
public class FastLogLabelProvider implements ITableLabelProvider {

    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    public String getColumnText(Object element, int columnIndex) {
        FastLogEntry entry = (FastLogEntry) element;
        return entry.getValue(columnIndex);
    }

    public void addListener(ILabelProviderListener listener) {

    }

    public void dispose() {

    }

    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    public void removeListener(ILabelProviderListener listener) {

    }
}
