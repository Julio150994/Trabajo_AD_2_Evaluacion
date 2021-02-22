package com.segprivado.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.segprivado.entity.Purchase;
import com.segprivado.model.PurchaseModel;
import com.segprivado.repository.PurchasesRepository;
import com.segprivado.service.PurchaseService;


@Service("purchaseImpl")
public class PurchaseImpl implements PurchaseService {
	
	@Autowired
	@Qualifier("purchasesRepository")
	private PurchasesRepository purchaseRepository;
	
	@Autowired
	private DozerBeanMapper dozerApp;
	
	
	@Override
	public List<PurchaseModel> listAllPurchases() {
		return purchaseRepository.findAll().stream().map(buy->converDozerPurchase(buy)).collect(Collectors.toList());
	}

	@Override
	public PurchaseModel findPurchase(int id) {
		return converDozerPurchase(purchaseRepository.findById(id).orElse(null));
	}

	@Override
	public Purchase converDozerPurchase(PurchaseModel purchaseModel) {
		return dozerApp.map(purchaseModel, Purchase.class);
	}

	@Override
	public PurchaseModel converDozerPurchase(Purchase purchase) {
		return dozerApp.map(purchase, PurchaseModel.class);
	}
}
