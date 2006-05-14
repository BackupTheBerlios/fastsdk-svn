/**
 *
 */
package org.fast.fastsdk.core.rulers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.rulers.RulerChangeListener;
import org.eclipse.gef.rulers.RulerProvider;
import org.fast.fastsdk.core.model.FastGuide;
import org.fast.fastsdk.core.model.FastRuler;
import org.fast.fastsdk.core.model.commands.CreateGuideCommand;
import org.fast.fastsdk.core.model.commands.DeleteGuideCommand;
import org.fast.fastsdk.core.model.commands.MoveGuideCommand;

/**
 * @author …Ú»›÷€
 */
public class FastRulerProvider extends RulerProvider {
    private FastRuler              ruler;

    private PropertyChangeListener rulerListener = new PropertyChangeListener() {
                                                     public void propertyChange(
                                                             PropertyChangeEvent evt) {
                                                         if (evt
                                                                 .getPropertyName()
                                                                 .equals(
                                                                         FastRuler.PROPERTY_CHILDREN)) {
                                                             FastGuide guide = (FastGuide) evt
                                                                     .getNewValue();
                                                             if (getGuides()
                                                                     .contains(
                                                                             guide)) {
                                                                 guide
                                                                         .addPropertyChangeListener(guideListener);
                                                             } else {
                                                                 guide
                                                                         .removePropertyChangeListener(guideListener);
                                                             }
                                                             for (int i = 0; i < listeners
                                                                     .size(); i++) {
                                                                 ((RulerChangeListener) listeners
                                                                         .get(i))
                                                                         .notifyGuideReparented(guide);
                                                             }
                                                         } else {
                                                             for (int i = 0; i < listeners
                                                                     .size(); i++) {
                                                                 ((RulerChangeListener) listeners
                                                                         .get(i))
                                                                         .notifyUnitsChanged(ruler
                                                                                 .getUnit());
                                                             }
                                                         }
                                                     }
                                                 };

    private PropertyChangeListener guideListener = new PropertyChangeListener() {
                                                     public void propertyChange(
                                                             PropertyChangeEvent evt) {
                                                         if (evt
                                                                 .getPropertyName()
                                                                 .equals(
                                                                         FastGuide.PROPERTY_CHILDREN)) {
                                                             for (int i = 0; i < listeners
                                                                     .size(); i++) {
                                                                 ((RulerChangeListener) listeners
                                                                         .get(i))
                                                                         .notifyPartAttachmentChanged(
                                                                                 evt
                                                                                         .getNewValue(),
                                                                                 evt
                                                                                         .getSource());
                                                             }
                                                         } else {
                                                             for (int i = 0; i < listeners
                                                                     .size(); i++) {
                                                                 ((RulerChangeListener) listeners
                                                                         .get(i))
                                                                         .notifyGuideMoved(evt
                                                                                 .getSource());
                                                             }
                                                         }
                                                     }
                                                 };

    public FastRulerProvider(FastRuler ruler) {
        this.ruler = ruler;
        this.ruler.addPropertyChangeListener(rulerListener);
        List guides = getGuides();
        for (int i = 0; i < guides.size(); i++) {
            ((FastGuide) guides.get(i))
                    .addPropertyChangeListener(guideListener);
        }
    }

    public List getAttachedModelObjects(Object guide) {
        return new ArrayList(((FastGuide) guide).getParts());
    }

    public Command getCreateGuideCommand(int position) {
        return new CreateGuideCommand(ruler, position);
    }

    public Command getDeleteGuideCommand(Object guide) {
        return new DeleteGuideCommand((FastGuide) guide, ruler);
    }

    public Command getMoveGuideCommand(Object guide, int pDelta) {
        return new MoveGuideCommand((FastGuide) guide, pDelta);
    }

    public int[] getGuidePositions() {
        List guides = getGuides();
        int[] result = new int[guides.size()];
        for (int i = 0; i < guides.size(); i++) {
            result[i] = ((FastGuide) guides.get(i)).getPosition();
        }
        return result;
    }

    public Object getRuler() {
        return ruler;
    }

    public int getUnit() {
        return ruler.getUnit();
    }

    public void setUnit(int newUnit) {
        ruler.setUnit(newUnit);
    }

    public int getGuidePosition(Object guide) {
        return ((FastGuide) guide).getPosition();
    }

    public List getGuides() {
        return ruler.getGuides();
    }
}
