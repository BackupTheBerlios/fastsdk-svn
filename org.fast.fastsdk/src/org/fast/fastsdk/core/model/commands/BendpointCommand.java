/**
 *
 */
package org.fast.fastsdk.core.model.commands;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.fast.fastsdk.core.model.FastConnection;

/**
 * @author …Ú»›÷€
 */
public class BendpointCommand extends Command {
    protected int            index;

    protected Point          location;

    protected FastConnection conn;

    private Dimension        d1;

    private Dimension        d2;

    protected Dimension getFirstRelativeDimension() {
        return d1;
    }

    protected Dimension getSecondRelativeDimension() {
        return d2;
    }

    protected int getIndex() {
        return index;
    }

    protected Point getLocation() {
        return location;
    }

    protected FastConnection getConnectionNode() {
        return conn;
    }

    public void redo() {
        execute();
    }

    public void setRelativeDimensions(Dimension dim1, Dimension dim2) {
        d1 = dim1;
        d2 = dim2;
    }

    public void setIndex(int i) {
        index = i;
    }

    public void setLocation(Point p) {
        location = p;
    }

    public void setConnectionNode(FastConnection c) {
        conn = c;
    }
}
