package com.lawencon.linovhrcommunity.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.config.SchedulerConfig;
import com.lawencon.linovhrcommunity.dto.scheduler.SchedulerUserMemberData;

@Service
public class SchedulerService {

	private Scheduler scheduler;

	@Autowired
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	public <T extends Job> void schedule(SchedulerUserMemberData data, Class<T> clazz) {
		SchedulerConfig schedulerConfig = new SchedulerConfig();

		JobDetail jobDetail = schedulerConfig.buildjobDetail(data, clazz);
		Trigger trigger = schedulerConfig.buildTrigger(data, clazz);
		
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@PostConstruct
	public void init() {
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	@PreDestroy
	public void predestroy() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
