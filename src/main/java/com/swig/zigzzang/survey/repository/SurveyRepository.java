package com.swig.zigzzang.survey.repository;

import com.swig.zigzzang.survey.domain.Survey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query("select distinct s from Survey s join fetch s.member m where m.userId = :userid")
    List<Survey> findAllJoinFetch(@Param("userid") String userid);
}
