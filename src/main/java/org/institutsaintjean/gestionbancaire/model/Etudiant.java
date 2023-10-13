package org.institutsaintjean.gestionbancaire.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Getter
@Setter
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codeEtud")
    private Long codeEtud;

    @Column(unique = true)
    private String matricule;

    @Column(unique = true)
    private String mail_etud;
    private String code_authentification;


    private String roles;

}
