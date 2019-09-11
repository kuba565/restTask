package pl.kuba565.resttask.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Aspect
//@Component
public class LogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    private static final String SERVICE_POINTCUT = "execution(* pl.kuba565.resttask.*.*.*(..))";

    @Around(SERVICE_POINTCUT)
    public Object logAdvice(ProceedingJoinPoint jp) throws Throwable {

        LOG.info("[METHOD] --------> {}", jp.getSignature());

        Object[] signatureArgs = jp.getArgs();

        if (signatureArgs.length > 0) {
            logArgs(signatureArgs);
        }

        Instant startTime = Instant.now();
        Object obj = jp.proceed();
        Instant endTime = Instant.now();

        LOG.info("[METRICS] --------------------> {}, time: {} milliseconds", jp.getSignature(), getMillisecondsBetweenInstants(startTime, endTime));

        return obj;
    }

    private long getMillisecondsBetweenInstants(Instant startTime, Instant endTime) {
        return TimeUnit.MILLISECONDS.convert
                (Duration.between(startTime, endTime)
                                .getNano(),
                        TimeUnit.NANOSECONDS);
    }

    private void logArgs(Object[] signatureArgs) {
        for (Object signatureArg : signatureArgs) {
            LOG.info("[ARGS] --------> {}: {}",
                    signatureArg.getClass().getSimpleName(), signatureArg);
        }
    }
}
