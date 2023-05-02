package com.example.dataanalyticrestfulwebservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UploadResponse {
    String imgname;
    String message;
}
