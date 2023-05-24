package app.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
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
    private Integer age;

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

    /**
     * статус кандидата
     */
    @Enumerated(EnumType.STRING)//в таблице enam будет храниться, как строка
    private CandidateStatus candidateStatus;

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setReadyToMove(String readyToMove) {
        this.readyToMove = readyToMove;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setWork_experience(Integer work_experience) {
        this.work_experience = work_experience;
    }

    public void setKnowledgeOfPrograms(String knowledgeOfPrograms) {
        this.knowledgeOfPrograms = knowledgeOfPrograms;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setInformationAboutYourself(String informationAboutYourself) {
        this.informationAboutYourself = informationAboutYourself;
    }

    public void setCandidateStatus(CandidateStatus candidateStatus) {
        this.candidateStatus = candidateStatus;
    }

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "candidate_orderCandidate",
            joinColumns = @JoinColumn(name = "candidate_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "orderCandidate_id", referencedColumnName = "id"))
    private List<OrderCandidateEntity> orderCandidateEntityList = new ArrayList<>();


}
