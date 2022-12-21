package com.epam.rd.tasks.zoo.setting;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import javax.sql.DataSource;
import java.io.InputStream;

public class MyBatisUtils {
    public SqlSessionFactory sqlSessionFactory(String way) throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(way);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
