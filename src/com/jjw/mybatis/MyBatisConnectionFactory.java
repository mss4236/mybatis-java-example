package com.jjw.mybatis;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConnectionFactory {
	
	
	private static SqlSessionFactory sqlSessionFactory;
	//SqlSessionFactory는 SqlSession인스턴스를 만들 수 있다.
	//SqlSessionFactory는 SqlSession인스턴스를 생성하기 위해 사용할 수 있는 6개의 메소드를 가지고있다.
	//SqlSession은 DB에 SQL을 실행하기 위해 필요한 모든 메소드를 가진다. 그래서 SqlSession 인스턴스를 통해서 직접 SQL문을 실행할 수 있다. PersonDAO.java

	//SqlSessionFactory의 scope는 'application scope'
	//한번 만든 뒤 app을 실행하는 동안 존재해야한다. 삭제하거나 재생성 할 필요 없다.
	//application scope : app scope로 유지하기 위한 방법으로 '싱글턴 패턴'. 'static 싱글턴 패턴'을 사용하는 것이다.	
	
	static {
		try {

			String resource = "com/jjw/mybatis/config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			//Resources라고 불리는 utility class, classpath나 파일시스템이나 웹URL로부터 자원들을 간단하게 로드하도록한다.

			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
				//SqlSessionFactoryBuilder는 'XML configuration 파일'로 'SqlSessionFactory인스턴스'를 build 할 수 있다.
				//build 메소드는 'configuration의 인스턴스'를 가진다.
				//SqlSessionFactoryBuilder는 여러개의 build()를 가진다. reader는 'xml문서인 Reader인스턴스를' 가지고있다.
			}
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} catch (IOException iOException) {
			iOException.printStackTrace();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

}
