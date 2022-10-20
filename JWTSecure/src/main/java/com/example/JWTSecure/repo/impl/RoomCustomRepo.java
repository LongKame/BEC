package com.example.JWTSecure.repo.impl;


import com.example.JWTSecure.DTO.RoomDTO;
import com.example.JWTSecure.DTO.TeacherDTO;
import com.example.JWTSecure.domain.Quiz;
import com.example.JWTSecure.domain.Room;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoomCustomRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RoomDTO> doSearch(RoomDTO roomDTO) {

        StringBuilder sql = new StringBuilder()
                .append("select id, roomname, capacity," +
                        "created_at as createdAt, updated_at as updatedAt " +
                        "from room ");
        sql.append(" WHERE 1 = 1 ");
        if (roomDTO.getKey_search()!=null) {
            sql.append(" AND (UPPER(roomname) LIKE CONCAT('%', UPPER(:roomname), '%') ESCAPE '&') ");
        }

        NativeQuery<RoomDTO> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());

        if (roomDTO.getKey_search()!=null) {
            query.setParameter("roomname", "%"+roomDTO.getKey_search()+"%");
        }

        query.addScalar("id", new LongType());
        query.addScalar("roomname", new StringType());
        query.addScalar("capacity", new IntegerType());
        query.addScalar("createdat", new LocalDateTimeType());
        query.addScalar("updatedat", new LocalDateTimeType());


        query.setResultTransformer(Transformers.aliasToBean(RoomDTO.class));
        if (null != String.valueOf(roomDTO.getPage())) {
            query.setMaxResults(roomDTO.getPageSize());
            query.setFirstResult(((roomDTO.getPage() - 1) * roomDTO.getPageSize()));
        }
        return query.list();
    }
}
