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

	//scope : 유효범위, 메소드 안에 선언된 변수는 메소드 안에서만 유효하다.
	//전역변수 : 클래스 전역에서 접근 할 수 있는 변수
	//지역변수 : 메소드 내에서만 접근 할 수 있는 변수, method scope
	//SqlSession의 scope : 각 쓰레드는 자체적으로 'SqlSession인스턴스'를 가져야한다. SqlSession인스턴스는 공유되지 않고, '스레드 safe' 하지 않다. 가장 좋은 scope는 request 또는 'method scope'이다.
	//그러므로 SqlSession을 static field나 instance field of a class로  지정해서는 안된다.
	//static field : static 키워드가 붙은 변수는 클래스 멤버이다. 인스턴스에 상관없이 고정된 값을 가질 경우, 인스턴스 생성할 필요없는 값을 클래스에 저장할 경우, 값의 변경 사항을 모든 인스턴스가 공유해야하는 경우이다.
	//HTTP 요청과 유사한 스코프에 두는 것으로 고려해야 한다. 달리 말해서 HTTP 요청을 받을때마다 만들고 응답을 리턴할때마다 SqlSession 을 닫을 수 있다. 
		
	/**
	 * Returns the list of all Person instances from the database.
	 * 
	 * @return the list of all Person instances from the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Person> selectAll() {
		
		List<Person> list = null;
		
		/* <시작-------------- 이런 형태를 권장한다. DB 자원을 잘 닫는 것을 보장한다. --------------시작> */
		
		SqlSession session = sqlSessionFactory.openSession();
		//SqlSession 인스턴스를 통해서 직접 SQL문을 실행할 수 있다.
		
		try {
			list = session.selectList("Person.selectAll");
		} finally {
			session.close();
			//SqlSession을 닫는 것은 중요하다. 항상 finally 블록에서 닫아야한다.
		}
		
		/* <끝-------------- 이런 형태를 권장한다. DB 자원을 잘 닫는 것을 보장한다. --------------끝> */
		
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
