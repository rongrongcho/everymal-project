package userMypage;

public class PetInfoVO {
	private String pet_code;
	private String pet_name;
	private int pet_age;
	private String pet_sex;
	private String pet_types;
	private String pet_number;
	private String b_type;
	private float pet_weight;
	private String pet_etc;
	private String user_code;
	
	public PetInfoVO() {
	}
	public PetInfoVO(String pet_code, String pet_name, int pet_age, String pet_sex, String pet_types, String pet_number,
			String b_type, float pet_weight, String pet_etc) {
		super();
		this.pet_code = pet_code;
		this.pet_name = pet_name;
		this.pet_age = pet_age;
		this.pet_sex = pet_sex;
		this.pet_types = pet_types;
		this.pet_number = pet_number;
		this.b_type = b_type;
		this.pet_weight = pet_weight;
		this.pet_etc = pet_etc;
	}
	public PetInfoVO(String pet_code, String pet_name, int pet_age, String pet_sex, String pet_types, String pet_number,
			String b_type, float pet_weight, String pet_etc, String user_code) {
		super();
		this.pet_code = pet_code;
		this.pet_name = pet_name;
		this.pet_age = pet_age;
		this.pet_sex = pet_sex;
		this.pet_types = pet_types;
		this.pet_number = pet_number;
		this.b_type = b_type;
		this.pet_weight = pet_weight;
		this.pet_etc = pet_etc;
		this.user_code = user_code;
	}
	
	public PetInfoVO(String pet_name, int pet_age, String pet_sex, String pet_types, String pet_number, String b_type,
			float pet_weight, String pet_etc, String user_code) {
		super();
		this.pet_name = pet_name;
		this.pet_age = pet_age;
		this.pet_sex = pet_sex;
		this.pet_types = pet_types;
		this.pet_number = pet_number;
		this.b_type = b_type;
		this.pet_weight = pet_weight;
		this.pet_etc = pet_etc;
		this.user_code = user_code;
	}
	public String getPet_code() {
		return pet_code;
	}
	public void setPet_code(String pet_code) {
		this.pet_code = pet_code;
	}
	public String getPet_name() {
		return pet_name;
	}
	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}
	public String getPet_sex() {
		return pet_sex;
	}
	public void setPet_sex(String pet_sex) {
		this.pet_sex = pet_sex;
	}
	public String getPet_types() {
		return pet_types;
	}
	public void setPet_types(String pet_types) {
		this.pet_types = pet_types;
	}
	public String getPet_number() {
		return pet_number;
	}
	public void setPet_number(String pet_number) {
		this.pet_number = pet_number;
	}
	public String getB_type() {
		return b_type;
	}
	public void setB_type(String b_type) {
		this.b_type = b_type;
	}
	public float getPet_weight() {
		return pet_weight;
	}
	public void setPet_weight(float pet_weight) {
		this.pet_weight = pet_weight;
	}
	public String getPet_etc() {
		return pet_etc;
	}
	public void setPet_etc(String pet_etc) {
		this.pet_etc = pet_etc;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public int getPet_age() {
		return pet_age;
	}
	public void setPet_age(int pet_age) {
		this.pet_age = pet_age;
	}
	
	
}
