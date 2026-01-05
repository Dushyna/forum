package ait.cohort70.configuration;

import ait.cohort70.forum.dto.FileDto;
import ait.cohort70.forum.model.AttachedFile;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

public class ServiceConfiguration {

    @Bean
    ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        mapper.addMappings(new PropertyMap<AttachedFile, FileDto>() {
            protected void configure() {
                map(source.getFileName(), destination.getFileName());
                map(source.getContentType(), destination.getContentType());
                map(source.getContent(), destination.getContent());
            }
        });
        return mapper;
    }
    @Bean
    PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();

    }
}
