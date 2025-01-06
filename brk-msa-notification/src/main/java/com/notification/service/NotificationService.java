package com.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.notification.util.Event;

public interface NotificationService {

    void consumer(String event) throws JsonProcessingException;
}
