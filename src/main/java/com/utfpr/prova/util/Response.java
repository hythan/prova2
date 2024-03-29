/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utfpr.prova.util;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

    private T data;
    private List<String> errors;

    public Response() {
    }

    public Response(T data) {
        this.data = data;
    }

    public Response(List<String> errors) {
        this.errors = errors;
    }

    public Response(T data, List<String> errors) {
        this.data = data;
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void setErrors(BindingResult result) {
        result.getAllErrors().forEach(error -> this.addError(error.getDefaultMessage()));
    }

    public void addError(String error) {
        if (errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }

}
