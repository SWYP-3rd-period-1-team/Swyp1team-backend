package com.swig.zigzzang.calender.repository.Supplement;

import com.swig.zigzzang.calender.domain.Supplement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SupplementRepository extends JpaRepository<Supplement,Long> {
}
