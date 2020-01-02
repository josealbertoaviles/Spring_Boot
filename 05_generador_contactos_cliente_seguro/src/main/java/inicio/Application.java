package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
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
		//creacion de interceptores de segurida
		BasicAuthenticationInterceptor interceptor = new BasicAuthenticationInterceptor("admin", "admin");
		RestTemplate template = new RestTemplate();
		template.getInterceptors().add(interceptor);
		return template;
	}
}
