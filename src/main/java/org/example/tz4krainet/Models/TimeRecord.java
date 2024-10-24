package org.example.tz4krainet.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "record")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeRecord {
    @EmbeddedId
    private TimeRecordId id;

    @Column(name = "notes")
    private String notes;

    @Column(name = "task")
    private String task;

    @Column(name ="time_in_hour")
    private Integer time_in_hour;

    @Column(name = "date_at_which")
    private Date date_at_which;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name="user_id",referencedColumnName = "users_id")
    private Users user;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name="project_id",referencedColumnName = "project_id")
    private Project project;

    public TimeRecord(Project project, Users user) {
        this.project = project;
        this.user = user;
        this.id=new TimeRecordId(project.getProjectId(),user.getUser_id());
    }
}
