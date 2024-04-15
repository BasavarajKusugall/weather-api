package com.mysite.core.schedulers;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Designate(ocd=WeatherScheduler.Config.class)
@Component(service=Runnable.class)
public class WeatherScheduler implements Runnable {

    @ObjectClassDefinition(name="A scheduled task",
            description = "Simple demo for cron-job like task with properties")
    public static @interface Config {

        @AttributeDefinition(name = "Cron-job expression")
        String scheduler_expression() default "*/30 * * * * ?";

        @AttributeDefinition(name = "Concurrent task",
                description = "Whether or not to schedule this task concurrently")
        boolean scheduler_concurrent() default false;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void run() {
        logger.debug("WeatherScheduler is now running, myParameter='{}'");
    }

    @Activate
    protected void activate(final Config config) {
        myParameter = config.myParameter();
    }

}
