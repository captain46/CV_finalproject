package es.uah.configuration;

import com.mathworks.toolbox.javabuilder.MWException;
import imageToText.Class1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bnjm@harmless.ninja - 1/15/17.
 */
@Configuration
public class ApplicationContextConfiguration {
    @Bean
    Class1 class1() throws MWException {
        return new Class1();
    }
}
