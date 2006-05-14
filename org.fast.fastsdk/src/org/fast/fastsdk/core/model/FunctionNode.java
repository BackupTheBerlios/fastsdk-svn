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
public class FunctionNode extends FastNode {
    public static String       IN_1             = "IN1";

    public static String       IN_2             = "IN2";

    public static String       IN_3             = "IN3";

    public static String       IN_4             = "IN4";

    public static String       OUT_1            = "OUT1";

    public static String       OUT_2            = "OUT2";

    public static String       OUT_3            = "OUT3";

    public static String       OUT_4            = "OUT4";

    static final long          serialVersionUID = 1;

    private static final Image ICON             = FastImagesUtil.createImage(
                                                        FastNode.class,
                                                        "icons/xor16.gif");

    public FunctionNode() {
        this.size = new Dimension(40, 50);
        this.location = new Point(20, 20);
        setName("f" + getId());
        setMaxInput(1);
        setMaxOutput(1);
    }

    /**
     * @see com.fastide.editors.gef.models.FastSubpart#getIconImage()
     */
    public Image getIconImage() {
        // TODO Auto-generated method stub
        return ICON;
    }

    public String toString() {
        return FastMessagesUtil.getMessage("function.toString");
    }

    /**
     * @see org.fastsdk.core.model.FastElement#update()
     */
    public void update() {
        // TODO Auto-generated method stub
        boolean bit1 = getInput(IN_1);
        boolean bit2 = getInput(IN_2);
        boolean bit3 = getInput(IN_3);
        boolean bit4 = getInput(IN_4);
        boolean bit = bit1 | bit2 | bit3 | bit4;
        setOutput(OUT_1, bit);
        setOutput(OUT_2, bit);
        setOutput(OUT_3, bit);
        setOutput(OUT_4, bit);
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
                buffer.append("[" + getName() + "]" + " => " + targetName
                        + "\n");
            } else {
                buffer.append("[" + getName() + "]" + " => " + target.getFast()
                        + "\n");
            }
        }
        return buffer.toString();
    }
}
