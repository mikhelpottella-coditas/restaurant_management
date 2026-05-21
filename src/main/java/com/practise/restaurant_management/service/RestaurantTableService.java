package com.practise.restaurant_management.service;

import com.practise.restaurant_management.dto.request.AddTableDto;
import com.practise.restaurant_management.dto.response.TableResponseDto;
import com.practise.restaurant_management.entity.Branches;
import com.practise.restaurant_management.entity.RestaurantTable;
import com.practise.restaurant_management.entity.Staff;
import com.practise.restaurant_management.exception.CustomException;
import com.practise.restaurant_management.repo.RestaurantTableRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantTableService {

    private final RestaurantTableRepo tableRepo;
    private final BranchService branchService;
    private final StaffService staffService;


    public String addTable(AddTableDto addTableDto) {
        Branches branches = branchService.getById(addTableDto.branchesId());
        RestaurantTable table = new RestaurantTable();
        table.setBranches(branches);
        table.setTableNumber(addTableDto.tableNumber());
        table.setCapacity(addTableDto.capacity());
        table.setCreatedAt(LocalDateTime.now());
        table.setUpdatedAt(LocalDateTime.now());

        tableRepo.save(table);
        log.info("table added in branch id {}", branches.getId());

        return "table added in branch id " + branches.getId();
    }


    public List<RestaurantTable> getAll() {
        return tableRepo.findAll();
    }

    public List<TableResponseDto> getAllTables() {

        List<RestaurantTable> tables = getAll();

        List<TableResponseDto> tableResponseDtoList = new ArrayList<>();

        tables.forEach(table -> {
            Long branchId = table.getBranches() == null ? null : table.getBranches().getId();
            Long staffId = table.getStaff() == null ? null : table.getStaff().getId();
            tableResponseDtoList.add(new TableResponseDto(table.getId(), table.getTableNumber(), table.getCapacity(), branchId, staffId, table.getCreatedAt(), table.getUpdatedAt()));
        });

        log.info("getting all tables");
        return tableResponseDtoList;
    }

    public RestaurantTable getById(Long id){
        return tableRepo.findById(id).orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND, "table not found with the id: "+id));
    }

    public TableResponseDto getTableById(Long id) {
        RestaurantTable table =  getById(id);

        Long branchId = table.getBranches() == null ? null : table.getBranches().getId();
        Long staffId = table.getStaff() == null ? null : table.getStaff().getId();
        log.info("getting table with id: {}",id);
        return new TableResponseDto(table.getId(), table.getTableNumber(), table.getCapacity(), branchId, staffId, table.getCreatedAt(), table.getUpdatedAt());
    }

    public String updateTable(Long id, AddTableDto addTableDto) {

        RestaurantTable table = getById(id);

        if(addTableDto.branchesId()!=null) table.setBranches(branchService.getById(addTableDto.branchesId()));
        if(addTableDto.capacity()!=null) table.setCapacity(addTableDto.capacity());
        if(addTableDto.tableNumber()!=null) table.setTableNumber(addTableDto.tableNumber());

        tableRepo.save(table);
        log.info("updated table with id: {}",id);
        return "table updated with the id : "+id;

    }

    public String deleteTable(Long id) {
        RestaurantTable table = getById(id);
        tableRepo.delete(table);
        log.info("deleted table with id: {}",id);
        return "table deleted with the id : "+id;
    }


    public String assignStaff(Long tableId, Long staffId) {
        RestaurantTable table = getById(tableId);
        Staff staff = staffService.getById(staffId);

        table.setStaff(staff);
        tableRepo.save(table);

        log.info("assigned staff with id: {}",staffId);

        return "staff assigned with the id : "+staffId;

    }
}
