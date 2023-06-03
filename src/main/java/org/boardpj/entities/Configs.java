package org.boardpj.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity @Data
public class Configs {
    @Id
    private String Code;
    @Lob
    private String Value;
}
