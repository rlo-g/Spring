package kr.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Member {

	private String memID;
	private String memPW;
	private String memName;
	private String memPhone;
	private String memAddr;
	private String latitude;
	private String longitude;
}
