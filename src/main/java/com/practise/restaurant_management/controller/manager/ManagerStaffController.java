package com.practise.restaurant_management.controller.manager;

import com.practise.restaurant_management.dto.request.StaffRegisterDto;
import com.practise.restaurant_management.dto.response.StaffResponseDto;
import com.practise.restaurant_management.entity.Staff;
import com.practise.restaurant_management.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager/staff")
@Slf4j
public class ManagerStaffController {

    private final StaffService staffService;

    @PostMapping()
    public ResponseEntity<String> createStaff(@Valid @RequestBody StaffRegisterDto staff){
        log.info("creating staff with name: {}", staff.firstName());
        String response =  staffService.createStaff(staff);
        return ResponseEntity.created(null).body(response);
    }

    @GetMapping("/{managerId}")
    public ResponseEntity<List<StaffResponseDto>> getAllStaffs(@PathVariable Long managerId,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "5") int size,
                                                               @RequestParam(defaultValue = "id") String sortBy,
                                                               @RequestParam(defaultValue = "true") boolean ascending){
        log.info("getting all staffs");
        List<StaffResponseDto> staffResponseDtoList = staffService.getAllStaff(managerId,page,size,sortBy,ascending);
        return ResponseEntity.ok(staffResponseDtoList);
    }

    @GetMapping("{id}")
    public ResponseEntity<StaffResponseDto> getStaffById(@PathVariable Long id){
        log.info("getting staff with id: {}", id);
        StaffResponseDto staffResponseDto = staffService.getStaffById(id);
        return ResponseEntity.ok(staffResponseDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateStaff(@PathVariable Long id, @RequestBody StaffRegisterDto staffDto){
        log.info("updating staff with id: {}", id);
        String response = staffService.updateStaff(id,staffDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable Long id){
        log.info("deleting staff with id: {}", id);
        String response = staffService.deleteStaff(id);
        return ResponseEntity.ok(response);
    }

}
