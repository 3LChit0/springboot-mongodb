package com.kodigo.SpringBootProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todo")
public class ToDo {

    @Id
    private String id;

    @NotNull(message = "Title can't be null")
    private String title;

    @NotNull(message = "Description can't be null")
    private String description;

    @NotNull(message = "CreatedAt can't be null")
    private Date createdAt;

    private Date updatedAt;
}
