package com.matkabal.loadbalance.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.matkabal.loadbalance.entities.Job;
import com.matkabal.loadbalance.util.Utilities;

public class LoadBalanceService<T> {

	private Map<Integer, Job<T>> listJobs = new HashMap();
	private Map<String, Integer> mapNumberFromName = new HashMap<String, Integer>();
	private Set<Integer> runnedJobs = new LinkedHashSet<>();
	private List<Integer> stackOfJobs = new ArrayList<>();
	private int numberTotalOfJobs = 0;
	private int actualJob = 0;

	public void addJobs(String jobName, Job<T> job) {
		job.setNameJob(jobName);
		numberTotalOfJobs++;
		mapNumberFromName.put(jobName, numberTotalOfJobs);
		listJobs.put(numberTotalOfJobs, job);
	}

	public T start() {
		analising();
		setRunnedJob();
		return listJobs.get(actualJob).process();
	}

	private void analising() {
		if (stackOfJobs.isEmpty()) {
			fillStackJobs();
		}
		List<Job<T>> listWithPreferences = listJobs.values().stream().filter(job -> job.getPreference() != 0)
				.sorted(Comparator.comparingInt(Job::getPreference)).collect(Collectors.toList());

		if (!listWithPreferences.isEmpty()) {
			Collections.reverse(listWithPreferences);
			decidingPreference(listWithPreferences);

		} else {
			setActualJobForStack();
			verifyExistsActualJobInRunnedJob();
		}

	}

	private void decidingPreference(List<Job<T>> listWithPreferences) {

		if (listWithPreferences.size() > 1) {
			int count = 0;
			int maxNumber = listWithPreferences.get(0).getPreference();
			listWithPreferences = listWithPreferences.stream().filter(job -> job.getPreference() == maxNumber)
					.collect(Collectors.toList());
			List<Integer> runnedJobsList = runnedJobs.stream().collect(Collectors.toList());
			Collections.reverse(runnedJobsList);

			while (listWithPreferences.size() > 1) {
				if (runnedJobsList.isEmpty()) {
					break;
				}
				int preference = runnedJobsList.get(count);
				String jobName = listJobs.get(preference).getNameJob();
				listWithPreferences = listWithPreferences.stream().filter(job -> !job.getNameJob().equals(jobName))
						.collect(Collectors.toList());
				if (runnedJobsList.size() - 1 > count) {
					count++;
				} else {
					break;
				}
			}
		}

		String nameJob = listWithPreferences.stream().map(job -> job.getNameJob()).findFirst()
				.orElse(Utilities.EMPTY_STRING);
		actualJob = mapNumberFromName.get(nameJob);
		verifyExistsActualJobInRunnedJob();
		lowerPreference();
		removeJobFromStack();
	}

	private void verifyExistsActualJobInRunnedJob() {
		List<Integer> runnedJobsList = runnedJobs.stream().collect(Collectors.toList());
		runnedJobs.clear();
		for (Integer job : runnedJobsList) {
			if (job != actualJob) {
				runnedJobs.add(job);
			}
		}

	}

	private void lowerPreference() {
		int preference = listJobs.get(actualJob).getPreference();
		listJobs.get(actualJob).setPreference(preference - 1);

	}

	private void removeJobFromStack() {
		stackOfJobs = stackOfJobs.stream().filter(job -> job != actualJob).collect(Collectors.toList());
	}

	private void setActualJobForStack() {
		actualJob = stackOfJobs.remove(0);
	}

	private void fillStackJobs() {

		for (Integer job : runnedJobs) {
			stackOfJobs.add(job);
		}

		List<Integer> jobsRemains = listJobs.keySet().stream().filter(x -> !runnedJobs.contains(x))
				.collect(Collectors.toList());

		for (Integer job : jobsRemains) {
			stackOfJobs.add(job);
		}
		analisingIfExistsPreference();
		
	}

	private void analisingIfExistsPreference() {
		List<Job<T>> listWithPreferences = listJobs.values().stream().filter(job -> job.getPreference() != 0)
				.sorted(Comparator.comparingInt(Job::getPreference)).collect(Collectors.toList());
		if(listWithPreferences.isEmpty()) {
			runnedJobs.clear();
		}
	}

	private void setRunnedJob() {
		runnedJobs.add(actualJob);
	}

}
