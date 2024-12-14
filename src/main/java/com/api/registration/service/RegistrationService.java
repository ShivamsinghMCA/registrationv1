package com.api.registration.service;

import com.api.registration.entity.Registration;
import com.api.registration.exception.ResourceNotFoundException;
import com.api.registration.payload.RegistrationDto;
import com.api.registration.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

//12/09/24

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    public List<RegistrationDto> getRegistrations(){
        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> dtos = registrations.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return dtos;
    }

    public RegistrationDto createRegistration(RegistrationDto registrationDto) {

        // Copy dto to entity
        Registration registration = mapToEntity(registrationDto);

        Registration savedEntity = registrationRepository.save(registration);

        //copy entity to dto
        RegistrationDto dto = mapToDto(savedEntity);
        return dto;
    }

    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    public Registration updateRegistration(long id, Registration registration) {
        Registration r = registrationRepository.findById(id).get();
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());
        Registration savedEntity = registrationRepository.save(r);
        return savedEntity;
    }

    // this methode will take the dto and convert at the entity
     Registration mapToEntity(RegistrationDto registrationDto){

//      All data we have copied to do it in one line help to modelMapper class. These one line helping to reduce the code
         Registration registration = modelMapper.map(registrationDto, Registration.class);
//        Registration registration = new Registration();
//        registration.setName(registrationDto.getName());
//        registration.setEmail(registrationDto.getEmail());
//        registration.setMobile(registrationDto.getMobile());
        return registration;
     }

     //i have to now convert the entity obj to dto obj
    RegistrationDto mapToDto(Registration registration){
//       All data we have copied to do it in one line help to modelMapper class. These one line helping to reduce the code
        RegistrationDto dto = modelMapper.map(registration, RegistrationDto.class);
//        RegistrationDto dto = new RegistrationDto();
//        dto.setName(registration.getName());
//        dto.setEmail(registration.getEmail());
//        dto.setMobile(registration.getMobile());
        return dto;
    }

    public RegistrationDto getRegistrationById(long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(
                        ()->new ResourceNotFoundException(" Record not found ")
                ); // () this Supplier functional interfaces
        return mapToDto(registration);
    }
}
