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
public class JoinpointNode extends FastNode {
    public static String       OUT_1            = "OUT1";

    public static String       OUT_2            = "OUT2";

    public static String       IN_1             = "IN1";

    public static String       IN_2             = "IN2";

    static final long          serialVersionUID = 1;

    private static final Image ICON             = FastImagesUtil.createImage(
                                                        FastNode.class,
                                                        "icons/and16.gif");

    public JoinpointNode() {
        this.size = new Dimension(40, 50);
        this.location = new Point(20, 20);
        setMaxInput(2);
        setMaxOutput(1);
        setName("");
    }

    /**
     * @see com.fastide.editors.gef.models.FastSubpart#getIconImage()
     */
    public Image getIconImage() {
        return ICON;
    }

    public String toString() {
        return FastMessagesUtil.getMessage("joinpoint.toString");
    }

    /**
     * @see org.fastsdk.core.model.FastElement#update()
     */
    public void update() {
        boolean bit[] = new boolean[2];
        bit[0] = getInput(IN_1);
        bit[1] = getInput(IN_2);
        setOutput(OUT_2, bit[0] & bit[1]);
        setOutput(OUT_1, bit[0] & bit[1]);
    }

    public String getFast() {
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
                buffer.append(targetName + "\n");
            } else {
                buffer.append(target.getFast() + "\n");
            }
        }
        return buffer.toString();
    }
}
