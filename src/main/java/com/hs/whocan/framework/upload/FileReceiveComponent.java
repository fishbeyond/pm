package com.hs.whocan.framework.upload;

import com.hs.whocan.framework.utils.UUIDGenerator;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
@Service
public class FileReceiveComponent {

    private String fileUploadSavePath;
    private UUIDGenerator uuidGenerator;
    private String fileSubDirPattern = String.format("%1$syyyy%1$sMM%1$sdd%1$s", SystemUtils.FILE_SEPARATOR);

    public SingleUploadedFile upload(SingleUploadedFile uploadedFile){
        MultipartFile file = uploadedFile.getFile();

        uploadedFile.setFileId(uuidGenerator.shortUuid());
        uploadedFile.setOriginalName(file.getOriginalFilename());
        uploadedFile.setExtName(getFileExtName(file.getOriginalFilename()));
        uploadedFile.setFileName(uploadedFile.getFileId() + "." + uploadedFile.getExtName());
        uploadedFile.setFileUrl(createFileDir(uploadedFile) + uploadedFile.getFileName());
        uploadedFile.setFilePath(fileUploadSavePath + uploadedFile.getFileUrl());

        saveFile(uploadedFile);

        return uploadedFile;
    }

    protected void saveFile(SingleUploadedFile uploadedFile) {
        try {
            uploadedFile.getFile().transferTo(new File(uploadedFile.getFilePath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String createFileDir(SingleUploadedFile partyPhoto) {
        String dir = DateFormatUtils.format(partyPhoto.getUploadTime(), fileSubDirPattern);
        File fileDir = new File(fileUploadSavePath + dir);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return dir;
    }

    protected String getFileExtName(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }


    public String getFileUploadSavePath() {
        return fileUploadSavePath;
    }

    public void setFileUploadSavePath(String fileUploadSavePath) {
        this.fileUploadSavePath = fileUploadSavePath;
    }

    public String getFileSubDirPattern() {
        return fileSubDirPattern;
    }

    public void setFileSubDirPattern(String fileSubDirPattern) {
        this.fileSubDirPattern = fileSubDirPattern;
    }

    public UUIDGenerator getUuidGenerator() {
        return uuidGenerator;
    }

    public void setUuidGenerator(UUIDGenerator uuidGenerator) {
        this.uuidGenerator = uuidGenerator;
    }
}
