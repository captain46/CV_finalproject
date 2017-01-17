package es.uah.controller;

import es.uah.domain.Picture;
import es.uah.ocr.OCRService;
import es.uah.ocr.domain.OCRPicture;
import es.uah.service.UploadFileService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

/**
 * Created by Simone on 15.01.2017.
 */
@Controller
public class FileUploadController {

    @Autowired
    @Qualifier("UploadFileServiceImpl")
    private UploadFileService uploadFileService;

    @Autowired
    private OCRService ocrService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("picture", new Picture());
        return "index";
    }

    @RequestMapping(value = "index", method = RequestMethod.POST)
    public String uploadFile(@ModelAttribute("picture") Picture picture){
        File uploadedFile = uploadFileService.uploadFile(picture);

        return "redirect:/showresult.html?image=" + uploadedFile.getName();
    }

    @RequestMapping(value = "showresult", method = RequestMethod.GET)
    public void showResult(Model model, @RequestParam String image) {
        File imageToProcess = new File(System.getProperty("java.io.tmpdir") + "/" + image);
        OCRPicture ocrPicture = ocrService.getOCRPicture(imageToProcess);
        model.addAttribute("resultText", ocrPicture.getContainingText());
        String base64String = Base64.encodeBase64String(ocrPicture.getResultImage());

        model.addAttribute("image", base64String);
        uploadFileService.deleteFile(imageToProcess);
    }
}
