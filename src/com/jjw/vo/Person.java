package com.jjw.vo;

//��ü���� ���α׷����̶� ��ü�� �����, ��ü�� ���α׷��� ����� ���̴�.
//��ü�� ���¿� �ൿ���� �����ȴ�.
//Ŭ������ ������ �޼ҵ��� �����̴�.
//Ŭ������ �ν��Ͻ��� ���赵�̴�.�ν��Ͻ��� Ŭ������ ���� new �����ڷ� ���� ��ü���� ��ǰ�̴�.
//Person�̶�� Ŭ������ �������.

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
