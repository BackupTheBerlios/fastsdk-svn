/**
 *
 */
package org.fast.fastsdk.core.model;

import java.io.Serializable;

import org.dom4j.Element;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/**
 * @author …Ú»›÷€
 */
public class FastBendpoint implements Bendpoint, Serializable, OutputModel {
    private float     weight           = 0.5f;

    private Dimension d1, d2;

    static final long serialVersionUID = 1;

    public FastBendpoint() {
    }

    public Dimension getFirstRelativeDimension() {
        return d1;
    }

    public Point getLocation() {
        return null;
    }

    public Dimension getSecondRelativeDimension() {
        return d2;
    }

    public float getWeight() {
        return weight;
    }

    public void setRelativeDimensions(Dimension dim1, Dimension dim2) {
        d1 = dim1;
        d2 = dim2;
    }

    public void setWeight(float w) {
        weight = w;
    }

    /**
     * @see org.fastide.model.OutputModel#getXml(org.w3c.dom.Document)
     */
    public Element getXml(Element parent) {
        return null;
    }
}
