package org.fast.fastsdk.core.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.tools.MarqueeSelectionTool;
import org.eclipse.jface.resource.ImageDescriptor;
import org.fast.fastsdk.core.model.EndNode;
import org.fast.fastsdk.core.model.FastNode;
import org.fast.fastsdk.core.model.FunctionNode;
import org.fast.fastsdk.core.model.JoinpointNode;
import org.fast.fastsdk.core.model.PredicateNode;
import org.fast.fastsdk.core.model.StartNode;

public class FastEditorPaletteFactory {
    public static PaletteRoot createPalette() {
        PaletteRoot fastPalette = new PaletteRoot();
        fastPalette.addAll(createCategories(fastPalette));
        return fastPalette;
    }

    static private List createCategories(PaletteRoot root) {
        List categories = new ArrayList();

        categories.add(createControlGroup(root));
        categories.add(createComponentsDrawer());
        return categories;
    }

    static private PaletteContainer createComponentsDrawer() {

        PaletteDrawer drawer = new PaletteDrawer("Fast Nodes", ImageDescriptor
                .createFromFile(FastNode.class, "icons/comp.gif"));

        List entries = new ArrayList();

        CombinedTemplateCreationEntry combined = new CombinedTemplateCreationEntry(
                "Function Node", "Function Node", "function node template",
                new SimpleFactory(FunctionNode.class), ImageDescriptor
                        .createFromFile(FastNode.class, "icons/xor16.gif"),
                ImageDescriptor.createFromFile(FastNode.class,
                        "icons/xor24.gif"));
        entries.add(combined);

        combined = new CombinedTemplateCreationEntry("Predicate Node",
                "Predicate Node", "predicate node template", new SimpleFactory(
                        PredicateNode.class), ImageDescriptor.createFromFile(
                        FastNode.class, "icons/or16.gif"), ImageDescriptor
                        .createFromFile(FastNode.class, "icons/or24.gif"));
        entries.add(combined);

        combined = new CombinedTemplateCreationEntry("Joinpoint Node",
                "Joinpoint Node", "joinpoint node template", new SimpleFactory(
                        JoinpointNode.class), ImageDescriptor.createFromFile(
                        FastNode.class, "icons/and16.gif"), ImageDescriptor
                        .createFromFile(FastNode.class, "icons/and24.gif"));
        entries.add(combined);

        combined = new CombinedTemplateCreationEntry("Start Node",
                "Start Node", "start node template", new SimpleFactory(
                        StartNode.class), ImageDescriptor.createFromFile(
                        FastNode.class, "icons/and16.gif"), ImageDescriptor
                        .createFromFile(FastNode.class, "icons/and24.gif"));
        entries.add(combined);

        combined = new CombinedTemplateCreationEntry("End Node", "End Node",
                "end node template", new SimpleFactory(EndNode.class),
                ImageDescriptor.createFromFile(FastNode.class,
                        "icons/and16.gif"), ImageDescriptor.createFromFile(
                        FastNode.class, "icons/and24.gif"));
        entries.add(combined);

        drawer.addAll(entries);
        return drawer;
    }

    static private PaletteContainer createControlGroup(PaletteRoot root) {
        PaletteGroup controlGroup = new PaletteGroup("Controls");

        List entries = new ArrayList();

        ToolEntry tool = new PanningSelectionToolEntry();
        entries.add(tool);
        root.setDefaultEntry(tool);

        PaletteStack marqueeStack = new PaletteStack("Marquee", "", null); //$NON-NLS-1$
        marqueeStack.add(new MarqueeToolEntry());
        MarqueeToolEntry marquee = new MarqueeToolEntry();
        marquee.setToolProperty(MarqueeSelectionTool.PROPERTY_MARQUEE_BEHAVIOR,
                new Integer(MarqueeSelectionTool.BEHAVIOR_CONNECTIONS_TOUCHED));
        marqueeStack.add(marquee);
        marquee = new MarqueeToolEntry();
        marquee.setToolProperty(MarqueeSelectionTool.PROPERTY_MARQUEE_BEHAVIOR,
                new Integer(MarqueeSelectionTool.BEHAVIOR_CONNECTIONS_TOUCHED
                        | MarqueeSelectionTool.BEHAVIOR_NODES_CONTAINED));
        marqueeStack.add(marquee);
        marqueeStack
                .setUserModificationPermission(PaletteEntry.PERMISSION_NO_MODIFICATION);
        entries.add(marqueeStack);

        tool = new ConnectionCreationToolEntry("Connection", "Connection",
                null, ImageDescriptor.createFromFile(FastNode.class,
                        "icons/connection16.gif"), ImageDescriptor
                        .createFromFile(FastNode.class,
                                "icons/connection24.gif"));
        entries.add(tool);
        controlGroup.addAll(entries);
        return controlGroup;
    }
}
