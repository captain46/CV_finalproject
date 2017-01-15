package es.uah.domain;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Simone on 15.01.2017.
 */
public class Picture {
    private String name;
    private MultipartFile file;

    public Picture() {
    }

    public Picture(String name, MultipartFile file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
