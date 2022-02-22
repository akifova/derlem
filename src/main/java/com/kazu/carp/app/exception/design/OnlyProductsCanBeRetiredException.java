package com.kazu.carp.app.exception.design;

/**
 * @author akifova
 * 17.07.2021
 */
public class OnlyProductsCanBeRetiredException extends Exception {
    public OnlyProductsCanBeRetiredException() {
        super("Sadece Ürün Kodlu Desenler Emekli Olabilir.");
    }

    public OnlyProductsCanBeRetiredException(String message) {
        super(message);
    }

    public OnlyProductsCanBeRetiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public OnlyProductsCanBeRetiredException(Throwable cause) {
        super(cause);
    }
}