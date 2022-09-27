package com.cg.farming.service;

import com.cg.farming.entity.Admin;
import com.cg.farming.exception.AdminNotFoundException;

public interface IAdminService {
	Admin deleteAdmin(int id) throws AdminNotFoundException;
	Admin updateAdmin(int id,Admin ad) throws AdminNotFoundException;
}
