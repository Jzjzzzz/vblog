package com.jzj.vblog;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Jzj
 * @Date 2022/6/6 16:36
 * @Version 1.0
 * @Message:
 */
public class CodeGenerator {

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        run("item_bank",true,map);
    }
    public static void run(String titleName,boolean isVm,HashMap<String,Object> map) {
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java"); //生成地址
        gc.setAuthor("Jzj"); //作者
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService");    //去掉Service接口的首字母I
        gc.setIdType(IdType.AUTO); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启Swagger2模式
        gc.setBaseResultMap(true); //开启通用查询映射结果
        gc.setBaseColumnList(true); //通用查询结果列
        gc.setIdType(IdType.ASSIGN_UUID); //id类型

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/vblog?useSSL=false&useUnicode=true&serverTimezone=Asia/Shanghai&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowPublicKeyRetrieval=true&allowMultiQueries=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.jzj.vblog.web");
        pc.setEntity("pojo.entity"); //此对象与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。
        mpg.setPackageInfo(pc);

        //这里生成Java层面逻辑代码
        if(isVm){
            TemplateConfig tc = new TemplateConfig();
            tc.setEntity("/vm/java/Entity.java.vm");
            tc.setController("/vm/java/Controller.java.vm");
            tc.setService("/vm/java/Service.java.vm");
            tc.setServiceImpl("/vm/java/ServiceImpl.java.vm");
            tc.setMapper("/vm/java/Mapper.java.vm");
            tc.setXml("/vm/java/Mapper.xml.vm");
            mpg.setTemplate(tc);
        }

        // 自定义配置,这段配置会让你更新了数据库字段后，可通过执行该方法一键生成对应entity，其他已生成的原样不动
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        cfg.setFileCreate((configBuilder, fileType, filePath) -> {
            //如果是Entity则直接返回true表示写文件
            if (fileType == FileType.ENTITY) {
                return true;
            }
            //否则先判断文件是否存在
            File file = new File(filePath);
            boolean exist = file.exists();
            if (!exist) {
                file.getParentFile().mkdirs();
            }
            //文件不存在或者全局配置的fileOverride为true才写文件
            return !exist || configBuilder.getGlobalConfig().isFileOverride();
        });

        mpg.setCfg(cfg);
        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(titleName); //这里输入你要生成的表
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);//去掉布尔值的is_前缀（确保tinyint(1)）
        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
        //自动填充策略
        ArrayList<TableFill> tableFills = new ArrayList<>();
        TableFill gmtCreate = new TableFill("create_time", FieldFill.INSERT); //自动填充，创建时间
        TableFill gmtModified = new TableFill("update_time", FieldFill.INSERT_UPDATE); //自动填充，修改时间
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);
        mpg.setStrategy(strategy);


        // 6、执行
        mpg.execute();
    }
}
