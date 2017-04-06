package sa

class QueueCard {
		Integer queue_number
		Date	date

		static belongsTo = [profiles:Profile]


    static constraints = {
    }
}
