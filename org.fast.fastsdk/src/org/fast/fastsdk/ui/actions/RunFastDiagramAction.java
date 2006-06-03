/**
 * 
 */
package org.fast.fastsdk.ui.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchWindow;
import org.fast.fastsdk.core.FastsdkPlugin;
import org.fast.fastsdk.core.editors.FastEditorInput;
import org.fast.fastsdk.core.editors.FastMultiPageEditor;
import org.fast.fastsdk.ui.preferences.PreferenceConstants;

/**
 * @author …Ú»›÷€
 */
public class RunFastDiagramAction extends Action {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(RunFastDiagramAction.class);

	private IWorkbenchWindow window;

	public RunFastDiagramAction(IWorkbenchWindow window) {
		super("&Run");
		this.window = window;
		setId("org.eclipse.fastide.actions.runAction");
	}

	public void run() {
		FastMultiPageEditor fastEditor = (FastMultiPageEditor) window
				.getActivePage().getActiveEditor();

		FastEditorInput input = (FastEditorInput) fastEditor.getEditorInput();
		File directory = input.getPath().removeLastSegments(1).toFile();
		String filePath = input.getPath().removeFileExtension()
				.addFileExtension("fss").toOSString();
		IPreferenceStore store = FastsdkPlugin.getDefault()
				.getPreferenceStore();
		String pathToFast = store.getString(PreferenceConstants.P_PATH);
		try {
			Process process = Runtime.getRuntime().exec(
					pathToFast + " " + filePath, null, directory);
			Thread.sleep(4000);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = "";
			while ((line = in.readLine()) != null) {
				logger.info(line);
			}
			in.close();

			fastEditor.setGenerated(true);
		} catch (IOException exception) {
			exception.printStackTrace();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
}
