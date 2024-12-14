package com.api.registration.cofiguration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// this auto class run first and load into the spring boot memory
// because dependency injection is performed after the configuration class loaded to the memory
// and after the config file is loaded spring IOC is got this idea that is the bean have created now that service layer code run

@Configuration
public class ConfigClass {
    // Whenever independence injection is not happening for Some regions like IOC does not be which object to create Then we use this annotation.
    //EX-in my project I want to copy the data entity class to dto class, dto class to entity class Show in my project as use model Mapper library
    // When we try to use model mapper library get throw error,error creating the bean This message got to because IOC
    //Is unable to create an object This particular class
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
