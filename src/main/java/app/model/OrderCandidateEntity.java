package app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*


 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderCandidate")
public class OrderCandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Должность, на которую ищется персонал
     */
    private String position;
    /**
     * количество мест
     */
    private Integer number_of_seats;
    /**
     * описание компании
     */
    private String company_description;
    /**
     * пол
     */
    private String sex;
    /**
     * возраст
     */
    private Integer age;
    /**
     * личные характеристики
     */
    private String personal_characteristics;
    /**
     * уровень образования
     */
    private String education_level;
    /**
     * профиль образования
     */
    private String education_profile;
    /**
     * дополнительное образование
     */
    private String additional_education;
    /**
     * опыт работы
     */
    private Integer work_experience;
    /**
     * дополнительная информация
     */
    private String additional_information;
    /**
     * адрес
     */
    private String address;
    /**
     * график работы
     */
    private String work_schedule;
    /**
     * Функциональные обязанности:
     */
    private String functional_responsibilities;
    /**
     * заработная плата
     */
    private Integer salary;
    /**
     * дни встреч
     */
    private String days_of_meetings;

    public void setPosition(String position) {
        this.position = position;
    }

    public void setNumber_of_seats(Integer number_of_seats) {
        this.number_of_seats = number_of_seats;
    }

    public void setCompany_description(String company_description) {
        this.company_description = company_description;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPersonal_characteristics(String personal_characteristics) {
        this.personal_characteristics = personal_characteristics;
    }

    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }

    public void setEducation_profile(String education_profile) {
        this.education_profile = education_profile;
    }

    public void setAdditional_education(String additional_education) {
        this.additional_education = additional_education;
    }

    public void setWork_experience(Integer work_experience) {
        this.work_experience = work_experience;
    }

    public void setAdditional_information(String additional_information) {
        this.additional_information = additional_information;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWork_schedule(String work_schedule) {
        this.work_schedule = work_schedule;
    }

    public void setFunctional_responsibilities(String functional_responsibilities) {
        this.functional_responsibilities = functional_responsibilities;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setDays_of_meetings(String days_of_meetings) {
        this.days_of_meetings = days_of_meetings;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "orderCandidateEntity")
    private OrderEntity orderEntity;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "orderCandidateEntityList")
    private List<CandidateEntity> candidateEntityList = new ArrayList<>();



}
