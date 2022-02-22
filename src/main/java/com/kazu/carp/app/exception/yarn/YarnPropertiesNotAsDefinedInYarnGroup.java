package com.kazu.carp.app.exception.yarn;

/**
 * @author akifova
 * 25.09.2021
 */
public class YarnPropertiesNotAsDefinedInYarnGroup extends Exception {
    public YarnPropertiesNotAsDefinedInYarnGroup() {
        super("{exception.yarn.properties.not.as.defined.in.group}");
    }

    public YarnPropertiesNotAsDefinedInYarnGroup(String message) {
        super(message);
    }

    public YarnPropertiesNotAsDefinedInYarnGroup(String message, Throwable cause) {
        super(message, cause);
    }

    public YarnPropertiesNotAsDefinedInYarnGroup(Throwable cause) {
        super(cause);
    }
}