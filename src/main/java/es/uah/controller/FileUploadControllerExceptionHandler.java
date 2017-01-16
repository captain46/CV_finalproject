package es.uah.controller;

import es.uah.ocr.exception.MatlabBindingException;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.File;

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
            deleteFiles(dir);
        }
    }

    private void deleteFiles(File directory) {
        Assert.notNull(directory);
        Assert.notNull(directory.list());
        if(directory.list().length == 0) {
            directory.delete();
        } else {
            for(String s : directory.list()) {
                File file = new File(directory.getPath(), s);
                file.delete();
            }
            directory.delete();
        }
    }
}
