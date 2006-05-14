package org.fast.fastsdk.core;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class FastsdkPlugin extends AbstractUIPlugin {

	// The shared instance.
	private static FastsdkPlugin plugin;

	/**
	 * The constructor.
	 */
	public FastsdkPlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		Properties properties = new Properties();
		properties.load(FastsdkPlugin.class
				.getResourceAsStream("log4j.properties"));
		PropertyConfigurator.configure(properties);
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static FastsdkPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin("org.fast.fastsdk",
				path);
	}
}
