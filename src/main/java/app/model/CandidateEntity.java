package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * ФИО
     */
    private String fio;

    /**
     * рассматриваемая должность
     */
    private String position;

    /**
     * номер телефона
     */
    private String telephone;

    /**
     * почта
     */
    private String email;

    /**
     * возраст
     */
    private  Integer age;

    /**
     * город проживания
     */
    private String city;

    /**
     * Заработная плата
     */
    private Integer salary;

    /**
     * готовность к переезду
     */
    private String readyToMove;

    /**
     * образование
     */
    private String education;

    /**
     * стаж работы
     */
    private Integer work_experience;

    /**
     * знание программ
     */
    private String knowledgeOfPrograms;

    /**
     * навыки
     */
    private String skill;

    /**
     * информация о себе
     */
    private String informationAboutYourself;

    @ManyToMany
    List<OrderCandidateEntity> orderCandidateEntityList = new ArrayList<>();
//{candidateId}/WAITING
//{candidateId}/PASSED
//{candidateId}/{status}





}
