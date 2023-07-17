package diex01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		// A��ü ������ B ��ü�� �����Ͽ� A.info() �޼ҵ带 ȣ���ϼ���
	
		A a = new A();
		// B b = new B();
		// a.setB(b);
		a.setB(new B()); // ���� ������ �߻��Ѵ� (setter ����)
		a.info();
		
		//student ��ü ���� �� school ��ü �����Ͽ� 
		//student�� info �޼ҵ� ȣ��
		//�̶� �л��� : ȫ�浿 , ���� : 20��
		
		Student student = new Student();
		School school = new School();
		
		student.setSchool(school); // setter ����
		
		student.setName("ȫ�浿");
		student.setAge("20��");
		student.info();
		
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationcontext.xml");
		
		A a1 = ctx.getBean("a1",A.class);
		a1.info();
		
		A a2 = ctx.getBean("a2",A.class);
		a2.info();
		System.out.println();
		
		// �� student ������ ioc �����̳ʸ� Ȱ���Ͽ� �Ȱ��� ����� ���� �� �ֵ��� �ۼ��ϼ���
		
		Student student1 = ctx.getBean("sudent",Student.class);
		student1.info();
		
		
	}

}
