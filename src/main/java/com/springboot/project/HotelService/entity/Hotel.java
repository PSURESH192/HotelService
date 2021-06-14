package com.springboot.project.HotelService.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="hotel")
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hotel {

	@Id
	@Column(name = "hotel_Id")
	private String hotelId;

	@NotBlank(message = "Hotel Name is mandatory")
	@Column(name = "hotel_name")
	private String hotelName;

	@Column(name = "contact")
	private Long contact;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "zipcode")
	private Long zipcode;

	@OneToMany(targetEntity = Room.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "hotel_Id", referencedColumnName = "hotel_Id")
	private List<Room> roomDetails;

}