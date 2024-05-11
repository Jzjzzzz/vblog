package com.jzj.vblog.web.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @Author Jzj
 * @Date 2022/7/8 16:32
 * @Version 1.0
 * @Message:
 */
@SpringBootTest
public class TestDemo01 {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test01(){
        Map<String, FilterRegistrationBean> beans = applicationContext.getBeansOfType(FilterRegistrationBean.class);
        for (Map.Entry<String, FilterRegistrationBean> entry : beans.entrySet()) {
                    FilterRegistrationBean bean = entry.getValue();
                     System.out.println("Filter: " + bean.getFilter().getClass().getName() +
                                        ", Order: " + bean.getOrder());
                 }

    }
}
