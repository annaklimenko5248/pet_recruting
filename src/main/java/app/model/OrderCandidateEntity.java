package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*


 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_candidate")
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

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "orderCandidateEntity")
    private OrderEntity orderEntity;

}
