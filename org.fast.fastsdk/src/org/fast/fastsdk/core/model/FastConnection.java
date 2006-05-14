/**
 *
 */
package org.fast.fastsdk.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.dom4j.Element;
import org.eclipse.draw2d.Bendpoint;

/**
 * @author ÉòÈÝÖÛ
 */
public class FastConnection extends FastElement {
    static final long   serialVersionUID = 1;

    private FastSubpart source;

    private FastSubpart target;

    private String      targetTerminal;

    private String      sourceTerminal;

    private boolean     value;

    protected List      bendpoints       = new ArrayList();

    public void attachSource() {
        if (getSource() == null
                || getSource().getSourceConnections().contains(this))
            return;
        getSource().connectOutput(this);
    }

    public void attachTarget() {
        if (getTarget() == null
                || getTarget().getTargetConnections().contains(this))
            return;
        getTarget().connectInput(this);
    }

    public void detachSource() {
        if (getSource() == null)
            return;
        getSource().disconnectOutput(this);
    }

    public void detachTarget() {
        if (getTarget() == null)
            return;
        getTarget().disconnectInput(this);
    }

    public List getBendpoints() {
        return bendpoints;
    }

    public FastSubpart getSource() {
        return source;
    }

    public String getSourceTerminal() {
        return sourceTerminal;
    }

    public FastSubpart getTarget() {
        return target;
    }

    public String getTargetTerminal() {
        return targetTerminal;
    }

    public boolean getValue() {
        return value;
    }

    public void insertBendpoint(int index, Bendpoint point) {
        getBendpoints().add(index, point);
        firePropertyChange("bendpoint", null, null);
    }

    public void removeBendpoint(int index) {
        getBendpoints().remove(index);
        firePropertyChange("bendpoint", null, null);
    }

    public void setBendpoint(int index, Bendpoint point) {
        getBendpoints().set(index, point);
        firePropertyChange("bendpoint", null, null);
    }

    public void setBendpoints(Vector points) {
        bendpoints = points;
        firePropertyChange("bendpoint", null, null);
    }

    public void setSource(FastSubpart e) {
        Object old = source;
        source = e;
        firePropertyChange("source", old, source);
    }

    public void setSourceTerminal(String s) {
        Object old = sourceTerminal;
        sourceTerminal = s;
        firePropertyChange("sourceTerminal", old, sourceTerminal);
    }

    public void setTarget(FastSubpart e) {
        Object old = target;
        target = e;
        firePropertyChange("target", old, source);
    }

    public void setTargetTerminal(String s) {
        Object old = targetTerminal;
        targetTerminal = s;
        firePropertyChange("targetTerminal", old, sourceTerminal);
    }

    public void setValue(boolean value) {
        if (value == this.value)
            return;
        this.value = value;
        if (target != null)
            target.update();
        firePropertyChange("value", null, null);
    }

    public String toString() {
        return "Wire(" + getSource() + "," + getSourceTerminal() + "->"
                + getTarget() + "," + getTargetTerminal() + ")";
    }

    /**
     * @see org.fastsdk.core.model.FastElement#getXml(org.w3c.dom.Document)
     */
    public Element getXml(Element doc) {
        Element connection = doc.addElement("connection");

        FastNode targetNode = (FastNode) target;
        FastNode sourceNode = (FastNode) source;

        Element temp = connection.addElement("source");
        temp.addAttribute("type", sourceNode.getClass().getSimpleName());
        temp.addAttribute("id", source.getId());
        temp.setText(sourceNode.getName());

        temp = connection.addElement("target");
        temp.addAttribute("id", target.getId());
        temp.addAttribute("type", targetNode.getClass().getSimpleName());
        temp.setText(targetNode.getName());

        return connection;
    }
}
