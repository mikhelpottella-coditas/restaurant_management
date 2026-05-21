package com.practise.restaurant_management.dto.request;

import com.practise.restaurant_management.entity.Branches;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AddTableDto(

        @NotNull
        Integer tableNumber,

        @NotNull
        Integer capacity,

        @NotNull
        Long branchesId

) {
}
