package in.bta.profiles.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "ahs")
public class AccountHolder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ahId;
	@NotBlank(message="fullName is mandate")
	private String fullName;
	@NotBlank(message="mobile is mandate")
	@Pattern(regexp = "[1-9][0-9]{9}",message = "Only ten digits are expected for mobile")
	private String mobile;
	@NotBlank(message="mailId is mandate")
	@Email(message = "A valid mailId expected")
	private String mailId;
	
	public AccountHolder() {
		// TODO Auto-generated constructor stub
	}

	public AccountHolder(Long ahId, @NotBlank(message = "fullName is mandate") String fullName,
			@NotBlank(message = "mobile is mandate") @Pattern(regexp = "[1-9][0-9] {9}", message = "Only ten digits are expected for mobile") String mobile,
			@NotBlank(message = "mailId is mandate") @Email(message = "A valid mailId expected") String mailId) {
		super();
		this.ahId = ahId;
		this.fullName = fullName;
		this.mobile = mobile;
		this.mailId = mailId;
	}

	public Long getAhId() {
		return ahId;
	}

	public void setAhId(Long ahId) {
		this.ahId = ahId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
	
}
