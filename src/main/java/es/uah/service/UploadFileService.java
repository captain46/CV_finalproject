package es.uah.service;

import es.uah.domain.Picture;

/**
 * Created by Simone on 15.01.2017.
 */
public interface UploadFileService {
    void uploadFile(Picture picture);
    void deleteFiles();
}
