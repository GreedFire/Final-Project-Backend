package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.Dto;
import com.kodilla.backend.domain.entity.Entity;
import com.kodilla.backend.domain.entity.User;
import com.kodilla.backend.mapper.mappers.UserMapper;


//SINGLETON + FACTORY
public class MapperFactory {

    private static MapperFactory mapperFactoryInstance = null;

    private MapperFactory() {

    }

    public static MapperFactory getInstance() {
        if (mapperFactoryInstance == null) {
            synchronized (MapperFactory.class) {
                if (mapperFactoryInstance == null) {
                    mapperFactoryInstance = new MapperFactory();
                }
            }
        }
        return mapperFactoryInstance;
    }

    public Mapper getMapper(Class clazz) {
        if (clazz.equals(User.class)) return new UserMapper();
        else throw new IllegalArgumentException();
    }


}
