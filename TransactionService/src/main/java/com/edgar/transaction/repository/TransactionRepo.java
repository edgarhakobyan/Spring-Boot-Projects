package com.edgar.transaction.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edgar.transaction.model.Transaction;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {
	Optional<Transaction> findFirstByCode(String code);
}
