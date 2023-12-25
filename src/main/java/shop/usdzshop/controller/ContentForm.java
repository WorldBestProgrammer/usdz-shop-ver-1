package shop.usdzshop.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class ContentForm {

    private String name;
    private MultipartFile image;
    private MultipartFile file;
}
