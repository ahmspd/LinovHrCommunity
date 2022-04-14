package com.lawencon.linovhrcommunity.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawencon.linovhrcommunity.dto.scheduler.SchedulerUserMemberData;
import com.lawencon.linovhrcommunity.service.UserMemberService;

@Component
public class UserMemberJob implements Job {

	private UserMemberService userMemberService;
	
	@Autowired
	public void setUserMemberService(UserMemberService userMemberService) {
		this.userMemberService = userMemberService;
	}


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		SchedulerUserMemberData data = (SchedulerUserMemberData) jobDataMap.get(UserMemberJob.class.getSimpleName());
		
		try {
			userMemberService.expiredMember(data.getIdUser(), data.getIdUpdatedBy());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}








