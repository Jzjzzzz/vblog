package com.jzj.vblog.web.service.impl.upload;

import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.ResponseEnum;
import com.jzj.vblog.utils.uuid.IdUtils;
import com.jzj.vblog.web.pojo.enums.UploadCode;
import com.jzj.vblog.web.service.UploadService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 本地文件存储实现类
 * </p>
 *
 * @author Jzj
 * @since 2022/7/27 17:14
 */
@Service
public class LocalUploadServiceImpl implements UploadService {

    /**
     * 获取当前实现类编码
     */
    @Override
    public UploadCode getCode() {
        return UploadCode.LOCAL;
    }

    /**
     * 文件上传本地
     *
     * @param photo   文件
     * @param name    文件模块名
     */
    @Override
    public String uploadImg(MultipartFile photo, String name, HttpServletRequest request) {
        try {
            File folder = new File(ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static" + '/' + name + '/' + LocalDate.now().getYear() + '/');
            if (!folder.isDirectory()) {
                boolean mkdirsResult = folder.mkdirs();
                if (!mkdirsResult) {
                    throw new BusinessException("文件夹创建失败！");
                }
            }
            // 对上传的文件重命名，避免文件重名
            String oldName = photo.getOriginalFilename();
            String newName = IdUtils.fastSimpleUUID() + oldName.substring(oldName.lastIndexOf("."));
            // 文件保存
            photo.transferTo(new File(folder, newName));
            // 返回上传文件的访问路径
            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + name + "/" + LocalDate.now().getYear() + "/" + newName;
        } catch (Exception e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR);
        }
    }

    /**
     * 本地文件删除
     */
    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    @Override
    public boolean deleteImg(String url, HttpServletRequest request) {
        try {
            //获取需要截取部分
            String s = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
            String substring = url.substring(s.length());
            //文件位置
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static" + '/' + substring;
            File file = new File(path);
            if (file.exists()) {
                return file.delete();
            } else {
                throw new BusinessException(ResponseEnum.UPLOAD_DELETE_NOT);
            }
        } catch (Exception e) {
            throw new BusinessException(ResponseEnum.UPLOAD_DELETE_ERROR);
        }
    }

    /**
     * 本地批量删除
     */
    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    @Override
    public void deleteBtnImg(List<String> list, HttpServletRequest request) {
        try {
            //获取需要截取部分
            String sub = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
            list.forEach(s -> {
                String substring = s.substring(sub.length());
                String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static" + '/' + substring;
                File file = new File(path);
                if (file.exists()) {
                    file.delete();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
