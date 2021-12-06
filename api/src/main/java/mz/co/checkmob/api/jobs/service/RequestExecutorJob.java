package mz.co.checkmob.api.jobs.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class RequestExecutorJob implements SchedulingConfigurer {
    private Executor executor;
    public void execute() {
        System.out.println("Awesome");
    }


//    private void createShipments(ScheduleShipment scheduleShipment){
//        List<Long> allAvailableOrders = orderJpaRepository.findAllByStagesValueAndCustomerId(
//                OrderStageValue.AVAILABLE_FOR_PICKING,scheduleShipment.getCustomerId()).stream().
//                map(Order::getId).collect(Collectors.toList());
//        if(!allAvailableOrders.isEmpty()){
//            shipments.create(new CreateShipmentCommand(scheduleShipment.getAssigneeName(),scheduleShipment.getDriverName(),scheduleShipment.getCarRegistration(),
//                    allAvailableOrders,12L,1L,null,scheduleShipment.getWarehouse(),scheduleShipment.getRoute()));
//
//            nextScheduledDate(scheduleShipment);
//        }
//    }
//
//    private void nextScheduledDate(ScheduleShipment scheduleShipment){
//        if(scheduleShipment.getScheduleShipmentFrequency().equals(ScheduleShipmentFrequency.DAILY)){
//            scheduleShipment.setSchedulingDate(scheduleShipment.getSchedulingDate().plusDays(1));
//        }else   if(scheduleShipment.getScheduleShipmentFrequency().equals(ScheduleShipmentFrequency.WEEKLY)){
//            scheduleShipment.setSchedulingDate(scheduleShipment.getSchedulingDate().plusWeeks(1));
//        } else if (scheduleShipment.getScheduleShipmentFrequency().equals(ScheduleShipmentFrequency.MONTHLY)){
//            scheduleShipment.setSchedulingDate(scheduleShipment.getSchedulingDate().plusMonths(1));
//        }
//        scheduleShipmentRepository.save(scheduleShipment);
//    }
//
//    private void process() {
//        try{
////            Thread.sleep(config.getDelay());
//            List<ScheduleShipment> listOfScheduledShipments = scheduleShipmentRepository.findBySchedulingDateLessThanEqual(LocalDateTime.now());
//            if(!listOfScheduledShipments.isEmpty()){
//                listOfScheduledShipments.forEach(this::createShipments);
//            }
//        }catch (Exception e){
//            LOGGER.error("Error Message " + e);
//        };
//    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(executor);
        scheduledTaskRegistrar.addTriggerTask(
                new Runnable() {
                    @Override
                    public void run() {
                        execute();
                    }
                },
                new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext context) {
                        CronTrigger crontrigger = new CronTrigger("* * * * *");

                        return crontrigger.nextExecutionTime(context);
                    }
                }
        );
    }
}
