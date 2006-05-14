package org.fast.fastsdk.core;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.fast.fastsdk.util.FastSdkConstants;

public class Perspective implements IPerspectiveFactory {

    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();

        // =============================
        // Left: Content Outline
        // =============================
        layout.addView(FastSdkConstants.CONTENT_OUTLINE_ID, IPageLayout.RIGHT,
                0.7f, editorArea);

        // =============================
        // Bottom: Property Sheet + Log View
        // =============================
        IFolderLayout folder = layout.createFolder("bottomViews",
                IPageLayout.BOTTOM, 0.6f, editorArea);
        folder.addPlaceholder(FastSdkConstants.PROPERTY_SHEET_ID + ":*");
        folder.addPlaceholder(FastSdkConstants.CONTENT_OUTLINE_ID + ":*");
        folder.addPlaceholder(FastSdkConstants.FASTFILESVIEW_ID + ":*");
        folder.addPlaceholder(FastSdkConstants.FASTLOGVIEW_ID + ":*");
        folder.addView(FastSdkConstants.PROPERTY_SHEET_ID);
        folder.addView(FastSdkConstants.FASTLOGVIEW_ID);

        // ==============================
        // Left: Files View
        // ==============================
        layout.addView(FastSdkConstants.FASTFILESVIEW_ID, IPageLayout.LEFT,
                0.4f, editorArea);
    }
}
