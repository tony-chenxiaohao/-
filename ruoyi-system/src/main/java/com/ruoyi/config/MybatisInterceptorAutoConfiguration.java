package com.ruoyi.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@AutoConfigureAfter(PageHelperAutoConfiguration.class)
@Configuration
public class MybatisInterceptorAutoConfiguration implements InitializingBean {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Override
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        //创建自定义mybatis拦截器，添加到chain的最后面
        MybatisPlusInterceptor mybatisInterceptor = new MybatisPlusInterceptor();
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
            //自己添加
            configuration.addInterceptor(mybatisInterceptor);
        }
    }

}

