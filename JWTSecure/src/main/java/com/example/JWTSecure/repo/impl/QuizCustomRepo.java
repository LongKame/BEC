package com.example.JWTSecure.repo.impl;

import com.example.JWTSecure.DTO.AcademicAdminDTO;
import com.example.JWTSecure.domain.Quiz;
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

public class QuizCustomRepo {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Quiz> doSearch(Long levelId) {

        StringBuilder sql = new StringBuilder()
                .append("SELECT id, aca_id as acaId, level_id as levelId, " +
                        "question, answer_a as answerA, answer_b as answerB, answer_c as answerC, answer_d as answerD, correct \n" +
                        "FROM quiz");
        sql.append(" WHERE 1 = 1 ");
        if(levelId!=null){
            sql.append(" AND level_id = :level_id ORDER BY RANDOM()  LIMIT 20");
        }
        NativeQuery<Quiz> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());

        if (levelId != null) {
            query.setParameter("level_id", levelId);
        }

        query.addScalar("id", new LongType());
        query.addScalar("acaId", new LongType());
        query.addScalar("levelId", new LongType());
        query.addScalar("question", new StringType());
        query.addScalar("answerA", new StringType());
        query.addScalar("answerB", new StringType());
        query.addScalar("answerC", new StringType());
        query.addScalar("answerD", new StringType());
        query.addScalar("correct", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(Quiz.class));
        return query.list();
    }
}
