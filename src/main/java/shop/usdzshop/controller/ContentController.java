package shop.usdzshop.controller;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.usdzshop.domain.Content;
import shop.usdzshop.service.ContentService;

@Controller
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/")
    public String showContents(Model model) {
        model.addAttribute("contents", contentService.findContents());
        return "index.html";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("contentForm", new ContentForm());
        return "contents/createContentForm.html";
    }

    @PostMapping("/new")
    public String create(@Valid ContentForm form, BindingResult result) throws IOException {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "contents/createContentForm.html";
        }

        String image = Base64.getEncoder().encodeToString(form.getImage().getBytes());
        byte[] fileBytes = form.getFile().getBytes();

        Content content = new Content(form.getName(), image, fileBytes);
        contentService.join(content);

        System.out.println("NAME:" + content.getName());
        System.out.println("imageBytes = " + image);
        System.out.println("fileBytes = " + fileBytes);
        return "redirect:/";
    }

    @GetMapping("/download/{contentId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("contentId") Long contentId) throws IOException {

        Content content = contentService.findOne(contentId);
        byte[] file = content.getFile();

        ByteArrayResource resource = new ByteArrayResource(file);

        String fileName = "file_" + content.getName() + ".usdz";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attatchment; filename=" + fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length)
                .body(resource);
    }

}
