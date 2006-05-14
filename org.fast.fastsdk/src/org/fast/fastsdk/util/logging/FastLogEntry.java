/**
 * 
 */
package org.fast.fastsdk.util.logging;

import java.io.Serializable;

/**
 * @author …Ú»›÷€
 */
public class FastLogEntry implements Serializable {
    private static final long serialVersionUID = 1;

    private String            time;

    private String            location;

    private String            level;

    private String            message;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue(int index) {
        String value = "";
        switch (index) {
            case 0:
                value = time;
                break;
            case 1:
                value = level;
                break;
            case 2:
                value = location;
                break;
            case 3:
                value = message;
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}
