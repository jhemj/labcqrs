package labcqrs.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import labcqrs.config.kafka.KafkaProcessor;
import labcqrs.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MyPage5ViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private MyPage5Repository myPage5Repository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1(
        @Payload OrderPlaced orderPlaced
    ) {
        try {
            if (!orderPlaced.validate()) return;

            // view 객체 생성
            MyPage5 myPage5 = new MyPage5();
            // view 객체에 이벤트의 Value 를 set 함
            myPage5.setOrderId(orderPlaced.getId());
            myPage5.setProductId(orderPlaced.getProductId());
            myPage5.setOrderStatus("주문됨");
            // view 레파지 토리에 save
            myPage5Repository.save(myPage5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryStarted_then_UPDATE_1(
        @Payload DeliveryStarted deliveryStarted
    ) {
        try {
            if (!deliveryStarted.validate()) return;
            // view 객체 조회
            Optional<MyPage5> myPage5Optional = myPage5Repository.findByOrderId(
                deliveryStarted.getId()
            );

            if (myPage5Optional.isPresent()) {
                MyPage5 myPage5 = myPage5Optional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myPage5.setDeliveryStatus("배송됨");
                // view 레파지 토리에 save
                myPage5Repository.save(myPage5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
