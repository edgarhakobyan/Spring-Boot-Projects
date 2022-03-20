package com.edgar.transaction.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edgar.transaction.model.TransactionProcess;

public interface TransactionProcessRepo extends CrudRepository<TransactionProcess, Long> {
	Optional<Long> findFirstIdByProcessId(String processId);
}