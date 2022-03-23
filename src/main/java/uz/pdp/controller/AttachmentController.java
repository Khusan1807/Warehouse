package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.dto.ApiResponse;
import uz.pdp.service.AttachmentService;

import java.io.IOException;

@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class AttachmentController {


    private final AttachmentService attachmentService;


    @PostMapping("/upload")
    public ApiResponse upload(MultipartHttpServletRequest request) throws IOException {
        return attachmentService.uploadFile(request);
    }
}
