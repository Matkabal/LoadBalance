package com.matkabal.loadbalance.entities;

import java.io.Serializable;
import java.util.Objects;

public abstract class Job<T> implements Serializable{
	private static final long serialVersionUID = -7422184843467068626L;
	
	private String nameJob;
	private int preference = 0;
	
	public String getNameJob() {
		return nameJob;
	}
	public void setNameJob(String nameJob) {
		this.nameJob = nameJob;
	}
	public int getPreference() {
		return preference;
	}
	public void setPreference(int preference) {
		this.preference = preference;
	}
	
	public abstract T process();
	
	
	@Override
	public int hashCode() {
		return Objects.hash(nameJob);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		return Objects.equals(nameJob, other.nameJob);
	}
	@Override
	public String toString() {
		return "Job [nameJob=" + nameJob + ", preference=" + preference + "]";
	}

}
