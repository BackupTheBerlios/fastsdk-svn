/**
 *
 */
package org.fast.fastsdk.core.model.commands;

import java.util.Iterator;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.fast.fastsdk.core.model.FastGuide;
import org.fast.fastsdk.core.model.FastSubpart;

/**
 * @author …Ú»›÷€
 */
public class MoveGuideCommand extends Command {
    private int       pDelta;

    private FastGuide guide;

    public MoveGuideCommand(FastGuide guide, int positionDelta) {
        super("move guide");
        this.guide = guide;
        pDelta = positionDelta;
    }

    public void execute() {
        guide.setPosition(guide.getPosition() + pDelta);
        Iterator iter = guide.getParts().iterator();
        while (iter.hasNext()) {
            FastSubpart part = (FastSubpart) iter.next();
            Point location = part.getLocation().getCopy();
            if (guide.isHorizontal()) {
                location.y += pDelta;
            } else {
                location.x += pDelta;
            }
            part.setLocation(location);
        }
    }

    public void undo() {
        guide.setPosition(guide.getPosition() - pDelta);
        Iterator iter = guide.getParts().iterator();
        while (iter.hasNext()) {
            FastSubpart part = (FastSubpart) iter.next();
            Point location = part.getLocation().getCopy();
            if (guide.isHorizontal()) {
                location.y -= pDelta;
            } else {
                location.x -= pDelta;
            }
            part.setLocation(location);
        }
    }
}
