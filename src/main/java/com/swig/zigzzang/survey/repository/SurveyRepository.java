package com.swig.zigzzang.survey.repository;

import com.swig.zigzzang.survey.domain.Survey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

}
