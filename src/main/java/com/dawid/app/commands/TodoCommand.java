package com.dawid.app.commands;


import com.dawid.app.domain.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoCommand {

    private Long id;

    @NotBlank(message = "Pole nie może być puste")
    @Size(min = 3, max = 255, message = "Tytuł musi zawierać od 3 do 255 znaków")
    private String title;

    private String longDesc;

    @NotNull
    private PriorityEnum priority;

    @NotNull
    private Boolean status;
}
