package com.dawid.app.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo implements Comparable<Todo> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String longDesc;

    @Enumerated(value = EnumType.STRING)
    private PriorityEnum priority;

    private Boolean status;

    @Override
    public int compareTo(Todo o) {
        if (this.title.compareTo(o.title) > 0) {
            return 1;
        } else if (this.title.compareTo(o.title) == 0) {
            return 0;
        } else {
            return -1;
        }

    }
}