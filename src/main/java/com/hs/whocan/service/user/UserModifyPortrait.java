package com.hs.whocan.service.user;

import com.hs.whocan.component.account.user.UserComponent;
import com.hs.whocan.framework.upload.FileReceiveComponent;
import com.hs.whocan.framework.upload.SingleUploadedFile;
import com.hs.whocan.service.WhoCanNeedLoginService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * User: fish
 */
@Service
@Scope("prototype")
public class UserModifyPortrait extends WhoCanNeedLoginService {
    private MultipartFile file;

    @Resource
    private UserComponent userComponent;
    @Resource
    private FileReceiveComponent fileReceiveComponent;

    @Override
    public Boolean execute() {
        SingleUploadedFile singleUploadedFile = saveFile(file);
        userComponent.modifyPortrait(userId,singleUploadedFile.getFilePath());
        return true;
    }

    private SingleUploadedFile saveFile(MultipartFile file) {
        SingleUploadedFile singleUploadedFile = new SingleUploadedFile();
        singleUploadedFile.setFile(file);
        return fileReceiveComponent.upload(singleUploadedFile);
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
