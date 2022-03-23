package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.dto.ApiResponse;
import uz.pdp.entity.attachment.Attachment;
import uz.pdp.entity.attachment.AttachmentContent;
import uz.pdp.repository.AttachmentContentRepo;
import uz.pdp.repository.AttachmentRepo;

import java.io.IOException;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class AttachmentService {


    private final AttachmentRepo attachmentRepo;
    private final AttachmentContentRepo attachmentContentRepo;


    public ApiResponse uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        // Attachment ga nomini, size ni, ContentType ni sqlaymiz.
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepo.save(attachment);

        // Bytes ==> asosoiy mag'izini saqlaymiz
        AttachmentContent attachmentContent = new AttachmentContent(file.getBytes(), savedAttachment);
        attachmentContentRepo.save(attachmentContent);
        return new ApiResponse(true, "Saved", savedAttachment.getId());
    }

}
