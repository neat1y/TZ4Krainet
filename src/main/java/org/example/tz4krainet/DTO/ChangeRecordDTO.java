package org.example.tz4krainet.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeRecordDTO {
    private Integer projectId;
    private String notes;
    private String task;
    private Integer time_in_hour;
}
