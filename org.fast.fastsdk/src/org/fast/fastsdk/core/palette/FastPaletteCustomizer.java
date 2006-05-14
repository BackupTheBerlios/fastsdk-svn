/**
 *
 */
package org.fast.fastsdk.core.palette;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.ui.palette.PaletteCustomizer;
import org.eclipse.gef.ui.palette.customize.DefaultEntryPage;
import org.eclipse.gef.ui.palette.customize.DrawerEntryPage;
import org.eclipse.gef.ui.palette.customize.EntryPage;

/**
 * @author ������
 */
public class FastPaletteCustomizer extends PaletteCustomizer {

    protected static final String ERROR_MESSAGE = "Invalid character";

    /**
     * @see org.eclipse.gef.ui.palette.PaletteCustomizer#getPropertiesPage(PaletteEntry)
     */
    public EntryPage getPropertiesPage(PaletteEntry entry) {
        if (entry.getType().equals(PaletteDrawer.PALETTE_TYPE_DRAWER)) {
            return new FastDrawerEntryPage();
        }
        return new FastEntryPage();
    }

    /**
     * @see org.eclipse.gef.ui.palette.PaletteCustomizer#revertToSaved()
     */
    public void revertToSaved() {
    }

    /**
     * @see org.eclipse.gef.ui.palette.PaletteCustomizer#save()
     */
    public void save() {
    }

    private class FastEntryPage extends DefaultEntryPage {
        protected void handleNameChanged(String text) {
            if (text.indexOf('*') >= 0) {
                getPageContainer().showProblem(ERROR_MESSAGE);
            } else {
                super.handleNameChanged(text);
                getPageContainer().clearProblem();
            }
        }
    }

    private class FastDrawerEntryPage extends DrawerEntryPage {
        protected void handleNameChanged(String text) {
            if (text.indexOf('*') >= 0) {
                getPageContainer().showProblem(ERROR_MESSAGE);
            } else {
                super.handleNameChanged(text);
                getPageContainer().clearProblem();
            }
        }
    }
}
