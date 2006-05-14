/**
 *
 */
package org.fast.fastsdk.core.model;

import org.eclipse.jface.viewers.ICellEditorValidator;

/**
 * @author …Ú»›÷€
 */
public class FastNumberCellEditorValidator implements ICellEditorValidator {

    private static FastNumberCellEditorValidator instance;

    public static FastNumberCellEditorValidator instance() {
        if (instance == null)
            instance = new FastNumberCellEditorValidator();
        return instance;
    }

    public String isValid(Object value) {
        try {
            new Integer((String) value);
            return null;
        } catch (NumberFormatException exc) {
            return "Not a number";
        }
    }
}
