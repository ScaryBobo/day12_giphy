package VTTP.gifphy;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import VTTP.gifphy.service.GiphyService;

@SpringBootTest
class GifphyApplicationTests {
	@Autowired
	GiphyService gifSvc;


	@Test
	void shouldLoadImages(){
		
		List<String> gifList = gifSvc.getGifs("pokemon","g");
		Assertions.assertEquals(10, gifList.size());
	}

}
