package kr.spring.config;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = {"kr.spring.mapper"}) // Mapper Interface 메모리 올리기위해 경로설정
@PropertySource({"classpath:persistence-mysql.properties"}) //persistence-mysql.properties 파일을 연결해줌 (위치설정)
public class RootConfig {
   // root-context.xml을 대체할 클래스
   
   @Autowired  // 자동으로 객체 연결해주는 어노테이션
   Environment env; // 내가 만든 properties 파일을 읽어오는 객체
   
   @Bean // 메모리에 사용할 수 있게 로딩하는 어노테이션
   public DataSource myDataSources() {
	// 히카리 config 객체 생성
      HikariConfig hikariConfig = new HikariConfig();
	// db 정보 불러와서 넣어주기 (persistence-mysql.properties)
      hikariConfig.setDriverClassName(env.getProperty("jdbc.driver"));
      hikariConfig.setJdbcUrl(env.getProperty("jdbc.url"));
      hikariConfig.setUsername(env.getProperty("jdbc.user"));
      hikariConfig.setPassword(env.getProperty("jdbc.password"));
      
      HikariDataSource myDataSource = new HikariDataSource(hikariConfig);
      return myDataSource;
   }
   
   @Bean
   public SqlSessionFactory sessionFactory() throws Exception {
						// 예외처리
      SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
      sessionFactory.setDataSource(myDataSources());
      return (SqlSessionFactory) sessionFactory.getObject();
   }
   
   
}


