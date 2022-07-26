package com.jzj.vblog.utils.sign;

import java.util.Calendar;

/**
 * @Author Jzj
 * @Date 2022/7/26 16:18
 * @Version 1.0
 * @Message: 图片上传工具类
 */
public class UpPhotoNameUtils {
    public static String getPhotoName(String name,String imageFileName){
        String fileName = name;
        Calendar cal=Calendar.getInstance();
        //如果年的目录不存在，创建年的目录
        int year=cal.get(Calendar.YEAR);
        fileName=fileName + "_" + year;
        //如果月份不存在，创建月份的目录
        int month=cal.get(Calendar.MONTH)+1;
        fileName=fileName+"_";
        if(month<10)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+month;
        //生成文件名的日部分
        int day=cal.get(Calendar.DAY_OF_MONTH);
        fileName=fileName+"_";
        if(day<10)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+day;
        //生成文件名的小时部分
        int hour=cal.get(Calendar.HOUR_OF_DAY);
        if(hour<10)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+hour;
        //生成文件名的分钟部分
        int minute=cal.get(Calendar.MINUTE);
        if(minute<10)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+minute;
        //生成文件名的秒部分
        int second=cal.get(Calendar.SECOND);
        if(second<10)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+second;
        //生成文件名的毫秒部分
        int millisecond=cal.get(Calendar.MILLISECOND);
        if(millisecond<10)
        {
            fileName=fileName+"0";
        }
        if(millisecond<100)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+millisecond;
        //生成文件的扩展名部分,只截取最后单位
        String endName = imageFileName.substring(imageFileName.lastIndexOf("."));//截取之后的值
        fileName=fileName+ endName;
        return fileName;
    }
}
