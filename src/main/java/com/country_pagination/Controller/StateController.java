package com.country_pagination.Controller;

import com.country_pagination.DTO.StateDTO;
import com.country_pagination.Model.State;
import com.country_pagination.Service.StateService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/state")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StateController {
    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @PostMapping
    public ResponseEntity<StateDTO> addState(@RequestBody StateDTO stateDto) {
        return ResponseEntity.ok(stateService.addState(stateDto));
    }
    @GetMapping("/all")
    public ResponseEntity<List<StateDTO>> getAllState() {
        List<StateDTO> states = stateService.getAllState();
        return ResponseEntity.ok(states);
    }

//    @GetMapping
//    public ResponseEntity<Page<State>> getStates(
//            @RequestParam(defaultValue = "0") int pageNo,
//            @RequestParam(defaultValue = "10") int pageSize) {
//
//        Page<State> states = stateService.getStates(pageNo, pageSize);
//        return ResponseEntity.ok(states);
//    }

    @GetMapping
    public ResponseEntity<Page<StateDTO>> getStates(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {

        Page<StateDTO> stateDTOPage = stateService.getStates(pageNo, pageSize);
        return ResponseEntity.ok(stateDTOPage);
    }

    @GetMapping("/{id}")
    public StateDTO getStateById(@PathVariable long id) {
        return stateService.getStateById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateDTO> updateState(@PathVariable long id, @RequestBody StateDTO state) {
        return ResponseEntity.ok(stateService.updateState(id, state));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteState(@PathVariable long id) {
        return ResponseEntity.ok(stateService.deleteState(id));
    }

}
