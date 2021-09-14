package br.com.sw2you.realmeet.mapper;

import br.com.sw2you.realmeet.api.model.RoomDTO;
import br.com.sw2you.realmeet.domain.entity.Room;
import org.mapstruct.Mapper;

/**
 * Para utilizar a API MapStruct
 * Será utilizada pelo plugin
 */

@Mapper(componentModel = "spring")
public abstract class RoomMapper {

    public abstract RoomDTO fromEntityToDto(Room room);
}
