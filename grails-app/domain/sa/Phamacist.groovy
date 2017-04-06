package sa

class Phamacist {
	Integer id
	String name
	String password

	static hasMany = [dispense_tables:Dispense_table]
	String toString(){
		return "$name"
	}

    static constraints = {
    }
}
