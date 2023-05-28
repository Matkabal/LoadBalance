package com.matkabal.loadbalance.scala.test

import com.matkabal.loadbalance.service.RoundRobinLoadBalanceService
import com.matkabal.loadbalance.test.entities.JobImplTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RoundRobinTestsWithScala {

  @Test
  def testSimpleRoundRobin(): Unit = {
    val loadBalance = new RoundRobinLoadBalanceService[String]

    val job1 = new JobImplTest
    val job2 = new JobImplTest

    loadBalance.addJobs("p1", job1)
    loadBalance.addJobs("p2", job2)

    assertEquals(loadBalance.start(), "p1")
    assertEquals(loadBalance.start(), "p2")

  }

  @Test
  def testRoundRobinWithPreference(): Unit = {
    val loadBalance = new RoundRobinLoadBalanceService[String]

    val job1 = new JobImplTest
    val job2 = new JobImplTest
    val job3 = new JobImplTest

    job3.setPreference(2)

    loadBalance.addJobs("p1", job1)
    loadBalance.addJobs("p2", job2)
    loadBalance.addJobs("p3", job3)

    assertEquals(loadBalance.start(), "p3")
    assertEquals(loadBalance.start(), "p3")
    assertEquals(loadBalance.start(), "p1")
    assertEquals(loadBalance.start(), "p2")
    assertEquals(loadBalance.start(), "p3")

  }

  @Test
  def testRoundRobinWithAdditions(): Unit = {
    val loadBalance = new RoundRobinLoadBalanceService[String]

    val job1 = new JobImplTest
    val job2 = new JobImplTest
    val job3 = new JobImplTest
    val job4 = new JobImplTest

    job3.setPreference(2)

    loadBalance.addJobs("p1", job1)
    loadBalance.addJobs("p2", job2)
    loadBalance.addJobs("p3", job3)
    loadBalance.addJobs("p4", job4)

    assertEquals(loadBalance.start(), "p3")
    assertEquals(loadBalance.start(), "p3")

    loadBalance.addPreferenceWithName("p4", 2)

    assertEquals(loadBalance.start(), "p4")
    assertEquals(loadBalance.start(), "p4")
    assertEquals(loadBalance.start(), "p1")
    assertEquals(loadBalance.start(), "p2")
    assertEquals(loadBalance.start(), "p3")
    assertEquals(loadBalance.start(), "p4")

  }
}
