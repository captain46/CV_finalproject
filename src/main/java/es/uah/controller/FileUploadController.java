package es.uah.controller;

import es.uah.domain.Picture;
import es.uah.ocr.OCRService;
import es.uah.ocr.domain.OCRPicture;
import es.uah.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.BASE64Encoder;

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
        uploadFileService.uploadFile(picture);
        return "redirect:/showresult.html";
    }

    @RequestMapping(value = "showresult", method = RequestMethod.GET)
    public void showResult(Model model) {
        File imageToProcess = new File(System.getProperty("user.dir") + "/docs/pics/").listFiles()[0];
        OCRPicture ocrPicture = ocrService.getOCRPicture(imageToProcess);
        model.addAttribute("resultText", ocrPicture.getContainingText());

        BASE64Encoder encoder = new BASE64Encoder();
        String base64string = encoder.encode(ocrPicture.getResultImage());

        model.addAttribute("image", base64string);
        uploadFileService.deleteFiles();
    }
}
