package com.george.banking.db;

import java.util.Collection;

import com.george.banking.model.Audit;

public interface AuditDAO {
	public Collection<Audit> getAudits();
}
