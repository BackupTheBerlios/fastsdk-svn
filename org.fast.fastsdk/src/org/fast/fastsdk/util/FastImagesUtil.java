/**
 * 
 */
package org.fast.fastsdk.util;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.graphics.Image;

/**
 * @author …Ú»›÷€
 */
public class FastImagesUtil {
    public static Image createImage(Class rsrcClass, String name) {
        InputStream stream = rsrcClass.getResourceAsStream(name);
        Image image = new Image(null, stream);
        try {
            stream.close();
        } catch (IOException ioe) {
        }
        return image;
    }
}
