package lk.ijse.webpossystembackend.entity;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private String orderId;
    private LocalDate date;
    private Integer discount;
    private Double total;
    private String customerId;
}
