package com.matkabal.loadbalance.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.matkabal.loadbalance.entities.Job;
import com.matkabal.loadbalance.service.RoundRobinLoadBalanceService;
import com.matkabal.loadbalance.test.entities.JobImplTest;

public class RoundRobinLoadBalanceTest {
	
	@Test
	public void testeWithSimpleQueue(){
		RoundRobinLoadBalanceService<String> loadBalance = new RoundRobinLoadBalanceService<String>();
		
		Job<String> job1 = new JobImplTest();
		Job<String> job2 = new JobImplTest();
		Job<String> job3 = new JobImplTest();
		
		job1.setNameJob("Mateus");
		job2.setNameJob("Sonia");
		job3.setNameJob("Clara");
				
		loadBalance.addJobs("Mateus", job1);
		loadBalance.addJobs("Sonia", job2);
		loadBalance.addJobs("Clara", job3);
		
		Assertions.assertEquals(loadBalance.start(), "Mateus");
		Assertions.assertEquals(loadBalance.start(), "Sonia");
		Assertions.assertEquals(loadBalance.start(), "Clara");

		
	}
	
	@Test
	public void testeWithQueueAndClaraPreference(){
		RoundRobinLoadBalanceService<String> loadBalance = new RoundRobinLoadBalanceService<String>();
		
		Job<String> job1 = new JobImplTest();
		Job<String> job2 = new JobImplTest();
		Job<String> job3 = new JobImplTest();
		
		job1.setNameJob("Mateus");
		job2.setNameJob("Sonia");
		job3.setNameJob("Clara");
		
		job3.setPreference(1);
				
		loadBalance.addJobs("Mateus", job1);
		loadBalance.addJobs("Sonia", job2);
		loadBalance.addJobs("Clara", job3);
		
		
		Assertions.assertEquals(loadBalance.start(), "Clara");
		Assertions.assertEquals(loadBalance.start(), "Mateus");
		Assertions.assertEquals(loadBalance.start(), "Sonia");

		
	}

	@Test
	public void testeWithQueueAndClaraAndSoniaPreference(){
		RoundRobinLoadBalanceService<String> loadBalance = new RoundRobinLoadBalanceService<String>();
		
		Job<String> job1 = new JobImplTest();
		Job<String> job2 = new JobImplTest();
		Job<String> job3 = new JobImplTest();
		
		job1.setNameJob("Mateus");
		job2.setNameJob("Sonia");
		job3.setNameJob("Clara");
		
		job3.setPreference(2);
		job2.setPreference(1);
				
		loadBalance.addJobs("Mateus", job1);
		loadBalance.addJobs("Sonia", job2);
		loadBalance.addJobs("Clara", job3);
		
		
		Assertions.assertEquals(loadBalance.start(), "Clara");
		Assertions.assertEquals(loadBalance.start(), "Sonia");
		Assertions.assertEquals(loadBalance.start(), "Clara");
		Assertions.assertEquals(loadBalance.start(), "Mateus");
		Assertions.assertEquals(loadBalance.start(), "Sonia");
		Assertions.assertEquals(loadBalance.start(), "Clara");
		Assertions.assertEquals(loadBalance.start(), "Mateus");


		
	}
	
	@Test
	public void testeWithQueueAndClaraAndSoniaAndMateusPreference(){
		RoundRobinLoadBalanceService<String> loadBalance = new RoundRobinLoadBalanceService<String>();
		
		Job<String> job1 = new JobImplTest();
		Job<String> job2 = new JobImplTest();
		Job<String> job3 = new JobImplTest();
		
		job1.setNameJob("Mateus");
		job2.setNameJob("Sonia");
		job3.setNameJob("Clara");
		
		job3.setPreference(2);
		job2.setPreference(3);
		job1.setPreference(1);
				
		loadBalance.addJobs("Mateus", job1);
		loadBalance.addJobs("Sonia", job2);
		loadBalance.addJobs("Clara", job3);
		
		Assertions.assertEquals(loadBalance.start(), "Sonia");
		Assertions.assertEquals(loadBalance.start(), "Clara");
		Assertions.assertEquals(loadBalance.start(), "Sonia");
		Assertions.assertEquals(loadBalance.start(), "Mateus");
		Assertions.assertEquals(loadBalance.start(), "Clara");
		Assertions.assertEquals(loadBalance.start(), "Sonia");
		Assertions.assertEquals(loadBalance.start(), "Mateus");
		Assertions.assertEquals(loadBalance.start(), "Clara");
		Assertions.assertEquals(loadBalance.start(), "Sonia");
		Assertions.assertEquals(loadBalance.start(), "Mateus");


		
	}
	
	@Test
	public void testeWithQueueAndClaraAndSoniaAndMateusAndEvertonPreference(){
		RoundRobinLoadBalanceService<String> loadBalance = new RoundRobinLoadBalanceService<String>();
		
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

		Assertions.assertEquals(loadBalance.start(), "Everton"); 
		Assertions.assertEquals(loadBalance.start(), "Everton");
		Assertions.assertEquals(loadBalance.start(), "Sonia");
		Assertions.assertEquals(loadBalance.start(), "Everton"); 
		Assertions.assertEquals(loadBalance.start(), "Clara"); 
		Assertions.assertEquals(loadBalance.start(), "Sonia");
		Assertions.assertEquals(loadBalance.start(), "Everton"); 
		Assertions.assertEquals(loadBalance.start(), "Mateus");
		Assertions.assertEquals(loadBalance.start(), "Clara");
		Assertions.assertEquals(loadBalance.start(), "Sonia");
		Assertions.assertEquals(loadBalance.start(), "Everton"); 
		Assertions.assertEquals(loadBalance.start(), "Mateus");
		Assertions.assertEquals(loadBalance.start(), "Clara");
		Assertions.assertEquals(loadBalance.start(), "Sonia");
		Assertions.assertEquals(loadBalance.start(), "Everton");



		
	}
	
	@Test
	public void testeWithQueueAndClaraAndSoniaAndMateusAndEvertonAndAnaPreference(){
		RoundRobinLoadBalanceService<String> loadBalance = new RoundRobinLoadBalanceService<String>();
		
		Job<String> job1 = new JobImplTest();
		Job<String> job2 = new JobImplTest();
		Job<String> job3 = new JobImplTest();
		Job<String> job4 = new JobImplTest();
		Job<String> job5 = new JobImplTest();


		
		job1.setNameJob("Mateus");
		job2.setNameJob("Sonia");
		job3.setNameJob("Clara");
		job4.setNameJob("Everton");
		job5.setNameJob("Ana");

		job3.setPreference(2);
		job2.setPreference(3);
		job1.setPreference(1);
		job4.setPreference(5);
		job5.setPreference(7);
		
		loadBalance.addJobs("Mateus", job1);
		loadBalance.addJobs("Sonia", job2);
		loadBalance.addJobs("Clara", job3);
		loadBalance.addJobs("Everton", job4);
		loadBalance.addJobs("Ana", job5);

		Assertions.assertEquals(loadBalance.start(), "Ana"); 
		Assertions.assertEquals(loadBalance.start(), "Ana"); 
		Assertions.assertEquals(loadBalance.start(), "Everton"); 
		Assertions.assertEquals(loadBalance.start(), "Ana"); 
		Assertions.assertEquals(loadBalance.start(), "Everton");
		Assertions.assertEquals(loadBalance.start(), "Ana"); 
		Assertions.assertEquals(loadBalance.start(), "Sonia");
		Assertions.assertEquals(loadBalance.start(), "Everton");
		Assertions.assertEquals(loadBalance.start(), "Ana"); 
		Assertions.assertEquals(loadBalance.start(), "Clara"); 
		Assertions.assertEquals(loadBalance.start(), "Sonia");
		Assertions.assertEquals(loadBalance.start(), "Everton"); 
		Assertions.assertEquals(loadBalance.start(), "Ana"); 
		Assertions.assertEquals(loadBalance.start(), "Mateus");
		Assertions.assertEquals(loadBalance.start(), "Clara");
		Assertions.assertEquals(loadBalance.start(), "Sonia");
		Assertions.assertEquals(loadBalance.start(), "Everton"); 
		Assertions.assertEquals(loadBalance.start(), "Ana"); 
		Assertions.assertEquals(loadBalance.start(), "Mateus");
		Assertions.assertEquals(loadBalance.start(), "Clara");
		Assertions.assertEquals(loadBalance.start(), "Sonia");
		Assertions.assertEquals(loadBalance.start(), "Everton");
		Assertions.assertEquals(loadBalance.start(), "Ana"); 
		
	}
}

