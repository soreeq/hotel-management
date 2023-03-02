package pl.hotelmanagement.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name= "rooms")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Room {
    @Id
    private int room_id;
    @NonNull
    private String type;
    @NonNull
    private int size;
    private String occupied;
    private int rate;

    public Room(int room_id, String type, int size, int rate)  {
        this.type = type;
        this.size = size;
        this.rate = rate;
        this.room_id = room_id;
    }
}


