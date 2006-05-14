/**
 *
 */
package org.fast.fastsdk.core.model;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * @author …Ú»›÷€
 */
public class LocationPropertySource implements IPropertySource {

    public static String                   XPOS_PROP = "xPos"; //$NON-NLS-1$

    public static String                   YPOS_PROP = "yPos"; //$NON-NLS-1$

    protected static IPropertyDescriptor[] descriptors;

    static {
        PropertyDescriptor xProp = new TextPropertyDescriptor(XPOS_PROP, "X");
        xProp.setValidator(FastNumberCellEditorValidator.instance());
        PropertyDescriptor yProp = new TextPropertyDescriptor(YPOS_PROP, "Y");
        yProp.setValidator(FastNumberCellEditorValidator.instance());
        descriptors = new IPropertyDescriptor[] { xProp, yProp };
    }

    protected Point                        point     = null;

    public LocationPropertySource(Point point) {
        this.point = point.getCopy();
    }

    public Object getEditableValue() {
        return point.getCopy();
    }

    public IPropertyDescriptor[] getPropertyDescriptors() {
        return descriptors;
    }

    public Object getPropertyValue(Object propName) {
        if (XPOS_PROP.equals(propName)) {
            return new String(new Integer(point.x).toString());
        }
        if (YPOS_PROP.equals(propName)) {
            return new String(new Integer(point.y).toString());
        }
        return null;
    }

    public boolean isPropertySet(Object propName) {
        return XPOS_PROP.equals(propName) || YPOS_PROP.equals(propName);
    }

    public void resetPropertyValue(Object propName) {
    }

    public void setPropertyValue(Object propName, Object value) {
        if (XPOS_PROP.equals(propName)) {
            Integer newInt = new Integer((String) value);
            point.x = newInt.intValue();
        }
        if (YPOS_PROP.equals(propName)) {
            Integer newInt = new Integer((String) value);
            point.y = newInt.intValue();
        }
    }

    public String toString() {
        return new String("[" + point.x + "," + point.y + "]");//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
    }
}
