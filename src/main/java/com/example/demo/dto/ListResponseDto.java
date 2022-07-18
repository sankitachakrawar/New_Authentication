package com.example.demo.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListResponseDto {


	public ListResponseDto(Long count) {

		super();
		this.count = count;

	}

	private Object data;

	private Long count;

	

}
