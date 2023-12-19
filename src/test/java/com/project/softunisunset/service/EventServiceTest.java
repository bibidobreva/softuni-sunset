package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.CreateEventDTO;
import com.project.softunisunset.models.entity.Event;
import com.project.softunisunset.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {


    @Mock
    private EventRepository mockEventRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() {
        mockEventRepository = mock(EventRepository.class);
        mockModelMapper = mock(ModelMapper.class);
        eventService = new EventService(mockEventRepository, mockModelMapper);
    }

    @Test
    void createEventShouldSaveEventAndReturnTrue() {

        CreateEventDTO createEventDTO = new CreateEventDTO();
        Event mappedEvent = new Event();

        when(mockModelMapper.map(createEventDTO, Event.class)).thenReturn(mappedEvent);

        boolean result = eventService.createEvent(createEventDTO);

        assertTrue(result);


        verify(mockEventRepository, times(1)).save(any(Event.class));
    }
}
