package be.pxl.api.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateTeamDto {
	@NotBlank(message = "Name cannot be blank.")
	private String name;
	@NotBlank(message = "Field city is required.")
	private String city;
	@NotBlank
	private String coach;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}
}
