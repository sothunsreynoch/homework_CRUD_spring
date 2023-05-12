package com.example.dataanalyticrestfulwebservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FileResponse {
    private  String filename;
    private String fileDownloadUrl;
    private String fileType;
    private long size;
}
