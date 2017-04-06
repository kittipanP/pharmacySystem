package sa

class Profile {
	Integer id
	String fname
	String lname
	String sex
	String age
	Date birthday
	String blood
	String rh
	String status
	String nationality
	String race
	String religion
	String career
	String address
	String postcode
	String tel

	

	static belongsTo = [patients:Patient,drugs:Drug]
    static constraints = {
    }

}
