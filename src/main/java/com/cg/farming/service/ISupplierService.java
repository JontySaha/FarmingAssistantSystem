package com.cg.farming.service;

import com.cg.farming.entity.Supplier;
import com.cg.farming.exception.SupplierNotFoundException;

public interface ISupplierService {
	Supplier deleteSupplier(int id) throws SupplierNotFoundException;
	Supplier updateSupplier(int id,Supplier sup) throws SupplierNotFoundException;
}
