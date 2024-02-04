package ru.fsv67.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fsv67.aspects.RecoverException;
import ru.fsv67.aspects.Time;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Time
public class ServiceBeanTwo {

    public void getStringBean(int timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void getNameClassBean(int timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @RecoverException
    public void getMessageException() {
        throw new NoSuchElementException("Exception Other");
    }
}
