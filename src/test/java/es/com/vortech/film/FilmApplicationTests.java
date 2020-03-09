package es.com.vortech.film;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
class FilmApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void main(){
		FilmApplication.main(new String[]{});
	}

}
