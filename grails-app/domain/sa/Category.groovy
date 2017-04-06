package sa

class Category {
	String type


	static belongsTo = [drugs:Drug]

    static constraints = {
    }
}
