package br.com.sw2you.realmeet.domain.entity;

import static java.util.Objects.isNull;

import java.util.Objects;
import javax.persistence.*;

/**
 * Classe que representa a sala
 * Em entidade é bom utilizar classes wrappers que podem armazenar valor nulo
 * Classes imutáveis para evitar a concorrencia e gerar erros na alteração feita pelos clientes
 * - criando somente gets
 * - construtor privado
 */

@Entity
@Table(name = "room")
public class Room {
    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "seats", nullable = false)
    private Integer seats; //número de assentos

    @Column(name = "active", nullable = false)
    private Boolean active;

    public Room() {} //a jpa precisa de um construtor publico

    private Room(Long id, String name, Integer seats, Boolean active) {
        this.id = id;
        this.name = name;
        this.seats = seats;
        this.active = active;
    }

    @PrePersist
    public void prePersist() {
        if(isNull(active)) {
            active = true;
        }

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getSeats() {
        return seats;
    }

    public Boolean getActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return (
                Objects.equals(id, room.id) &&
                        Objects.equals(name, room.name) &&
                        Objects.equals(seats, room.seats) &&
                        Objects.equals(active, room.active)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, seats, active);
    }

    @Override
    public String toString() {
        return (
                "Room{" +
                        "id=" +
                        id +
                        ", name='" +
                        name + '\'' +
                        ", seats=" +
                        seats +
                        ", active=" +
                        active +
                        '}'
        );
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String name;
        private Integer seats; //número de assentos
        private Boolean active;

        private Builder() {}

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSeats(Integer seats) {
            this.seats = seats;
            return this;
        }

        public Builder withActive(Boolean active) {
            this.active = active;
            return this;
        }

        public Room build() {
            return new Room(id, name, seats, active);
        }
    }
}
