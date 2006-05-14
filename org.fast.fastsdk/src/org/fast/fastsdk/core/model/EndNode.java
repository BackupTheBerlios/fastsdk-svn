/**
 *
 */
package org.fast.fastsdk.core.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Image;
import org.fast.fastsdk.util.FastImagesUtil;
import org.fast.fastsdk.util.FastMessagesUtil;

/**
 * @author …Ú»›÷€
 */
public class EndNode extends FastNode {
    public static final String IN1              = "IN1";

    public static final String IN2              = "IN2";

    public static final String IN3              = "IN3";

    public static final String IN4              = "IN4";

    static final long          serialVersionUID = 1;

    private static final Image ICON             = FastImagesUtil.createImage(
                                                        FastNode.class,
                                                        "icons/or16.gif");

    public EndNode() {
        size = new Dimension(30, 30);
        location = new Point(20, 20);
        setMaxInput(1);
        setMaxOutput(0);
        setName("exit");
    }

    /**
     * @see org.fastsdk.core.model.FastElement#update()
     */
    public void update() {
        getInput(IN1);
        getInput(IN2);
        getInput(IN3);
        getInput(IN4);
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return FastMessagesUtil.getMessage("end.toString");
    }

    /**
     * @see org.fastsdk.core.model.FastNode#getIconImage()
     */
    public Image getIconImage() {
        return ICON;
    }

    public String getFast() {
        return " ";
    }
}
