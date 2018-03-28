package ro.ubb.diary.aspects;

import ro.ubb.diary.service.impl.CustomerServiceImpl;
import ro.ubb.diary.service.impl.NoteServiceImpl;
import ro.ubb.diary.console.Console;
import ro.ubb.diary.dao.impl.CustomerDaoImpl;
import ro.ubb.diary.dao.impl.NoteDaoImpl;

import java.util.logging.Level;
import java.util.logging.Logger;

public aspect LoggingAspect {


    private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    pointcut logPoint():
            execution(public * CustomerServiceImpl.*(..)) ||
            execution(public * NoteServiceImpl.*(..)) ||
            execution(public * Console.*(..)) ||
            execution(public * CustomerDaoImpl.*(..)) ||
            execution(public * NoteDaoImpl.*(..))
            ;

    before(): logPoint() {
        logger.log(Level.INFO, "Entering {0}", thisJoinPoint.getSignature().toShortString());
//        logger.info("Entering " + thisJoinPoint.getSignature().toShortString());
    }

    after(): logPoint() {
        logger.log(Level.INFO, "Exiting {0}", thisJoinPoint.getSignature().toShortString());
//        logger.info("Exiting " + thisJoinPoint.getSignature().toShortString());
    }
}
