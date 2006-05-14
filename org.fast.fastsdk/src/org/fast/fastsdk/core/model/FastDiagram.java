/**
 *
 */
package org.fast.fastsdk.core.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.fast.fastsdk.util.FastMessagesUtil;

/**
 * @author …Ú»›÷€
 */
public class FastDiagram extends FastSubpart {
    static final long           serialVersionUID     = 1;

    public static String        GENERATED_PROP       = "generated";

    public static String        ID_ROUTER            = "router";

    public static Integer       ROUTER_MANUAL        = new Integer(0);

    public static Integer       ROUTER_MANHATTAN     = new Integer(1);

    public static Integer       ROUTER_SHORTEST_PATH = new Integer(2);

    private static int          count;

    protected List<FastElement> children             = new ArrayList<FastElement>();

    protected FastRuler         leftRuler;

    protected FastRuler         topRuler;

    protected Integer           connectionRouter     = null;

    private boolean             rulersVisibility     = false;

    private boolean             snapToGeometry       = false;

    private boolean             gridEnabled          = false;

    private double              zoom                 = 1.0;

    private boolean             generated            = false;

    public FastDiagram() {
        size.width = 100;
        size.height = 100;
        location.x = 20;
        location.y = 20;
        createRulers();
    }

    public void addChild(FastElement child) {
        addChild(child, -1);
    }

    public void addChild(FastElement child, int index) {
        if (index >= 0)
            children.add(index, child);
        else
            children.add(child);
        fireChildAdded(CHILDREN, child, new Integer(index));
    }

    protected void createRulers() {
        leftRuler = new FastRuler(false);
        topRuler = new FastRuler(true);
    }

    public List getChildren() {
        return children;
    }

    public Integer getConnectionRouter() {
        if (connectionRouter == null)
            connectionRouter = ROUTER_MANUAL;
        return connectionRouter;
    }

    public Image getIconImage() {
        return null;
    }

    public String getNewId() {
        return Integer.toString(count++);
    }

    public double getZoom() {
        return zoom;
    }

    public IPropertyDescriptor[] getPropertyDescriptors() {
        if (getClass().equals(FastDiagram.class)) {
            ComboBoxPropertyDescriptor cbd = new ComboBoxPropertyDescriptor(
                    ID_ROUTER,
                    FastMessagesUtil.getMessage("pd.diagram.cr"),
                    new String[] {
                            FastMessagesUtil.getMessage("pd.diagram.cr.manual"),
                            FastMessagesUtil
                                    .getMessage("pd.diagram.cr.manhattan"),
                            FastMessagesUtil
                                    .getMessage("pd.diagram.cr.shortestPath") });
            cbd.setLabelProvider(new ConnectionRouterLabelProvider());
            return new IPropertyDescriptor[] { cbd };
        }
        return super.getPropertyDescriptors();
    }

    public Object getPropertyValue(Object propName) {
        if (propName.equals(ID_ROUTER))
            return connectionRouter;
        return super.getPropertyValue(propName);
    }

    public FastRuler getRuler(int orientation) {
        FastRuler result = null;
        switch (orientation) {
            case PositionConstants.NORTH:
                result = topRuler;
                break;
            case PositionConstants.WEST:
                result = leftRuler;
                break;
        }
        return result;
    }

    public boolean getRulerVisibility() {
        return rulersVisibility;
    }

    public boolean isGenerated() {
        return generated;
    }

    public void setGenerated(boolean generated) {
        boolean oldGenerated = this.generated;
        this.generated = generated;
        firePropertyChange(GENERATED_PROP, oldGenerated, generated);
    }

    public boolean isGridEnabled() {
        return gridEnabled;
    }

    public boolean isSnapToGeometryEnabled() {
        return snapToGeometry;
    }

    private void readObject(java.io.ObjectInputStream s) throws IOException,
            ClassNotFoundException {
        s.defaultReadObject();
    }

    public void removeChild(FastElement child) {
        children.remove(child);
        fireChildRemoved(CHILDREN, child);
    }

    public void setConnectionRouter(Integer router) {
        Integer oldConnectionRouter = connectionRouter;
        connectionRouter = router;
        firePropertyChange(ID_ROUTER, oldConnectionRouter, connectionRouter);
    }

    public void setPropertyValue(Object id, Object value) {
        if (ID_ROUTER.equals(id))
            setConnectionRouter((Integer) value);
        else
            super.setPropertyValue(id, value);
    }

    public void setRulerVisibility(boolean newValue) {
        rulersVisibility = newValue;
    }

    public void setGridEnabled(boolean isEnabled) {
        gridEnabled = isEnabled;
    }

    public void setSnapToGeometry(boolean isEnabled) {
        snapToGeometry = isEnabled;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public String toString() {
        return FastMessagesUtil.getMessage("diagram.label");
    }

    public Element getXml(Element parent) {
        Iterator childrenIterator = children.iterator();
        while (childrenIterator.hasNext()) {
            FastElement child = (FastElement) childrenIterator.next();
            child.getXml(parent);
        }
        return parent;
    }

    private class ConnectionRouterLabelProvider extends
            org.eclipse.jface.viewers.LabelProvider {

        public ConnectionRouterLabelProvider() {
            super();
        }

        public String getText(Object element) {
            if (element instanceof Integer) {
                Integer integer = (Integer) element;
                if (ROUTER_MANUAL.intValue() == integer.intValue())
                    return FastMessagesUtil.getMessage("pd.diagram.cr.manual");
                if (ROUTER_MANHATTAN.intValue() == integer.intValue())
                    return FastMessagesUtil
                            .getMessage("pd.diagram.cr.manhattan");
                if (ROUTER_SHORTEST_PATH.intValue() == integer.intValue())
                    return FastMessagesUtil
                            .getMessage("pd.diagram.cr.shortestPath");
            }
            return super.getText(element);
        }
    }
}
