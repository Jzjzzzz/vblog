package com.jzj.vblog.utils.sign;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文件读取工具类
 * </p>
 *
 * @author Jzj
 * @since 2022/7/11 14:33
 */
public class FileUtils {

    public static List<String> getContent(String fileName)  {
        List<String> res = new ArrayList<>();
        try {
            File file = new File(fileName);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");// 编码格式必须和文件的一致
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    res.add(lineTxt);
                }
                read.close();
            } else {
                System.out.println("指定的文件不存在");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return res;
    }
}
