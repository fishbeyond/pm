package com.hs.whocan.service.user;

import com.hs.whocan.component.account.user.UserComponent;
import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.upload.FileReceiveComponent;
import com.hs.whocan.component.upload.SingleUploadedFile;
import com.hs.whocan.service.VerifySignInService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * User: fish
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserModifyPortrait extends VerifySignInService {
    private MultipartFile file;

    @Resource
    private UserComponent userComponent;
    @Resource
    private FileReceiveComponent fileReceiveComponent;

    @Override
    public Boolean execute(User user) {
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
