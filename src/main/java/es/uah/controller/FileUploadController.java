package es.uah.controller;

import es.uah.domain.Picture;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by Simone on 15.01.2017.
 */
@Controller
public class FileUploadController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("picture", new Picture());
        return "index";
    }

    @RequestMapping(value = "index", method = RequestMethod.POST)
    public String uploadFile(@ModelAttribute("picture") Picture picture){

        if(!picture.getFile().isEmpty()){
            picture.setName(picture.getFile().getOriginalFilename());
            String path = System.getProperty("user.dir") + "/docs/" + picture.getName();
            File file = new File(path);
            try {
                picture.getFile().transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/showresult.html";
    }

    @RequestMapping(value = "showresult", method = RequestMethod.GET)
    public void showResult() {

    }

}
