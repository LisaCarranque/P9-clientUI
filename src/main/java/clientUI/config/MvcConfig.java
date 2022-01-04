package clientUI.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Generated
public class MvcConfig implements WebMvcConfigurer {

    /**
     * This method is used to associate a view to the corresponding url path
     *
     * @param registry
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/").setViewName("index");
    }

}