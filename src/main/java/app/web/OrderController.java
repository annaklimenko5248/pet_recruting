package app.web;

import app.model.CandidateEntity;
import app.model.OrderCandidateEntity;
import app.model.OrderEntity;
import app.service.CandidateRepository;
import app.service.OrderCandidateRepository;
import app.service.OrderEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderCandidateRepository orderCandidateRepository;

    private OrderEntityRepository orderEntityRepository;

    private CandidateRepository candidateRepository;

    @Autowired
    public OrderController(OrderCandidateRepository orderCandidateRepository, OrderEntityRepository orderEntityRepository, CandidateRepository candidateRepository) {
        this.orderCandidateRepository = orderCandidateRepository;
        this.orderEntityRepository = orderEntityRepository;
        this.candidateRepository = candidateRepository;
    }


    //Сохраняю короткую версию
    @PostMapping("/save")
    public void recruitmentSave(@ModelAttribute OrderEntity orderEntity){//это аннотация создает объект, добавляет в него параметры из класса
        orderEntityRepository.save(orderEntity);
    }

    //сохраняю сложную версию
    @PostMapping("/save/order/candidate")
    public void saveOrder(@ModelAttribute OrderEntity orderEntity, @ModelAttribute OrderCandidateEntity orderCandidateEntity){
        orderEntity.setOrderCandidateEntity(orderCandidateEntity);
        orderEntityRepository.save(orderEntity);
    }


    //сохраняю кандидата
    @PostMapping("/save/candidate")
    public void saveCandidate(@ModelAttribute CandidateEntity candidateEntity){//создает объект и добавляет параметры из класса
        candidateRepository.save(candidateEntity);

    }
    //получить всех кандидатов
    @GetMapping("/all")
    public List<OrderEntity> getAllOrders(){
        return orderEntityRepository.findAll();
    }
}