package uz.pdp.wearhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @GetMapping
    public Result getAttachment(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Result result = attachmentService.getFile(id, response);
        return result;
    }

    @PostMapping
    public Result upload(MultipartHttpServletRequest request) {
        Result upload = attachmentService.upload(request);
        return upload;

    }

}
