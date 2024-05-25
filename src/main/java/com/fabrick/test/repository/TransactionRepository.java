package com.fabrick.test.repository;

import com.fabrick.test.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for queries on transaction entities
 */
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
