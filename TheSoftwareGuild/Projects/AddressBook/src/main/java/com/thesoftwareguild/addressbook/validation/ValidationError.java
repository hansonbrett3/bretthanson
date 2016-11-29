/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.addressbook.validation;

/**
 *
 * @author bretthanson
 */
public class ValidationError {

    private String fieldName;
    private String message;

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

    public ValidationError(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
