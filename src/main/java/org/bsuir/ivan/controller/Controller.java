package org.bsuir.ivan.controller;

import java.util.List;

public interface Controller<IdType, Dao, DaoSearchRequest, DaoUpdateRequest> {

    void save(Dao dao);

    Dao findById(IdType id);

    List<Dao> findByParams(DaoSearchRequest searchRequest);

    Dao update(IdType id, DaoUpdateRequest updateRequest);

    void deleteById(IdType id);
}
