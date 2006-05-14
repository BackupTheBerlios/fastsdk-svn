/**
 *
 */
package org.fast.fastsdk.core.model;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.dom4j.Element;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.fast.fastsdk.util.FastMessagesUtil;

/**
 * @author …Ú»›÷€
 */
public abstract class FastSubpart extends FastElement implements
        IPropertySource, Cloneable, OutputModel, Serializable {
    /**
     * Id of the subpart.
     */
    private String                         id;

    /**
     * Vertical guide of the subpart.
     */
    private FastGuide                      verticalGuide;

    /**
     * Horizontal guide of the subpart.
     */
    private FastGuide                      horizontalGuide;

    /**
     * Map containing the inputs with the input's terminal as the key.
     */
    protected Hashtable                    inputs           = new Hashtable(7);

    /**
     * Property for maintaining the location of the subpart in the canvas.
     */
    protected Point                        location         = new Point(0, 0);

    /**
     * Vector for containing the outputs of the subpart.
     */
    protected Vector                       outputs          = new Vector(4, 4);

    /**
     * Property for maintaining the size of the subpart.
     */
    protected Dimension                    size             = new Dimension(-1,
                                                                    -1);

    /**
     * Maximum input size for the subpart
     */
    protected int                          maxInput;

    /**
     * Maximum output size for the subpart
     */
    protected int                          maxOutput;

    /**
     * Property descriptors for this subpart.
     */
    protected static IPropertyDescriptor[] descriptors      = null;

    /**
     * Property name for size property.
     */
    public static final String             SIZE_PROP        = "size";

    /**
     * Property name for location property.
     */
    public static final String             LOCATION_PROP    = "location";

    static final long                      serialVersionUID = 1;

    static {
        descriptors = new IPropertyDescriptor[] {
            new PropertyDescriptor(SIZE_PROP, FastMessagesUtil
                    .getMessage("pd.subpart.size")),
            new PropertyDescriptor(LOCATION_PROP, FastMessagesUtil
                    .getMessage("pd.subpart.location")) };
    }

    /**
     * <p>
     * Default constructor that sets the ID of the subpart
     * </p>
     */
    public FastSubpart() {
        setId(getNewId());
    }

    /**
     * <p>
     * Subclasses implement this method to build the image to be displayed in
     * the palette.
     * </p>
     * 
     * @return The image of the subpart to be displayed in the palette.
     */
    public abstract Image getIconImage();

    /**
     * <p>
     * Subclasses implement this method to generate their own style of id.
     * </p>
     * 
     * @return The new id of the subpart.
     */
    protected abstract String getNewId();

    /**
     * <p>
     * Adds an input to the subpart by putting a connection into the inputs
     * hashtable with the target terminal as the key, then updates the subpart's
     * model
     * </p>
     * 
     * @see org.fastsdk.core.model.FastElement#update()
     * @param connection
     *            The connection to be put into the inputs hashtable
     */
    public void connectInput(FastConnection connection) {
        inputs.put(connection.getTargetTerminal(), connection);
        update();
        fireStructureChange(INPUTS, connection);
    }

    /**
     * <p>
     * Adds an output to the subpart's output vector and update the model
     * </p>
     * 
     * @see org.fastsdk.core.model.FastElement#update()
     * @param connection
     *            The conneciton to be added to the outputs vector
     */
    public void connectOutput(FastConnection connection) {
        outputs.addElement(connection);
        update();
        fireStructureChange(OUTPUTS, connection);
    }

    /**
     * <p>
     * Removes the input from the inputs hastable with the give target terminal
     * and updates the model
     * </p>
     * 
     * @see org.fastsdk.core.model.FastElement#update()
     * @param connection
     *            The connection to be removed
     */
    public void disconnectInput(FastConnection connection) {
        inputs.remove(connection.getTargetTerminal());
        update();
        fireStructureChange(INPUTS, connection);
    }

    /**
     * <p>
     * Removes the output from the outputs vector and updates the model
     * </p>
     * 
     * @see org.fastsdk.core.model.FastElement#update()
     * @param connection
     *            The output to be removed
     */
    public void disconnectOutput(FastConnection connection) {
        outputs.removeElement(connection);
        update();
        fireStructureChange(OUTPUTS, connection);
    }

    /**
     * <p>
     * Return all connections connected to this subpart, both inputs and
     * outputs.
     * </p>
     * 
     * @see org.fastsdk.core.model.commands.ConnectionCommand#canExecute()
     * @return The vector of connections that contain both the outputs and
     *         inputs
     */
    public Vector getConnections() {
        Vector v = (Vector) outputs.clone();
        Enumeration ins = inputs.elements();
        while (ins.hasMoreElements())
            v.addElement(ins.nextElement());
        return v;
    }

    public FastGuide getHorizontalGuide() {
        return horizontalGuide;
    }

    public Image getIcon() {
        return getIconImage();
    }

    public String getId() {
        return id;
    }

    public Point getLocation() {
        return location;
    }

    public Dimension getSize() {
        return size;
    }

    public Vector getSourceConnections() {
        return (Vector) outputs.clone();
    }

    public Vector getTargetConnections() {
        Enumeration elements = inputs.elements();
        Vector v = new Vector(inputs.size());
        while (elements.hasMoreElements())
            v.addElement(elements.nextElement());
        return v;
    }

    public FastGuide getVerticalGuide() {
        return verticalGuide;
    }

    public boolean isPropertySet() {
        return true;
    }

    public void setHorizontalGuide(FastGuide hGuide) {
        horizontalGuide = hGuide;
    }

    public void setId(String s) {
        id = s;
    }

    public void setLocation(Point location) {
        if (this.location.equals(location))
            return;
        this.location = location;
        firePropertyChange(LOCATION_PROP, null, location); //$NON-NLS-1$
    }

    public void setSize(Dimension d) {
        if (size.equals(d))
            return;
        size = d;
        firePropertyChange("size", null, size); //$NON-NLS-1$
    }

    public void setVerticalGuide(FastGuide vGuide) {
        verticalGuide = vGuide;
    }

    protected void setOutput(String terminal, boolean val) {
        Enumeration elements = outputs.elements();
        FastConnection conn;
        while (elements.hasMoreElements()) {
            conn = (FastConnection) elements.nextElement();
            if (conn.getSourceTerminal().equals(terminal)
                    && this.equals(conn.getSource()))
                conn.setValue(val);
        }
    }

    /**
     * @return Returns the maxInput.
     */
    public int getMaxInput() {
        return maxInput;
    }

    /**
     * @param maxInput
     *            The maxInput to set.
     */
    public void setMaxInput(int maxInput) {
        this.maxInput = maxInput;
    }

    /**
     * @return Returns the maxOutput.
     */
    public int getMaxOutput() {
        return maxOutput;
    }

    /**
     * @param maxOutput
     *            The maxOutput to set.
     */
    public void setMaxOutput(int maxOutput) {
        this.maxOutput = maxOutput;
    }

    protected boolean getInput(String terminal) {
        FastConnection conn = (FastConnection) inputs.get(terminal);
        return (conn == null) ? false : conn.getValue();
    }

    /**
     * Returns useful property descriptors for the use in property sheets. this
     * supports location and size.
     * 
     * @return Array of property descriptors.
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
        return descriptors;
    }

    /**
     * Returns an Object which represents the appropriate value for the property
     * name supplied.
     * 
     * @param propName
     *            Name of the property for which the the values are needed.
     * @return Object which is the value of the property.
     */
    public Object getPropertyValue(Object propName) {
        if (SIZE_PROP.equals(propName))
            return new DimensionPropertySource(getSize());
        else if (LOCATION_PROP.equals(propName))
            return new LocationPropertySource(getLocation());
        return null;
    }

    /**
     * Sets the value of a given property with the value supplied. Also fires a
     * property change if necessary.
     * 
     * @param id
     *            Name of the parameter to be changed.
     * @param value
     *            Value to be set to the given parameter.
     */
    public void setPropertyValue(Object id, Object value) {
        if (SIZE_PROP.equals(id))
            setSize((Dimension) value);
        else if (LOCATION_PROP.equals(id))
            setLocation((Point) value);
    }

    /**
     * 
     */
    public Element getXml(Element parent) {
        Element node = parent.addElement("node");
        node.addAttribute("type", getClass().getSimpleName());

        return node;
    }
}
