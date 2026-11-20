package com.asutosh.prod_ready_features.prod_ready_features.advice;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<B> {

//    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timeStamp;
    private B data;
    private ApiError error;

    public ApiResponse () {
        this.timeStamp = LocalDateTime.now ();
    }

    public ApiResponse (B data) {
        this ();
        this.data = data;
    }

    public ApiResponse (ApiError error) {
        this ();
        this.error = error;
    }
}
