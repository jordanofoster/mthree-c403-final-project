package com.jfoster.finalproject.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimestampEditor extends PropertyEditorSupport {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(new Timestamp(dateFormat.parse(text).getTime()));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
