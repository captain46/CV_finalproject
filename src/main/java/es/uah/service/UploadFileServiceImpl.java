package es.uah.service;

import es.uah.domain.Picture;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Created by Simone on 15.01.2017.
 */
@Service("UploadFileServiceImpl")
public class UploadFileServiceImpl implements UploadFileService {

    private static final String PATH = System.getProperty("user.dir") + "/docs/pics/";

    @Override
    public void uploadFile(Picture picture) {
        if(!picture.getFile().isEmpty()){
            picture.setName(picture.getFile().getOriginalFilename());

            if(!new File(PATH).exists()) {
                new File(PATH).mkdir();
            }

            String filePath = PATH + picture.getName();
            File file = new File(filePath);
            try {
                picture.getFile().transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteFiles() {
        try {
            FileUtils.cleanDirectory(new File(PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
