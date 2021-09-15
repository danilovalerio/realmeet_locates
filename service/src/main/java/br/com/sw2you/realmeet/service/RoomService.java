package br.com.sw2you.realmeet.service;

import br.com.sw2you.realmeet.api.model.RoomDTO;
import br.com.sw2you.realmeet.domain.entity.Room;
import br.com.sw2you.realmeet.domain.repository.RoomRepository;
import br.com.sw2you.realmeet.exception.RoomNotFoundException;
import br.com.sw2you.realmeet.mapper.RoomMapper;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private final RoomRepository roomRepository; //Definir como final para evitar reatribuição por algum motivo
    private final RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    public RoomDTO getRoom(Long id) {
        Objects.requireNonNull(id);
        /*
          Caso exista sala retorna, caso não retorna uma Exception
         */
        Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found:"+id));
        /*
          Forma manual para fazer o mapper
          //return new RoomDTO().name(room.getName()).id(room.getId()).seats(room.getSeats());
         */
        return roomMapper.fromEntityToDto(room); //gera uma RoomMapperImpl
    }
}
