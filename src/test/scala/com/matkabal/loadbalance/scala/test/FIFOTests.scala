package com.matkabal.loadbalance.scala.test

import com.matkabal.loadbalance.scala.service.FIFOLoadBalanceService
import com.matkabal.loadbalance.test.entities.JobImplTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FIFOTests {

  @Test
  def testSimpleIfFIFO(): Unit = {
    val loadBalance = new FIFOLoadBalanceService[String]

    val job1 = new JobImplTest()
    val job2 = new JobImplTest()

    loadBalance.addJobs("p1", job1)
    loadBalance.addJobs("p2", job2)

    assertEquals(loadBalance.start(), "p1")
    assertEquals(loadBalance.start(), "p2")
  }

  @Test
  def testSimpleIfFIFOWithAdditionJob(): Unit = {
    val loadBalance = new FIFOLoadBalanceService[String]

    val job1 = new JobImplTest()
    val job2 = new JobImplTest()
    val job3 = new JobImplTest()


    loadBalance.addJobs("p1", job1)
    loadBalance.addJobs("p2", job2)

    assertEquals(loadBalance.start(), "p1")
    assertEquals(loadBalance.start(), "p2")

    loadBalance.addJobs("p2", job2)
    loadBalance.addJobs("p3", job3)

    assertEquals(loadBalance.start(), "p2")
    assertEquals(loadBalance.start(), "p3")

  }
}
