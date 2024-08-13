package com.bulletin.repository;

import com.bulletin.entity.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BulletinRepository extends JpaRepository<Bulletin, Long>,
        QuerydslPredicateExecutor<Bulletin> {
}
