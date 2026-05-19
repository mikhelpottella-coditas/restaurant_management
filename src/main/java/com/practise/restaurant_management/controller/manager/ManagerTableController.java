package com.practise.restaurant_management.controller.manager;

import com.practise.restaurant_management.dto.request.AddTableDto;
import com.practise.restaurant_management.dto.response.TableResponseDto;
import com.practise.restaurant_management.entity.RestaurantTable;
import com.practise.restaurant_management.service.RestaurantTableService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/manager/tables")
public class ManagerTableController {

    private final RestaurantTableService  tableService;

    @PostMapping
    public ResponseEntity<String> addTable(@Valid @RequestBody AddTableDto addTableDto){
        log.info("adding table");
        String response = tableService.addTable(addTableDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TableResponseDto>> getAllTables() {
        log.info("getting all tables");
        List<TableResponseDto> tableResponseDtoList = tableService.getAllTables();
        return ResponseEntity.ok(tableResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableResponseDto> getTable(@PathVariable Long id){
        log.info("getting table with id: {}",id);
        TableResponseDto tableResponseDto = tableService.getTableById(id);
        return ResponseEntity.ok(tableResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTable(@PathVariable Long id, @RequestBody AddTableDto addTableDto){
        log.info("updating table with id: {}",id);

        String response = tableService.updateTable(id,addTableDto);

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTable(@PathVariable Long id){
        log.info("deleting table with id: {}",id);
        String response = tableService.deleteTable(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{tableId}/assign-staff/{staffId}")
    public ResponseEntity<String> assignStaff(@PathVariable Long tableId, @PathVariable Long staffId){
        log.info("assigning staff with id: {}",staffId);
        String response = tableService.assignStaff(tableId,staffId);
        return ResponseEntity.ok(response);
    }
}
