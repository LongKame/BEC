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
public class StudentCustomRepo {
    @PersistenceContext
    private EntityManager entityManager;

    public List<StudentDTO> doSearch(StudentDTO studentDTO) {

        StringBuilder sql = new StringBuilder()
                .append("select s.id as student_Id, s.user_id as user_Id, s.role_id as role_Id, " +
                        " u.username as user_name, u.fullname as full_name, u.email as email, u.phone as phone, u.address as address, u.active as active\\n\" +\n" +
                        " from student s join users u on s.user_id = u.id");
        sql.append(" WHERE 1 = 1 ");
        if (studentDTO.getKey_search()!=null) {
            sql.append(" AND (UPPER(u.fullname) LIKE CONCAT('%', UPPER(:full_name), '%') ESCAPE '&') ");
        }

        NativeQuery<StudentDTO> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());

        if (studentDTO.getKey_search()!=null) {
            query.setParameter("full_name", "%"+studentDTO.getKey_search()+"%");
        }

        query.addScalar("student_Id", new LongType());
        query.addScalar("user_Id", new LongType());
        query.addScalar("role_Id", new LongType());
        query.addScalar("user_name", new StringType());
        query.addScalar("full_name", new StringType());
        query.addScalar("email", new StringType());
        query.addScalar("phone", new StringType());
        query.addScalar("address", new StringType());
        query.addScalar("active", new BooleanType());

        query.setResultTransformer(Transformers.aliasToBean(TeacherDTO.class));
        if (null != String.valueOf(studentDTO.getPage())) {
            query.setMaxResults(studentDTO.getPageSize());
            query.setFirstResult(((studentDTO.getPage() - 1) * studentDTO.getPageSize()));
        }
        return query.list();
    }

    public List<StudentDTO> getTotal(StudentDTO studentDTO) {

        StringBuilder sql = new StringBuilder()
                .append("select s.id as student_Id, s.user_id as user_Id, s.role_id as role_Id, " +
                        " u.username as user_name, u.fullname as full_name, u.email as email, u.phone as phone, u.address as address, u.active as active\\n\" +\n" +
                        " from student s join users u on s.user_id = u.id");
        sql.append(" WHERE 1 = 1 ");
        if (studentDTO.getKey_search()!=null) {
            sql.append(" AND (UPPER(u.fullname) LIKE CONCAT('%', UPPER(:full_name), '%') ESCAPE '&') ");
        }

        NativeQuery<StudentDTO> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());

        if (studentDTO.getKey_search()!=null) {
            query.setParameter("full_name", "%"+studentDTO.getKey_search()+"%");
        }

        query.addScalar("student_Id", new LongType());
        query.addScalar("user_Id", new LongType());
        query.addScalar("role_Id", new LongType());
        query.addScalar("user_name", new StringType());
        query.addScalar("full_name", new StringType());
        query.addScalar("email", new StringType());
        query.addScalar("phone", new StringType());
        query.addScalar("address", new StringType());
        query.addScalar("active", new BooleanType());

        query.setResultTransformer(Transformers.aliasToBean(TeacherDTO.class));
        return query.list();
    }

    public StudentDTO getStudent(StudentDTO studentDTO) {

        StringBuilder sql = new StringBuilder()
                .append("select s.id as student_Id, s.user_id as user_Id, s.role_id as role_Id, \n" +
                        "u.username as user_name, u.fullname as full_name, u.email as email, u.phone as phone, u.address as address, u.active as active\n" +
                        "from student s join users u on s.user_id = u.id ");
         sql.append("WHERE 1 = 1 ");
        if(studentDTO.getUser_Id()!=null){
            sql.append(" AND s.user_Id = :user_Id ");
        }
        NativeQuery<StudentDTO> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());

        if (studentDTO.getUser_Id() != null) {
            query.setParameter("user_Id", studentDTO.getUser_Id());
        }

        query.addScalar("student_Id", new LongType());
        query.addScalar("user_Id", new LongType());
        query.addScalar("role_Id", new LongType());
        query.addScalar("user_name", new StringType());
        query.addScalar("full_name", new StringType());
        query.addScalar("email", new StringType());
        query.addScalar("phone", new StringType());
        query.addScalar("address", new StringType());
        query.addScalar("active", new BooleanType());

        query.setResultTransformer(Transformers.aliasToBean(StudentDTO.class));
        if (null != String.valueOf(studentDTO.getPage())) {
            query.setMaxResults(studentDTO.getPageSize());
            query.setFirstResult(((studentDTO.getPage() - 1) * studentDTO.getPageSize()));
        }
        return (StudentDTO) query.getSingleResult();
    }
//bug
    public List<StudentDTO> getListStudentByIdClass(Long id) {

        StringBuilder sql = new StringBuilder()
                .append("select s.id as student_Id, s.user_id as user_Id, s.class_id as class_Id, " +
                        "u.fullname as full_name, u.email, u.phone, u.address, u.active\n" +
                        "from student s join class c on c.id = s.class_id join users u on s.user_id = u.id ");
        sql.append("WHERE 1 = 1 ");
        if(id!=null){
            sql.append(" AND s.class_id = :class_id ");
        }
        NativeQuery<StudentDTO> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());

        if (id != null) {
            query.setParameter("class_id", id);
        }

        query.addScalar("student_Id", new LongType());
        query.addScalar("user_Id", new LongType());
        query.addScalar("class_Id", new LongType());
        query.addScalar("full_name", new StringType());
        query.addScalar("email", new StringType());
        query.addScalar("phone", new StringType());
        query.addScalar("address", new StringType());
        query.addScalar("active", new BooleanType());

        query.setResultTransformer(Transformers.aliasToBean(StudentDTO.class));
        return query.list();
    }

    public List<StudentDTO> detailStudentClass(Long id) {
        StringBuilder sql = new StringBuilder()
                .append("select s.id as student_Id, s.user_id as user_Id, c.name as class_name,\n" +
                        "u.username as user_name, u.fullname as full_name, u.email as email, u.phone as phone, u.address as address, u.active as active,\n" +
                        "sic.from_date as from_date, sic.to_date as to_date\n" +
                        "from student s join users u on s.user_id = u.id join student_in_class sic on s.id = sic.student_id\n" +
                        "join class c on sic.class_id = c.id ");
        sql.append("WHERE 1 = 1 ");
        if(id!=null){
            sql.append(" AND s.id = :id ");
        }
        NativeQuery<StudentDTO> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());

        if (id != null) {
            query.setParameter("id", id);
        }

        query.addScalar("student_Id", new LongType());
        query.addScalar("user_Id", new LongType());
        query.addScalar("class_name", new StringType());
        query.addScalar("user_name", new StringType());
        query.addScalar("full_name", new StringType());
        query.addScalar("email", new StringType());
        query.addScalar("phone", new StringType());
        query.addScalar("address", new StringType());
        query.addScalar("active", new BooleanType());
        query.addScalar("from_date", new LocalDateTimeType());
        query.addScalar("to_date", new LocalDateTimeType());

        query.setResultTransformer(Transformers.aliasToBean(StudentDTO.class));

        return query.list();
    }
}
