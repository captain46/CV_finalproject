package es.uah.ocr.service;

import com.mathworks.toolbox.javabuilder.Images;
import com.mathworks.toolbox.javabuilder.MWCharArray;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import es.uah.ocr.OCRService;
import es.uah.ocr.domain.OCRPicture;
import es.uah.ocr.exception.MatlabBindingException;
import imageToText.Class1;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
    public OCRPicture getOCRPicture(String absolutePath) {
        Assert.hasText(absolutePath, "Path cannot be empty.");
        Assert.isTrue(new File(absolutePath).exists(), "File must exist.");

        return assembleOCRPicture(absolutePath);
    }

    @Override
    public OCRPicture getOCRPicture(Path path) {
        Assert.isTrue(path.toFile().exists(), "File must exist.");
        Assert.notNull(path, "Path cannot be null.");

        return assembleOCRPicture(path.toAbsolutePath().toString());
    }

    @Override
    public OCRPicture getOCRPicture(File file) {
        Assert.isTrue(file.exists(), "File must exist.");
        Assert.notNull("File cannot be null.");

        String path = file.getAbsolutePath();

        return assembleOCRPicture(path);
    }

    private Object[] executeMLBindings(String path) {
        MWCharArray mwPath = new MWCharArray(path);
        Object[] returnObj;
        try {
            returnObj = ocr.imageToText(2, mwPath); // first parameter = number of outputs
        } catch (MWException e) {
            throw new MatlabBindingException("Error occured while processing the image.", e.getCause());
        }

        return returnObj;
    }

    private String getText(Object[] objects) {
        return StringUtils.join(objects[0], ' ');
    }

    private byte[] getImage(Object[] objects) {
        BufferedImage bi = Images.renderArrayData((MWNumericArray) objects[1]);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();

    }

    private OCRPicture assembleOCRPicture(String path) {
        Object[] mlReturnValue = executeMLBindings(path);
        String text = getText(mlReturnValue);
        byte[] image = getImage(mlReturnValue);

        return new OCRPicture(text, image);
    }
}
