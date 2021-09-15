package br.com.sw2you.realmeet.unit;

import static br.com.sw2you.realmeet.utils.MapperUtils.roomMapper;
import static br.com.sw2you.realmeet.utils.TestConstants.DEFAULT_ROOM_ID;

import br.com.sw2you.realmeet.domain.repository.RoomRepository;
import br.com.sw2you.realmeet.service.RoomService;
import br.com.sw2you.realmeet.utils.TestDataCreator;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Aqui adiciona suporte do Junit ao Mockito
 */
@ExtendWith(MockitoExtension.class)
class RoomServiceUnitTest {

    private RoomService victim;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    void setupEach(){
        victim = new RoomService(roomRepository, roomMapper());
    }

    @Test
    void testFromEntityToDto() {
        var room = TestDataCreator.newRoomBuilder().withId(DEFAULT_ROOM_ID).build();
        Mockito.when(roomRepository.findById(DEFAULT_ROOM_ID)).thenReturn(Optional.of(room));

        var dto = victim.getRoom(DEFAULT_ROOM_ID);

        Assertions.assertEquals(room.getId(), dto.getId());
        Assertions.assertEquals(room.getName(), dto.getName());
        Assertions.assertEquals(room.getSeats(), dto.getSeats());
    }
}
