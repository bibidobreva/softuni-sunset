package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.CreateEventDTO;
import com.project.softunisunset.models.entity.Event;
import com.project.softunisunset.repositories.EventRepository;
import org.modelmapper.ModelMapper;

public class EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public EventService(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    public boolean createEvent(CreateEventDTO createEventDTO){
        this.eventRepository.save(this.modelMapper.map(createEventDTO, Event.class));
        return true;
    }
}
