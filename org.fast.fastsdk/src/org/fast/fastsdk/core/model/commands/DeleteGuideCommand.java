/**
 *
 */
package org.fast.fastsdk.core.model.commands;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.fast.fastsdk.core.model.FastGuide;
import org.fast.fastsdk.core.model.FastRuler;
import org.fast.fastsdk.core.model.FastSubpart;

/**
 * @author …Ú»›÷€
 */
public class DeleteGuideCommand extends Command {
    private FastRuler                 parent;

    private FastGuide                 guide;

    private Map<FastSubpart, Integer> oldParts;

    public DeleteGuideCommand(FastGuide guide, FastRuler parent) {
        super("delete guide");
        this.guide = guide;
        this.parent = parent;
    }

    public boolean canUndo() {
        return true;
    }

    public void execute() {
        oldParts = new HashMap<FastSubpart, Integer>(guide.getMap());
        Iterator iter = oldParts.keySet().iterator();
        while (iter.hasNext()) {
            guide.detachPart((FastSubpart) iter.next());
        }
        parent.removeGuide(guide);
    }

    public void undo() {
        parent.addGuide(guide);
        Iterator iter = oldParts.keySet().iterator();
        while (iter.hasNext()) {
            FastSubpart part = (FastSubpart) iter.next();
            guide.attachPart(part, ((Integer) oldParts.get(part)).intValue());
        }
    }
}
