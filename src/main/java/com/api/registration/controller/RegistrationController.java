package com.api.registration.controller;

import com.api.registration.entity.Registration;
import com.api.registration.payload.RegistrationDto;
import com.api.registration.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations(){
        List<RegistrationDto> dtos = registrationService.getRegistrations();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    /* @Valid Annoctation if you not apply this annoctaotion than spring validion will not work this object if you want spring validion
       to work  on this particuler object show use this annoctaotin
      *BindingResult result --  if any error acrrus during the validions those error messeges got from this object
      * [? or Qbject]--when the same method has diffrent diffrent kinds values is return it can be use [? or Qbject]( which is super most class in java)
      step 1- add valideon lbry, Step 2- add the annoctation step 3- add @vailed annoctation and find the result Step 4- if result.hasErrors than return the error msg
    */
    @PostMapping
    public ResponseEntity<?>createRegistration(
       @Valid @RequestBody RegistrationDto registrationDto, BindingResult result

    ){
        if(result.hasErrors()){
            return  new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.CREATED);
        }
        RegistrationDto regDto = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(regDto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String>deleteRegistration(
            @RequestParam  long id

    ){
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<Registration> updateRegistration(
            @PathVariable  long id,
            @RequestBody Registration registration

    ){
        Registration updateReg = registrationService.updateRegistration(id,registration);
        return new ResponseEntity<>(updateReg,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegistrationById(
            @PathVariable long id
    ){
        RegistrationDto dto = registrationService.getRegistrationById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
