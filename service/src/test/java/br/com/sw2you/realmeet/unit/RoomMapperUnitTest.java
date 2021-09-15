package br.com.sw2you.realmeet.unit;

import static br.com.sw2you.realmeet.utils.TestConstants.DEFAULT_ROOM_ID;

import br.com.sw2you.realmeet.core.BaseUnitTest;
import br.com.sw2you.realmeet.mapper.RoomMapper;
import br.com.sw2you.realmeet.utils.MapperUtils;
import br.com.sw2you.realmeet.utils.TestDataCreator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomMapperUnitTest extends BaseUnitTest {

    private RoomMapper victim;

    @BeforeEach
    void setupEach(){
        victim = MapperUtils.roomMapper();
    }

    @Test
    void testFromEntityToDto() {
        var room = TestDataCreator.newRoomBuilder().withId(DEFAULT_ROOM_ID).build();
        var dto = victim.fromEntityToDto(room);

        Assertions.assertEquals(room.getId(), dto.getId());
        Assertions.assertEquals(room.getName(), dto.getName());
        Assertions.assertEquals(room.getSeats(), dto.getSeats());
    }
}
