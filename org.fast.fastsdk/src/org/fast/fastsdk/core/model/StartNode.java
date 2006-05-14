/**
 *
 */
package org.fast.fastsdk.core.model;

import java.util.Iterator;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Image;
import org.fast.fastsdk.util.FastImagesUtil;
import org.fast.fastsdk.util.FastMessagesUtil;

/**
 * @author …Ú»›÷€
 */
public class StartNode extends FastNode {
    public static final String OUT1             = "OUT1";

    public static final String OUT2             = "OUT2";

    public static final String OUT3             = "OUT3";

    public static final String OUT4             = "OUT4";

    static final long          serialVersionUID = 1;

    private static final Image ICON             = FastImagesUtil.createImage(
                                                        FastNode.class,
                                                        "icons/or16.gif");

    public StartNode() {
        size = new Dimension(30, 30);
        location = new Point(20, 20);
        setMaxInput(0);
        setMaxOutput(1);
        setName("entry");
    }

    /**
     * @see org.fastsdk.core.model.FastSubpart#getIconImage()
     */
    public Image getIconImage() {
        // TODO Auto-generated method stub
        return ICON;
    }

    /**
     * @see org.fastsdk.core.model.FastElement#update()
     */
    public void update() {
        // TODO Auto-generated method stub
        setOutput(OUT1, true);
        setOutput(OUT2, true);
        setOutput(OUT3, true);
        setOutput(OUT4, true);
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        // TODO Auto-generated method stub
        return FastMessagesUtil.getMessage("start.toString");
    }

    public String getFast() {
        // TODO Auto-generated method stub
        StringBuffer buffer = new StringBuffer("");
        Iterator iter = outputs.iterator();
        while (iter.hasNext()) {
            FastConnection connection = (FastConnection) iter.next();
            FastNode target = (FastNode) connection.getTarget();
            String targetName = null;
            if (target.getClass().equals(FunctionNode.class))
                targetName = "[" + target.getName() + "]";
            else if (target.getClass().equals(PredicateNode.class))
                targetName = "<" + target.getName() + ">";
            else if (target.getClass().equals(EndNode.class))
                targetName = target.getName();
            if (!target.getClass().equals(JoinpointNode.class)) {
                buffer.append(getName() + " => " + targetName + "\n");
            } else {
                buffer.append(getName() + " => " + target.getFast() + "\n");
            }
        }
        return buffer.toString();
    }
}
