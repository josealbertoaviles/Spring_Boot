package inicio;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	//definiciÃ³n roles y usuarios
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth
	        .inMemoryAuthentication()
	        .withUser("user1")
	          .password("{noop}user1") //lo de {noop} se pone para no obligar a usar mecanismo de encriptaciÃ³n
	          .roles("USER")
	          .and()
	        .withUser("admin")
	          .password("{noop}admin")
	          .roles("USER", "ADMIN");
			
			/*lo siguiente serÃ­a para el caso de que
			 * quisiÃ©ramos encriptar la password:
			 * auth
		        .inMemoryAuthentication()
		        .withUser("user1")
		          .password(new BCryptPasswordEncoder().encode("user1"))
		          .roles("USER")
		          .and()
		        .withUser("admin")
		          .password(new BCryptPasswordEncoder().encode("admin"))
		          .roles("USER", "ADMIN");
			 */
			
			/*la seguiente configuraciÃ³n serÃ­a para el caso de 
			 * usuarios en una base de datos
			 * auth.jdbcAuthentication().dataSource(dataSource)
	        	.usersByUsernameQuery("select username, password, enabled"
	            	+ " from users where username=?")
	        	.authoritiesByUsernameQuery("select username, authority "
	            	+ "from authorities where username=?");
			 */
		}
		//definiciÃ³n de polÃ­ticas de seguridad
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST,"/contactos").hasRole("ADMIN")
			.antMatchers("/contactos").authenticated()	
			.antMatchers("/eliminarPorEmail/").authenticated()//aqui indicamos que direcciones tienen qe accder con autenticacion	
			//.antMatchers("/**").authenticated()
			.and()
			.httpBasic();
		
		}
}
