package com.jjw.mybatis;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConnectionFactory {
	
	
	private static SqlSessionFactory sqlSessionFactory;
	//SqlSessionFactory�� SqlSession�ν��Ͻ��� ���� �� �ִ�.
	//SqlSessionFactory�� SqlSession�ν��Ͻ��� �����ϱ� ���� ����� �� �ִ� 6���� �޼ҵ带 �������ִ�.
	//SqlSession�� DB�� SQL�� �����ϱ� ���� �ʿ��� ��� �޼ҵ带 ������. �׷��� SqlSession �ν��Ͻ��� ���ؼ� ���� SQL���� ������ �� �ִ�. PersonDAO.java

	//SqlSessionFactory�� scope�� 'application scope'
	//�ѹ� ���� �� app�� �����ϴ� ���� �����ؾ��Ѵ�. �����ϰų� ����� �� �ʿ� ����.
	//application scope : app scope�� �����ϱ� ���� ������� '�̱��� ����'. 'static �̱��� ����'�� ����ϴ� ���̴�.	
	
	static {
		try {

			String resource = "com/jjw/mybatis/config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			//Resources��� �Ҹ��� utility class, classpath�� ���Ͻý����̳� ��URL�κ��� �ڿ����� �����ϰ� �ε��ϵ����Ѵ�.

			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
				//SqlSessionFactoryBuilder�� 'XML configuration ����'�� 'SqlSessionFactory�ν��Ͻ�'�� build �� �� �ִ�.
				//build �޼ҵ�� 'configuration�� �ν��Ͻ�'�� ������.
				//SqlSessionFactoryBuilder�� �������� build()�� ������. reader�� 'xml������ Reader�ν��Ͻ���' �������ִ�.
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
