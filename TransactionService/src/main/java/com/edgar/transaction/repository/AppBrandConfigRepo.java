package com.edgar.transaction.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edgar.transaction.model.AppBrandConfig;
import com.edgar.transaction.model.AppId;
import com.edgar.transaction.model.Brand;

public interface AppBrandConfigRepo extends CrudRepository<AppBrandConfig, Long> {
	Optional<AppBrandConfig> findFirstByBrandAndAppIdAndFunctionalityAndIsEnabledTrue(Brand brand, AppId appId, String function);
}
