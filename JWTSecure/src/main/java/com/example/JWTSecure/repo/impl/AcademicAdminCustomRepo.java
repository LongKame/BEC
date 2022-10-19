package com.example.JWTSecure.repo.impl;

import com.example.JWTSecure.DTO.AcademicAdminDTO;
import com.example.JWTSecure.DTO.StudentDTO;
import com.example.JWTSecure.DTO.TeacherDTO;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class AcademicAdminCustomRepo {
    @PersistenceContext
    private EntityManager entityManager;

    public List<AcademicAdminDTO> doSearch(AcademicAdminDTO academicAdminDTO) {

        StringBuilder sql = new StringBuilder()
                .append("select s.id as acad_Id, s.user_id as user_Id, s.role_id as role_Id,\n" +
                        "u.username as user_name, u.fullname as full_name, u.email as email, u.phone as phone, u.address as address, u.active as active\n" +
                        "from academic_admin s join users u on s.user_id = u.id ");
        sql.append(" WHERE 1 = 1 ");
        if(academicAdminDTO.getKey_search()!=null){
            sql.append(" AND (UPPER(u.fullname) LIKE CONCAT('%', UPPER(:full_name), '%') ESCAPE '&') ");
        }
        NativeQuery<AcademicAdminDTO> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());

        if (academicAdminDTO.getKey_search() != null) {
            query.setParameter("full_name", "%"+academicAdminDTO.getKey_search()+"%");
        }

        query.addScalar("acad_Id", new LongType());
        query.addScalar("user_Id", new LongType());
        query.addScalar("role_Id", new LongType());
        query.addScalar("user_name", new StringType());
        query.addScalar("full_name", new StringType());
        query.addScalar("email", new StringType());
        query.addScalar("phone", new StringType());
        query.addScalar("address", new StringType());
        query.addScalar("active", new BooleanType());

        query.setResultTransformer(Transformers.aliasToBean(AcademicAdminDTO.class));
        if (null != String.valueOf(academicAdminDTO.getPage())) {
            query.setMaxResults(academicAdminDTO.getPageSize());
            query.setFirstResult(((academicAdminDTO.getPage() - 1) * academicAdminDTO.getPageSize()));
        }
        return query.list();
    }
}