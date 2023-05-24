package app.web;

import app.model.CandidateEntity;
import app.model.CandidateStatus;
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
    public OrderController(OrderCandidateRepository orderCandidateRepository, OrderEntityRepository orderEntityRepository
            , CandidateRepository candidateRepository) {
        this.orderCandidateRepository = orderCandidateRepository;
        this.orderEntityRepository = orderEntityRepository;
        this.candidateRepository = candidateRepository;

    }


    //Сохраняю короткую версию
    @PostMapping("/save")
    public void recruitmentSave(@ModelAttribute OrderEntity orderEntity) {//это аннотация создает объект, добавляет в него параметры из класса
        orderEntityRepository.save(orderEntity);
    }

    //сохраняю сложную версию
    @PostMapping("/save/order/candidate")
    public void saveOrder(@ModelAttribute OrderEntity orderEntity, @ModelAttribute OrderCandidateEntity orderCandidateEntity) {
        orderEntity.setOrderCandidateEntity(orderCandidateEntity);
        orderEntityRepository.save(orderEntity);
    }

    //TODO разобраться с этим эндпоинтом
    //сохраняю кандидата
    @PostMapping("/save/candidate")
    public void saveCandidate(@ModelAttribute CandidateEntity candidateEntity) {//создает объект и добавляет параметры из класса
        candidateRepository.save(candidateEntity);


    }

    //получить всех кандидатов
    @GetMapping("/all")
    public List<OrderEntity> getAllOrders() {
        return orderEntityRepository.findAll();
    }


    //Hh может прикреплять кандидата к заказу(разобраться с какскадами)
    @GetMapping("/order/{orderId}/candidate/{candidateId}/save")
    public void attachCandidateOrder(@PathVariable Integer orderId, @PathVariable Integer candidateId) {
        OrderCandidateEntity orderFromBd = orderCandidateRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not fount"));
        CandidateEntity candidateFromBd = candidateRepository.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not fount"));
        //TODO в кандидате прописано каскадное действие на сохранение
        //Проверить, что если сохранить заказ в кандидате и сохранить кандидата, связь появится
        orderFromBd.getCandidateEntityList().add(candidateFromBd);
        //Не получается
        // candidateFromBd.getOrderCandidateEntityList().add(orderFromBd);
       // orderCandidateRepository.save(orderFromBd);
        candidateRepository.save(candidateFromBd);
    }

    //Hh может открепить кандидата от заказа//Не проверяла(проверить)
    @GetMapping("/order/{orderId}/candidate/{candidateId}/delete")
    public void detachCandidateOrder(@PathVariable Integer orderId, @PathVariable Integer candidateId) {
        //TODO поскольку каскад прописан на стороне кандидата
        // Нужно попробовать загрузить кандидата по id, в его списке найти заказ по id и удалить этот заказ из списка
        // После чего сохранить кандидата через метод save
        OrderCandidateEntity orderFromBd = orderCandidateRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not fount"));
        CandidateEntity candidateFromBd = candidateRepository.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not fount"));
        List<OrderCandidateEntity> ordersList = candidateFromBd.getOrderCandidateEntityList();
        for (int i = 0; i < ordersList.size(); i++) {
            if (ordersList.get(i).getId() == orderId){
                ordersList.remove(orderFromBd);
            }
        }
        candidateRepository.save(candidateFromBd);
    }

    //проверить
    //hh может менять статус кандидата
    @PutMapping("/order/candidate/{candidateId}/status/{status}")
    public void changerStatus(@PathVariable Integer candidateId, @RequestParam CandidateEntity candidateEntity) {
        CandidateEntity foundCandidate = candidateRepository.findById(candidateId).orElseThrow(() -> new RuntimeException("candidate not fount"));
        foundCandidate.setCandidateStatus(candidateEntity.getCandidateStatus());
        candidateRepository.save(foundCandidate);

    }
    //проверить
    // Эндпоинт, который позволит получить всех прикрепленных кандидатов по конкретному заказу
    @GetMapping("/order/orderCandidate/{orderCandidateId}")
    public List<CandidateEntity> getCandidates(@PathVariable Integer orderCandidateId){
        OrderCandidateEntity foundOrder = orderCandidateRepository.findById(orderCandidateId).orElseThrow(() -> new RuntimeException("order not fount"));
        return foundOrder.getCandidateEntityList();
    }
}


