package com.practise.restaurant_management.controller.owner;

import com.practise.restaurant_management.dto.request.SendOwnerInviteRequestDto;
import com.practise.restaurant_management.dto.response.ManagerProfileDto;
import com.practise.restaurant_management.service.InviteService;
import com.practise.restaurant_management.service.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/owner/managers")
public class OwnerManagerController {

    private final ManagerService managerService;
    private final InviteService inviteService;

    @PostMapping("/send")
    public ResponseEntity<String> inviteRestaurantOwner(@RequestBody @Valid SendOwnerInviteRequestDto request) {
        return ResponseEntity.ok(inviteService.inviteManager(request));
    }


    @GetMapping
    public ResponseEntity<List<ManagerProfileDto>> getAllManagers(
                                                                  @RequestParam(defaultValue = "0") Integer page,
                                                                  @RequestParam(defaultValue = "5") Integer size,
                                                                  @RequestParam(defaultValue = "id") String sortBy,
                                                                  @RequestParam(defaultValue = "true") Boolean ascending) {
        log.info("get all managers");
        List<ManagerProfileDto> managerProfiles = managerService.getAllManagers(page,size,sortBy,ascending);
        return ResponseEntity.ok(managerProfiles);
    }

    @GetMapping("/{managerId}")
    public ResponseEntity<ManagerProfileDto> getManagerById(@PathVariable Long managerId) {
        log.info("get manager by id {}", managerId);
        ManagerProfileDto managerProfileDto = managerService.getProfile(managerId);
        return ResponseEntity.ok(managerProfileDto);
    }

    @PutMapping("/assignBranch/{managerId}/{branchId}")
    public ResponseEntity<String> assignBranchToManager(@PathVariable Long managerId, @PathVariable Long branchId) {
        log.info("assign branch {} to manager {}", branchId, managerId);
        String response = managerService.assignBranchToManager(managerId, branchId);
        return ResponseEntity.ok(response);
    }


}
