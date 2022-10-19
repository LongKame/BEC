package com.example.JWTSecure.repo.impl;

import com.example.JWTSecure.DTO.StudentDTO;
import com.example.JWTSecure.DTO.TeacherDTO;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TeacherCustomRepo {
    @PersistenceContext
    private EntityManager entityManager;

    public List<TeacherDTO> doSearch(TeacherDTO teacherDTO) {

        StringBuilder sql = new StringBuilder()
                .append("select t.id as teacher_Id, t.user_id as user_Id, t.role_id as role_Id, \n" +
                        "u.username as user_name, u.fullname as full_name, u.email as email, u.phone as phone, u.address as address, u.active as active\n" +
                        "from teacher t join users u on t.user_id = u.id");
        sql.append(" WHERE 1 = 1 ");
        if (teacherDTO.getKey_search()!=null) {
            sql.append(" AND (UPPER(u.fullname) LIKE CONCAT('%', UPPER(:full_name), '%') ESCAPE '&') ");
        }

        NativeQuery<TeacherDTO> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());

        if (teacherDTO.getKey_search()!=null) {
            query.setParameter("full_name", "%"+teacherDTO.getKey_search()+"%");
        }

        query.addScalar("teacher_Id", new LongType());
        query.addScalar("user_Id", new LongType());
        query.addScalar("role_Id", new LongType());
        query.addScalar("user_name", new StringType());
        query.addScalar("full_name", new StringType());
        query.addScalar("email", new StringType());
        query.addScalar("phone", new StringType());
        query.addScalar("address", new StringType());
        query.addScalar("active", new BooleanType());

        query.setResultTransformer(Transformers.aliasToBean(TeacherDTO.class));
        if (null != String.valueOf(teacherDTO.getPage())) {
            query.setMaxResults(teacherDTO.getPageSize());
            query.setFirstResult(((teacherDTO.getPage() - 1) * teacherDTO.getPageSize()));
        }
        return query.list();
    }


    public TeacherDTO getTeacher(TeacherDTO teacherDTO) {

        StringBuilder sql = new StringBuilder()
                .append("select s.id as teacher_Id, s.user_id as user_Id, s.role_id as role_Id,\n" +
                        "u.username as user_name, u.fullname as full_name, u.email as email, u.phone as phone, u.address as address, u.active as active\n" +
                        "from teacher s join users u on s.user_id = u.id ");
        sql.append("WHERE 1 = 1 ");
        if(teacherDTO.getUser_Id()!=null){
            sql.append(" AND s.user_Id = :user_Id ");
        }
        NativeQuery<TeacherDTO> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());

        if (teacherDTO.getUser_Id() != null) {
            query.setParameter("user_Id", teacherDTO.getUser_Id());
        }

        query.addScalar("teacher_Id", new LongType());
        query.addScalar("user_Id", new LongType());
        query.addScalar("role_Id", new LongType());
        query.addScalar("user_name", new StringType());
        query.addScalar("full_name", new StringType());
        query.addScalar("email", new StringType());
        query.addScalar("phone", new StringType());
        query.addScalar("address", new StringType());
        query.addScalar("active", new BooleanType());

        query.setResultTransformer(Transformers.aliasToBean(TeacherDTO.class));
        if (null != String.valueOf(teacherDTO.getPage())) {
            query.setMaxResults(teacherDTO.getPageSize());
            query.setFirstResult(((teacherDTO.getPage() - 1) * teacherDTO.getPageSize()));
        }
        return (TeacherDTO) query.getSingleResult();
    }
}
