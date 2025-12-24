package com.algovoltix.evbooking.entity;



import com.algovoltix.evbooking.entity.enums.UserType;
import lombok.Data;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class BaseUser {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long userId;
  private UserType userType;
  private String geoLocation;
  private String mobileNumber;
  private String email;
  private String name;
  private String address;

}



