package es.uah.ocr.domain;

/**
 * @author bnjm@harmless.ninja - 1/15/17.
 */
public class OCRPicture {
    private String containingText;
    private byte[] resultImage;

    public OCRPicture(String containingText, byte[] resultImage) {
        this.containingText = containingText;
        this.resultImage = resultImage;
    }

    public String getContainingText() {
        return containingText;
    }

    public void setContainingText(String containingText) {
        this.containingText = containingText;
    }

    public byte[] getResultImage() {
        return resultImage;
    }

    public void setResultImage(byte[] resultImage) {
        this.resultImage = resultImage;
    }
}
