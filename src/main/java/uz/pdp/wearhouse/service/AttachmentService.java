package uz.pdp.wearhouse.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.wearhouse.entity.AttachmentContent;
import uz.pdp.wearhouse.entity.Attachments;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.AttachmentContentRepository;
import uz.pdp.wearhouse.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public Result upload(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {

            Attachments attachments = new Attachments();
            attachments.setName(file.getOriginalFilename());
            attachments.setSize(file.getSize());
            attachments.setContentType(file.getContentType());
            Attachments saved = attachmentRepository.save(attachments);

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setBytes(file.getBytes());
            attachmentContent.setAttachments(saved);
            attachmentContentRepository.save(attachmentContent);
            return new Result("Fayl saqlandi!", true, saved.getId());
        }
        return new Result("Error fayl saqlanmadi!", false);
    }

    public Result getFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachments> optionalAttachments = attachmentRepository.findById(id);
        if (optionalAttachments.isPresent()){
              Attachments attachments= optionalAttachments.get();
            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentsId(id);
            if (optionalAttachmentContent.isPresent()){
                AttachmentContent attachmentContent = optionalAttachmentContent.get();
                response.setHeader("Content-Disposition","attachment; filename =\""+attachments.getName()+"\"");
                response.setContentType(attachments.getContentType());
                FileCopyUtils.copy(attachmentContent.getBytes(),response.getOutputStream());
            }
        }
        return new Result("File not  found!",false);
    }

}
