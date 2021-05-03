package com.tekdays

import grails.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled

@Transactional
class SchedulerService {

    public static final Logger LOGGER = LoggerFactory.getLogger(SchedulerService.class)

    def scheduleRun() {
        LOGGER.info('Run time = {} ',new Date())
    }
}
