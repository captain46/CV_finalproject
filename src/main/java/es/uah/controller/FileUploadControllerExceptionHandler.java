package es.uah.controller;

import es.uah.ocr.exception.MatlabBindingException;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.File;
import java.io.IOException;

/**
 * @author bnjm@harmless.ninja - 1/16/17.
 */
@ControllerAdvice
public class FileUploadControllerExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MatlabBindingException.class)
    public String handleError() {
        clearUploadPicture();
        return "error";
    }

    /**
     * Delete whats left from the image upload after handling {@link MatlabBindingException}
     */
    private void clearUploadPicture() {
        File dir = new File(System.getProperty("user.dir") + "/docs/pics/");
        if(dir.exists() && dir.isDirectory()) {
            try {
                FileUtils.cleanDirectory(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
