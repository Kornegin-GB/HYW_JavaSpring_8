package ru.fsv67.controllers;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import ru.fsv67.services.ServiceBeanTwo;

@Controller
@AllArgsConstructor
public class BeanTwoController {
    ServiceBeanTwo beanTwo;

    @EventListener(ApplicationReadyEvent.class)
    public void getData() {
        beanTwo.getStringBean(1000);
        beanTwo.getNameClassBean(1000);
        beanTwo.getMessageException();
    }
}
