package com.jzj.vblog.web.service.impl.upload;

import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.ResponseEnum;
import com.jzj.vblog.utils.uuid.IdUtils;
import com.jzj.vblog.web.pojo.enums.UploadCode;
import com.jzj.vblog.web.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

/**
 * @Author Jzj
 * @Date 2022/7/27 17:14
 * @Version 1.0
 * @Message: 本地文件存储实现类
 */
@Service
public class LocalUploadServiceImpl implements UploadService {


    /**
     * 获取当前实现类编码
     * @return
     */
    @Override
    public UploadCode getCode() {
        return UploadCode.LOCAL;
    }

    /**
     * 文件上传本地
     * @param photo 文件
     * @param name 文件模块名
     * @param request
     * @return
     */
    @Override
    public String uploadImg(MultipartFile photo, String name, HttpServletRequest request) {
        File folder = new File(ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static"+'/'+name+'/'+ LocalDate.now().getYear()+'/');
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        // 对上传的文件重命名，避免文件重名
        String oldName = photo.getOriginalFilename();
        String newName = IdUtils.fastSimpleUUID() + oldName.substring(oldName.lastIndexOf("."));
        try {
            // 文件保存
            photo.transferTo(new File(folder, newName));
            // 返回上传文件的访问路径
            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() +"/"+name+"/"+LocalDate.now().getYear()+"/"+ newName;
            return url;
        } catch (IOException e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR);
        }
    }

}
