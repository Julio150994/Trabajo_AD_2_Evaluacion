package com.segprivado.service;

import java.util.List;
import com.segprivado.entity.Purchase;
import com.segprivado.model.PurchaseModel;

public interface PurchaseService {
	/*--------Métodos para la factura de la compra-----------*/
	public abstract List<PurchaseModel> listAllPurchases();
	public abstract PurchaseModel findPurchase(int id);
	public abstract Purchase converDozerPurchase(PurchaseModel purchaseModel);
	public abstract PurchaseModel converDozerPurchase(Purchase purchase);
}
