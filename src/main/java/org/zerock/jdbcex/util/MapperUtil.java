package org.zerock.jdbcex.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {

    INSTANCE;

    private ModelMapper modelMapper;

    MapperUtil() {

        this.modelMapper= new ModelMapper();
        this.modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE).setMatchingStrategy(MatchingStrategies.LOOSE);  // STANDARD(지능적 매핑), STRICT(정확히 일치하는 매핑), LOOSE(느슨하게 매핑)

    }

    public ModelMapper get(){
        return modelMapper;
    }

}