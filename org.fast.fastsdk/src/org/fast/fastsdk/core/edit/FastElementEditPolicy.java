/**
 *
 */
package org.fast.fastsdk.core.edit;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.fast.fastsdk.core.model.FastDiagram;
import org.fast.fastsdk.core.model.FastSubpart;
import org.fast.fastsdk.core.model.commands.FastDeleteCommand;

/**
 * @author …Ú»›÷€
 */
public class FastElementEditPolicy extends ComponentEditPolicy {
    protected Command createDeleteCommand(GroupRequest request) {
        Object parent = getHost().getParent().getModel();
        FastDeleteCommand deleteCmd = new FastDeleteCommand();
        deleteCmd.setParent((FastDiagram) parent);
        deleteCmd.setChild((FastSubpart) getHost().getModel());
        return deleteCmd;
    }
}
