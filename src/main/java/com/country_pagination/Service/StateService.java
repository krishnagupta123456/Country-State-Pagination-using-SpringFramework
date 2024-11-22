package com.country_pagination.Service;

import com.country_pagination.DTO.StateDTO;
import com.country_pagination.Model.Country;
import com.country_pagination.Model.State;
import com.country_pagination.Repository.CountryRepository;
import com.country_pagination.Repository.StateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateService {
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    public StateService(StateRepository stateRepository, CountryRepository countryRepository) {
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
    }

    public StateDTO addState(StateDTO stateDto) {
        Country country = countryRepository.findByName(stateDto.getCountry());
        State state = new State();
        state.setName(stateDto.getName());
        state.setCountry(country);
        stateRepository.save(state);
        stateDto.setId(state.getId());
        return stateDto;
    }

    public List<StateDTO> getAllState() {
        List<State> states = stateRepository.findAll();

        return states.stream().map(state -> {
            StateDTO dto = new StateDTO();
            dto.setId(state.getId());
            dto.setName(state.getName());
            dto.setCountry(state.getCountry() != null ? state.getCountry().getName() : "Unknown Country");
            return dto;
        }).collect(Collectors.toList());
    }

//    public Page<State> getStates(int pageNo, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNo, pageSize);
//        return stateRepository.findAll(pageable);
//    }
public Page<StateDTO> getStates(int pageNo, int pageSize) {
    Pageable pageable = PageRequest.of(pageNo, pageSize);
    Page<State> states = stateRepository.findAll(pageable);

    List<StateDTO> stateDTOList = states.getContent().stream().map(state -> {
        StateDTO dto = new StateDTO();
        dto.setId(state.getId());
        dto.setName(state.getName());
        dto.setCountry(state.getCountry() != null ? state.getCountry().getName() : "Unknown Country");
        return dto;
    }).collect(Collectors.toList());

    return new PageImpl<>(stateDTOList, pageable, states.getTotalElements());
}

    public StateDTO getStateById(long id) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("State not found with ID: " + id));
        StateDTO dto1 = new StateDTO();
        dto1.setId(state.getId());
        dto1.setName(state.getName());
        dto1.setCountry(state.getCountry() != null ? state.getCountry().getName() : "Unknown Country");
        return dto1;
    }

    public StateDTO updateState(long id, StateDTO stateDto) {
        State existingState = stateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("State not found with ID: " + id));

        Country country = countryRepository.findByName(stateDto.getCountry());
        if (country == null) {
            throw new RuntimeException("Country not found with name: " + stateDto.getCountry());
        }
        existingState.setName(stateDto.getName());
        existingState.setCountry(country);
        existingState = stateRepository.save(existingState);
        stateDto.setId(existingState.getId());
        return stateDto;
    }

    public boolean deleteState(long id) {
        if (stateRepository.existsById(id)) {
            stateRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
