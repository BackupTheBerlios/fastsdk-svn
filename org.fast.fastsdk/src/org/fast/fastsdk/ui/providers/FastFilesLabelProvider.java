/**
 * 
 */
package org.fast.fastsdk.ui.providers;

import org.dom4j.Element;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.fast.fastsdk.core.editors.FastGraphicalEditor;
import org.fast.fastsdk.util.FastImagesUtil;

/**
 * @author …Ú»›÷€
 */
public class FastFilesLabelProvider implements ILabelProvider {
    public Image getImage(Object element) {
        Element file = (Element) element;
        if (file.getName().equals("Files"))
            return FastImagesUtil.createImage(FastGraphicalEditor.class,
                    "icons/dependencyFolder.gif");
        return FastImagesUtil.createImage(FastGraphicalEditor.class,
                "icons/file_obj.gif");
    }

    public String getText(Object element) {
        Element file = (Element) element;
        if (file.getName().equals("Files"))
            return file.getName();
        return ((Element) element).getTextTrim();
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
