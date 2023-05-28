package com.matkabal.loadbalance.scala.service

import com.matkabal.loadbalance.LoadBalance
import com.matkabal.loadbalance.entities.Job

import java.util
import scala.collection.mutable

class FIFOLoadBalanceService[T] extends LoadBalance[T]{

  private val listJobs:mutable.Queue[Job[T]] = mutable.Queue()
  private val listJobsRunned:mutable.LinkedHashSet[Job[T]] = mutable.LinkedHashSet()
  val actualJob = 0

  override def addJobs(name: String, partialJob: Job[T]): Unit = {
    partialJob.setNameJob(name)
    listJobs.enqueue(partialJob)
  }

  override def start(): T = {
    verifyIfQueueIsEmpty()
    val job = listJobs.dequeue()
    listJobsRunned.addOne(job)
    job.process()
  }

  @deprecated
  override def addPreferenceWithName(name: String, preference: Int): Unit = {
  }

  private def verifyIfQueueIsEmpty(): Unit = {
    if(listJobs.isEmpty){
      listJobsRunned.map(job => {
        listJobs.enqueue(job)
      })
    }
  }
}
