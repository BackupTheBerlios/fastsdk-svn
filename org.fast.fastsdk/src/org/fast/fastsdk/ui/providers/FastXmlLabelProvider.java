/**
 * 
 */
package org.fast.fastsdk.ui.providers;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.fast.fastsdk.core.editors.FastGraphicalEditor;
import org.fast.fastsdk.util.FastImagesUtil;

/**
 * @author …Ú»›÷€
 */
public class FastXmlLabelProvider implements ITableLabelProvider {
    public void addListener(ILabelProviderListener listener) {
    }

    public void dispose() {
    }

    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    public void removeListener(ILabelProviderListener listener) {
    }

    public Image getColumnImage(Object element, int columnIndex) {
        if (columnIndex == 0)
            if (element instanceof Element) {
                return FastImagesUtil.createImage(FastGraphicalEditor.class,
                        "icons/enum.gif");
            } else {
                return FastImagesUtil.createImage(FastGraphicalEditor.class,
                        "icons/annotation.gif");
            }
        return null;
    }

    public String getColumnText(Object element, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return element instanceof Element ? ((Element) element)
                        .getName() : ((Attribute) element).getName();
            case 1:
                return element instanceof Element ? ((Element) element)
                        .getTextTrim() : ((Attribute) element).getValue();
            default:
                return "";
        }
    }
}
