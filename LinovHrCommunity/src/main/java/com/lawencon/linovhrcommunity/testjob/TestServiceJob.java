package com.lawencon.linovhrcommunity.testjob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dto.scheduler.SchedulerUserMemberData;
import com.lawencon.linovhrcommunity.job.UserMemberJob;
import com.lawencon.linovhrcommunity.service.SchedulerService;

@Service
public class TestServiceJob {

	private SchedulerService schedulerService;

	@Autowired
	public void setSchedulerService(SchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}
	
	public void runJob() {
		SchedulerUserMemberData data = new SchedulerUserMemberData();
		
		data.setIdUser("Yuhuu");
		data.setRunForever(false);
		data.setRepeatIntervalMs(2000L);
		data.setInitialOffsetMs(1000L);
		data.setTotalFireCount(5);
		
		schedulerService.schedule(data, UserMemberJob.class);
	}
	
}
