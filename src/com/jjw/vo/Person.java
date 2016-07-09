package com.jjw.vo;

//객체지향 프로그래밍이란 객체를 만들어, 객체로 프로그램을 만드는 것이다.
//객체는 상태와 행동으로 구성된다.
//클래스는 변수와 메소드의 집합이다.
//클래스는 인스턴스의 설계도이다.인스턴스는 클래스를 통해 new 연산자로 나온 구체적인 제품이다.
//Person이라는 클래스를 만들었다.

public class Person {
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "id: " + id + " Name: " + name;
	}

}
