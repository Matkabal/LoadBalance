package com.matkabal.loadbalance;

import com.matkabal.loadbalance.entities.Job;

public interface LoadBalance<T> {

    void addJobs(String name, Job<T> partialJob);
    T start();
    void addPreferenceWithName(String name, int preference);

}
