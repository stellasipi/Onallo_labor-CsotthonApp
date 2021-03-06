package hu.bme.vik.tbs.szakdolgozat.CsotthonApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomCleaningItemPairingMapDTO {
    private Integer id; //roomId
    private String roomName;
    private List<String> cleaningItems;
}
