package com.project.geeksworld.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import jakarta.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "characters")
public class CharacterEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long characterId;
    private String charname;
    private File photo;
    private String about;
    private Boolean isMarvel;
    private Boolean isHero;

    @JsonIgnore
    @ManyToMany(mappedBy = "characters")
    private List<ListEntity> lists;

}
