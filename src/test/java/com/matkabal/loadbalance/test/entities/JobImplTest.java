package com.matkabal.loadbalance.test.entities;

import com.matkabal.loadbalance.entities.Job;

public class JobImplTest extends Job<String> {
	private static final long serialVersionUID = 1L;

	@Override
	public String process() {
		// TODO Auto-generated method stub
		return getNameJob();
	}

}
