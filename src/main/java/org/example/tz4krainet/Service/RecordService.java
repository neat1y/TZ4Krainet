package org.example.tz4krainet.Service;

import lombok.AllArgsConstructor;
import org.example.tz4krainet.DTO.ChangeRecordDTO;
import org.example.tz4krainet.DTO.RecordDTO;
import org.example.tz4krainet.Exception.NotAllComponentToSaveException;
import org.example.tz4krainet.Exception.ProjectNotFoundException;
import org.example.tz4krainet.Exception.UserNotFoundException;
import org.example.tz4krainet.Models.Project;
import org.example.tz4krainet.Models.TimeRecord;
import org.example.tz4krainet.Models.TimeRecordId;
import org.example.tz4krainet.Models.Users;
import org.example.tz4krainet.Repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final UsersService usersService;
    private final ProjectService projectService;
    public void save_record_with(RecordDTO recordDTO){
        Optional<Users> users=usersService.findById(recordDTO.getUser_id());
        Optional<Project> project=projectService.findById(recordDTO.getProject_id());
        if(users.isEmpty()){
            throw new UserNotFoundException(recordDTO.getUser_id());
        }
        if(project.isEmpty()){
            throw new ProjectNotFoundException(recordDTO.getProject_id());
        }

        Users users1=users.get();
        Project project1=project.get();

        TimeRecord timeRecord=new TimeRecord(project1,users1);
        timeRecord.setDate_at_which(new Date());
        recordRepository.save(timeRecord);
    }

    public List<TimeRecord> findAll() {
        return recordRepository.findAll();
    }

    public void changeRecord(ChangeRecordDTO changeRecordDTO, Integer id) {
        if(changeRecordDTO.getProjectId()== null || changeRecordDTO.getTask() == null || changeRecordDTO.getTime_in_hour() ==null){
            throw new NotAllComponentToSaveException("not all component");
        }
        Project project= projectService.findById(changeRecordDTO.getProjectId()).orElseThrow(()->new ProjectNotFoundException(
                changeRecordDTO.getProjectId()
        ));
        TimeRecordId timeId=new TimeRecordId(id,project.getProjectId());
        List<TimeRecord> record=recordRepository.findAllById(timeId);
        TimeRecord timeRecord= record.get(0);
        timeRecord.setTask(changeRecordDTO.getTask());
        timeRecord.setTime_in_hour(changeRecordDTO.getTime_in_hour());
        if(changeRecordDTO.getNotes() !=null){
            timeRecord.setNotes(changeRecordDTO.getNotes());
        }
        recordRepository.save(timeRecord);
    }

    public void delete(TimeRecordId timeRecordId) {
        recordRepository.deleteById(timeRecordId);
    }

    public List<TimeRecord> findByUserId(Integer id) {
       return recordRepository.findByUser(usersService.findById(id).get());
    }
}
