package com.example.JWTSecure.repo.impl;
import com.example.JWTSecure.DTO.ClassDTO;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClassCustomRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ClassDTO> doSearch(ClassDTO classDTO) {

        StringBuilder sql = new StringBuilder()
                .append("select c.id as class_id, c.name as class_name, c.room_id as room_id, r.roomname as room_name,\n" +
                        "u.id as user_id, t.id as teacher_Id, u.fullname as full_name, u.email as email, c.number_of_student as number_of_student,\n" +
                        "r.capacity as capacity, c.start_date, r.active as active_room\n" +
                        "from class c\n" +
                        "join room r on r.id = c.room_id\n" +
                        "join teacher t on c.teacher_id = t.id\n" +
                        "join users u on t.user_id = u.id  ");
        sql.append(" WHERE 1 = 1 ");
        if(classDTO.getKey_search()!=null){
            sql.append(" AND (UPPER(c.name) LIKE CONCAT('%', UPPER(:class_name), '%') ESCAPE '&') ");
        }
        NativeQuery<ClassDTO> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());

        if (classDTO.getKey_search() != null) {
            query.setParameter("class_name", "%"+classDTO.getKey_search()+"%");
        }

        query.addScalar("class_id", new LongType());
        query.addScalar("class_name", new StringType());
        query.addScalar("room_id", new LongType());
        query.addScalar("room_name", new StringType());
        query.addScalar("user_id", new LongType());
        query.addScalar("teacher_id", new LongType());
        query.addScalar("full_name", new StringType());
        query.addScalar("email", new StringType());
        query.addScalar("number_of_student", new IntegerType());
        query.addScalar("capacity", new IntegerType());
        query.addScalar("start_date", new LocalDateTimeType());
        query.addScalar("active_room", new BooleanType());

        query.setResultTransformer(Transformers.aliasToBean(ClassDTO.class));
        if (null != String.valueOf(classDTO.getPage())) {
            query.setMaxResults(classDTO.getPageSize());
            query.setFirstResult(((classDTO.getPage() - 1) * classDTO.getPageSize()));
        }
        return query.list();
    }

    public List<ClassDTO> getTotal(ClassDTO classDTO) {

        StringBuilder sql = new StringBuilder()
                .append("select c.id as class_id, c.name as class_name, c.room_id as room_id, r.roomname as room_name,\n" +
                        "u.id as user_id, t.id as teacher_Id, u.fullname as full_name, u.email as email, c.number_of_student as number_of_student,\n" +
                        "r.capacity as capacity, c.start_date, r.active as active_room\n" +
                        "from class c\n" +
                        "join room r on r.id = c.room_id\n" +
                        "join teacher t on c.teacher_id = t.id\n" +
                        "join users u on t.user_id = u.id  ");
        sql.append(" WHERE 1 = 1 ");
        if(classDTO.getKey_search()!=null){
            sql.append(" AND (UPPER(c.name) LIKE CONCAT('%', UPPER(:class_name), '%') ESCAPE '&') ");
        }
        NativeQuery<ClassDTO> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());

        if (classDTO.getKey_search() != null) {
            query.setParameter("class_name", "%"+classDTO.getKey_search()+"%");
        }

        query.addScalar("class_id", new LongType());
        query.addScalar("class_name", new StringType());
        query.addScalar("room_id", new LongType());
        query.addScalar("room_name", new StringType());
        query.addScalar("user_id", new LongType());
        query.addScalar("teacher_id", new LongType());
        query.addScalar("full_name", new StringType());
        query.addScalar("email", new StringType());
        query.addScalar("number_of_student", new IntegerType());
        query.addScalar("capacity", new IntegerType());
        query.addScalar("start_date", new LocalDateTimeType());
        query.addScalar("active_room", new BooleanType());

        query.setResultTransformer(Transformers.aliasToBean(ClassDTO.class));
        return query.list();
    }
}
