/**
 * 
 */
package org.fast.fastsdk.ui.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * @author …Ú»›÷€
 */
public class UIModel implements Serializable {
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
}