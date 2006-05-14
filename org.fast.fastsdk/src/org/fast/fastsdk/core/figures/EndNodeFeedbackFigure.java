package org.fast.fastsdk.core.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

public class EndNodeFeedbackFigure extends EndNodeFigure {
    /**
     * @see org.fastsdk.core.figures.JoinpointNodeFigure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    protected void paintFigure(Graphics g) {
        g.setAntialias(SWT.ON);
        Rectangle r = getBounds().getCopy();
        g.setForegroundColor(ColorConstants.black);
        r.width = r.width - 1;
        r.height = r.height - 1;
        g.setBackgroundColor(ColorConstants.gray);
        g.setAlpha(50);
        g.fillOval(r);
        g.setAlpha(255);
        r.width = r.width - 1;
        r.height = r.height - 1;
        g.drawOval(r);
    }
}
