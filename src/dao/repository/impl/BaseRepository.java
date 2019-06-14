package dao.repository.impl;

import dao.repository.EntytiRepository;

public abstract class BaseRepository<T> implements EntytiRepository<T> {

    protected static volatile EntytiRepository INSTANCE = null;


}
