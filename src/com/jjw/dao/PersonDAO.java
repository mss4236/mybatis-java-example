package com.jjw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jjw.vo.Person;

public class PersonDAO {
	
	private SqlSessionFactory sqlSessionFactory = null;

	public PersonDAO(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	//scope : ��ȿ����, �޼ҵ� �ȿ� ����� ������ �޼ҵ� �ȿ����� ��ȿ�ϴ�.
	//�������� : Ŭ���� �������� ���� �� �� �ִ� ����
	//�������� : �޼ҵ� �������� ���� �� �� �ִ� ����, method scope
	//SqlSession�� scope : �� ������� ��ü������ 'SqlSession�ν��Ͻ�'�� �������Ѵ�. SqlSession�ν��Ͻ��� �������� �ʰ�, '������ safe' ���� �ʴ�. ���� ���� scope�� request �Ǵ� 'method scope'�̴�.
	//�׷��Ƿ� SqlSession�� static field�� instance field of a class��  �����ؼ��� �ȵȴ�.
	//static field : static Ű���尡 ���� ������ Ŭ���� ����̴�. �ν��Ͻ��� ������� ������ ���� ���� ���, �ν��Ͻ� ������ �ʿ���� ���� Ŭ������ ������ ���, ���� ���� ������ ��� �ν��Ͻ��� �����ؾ��ϴ� ����̴�.
	//HTTP ��û�� ������ �������� �δ� ������ �����ؾ� �Ѵ�. �޸� ���ؼ� HTTP ��û�� ���������� ����� ������ �����Ҷ����� SqlSession �� ���� �� �ִ�. 
		
	/**
	 * Returns the list of all Person instances from the database.
	 * 
	 * @return the list of all Person instances from the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Person> selectAll() {
		
		List<Person> list = null;
		
		/* <����-------------- �̷� ���¸� �����Ѵ�. DB �ڿ��� �� �ݴ� ���� �����Ѵ�. --------------����> */
		
		SqlSession session = sqlSessionFactory.openSession();
		//SqlSession �ν��Ͻ��� ���ؼ� ���� SQL���� ������ �� �ִ�.
		
		try {
			list = session.selectList("Person.selectAll");
		} finally {
			session.close();
			//SqlSession�� �ݴ� ���� �߿��ϴ�. �׻� finally ���Ͽ��� �ݾƾ��Ѵ�.
		}
		
		/* <��-------------- �̷� ���¸� �����Ѵ�. DB �ڿ��� �� �ݴ� ���� �����Ѵ�. --------------��> */
		
		System.out.println("selectAll() --> " + list);
		return list;

	}

	/**
	 * Select instance of Person from the database.
	 * 
	 * @param person
	 *            the instance to be persisted.
	 */
	public Person selectById(int id) {
		Person person = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			person = session.selectOne("Person.selectById", id);

		} finally {
			session.close();
		}
		System.out.println("selectById(" + id + ") --> " + person);
		return person;
	}

	/**
	 * Insert an instance of Person into the database.
	 * 
	 * @param person
	 *            the instance to be persisted.
	 */
	public int insert(Person person) {
		int id = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			id = session.insert("Person.insert", person);
		} finally {
			session.commit();
			session.close();
		}
		System.out.println("insert(" + person + ") --> " + person.getId());
		return id;
	}

	/**
	 * Update an instance of Person into the database.
	 * 
	 * @param person
	 *            the instance to be persisted.
	 */
	public void update(Person person) {
		int id = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			id = session.update("Person.update", person);

		} finally {
			session.commit();
			session.close();
		}
		System.out.println("update(" + person + ") --> updated");
	}

	/**
	 * Delete an instance of Person from the database.
	 * 
	 * @param id
	 *            value of the instance to be deleted.
	 */
	public void delete(int id) {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			session.delete("Person.delete", id);
		} finally {
			session.commit();
			session.close();
		}
		System.out.println("delete(" + id + ")");

	}

}