package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.Dto;
import com.kodilla.backend.domain.entity.Entity;
import com.kodilla.backend.domain.entity.User;
import com.kodilla.backend.mapper.mappers.UserMapper;


//SINGLETON + FACTORY
public class MapperFactory<T extends Entity, S extends Dto> {

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

    public Mapper<T, S> getMapper(Class entity) {
        if (entity.equals(User.class)) {
            Mapper mapper = new UserMapper();
            return mapper;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
