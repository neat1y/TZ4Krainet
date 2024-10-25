package org.example.tz4krainet.Service;

import lombok.AllArgsConstructor;
import org.example.tz4krainet.DTO.ChangeRecordDTO;
import org.example.tz4krainet.DTO.RecordDTO;
import org.example.tz4krainet.Exception.NotAllComponentToSaveException;
import org.example.tz4krainet.Exception.ProjectNotFoundException;
import org.example.tz4krainet.Exception.UserNotFoundException;
import org.example.tz4krainet.Models.Project;
import org.example.tz4krainet.Models.TimeRecord;
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
    // Сохраняет запись
    // Проверяет есть ли project_id и user_id, если нет выкидывает ошибку
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
    //Все записи
    public List<TimeRecord> findAll() {
        return recordRepository.findAll();
    }
    //Изменяет запись для определенного пользователя его определенный проект
    // выкидывает ошибку если не все компоненты
    public void changeRecord(ChangeRecordDTO changeRecordDTO) {
        if(changeRecordDTO.getRecord_id()== null || changeRecordDTO.getTask() == null || changeRecordDTO.getTime_in_hour() ==null){
            throw new NotAllComponentToSaveException("not all component");
        }
        Optional<TimeRecord> record=recordRepository.findById(changeRecordDTO.getRecord_id());
        TimeRecord timeRecord= record.get();
        timeRecord.setTask(changeRecordDTO.getTask());
        timeRecord.setTime_in_hour(changeRecordDTO.getTime_in_hour());
        if(changeRecordDTO.getNotes() !=null){
            timeRecord.setNotes(changeRecordDTO.getNotes());
        }
        recordRepository.save(timeRecord);
    }
    // удаляет запис
    public void delete(Integer timeRecordId) {
        recordRepository.deleteById(timeRecordId);
    }
    // Ишет все проекты по пользователю(все проекты пользователя)
    public List<TimeRecord> findByUserId(Integer id) {
       return recordRepository.findByUser(usersService.findById(id).get());
    }
}
