/**
 *
 */
package org.fast.fastsdk.core.model;

import org.eclipse.gef.requests.CreationFactory;

/**
 * @author …Ú»›÷€
 */
public class FastElementFactory implements CreationFactory {
    private String template;

    public FastElementFactory(String template) {
        this.template = template;
    }

    /**
     * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
     */
    public Object getNewObject() {
        if ("function node template".equals(template))
            return new FunctionNode();
        if ("predicate node template".equals(template))
            return new PredicateNode();
        if ("joinpoint node template".equals(template))
            return new JoinpointNode();
        if ("start node template".equals(template))
            return new StartNode();
        if ("end node template".equals(template))
            return new EndNode();
        return null;
    }

    public Object getObjectType() {
        return template;
    }
}
