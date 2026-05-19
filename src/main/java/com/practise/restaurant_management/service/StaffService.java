package com.practise.restaurant_management.service;

import com.practise.restaurant_management.dto.request.StaffRegisterDto;
import com.practise.restaurant_management.dto.response.StaffResponseDto;
import com.practise.restaurant_management.entity.Branches;
import com.practise.restaurant_management.entity.Staff;
import com.practise.restaurant_management.entity.User;
import com.practise.restaurant_management.exception.CustomException;
import com.practise.restaurant_management.repo.StaffRepo;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {

    private static final Logger log = LogManager.getLogger(StaffService.class);
    private final StaffRepo staffRepo;
    private final ManagerService managerService;
    private final UserService userService;
    private final BranchService branchService;

    public String createStaff(StaffRegisterDto staffRegisterDto) {

        User manager = managerService.findById(staffRegisterDto.managerId());

        log.info("building user to create staff");
        User user = User.builder()
                .firstName(staffRegisterDto.firstName())
                .lastName(staffRegisterDto.lastName())
                .password(staffRegisterDto.password())
                .email(staffRegisterDto.email())
                .phoneNumber(staffRegisterDto.phoneNumber())
                .role(staffRegisterDto.role())
                .image(staffRegisterDto.image())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Branches branches = branchService.getById(staffRegisterDto.branchId());

        log.info("building staff");
        Staff staff = Staff.builder()
                .branches(branches)
                .user(user)
                .updatedAt(LocalDateTime.now())
                .availability(true)
                .joinedAt(LocalDateTime.now())
                .managerUser(manager)
                .salary(staffRegisterDto.salary())
                .build();

        staffRepo.save(staff);

        log.info("Staff created successfully with name: {}", staffRegisterDto.firstName());

        return "Staff created successfully";
    }

    public List<Staff> getAll(){
        return staffRepo.findAll();
    }

    public List<StaffResponseDto> getAllStaff() {

        List<Staff> staffList = getAll();

        log.info("getting all staff");

        List<StaffResponseDto> staffResponseDtoList = new ArrayList<>();
        staffList.forEach(staff -> {
            staffResponseDtoList.add(
                    new StaffResponseDto(
                            staff.getUser().getFirstName(),
                            staff.getUser().getLastName(),
                            staff.getUser().getEmail(),
                            staff.getUser().getPhoneNumber(),
                            staff.getUser().getRole(),
                            staff.getUser().getImage(),
                            staff.getId(),
                            staff.getBranches().getId(),
                            staff.getSalary()
                            ));
        });

        log.info("getting all staff successfully");
        return staffResponseDtoList;
    }

    public Staff getById(Long id){
        return staffRepo.findById(id).orElseThrow(()->
                new CustomException(HttpStatus.NOT_FOUND,
                        "staff not found with the given id"));
    }

    public StaffResponseDto getStaffById(Long id) {
        Staff staff = getById(id);
        StaffResponseDto staffResponseDto = new StaffResponseDto(
                staff.getUser().getFirstName(),
                staff.getUser().getLastName(),
                staff.getUser().getEmail(),
                staff.getUser().getPhoneNumber(),
                staff.getUser().getRole(),
                staff.getUser().getImage(),
                staff.getManagerUser().getId(),
                staff.getBranches().getId(),
                staff.getSalary());

        log.info("getting staff successfully with id : {}", id);
        return staffResponseDto;
    }

    public String updateStaff(Long id, StaffRegisterDto staffDto) {
        Staff staff = getById(id);

        if(staffDto.firstName()!=null) staff.getUser().setFirstName(staffDto.firstName());
        if(staffDto.lastName()!=null) staff.getUser().setLastName(staffDto.lastName());
        if(staffDto.email()!=null) staff.getUser().setEmail(staffDto.email());
        if(staffDto.phoneNumber()!=null) staff.getUser().setPhoneNumber(staffDto.phoneNumber());
        if(staffDto.role()!=null) staff.getUser().setRole(staffDto.role());
        if (staffDto.image()!=null) staff.getUser().setImage(staffDto.image());
        if (staffDto.salary()!=null) staff.setSalary(staffDto.salary());

        log.info("updating staff with id: {}", id);
        staffRepo.save(staff);
        return "Staff updated successfully";
    }


    public String deleteStaff(Long id) {

        Staff staff = getById(id);

        staff.getManagerUser().setStaff(null);


        User staffUser = staff.getUser();
        userService.delete(staffUser);
        staffRepo.delete(staff);
        log.info("deleting staff with id: {}", id);
        return "staff deleted successfully";
    }
}
