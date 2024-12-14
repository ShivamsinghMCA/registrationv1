package com.api.registration.payload;

import java.util.Date;

public class ErrorDto {
    private String mse;
    private Date date;
    private String uri;

    public ErrorDto(String mse, Date date, String uri) {
        this.mse = mse;
        this.date = date;
        this.uri = uri;
    }

    public String getMse() {
        return mse;
    }

    public Date getDate() {
        return date;
    }

    public String getUri() {
        return uri;
    }
}
