/**
 *
 */
package org.fast.fastsdk.core.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * @author …Ú»›÷€
 */
public class DimensionPropertySource implements IPropertySource {

    public static String                   WIDTH_PROP  = "width"; //$NON-NLS-1$

    public static String                   HEIGHT_PROP = "height"; //$NON-NLS-1$

    protected static IPropertyDescriptor[] descriptors;

    static {
        PropertyDescriptor widthProp = new TextPropertyDescriptor(WIDTH_PROP,
                "Width");
        widthProp.setValidator(FastNumberCellEditorValidator.instance());
        PropertyDescriptor heightProp = new TextPropertyDescriptor(HEIGHT_PROP,
                "Height");
        heightProp.setValidator(FastNumberCellEditorValidator.instance());
        descriptors = new IPropertyDescriptor[] { widthProp, heightProp };
    }

    protected Dimension                    dimension   = null;

    public DimensionPropertySource(Dimension dimension) {
        this.dimension = dimension.getCopy();
    }

    public Object getEditableValue() {
        return dimension.getCopy();
    }

    public Object getPropertyValue(Object propName) {
        return getPropertyValue((String) propName);
    }

    public Object getPropertyValue(String propName) {
        if (HEIGHT_PROP.equals(propName)) {
            return new String(new Integer(dimension.height).toString());
        }
        if (WIDTH_PROP.equals(propName)) {
            return new String(new Integer(dimension.width).toString());
        }
        return null;
    }

    public void setPropertyValue(Object propName, Object value) {
        setPropertyValue((String) propName, value);
    }

    public void setPropertyValue(String propName, Object value) {
        if (HEIGHT_PROP.equals(propName)) {
            Integer newInt = new Integer((String) value);
            dimension.height = newInt.intValue();
        }
        if (WIDTH_PROP.equals(propName)) {
            Integer newInt = new Integer((String) value);
            dimension.width = newInt.intValue();
        }
    }

    public IPropertyDescriptor[] getPropertyDescriptors() {
        return descriptors;
    }

    public void resetPropertyValue(String propName) {
    }

    public void resetPropertyValue(Object propName) {
    }

    public boolean isPropertySet(Object propName) {
        return true;
    }

    public boolean isPropertySet(String propName) {
        if (HEIGHT_PROP.equals(propName) || WIDTH_PROP.equals(propName))
            return true;
        return false;
    }

    public String toString() {
        return new String("(" + dimension.width + "," + dimension.height + ")");//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
    }
}
