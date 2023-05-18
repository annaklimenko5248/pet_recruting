package app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Класс представляет собой сущность заказа на подбор персонала для сохранения его в базе данных
 */
@ToString(exclude = "orderCandidateEntity")//Убираю рекурсивную зависимость из ToString и из десериализации в Json
@JsonIgnoreProperties("orderCandidateEntity")
@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "recruitment_order")
public class OrderEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Имя заказчика
     */
    private String name;
    /**
     * Название организации
     */
    private String organization;
    /**
     * Номер телефона
     */
    private String telephone;
    /**
     * email
     */
    private String email;
    /**
     * Тип услуги
     */
    private String type_of_service;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_candidate_id")
    private OrderCandidateEntity orderCandidateEntity;


}

