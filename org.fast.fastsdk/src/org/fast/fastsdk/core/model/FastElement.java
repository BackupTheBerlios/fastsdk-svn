/**
 *
 */
package org.fast.fastsdk.core.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * <p>
 * Class <code>FastElement</code> is the base class for all model classes.
 * </p>
 * It maintains the job of:
 * <ol>
 * <li>Setting properties of the model.</li>
 * <li>Updating inputs and outputs connecting the model.</li>
 * <li>Generating XML and FST output.</li>
 * <li>Firing property changes.</li>
 * </ol>
 * 
 * @author …Ú»›÷€
 */
public abstract class FastElement implements IPropertySource, Cloneable,
        Serializable, OutputModel {

    /**
     * The property name for <code>inputs</code> property.
     */
    public static final String                INPUTS           = "inputs";

    /**
     * The property name for <code>outputs</code> property.
     */
    public static final String                OUTPUTS          = "outputs";

    /**
     * The property name for <code>children</code> property.
     */
    public static final String                CHILDREN         = "Children";

    /**
     * <code>PropertyChangeSupport</code> for firing notifying
     * <code>PropertyChangeListener</code>s of value changes.
     */
    protected transient PropertyChangeSupport listeners        = new PropertyChangeSupport(
                                                                       this);

    static final long                         serialVersionUID = 1;

    protected void firePropertyChange(String prop, Object old, Object newValue) {
        listeners.firePropertyChange(prop, old, newValue);
    }

    protected void fireChildAdded(String prop, Object child, Object index) {
        listeners.firePropertyChange(prop, index, child);
    }

    protected void fireChildRemoved(String prop, Object child) {
        listeners.firePropertyChange(prop, child, null);
    }

    protected void fireStructureChange(String prop, Object child) {
        listeners.firePropertyChange(prop, null, child);
    }

    /**
     * <p>
     * <code>FastElement</code> itself is the editable value, therefore
     * returning <code>this</code> as a reference to the object itself.
     * </p>
     * 
     * @return The editable value
     * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
     */
    public Object getEditableValue() {
        return this;
    }

    /**
     * <p>
     * <code>FastElement</code> does not have any property to be edited by the
     * property sheet, therefore a zero content property descriptor is returned.
     * </p>
     * 
     * @return The array of property descriptors
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
        return new IPropertyDescriptor[0];
    }

    /**
     * <p>
     * <code>FastElement</code> does not have any property to be edited by the
     * property sheet, therefore no manipulation needs to be done here.
     * </p>
     * 
     * @return The property's value
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
     */
    public Object getPropertyValue(Object id) {
        return null;
    }

    /**
     * <p>
     * Always true for property is always set in <code>FastElement</code>
     * </p>
     * 
     * @return Always true
     * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
     */
    public boolean isPropertySet(Object id) {
        return true;
    }

    /**
     * <p>
     * No property needs to be reset.
     * </p>
     * 
     * @param id
     *            The property of the id.
     * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
     */
    public void resetPropertyValue(Object id) {

    }

    /**
     * <p>
     * No property needs to be set.
     * </p>
     * 
     * @param id
     *            The id of the property.
     * @param value
     *            The value of the property.
     * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object,
     *      java.lang.Object)
     */
    public void setPropertyValue(Object id, Object value) {

    }

    /**
     * <p>
     * Read in the object from the binary input stream.
     * </p>
     * 
     * @param in
     *            the input stream to be read from.
     * @throws IOException
     *             when I/O exception occurs.
     * @throws ClassNotFoundException
     *             when the class specified in the input stream can't be
     *             resolved.
     */
    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        in.defaultReadObject();
        listeners = new PropertyChangeSupport(this);
    }

    /**
     * <p>
     * Removes the specified listener from the list of
     * <code>PropertyChangeListener</code>s.
     * </p>
     * 
     * @param listener
     *            the <code>PropertyChangeListener</code> to be removed.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        listeners.removePropertyChangeListener(listener);
    }

    /**
     * <p>
     * Adds a <code>PropertyChangeListener</code> to the class.
     * </p>
     * If the same listener was added, then the newly added one is ignored.
     * 
     * @param listener
     *            the <code>PropertyChangeListener</code> to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }

    /**
     * <p>
     * Subclasses must implement this method in order to update the inputs and
     * outputs.
     * </p>
     */
    public void update() {

    }
}
