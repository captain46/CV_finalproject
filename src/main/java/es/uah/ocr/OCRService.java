package es.uah.ocr;

import java.io.File;
import java.nio.file.Path;

/**
 * Extracts textual information from a given image using MATLAB Java bindings and the MATLAB Compiler SDK.
 * @see https://ch.mathworks.com/help/compiler_sdk/package-integration.html
 *
 * @author bnjm@harmless.ninja - 1/15/17.
 */
public interface OCRService {

    String imageToText(String absolutePath);

    String imageToText(Path path);

    String imageToText(File file);
}
