/**
 *
 */
package org.fast.fastsdk.core.model.commands;

import org.eclipse.gef.commands.Command;
import org.fast.fastsdk.core.model.FastGuide;
import org.fast.fastsdk.core.model.FastRuler;

/**
 * @author …Ú»›÷€
 */
public class CreateGuideCommand extends Command {
    private FastGuide guide;

    private FastRuler parent;

    private int       position;

    public CreateGuideCommand(FastRuler parent, int position) {
        super("create guide");
        this.parent = parent;
        this.position = position;
    }

    public boolean canUndo() {
        return true;
    }

    public void execute() {
        if (guide == null)
            guide = new FastGuide(!parent.isHorizontal());
        guide.setPosition(position);
        parent.addGuide(guide);
    }

    public void undo() {
        parent.removeGuide(guide);
    }
}
