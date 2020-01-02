package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
@EntityScan(basePackages = {"model"})
@ComponentScan(basePackages = {"controller","service"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	//creaccion del objeto RestTemplate, hay que hacerlo si o si
	@Bean
	public RestTemplate crearTemplate() {
		return new RestTemplate();
	}
}
