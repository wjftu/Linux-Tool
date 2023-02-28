package com.wjftu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileController {

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("dest") String dest, Model model){
        String fileName = file.getOriginalFilename();
        File destFile = new File(dest);
        if(destFile.exists()){
            model.addAttribute("flag", false);
            model.addAttribute("msg", "file exist");
            return "upload";
        }
        try {
            file.transferTo(destFile);
            model.addAttribute("flag", true);
        } catch (IOException e) {
            model.addAttribute("flag", false);
            model.addAttribute("msg", e.getMessage());
        }
        return "upload";
    }

}
