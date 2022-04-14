package com.lawencon.linovhrcommunity.config;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.lawencon.linovhrcommunity.dto.scheduler.SchedulerUserMemberData;

public class SchedulerConfig {

	public <T extends Job> JobDetail buildjobDetail(SchedulerUserMemberData data, Class<T> clazz) {
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put(clazz.getSimpleName(), data);
		
		return JobBuilder.newJob(clazz).withIdentity(clazz.getSimpleName()).setJobData(jobDataMap).build();
	}
	
	public <T extends Job> Trigger buildTrigger(SchedulerUserMemberData data, Class<T> clazz) {
		SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(data.getRepeatIntervalMs());
	
		if(data.getRunForever()) {
			builder = builder.repeatForever();
		} else {
			builder = builder.withRepeatCount(data.getTotalFireCount() - 1);
		}
		
		return TriggerBuilder
				.newTrigger()
				.withIdentity(clazz.getSimpleName())
				.withSchedule(builder)
				.startAt(new Date(System.currentTimeMillis() + data.getInitialOffsetMs()))
				.build();
	}
}
