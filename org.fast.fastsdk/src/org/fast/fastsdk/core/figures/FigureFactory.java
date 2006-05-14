/**
 *
 */
package org.fast.fastsdk.core.figures;

import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.fast.fastsdk.core.model.FastConnection;

/**
 * @author …Ú»›÷€
 */
public class FigureFactory {
    public static PolylineConnection createNewBendableConnectionNode(
            FastConnection connection) {
        PolylineConnection conn = new PolylineConnection();
        conn.setTargetDecoration(new PolylineDecoration());
        return conn;
    }

    public static PolylineConnection createNewWire(FastConnection connection) {
        PolylineConnection conn = new PolylineConnection();
        PolygonDecoration arrow;
        arrow = new PolygonDecoration();
        arrow.setTemplate(PolygonDecoration.INVERTED_TRIANGLE_TIP);
        arrow.setScale(5, 2.5);
        conn.setTargetDecoration(arrow);
        return conn;
    }
}
