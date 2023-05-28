package com.matkabal.loadbalance.application;

import com.matkabal.loadbalance.entities.Job;
import com.matkabal.loadbalance.service.LoadBalanceService;

public class LoadBalance {

	public static void main(String[] args) {

		LoadBalanceService<String> loadBalance = new LoadBalanceService<String>();

		Job<String> job1 = new JobImplTest();
		Job<String> job2 = new JobImplTest();
		Job<String> job3 = new JobImplTest();
		Job<String> job4 = new JobImplTest();

		job1.setNameJob("Mateus");
		job2.setNameJob("Sonia");
		job3.setNameJob("Clara");
		job4.setNameJob("Everton");

	    job3.setPreference(2);
		job2.setPreference(3);
		job1.setPreference(1);
		job4.setPreference(5);
		
		loadBalance.addJobs("Mateus", job1);
		loadBalance.addJobs("Sonia", job2);
		loadBalance.addJobs("Clara", job3);
		loadBalance.addJobs("Everton", job4);

		for (int i = 0; i < 15; i++) {
			System.out.println(loadBalance.start());
		}

	}

}
