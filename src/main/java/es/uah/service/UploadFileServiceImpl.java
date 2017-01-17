package es.uah.service;

import es.uah.domain.Picture;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by Simone on 15.01.2017.
 */
@Service("UploadFileServiceImpl")
public class UploadFileServiceImpl implements UploadFileService {

    @Override
    public File uploadFile(Picture picture) {
        if(!picture.getFile().isEmpty()){
            picture.setName(picture.getFile().getOriginalFilename());

            return createTemporaryFile(picture);
        }
        throw new IllegalArgumentException("Picture is empty!");
    }

    @Override
    public void deleteFile(File file) {
        try {
            Files.delete(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String generatePrefix(Picture picture) {
        return Base64.encodeBase64URLSafeString(picture.getName().getBytes());
    }

    private File createTemporaryFile(Picture picture) {
        try {
            // Create a temporary file in the systems temp folder
            File temp = Files.createTempFile("img-",generatePrefix(picture)).toFile();
            // transfer contents of the uploaded file to the temp file
            picture.getFile().transferTo(temp);

            return temp;

        } catch (IOException e) {
            throw new IllegalStateException("Could not create temporary file!");
        }
    }
}
