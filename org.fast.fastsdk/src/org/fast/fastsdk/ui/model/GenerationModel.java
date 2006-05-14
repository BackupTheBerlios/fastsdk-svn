/**
 * 
 */
package org.fast.fastsdk.ui.model;

/**
 * @author …Ú»›÷€
 */
public class GenerationModel extends UIModel {
    private static final long serialVersionUID = 1;

    public static String      GENERATED_PROP   = "generated";

    private boolean           generated        = false;

    public boolean isGenerated() {
        return generated;
    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
        firePropertyChange(GENERATED_PROP, null, generated);
    }
}
