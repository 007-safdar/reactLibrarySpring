
package react.library.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import react.library.springboot.entity.Book;
import react.library.springboot.entity.Review;;
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    private String theAllowedOrigins = "http://localhost:3000";
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,CorsRegistry cors){
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);
        config.exposeIdsFor(Book.class);
        config.exposeIdsFor(Review.class);
    }

}


