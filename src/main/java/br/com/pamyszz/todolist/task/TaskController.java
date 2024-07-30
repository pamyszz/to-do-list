package br.com.pamyszz.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.pamyszz.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        UUID idUser = (UUID) request.getAttribute("idUser");
        taskModel.setIdUser(idUser);

        LocalDateTime currentDate = LocalDateTime.now();

        if (taskModel.getStartAt() == null || taskModel.getEndAt() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Data de início e data de término não podem ser nulas.");
        }

        if (currentDate.isAfter(taskModel.getStartAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de início deve ser maior do que a data atual.");
        }

        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de início deve ser menor que a data de término.");
        }

        TaskModel task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tarefa criada com sucesso: " + task.toString());
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskModel>> list(HttpServletRequest request) {
        UUID idUser = (UUID) request.getAttribute("idUser");
        List<TaskModel> tasks = this.taskRepository.findByIdUser(idUser);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request) {
        TaskModel existingTask = this.taskRepository.findById(id).orElse(null);

        if (existingTask == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tarefa não encontrada.");
        }

        UUID idUser = (UUID) request.getAttribute("idUser");
        if (!existingTask.getIdUser().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Usuário não tem permissão para alterar essa tarefa.");
        }

        Utils.copyNonNullProperties(taskModel, existingTask);
        TaskModel updatedTask = this.taskRepository.save(existingTask);
        return ResponseEntity.ok("Tarefa atualizada com sucesso: " + updatedTask.toString());
    }
}
