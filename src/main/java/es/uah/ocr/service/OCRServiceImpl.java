package es.uah.ocr.service;

import com.mathworks.toolbox.javabuilder.MWCharArray;
import com.mathworks.toolbox.javabuilder.MWException;
import es.uah.ocr.OCRService;
import es.uah.ocr.exception.MatlabBindingException;
import imageToText.Class1;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.nio.file.Path;

/**
 * @author bnjm@harmless.ninja - 1/15/17.
 */
@Service
public class OCRServiceImpl implements OCRService {

    private Class1 ocr;

    @Autowired
    public OCRServiceImpl(Class1 ocr) {
        this.ocr = ocr;
    }

    @Override
    public String imageToText(String absolutePath) {
        Assert.hasText(absolutePath, "Path cannot be empty.");
        Assert.isTrue(new File(absolutePath).exists(), "File must exist.");

        return executeMLBindings(absolutePath);
    }

    @Override
    public String imageToText(Path path) {
        Assert.isTrue(path.toFile().exists(), "File must exist.");
        Assert.notNull(path, "Path cannot be null.");

        return executeMLBindings(path.toAbsolutePath().toString());
    }

    @Override
    public String imageToText(File file) {
        Assert.isTrue(file.exists(), "File must exist.");
        Assert.notNull("File cannot be null.");

        String path = file.getAbsolutePath();

        return executeMLBindings(path);
    }


    private String executeMLBindings(String path) {
        MWCharArray mwPath = new MWCharArray(path);
        Object[] returnObj;
        try {
            returnObj = ocr.imageToText(1, mwPath);
        } catch (MWException e) {
            throw new MatlabBindingException("Error occured while processing the image.", e.getCause());
        }

        return StringUtils.join(returnObj, ' ');
    }
}
