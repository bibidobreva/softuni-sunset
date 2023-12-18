package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.CreateEventDTO;
import com.project.softunisunset.models.entity.Event;
import com.project.softunisunset.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {


        private EventRepository mockEventRepository;
        private ModelMapper mockModelMapper;
        private EventService eventService;

        @BeforeEach
        void setUp() {
            mockEventRepository = mock(EventRepository.class);
            mockModelMapper = mock(ModelMapper.class);
            eventService = new EventService(mockEventRepository, mockModelMapper);
        }

        @Test
        void createEventShouldSaveEventAndReturnTrue() {
            // Arrange
            CreateEventDTO createEventDTO = new CreateEventDTO();
            Event mappedEvent = new Event();

            when(mockModelMapper.map(createEventDTO, Event.class)).thenReturn(mappedEvent);

            // Act
            boolean result = eventService.createEvent(createEventDTO);

            // Assert
            assertTrue(result);
            verify(mockEventRepository).save(any(Event.class));
        }
}
