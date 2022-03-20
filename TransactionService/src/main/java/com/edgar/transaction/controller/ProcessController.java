package com.edgar.transaction.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.edgar.transaction.model.AppBrandConfig;
import com.edgar.transaction.model.AppId;
import com.edgar.transaction.model.Brand;
import com.edgar.transaction.model.Component;
import com.edgar.transaction.model.RequestData;
import com.edgar.transaction.model.Resolution;
import com.edgar.transaction.model.Status;
import com.edgar.transaction.model.Transaction;
import com.edgar.transaction.model.TransactionProcess;
import com.edgar.transaction.repository.AppBrandConfigRepo;
import com.edgar.transaction.repository.AppIdRepo;
import com.edgar.transaction.repository.BrandRepo;
import com.edgar.transaction.repository.ComponentRepo;
import com.edgar.transaction.repository.ResolutionRepo;
import com.edgar.transaction.repository.StatusRepo;
import com.edgar.transaction.repository.TransactionProcessRepo;
import com.edgar.transaction.repository.TransactionRepo;

@RestController
public class ProcessController {
	private ComponentRepo componentRepo;
	private StatusRepo statusRepo;
	private TransactionProcessRepo transactionProcessRepo;
	private AppIdRepo appIdRepo;
	private BrandRepo brandRepo;
	private AppBrandConfigRepo appBrandConfigRepo;
	private TransactionRepo transactionRepo;
	private ResolutionRepo resolutionRepo;
	
	public ProcessController(ComponentRepo componentRepo, StatusRepo statusRepo,
			TransactionProcessRepo transactionProcessRepo, AppIdRepo appIdRepo, BrandRepo brandRepo,
			AppBrandConfigRepo appBrandConfigRepo, TransactionRepo transactionRepo, ResolutionRepo resolutionRepo) {
		this.componentRepo = componentRepo;
		this.statusRepo = statusRepo;
		this.transactionProcessRepo = transactionProcessRepo;
		this.appIdRepo = appIdRepo;
		this.brandRepo = brandRepo;
		this.appBrandConfigRepo = appBrandConfigRepo;
		this.transactionRepo = transactionRepo;
		this.resolutionRepo = resolutionRepo;
	}
	
	@PostMapping("transaction/process")
	public String processRequest(@Valid @RequestBody RequestData requestData, Errors errors) {
		if (errors.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		Optional<Component> component = componentRepo.findByName(requestData.getComponent());
		Optional<Status> status = statusRepo.findByName(requestData.getStatus());
		
		if (component.isPresent() && status.isPresent()) {
			TransactionProcess transactionProcess = new TransactionProcess();
			
			transactionProcess.setComponent(component.get());
			transactionProcess.setStatus(status.get());
			
			String functionId = null;
			String requestFeatureId = requestData.getFeatureId();
			if (requestFeatureId != null) {
				if (requestFeatureId.equals("IDScan")) {
					functionId = "1";
				} else if (requestFeatureId.equals("FV")) {
					functionId = "2";
				} else if (requestFeatureId.equals("LD")) {
					functionId = "3";
				} else if (requestFeatureId.equals("LDFV")) {
					functionId = "4";
				} else if (requestFeatureId.equals("a4Scan")) {
					functionId = "5";
				}
			}
			
			if (functionId != null) {
				Optional<AppId> appId = appIdRepo.findByName(requestData.getAppId());
				Optional<Brand> brand = brandRepo.findFirstByName(requestData.getBrand());

				if (appId.isPresent() && brand.isPresent()) {
					Optional<AppBrandConfig> appBrandConfig = appBrandConfigRepo.findFirstByBrandAndAppIdAndFunctionalityAndIsEnabledTrue(brand.get(), appId.get(), functionId);
					if (appBrandConfig.isPresent()) {
						transactionProcess.setAppBrandConfig(appBrandConfig.get());	
					} else {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
					}
				}
			} else if (status.get().getName().equals("completed") && requestData.getProcessId() != null) {
				Optional<Long> transactionProcessId = transactionProcessRepo.findFirstIdByProcessId(requestData.getProcessId());
				if (transactionProcessId.isEmpty()) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
				}

			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
			
			if (requestData.getCode() != null && !requestData.getCode().isBlank()) {
				Optional<Transaction> transaction = transactionRepo.findFirstByCode(requestData.getCode()); 
				if (transaction.isPresent()) {
					transactionProcess.setTransaction(transaction.get());
				}
			}
			
			if (requestData.getResolution() != null && !requestData.getResolution().isBlank()) {
				Optional<Resolution> resolution = resolutionRepo.findByName(requestData.getResolution());
				if (resolution.isPresent()) {
					transactionProcess.setResolution(resolution.get());
				}
			}
			
			if (requestData.getProcessId() != null && !requestData.getProcessId().isBlank()) {
				transactionProcess.setProcessId(requestData.getProcessId());
			}
			
			if (requestData.getUserId() != null && !requestData.getUserId().isBlank()) {
				transactionProcess.setUserEmail(requestData.getUserId());
			}
			
			if (requestData.getFstid() != null && !requestData.getFstid().isBlank()) {
				transactionProcess.setFstid(requestData.getFstid());
			}
			
			if (requestData.getUuid() != null && !requestData.getUuid().isBlank()) {
				transactionProcess.setUuid(requestData.getUuid());
			}
			
			if (requestData.getModel() != null && !requestData.getModel().isBlank()) {
				transactionProcess.setModel(requestData.getModel());
			}
			
			if (requestData.getOs() != null && !requestData.getOs().isBlank()) {
				transactionProcess.setOs(requestData.getOs());
			}
			
			transactionProcessRepo.save(transactionProcess);
			
			
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		
		return "OK";
	}
}
