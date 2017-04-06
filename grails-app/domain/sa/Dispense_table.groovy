package sa

class Dispense_table {

	Integer no_ID
	String	unit
	String  howto
	String	howto2
	String	howto3
	String	howto4


	static belongsTo = [/*queues:Queue,*/queueCards:QueueCard,drugs:Drug,phamacists:Phamacist]
    static constraints = {
    }
}
