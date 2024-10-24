package org.example.tz4krainet.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeProjectDTO {
    private String projectName;
    private String description;
    private String OldProjectName;
}
