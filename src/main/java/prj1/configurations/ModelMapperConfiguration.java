package prj1.configurations;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTransformers;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ModelMapperConfiguration {
  @Bean("mapperForBuilder")
  @Primary
  public ModelMapper mapperWithBuilder() {
    var mapper = new ModelMapper();
    mapper
        .getConfiguration()
        .setDestinationNameTransformer(NameTransformers.builder())
        .setDestinationNamingConvention(NamingConventions.builder())
        .setMatchingStrategy(MatchingStrategies.STRICT) // search match 100%
        .setFieldMatchingEnabled(true)
        .setAmbiguityIgnored(true);
    return mapper;
  }

  @Bean("mapper")
  public ModelMapper mapperWithoutBuilder() {
    var mapper = new ModelMapper();
    mapper
        .getConfiguration()
        .setFieldMatchingEnabled(true)
        .setMatchingStrategy(MatchingStrategies.STRICT)
        .setAmbiguityIgnored(true);
    return mapper;
  }
}
