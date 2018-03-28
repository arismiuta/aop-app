package ro.ubb.diary.aspects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.ubb.diary.service.impl.CustomerServiceImpl;
import ro.ubb.diary.service.impl.NoteServiceImpl;
import ro.ubb.diary.console.Console;
import ro.ubb.diary.dao.impl.CustomerDaoImpl;
import ro.ubb.diary.dao.impl.NoteDaoImpl;

public aspect LoggingAspect {


    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    pointcut logPoint():
            execution(public * CustomerServiceImpl.*(..)) ||
            execution(public * NoteServiceImpl.*(..)) ||
            execution(public * Console.*(..)) ||
            execution(public * CustomerDaoImpl.*(..)) ||
            execution(public * NoteDaoImpl.*(..))
            ;

    before(): logPoint() {
        logger.info("Entering " + thisJoinPoint.getSignature().toShortString());
    }

    after(): logPoint() {
        logger.info("Exiting " + thisJoinPoint.getSignature().toShortString());
    }
}
