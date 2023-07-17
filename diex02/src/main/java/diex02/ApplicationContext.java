package diex02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {
	@Bean
	public B b1() {
		return new B();
	} // < bean id = "b1" class="diex02.B"/>
		
	@Bean
	public B b2() {
		return new B();
	}// < bean id = "b2" class="diex02.B"/>
	
	
	// a1�̶�� �̸��� bean�� ����� �ּ��� �̶� a1���� b1�� setter ���� �˴ϴ�.
	
	@Bean
	public A a1() {
		A a = new A();
		a.setB(b1());  //setter ����
				
		return a;
	}// < bean id ="a1" class="diaex02.A"/>
	
	//a2��� �̸��� A Ÿ�� bean�� ������ּ��� �̶� a2���� �����ڷ� b2�� �����մϴ�.
	
	@Bean
	public A a2() {
		A a = new A();
		return new A(b2());
	} //<bean id ="a2" class="diaex02.A"/>
}
