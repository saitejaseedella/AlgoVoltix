package com.algovoltix.evbooking.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponse implements Serializable {
    @Serial
    private final static long serialVersionUID=1L;

    private Long bookingId;
    private Long userId;
    private Long seatId;
    private Instant startTime;
    private Instant endTime;
    private String status;
    private BigDecimal price;

}
