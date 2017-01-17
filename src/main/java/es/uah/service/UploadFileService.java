package es.uah.service;

import es.uah.domain.Picture;

import java.io.File;

/**
 * Created by Simone on 15.01.2017.
 */
public interface UploadFileService {
    File uploadFile(Picture picture);
    void deleteFile(File file);
}
