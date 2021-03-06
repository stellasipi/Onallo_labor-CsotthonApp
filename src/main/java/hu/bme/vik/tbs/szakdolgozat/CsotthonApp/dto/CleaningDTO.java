package hu.bme.vik.tbs.szakdolgozat.CsotthonApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CleaningDTO {
    private Integer id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="Europe/Budapest")
    private Timestamp time;

    private ScoutGroupDTO scoutGroup;
    private UserDTO user;
}
