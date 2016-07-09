import java.util.List;

import com.jjw.dao.PersonDAO;
import com.jjw.mybatis.MyBatisConnectionFactory;
import com.jjw.vo.Person;

public class Main {

	public static void main(String[] args) {

		// jdbcTemplatePersonDAO
		PersonDAO personDAO = new PersonDAO(MyBatisConnectionFactory.getSqlSessionFactory());

		// insert 하기 위해 person 빈을 만든다.
		Person person = new Person();
		
		person.setName("Person 1");
		// ( 1 ) insert person
		personDAO.insert(person);

		// **set name of person
		
		person.setName("Person 2");
		
		// ** insert another person
		
		int id = personDAO.insert(person);

		// ( 2 ) select persons by id
		
		personDAO.selectById(id);

		// ( 3 ) select all
		List<Person> persons = personDAO.selectAll();

		// **set name of all persons
		for (int i = 0; i < persons.size(); i++) {
			persons.get(i).setName("Person Name " + i);
			// ( 4 ) update person
			personDAO.update(persons.get(i));
		}

		// **check update
		persons = personDAO.selectAll();

	}

}
