package es.uah.controller;

import es.uah.domain.Picture;
import es.uah.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Simone on 15.01.2017.
 */
@Controller
public class FileUploadController {

    @Autowired
    @Qualifier("UploadFileServiceImpl")
    private UploadFileService uploadFileService;

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
    public void showResult() {
        // TODO
        uploadFileService.deleteFiles();
    }

}
