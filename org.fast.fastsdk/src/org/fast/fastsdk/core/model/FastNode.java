/**
 *
 */
package org.fast.fastsdk.core.model;

import java.util.Iterator;

import org.dom4j.Element;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.fast.fastsdk.util.FastMessagesUtil;

/**
 * @author …Ú»›÷€
 */
public abstract class FastNode extends FastSubpart {
    private static int                     count;

    private String                         name;

    protected static IPropertyDescriptor[] newDescriptors   = null;

    public static String                   NAME_PROP        = "name";

    static final long                      serialVersionUID = 1;

    static {
        PropertyDescriptor pValueProp = new TextPropertyDescriptor(NAME_PROP,
                FastMessagesUtil.getMessage("pd.simple.name"));
        if (descriptors != null) {
            newDescriptors = new IPropertyDescriptor[descriptors.length + 1];
            for (int i = 0; i < descriptors.length; i++)
                newDescriptors[i] = descriptors[i];
            newDescriptors[descriptors.length] = pValueProp;
        } else
            newDescriptors = new IPropertyDescriptor[] { pValueProp };
    }

    public FastNode() {
        name = "any";
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
        firePropertyChange(NAME_PROP, null, name);
    }

    /**
     * @see com.fastide.editors.gef.models.FastSubpart#getNewId()
     */
    protected String getNewId() {
        return Integer.toString(count++);
    }

    /**
     * @see org.fastsdk.core.model.FastSubpart#getIconImage()
     */
    public Image getIconImage() {
        return null;
    }

    /**
     * @see org.fastsdk.core.model.FastSubpart#setPropertyValue(java.lang.Object,
     *      java.lang.Object)
     */
    public void setPropertyValue(Object id, Object value) {
        if (NAME_PROP.equals(id)) {
            name = (String) value;
        } else
            super.setPropertyValue(id, value);
    }

    /**
     * @see org.fastsdk.core.model.FastSubpart#getSize()
     */
    public Object getPropertyValue(Object param) {
        if (NAME_PROP.equals(param))
            return name;
        else
            return super.getPropertyValue(param);
    }

    public IPropertyDescriptor[] getPropertyDescriptors() {
        return newDescriptors;
    }

    public boolean hasInput() {
        return !inputs.isEmpty();
    }

    /**
     * @see org.fastsdk.core.model.FastSubpart#getXml(org.w3c.dom.Document)
     */
    public Element getXml(Element parent) {
        Element simple = super.getXml(parent);

        simple.addAttribute("id", getId());

        Element temp = simple.addElement("name");
        if (name == null)
            name = "";
        temp.setText(getName());

        temp = simple.addElement("inputs");
        Iterator iter = inputs.values().iterator();
        while (iter.hasNext()) {
            FastConnection conn = (FastConnection) iter.next();
            conn.getXml(temp);
        }

        temp = simple.addElement("outputs");
        iter = outputs.iterator();
        while (iter.hasNext()) {
            FastConnection conn = (FastConnection) iter.next();
            conn.getXml(temp);
        }

        return simple;
    }

    public abstract String getFast();
}
