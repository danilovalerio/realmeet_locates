package br.com.sw2you.realmeet.service;

import br.com.sw2you.realmeet.domain.entity.Room;
import br.com.sw2you.realmeet.domain.repository.RoomRepository;
import br.com.sw2you.realmeet.exception.RoomNotFoundException;
import java.util.Objects;

public class RoomService {
    private final RoomRepository roomRepository; //Definir como final para evitar reatribuição por algum motivo

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room findById(Long id) {
        Objects.requireNonNull(id);
        /**
         * Caso exista sala retorna, caso não retorna uma Exception
         */
        return roomRepository.findById(id).orElseThrow(RoomNotFoundException::new);
    }
}
