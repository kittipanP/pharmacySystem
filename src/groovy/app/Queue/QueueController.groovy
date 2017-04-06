package app

import ticket.*
import com.vaadin.server.*
import com.vaadin.ui.*
import com.vaadin.grails.*
import com.vaadin.ui.UI.*
import com.vaadin.annotations.*
import com.vaadin.terminal.*
import com.vaadin.data.*
import java.util.*
import system.*
import com.vaadin.ui.Button.*
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.MenuBar.MenuItem
import com.vaadin.server.ExternalResource
import com.vaadin.shared.ui.label.ContentMode
import app.*
import app.Patient.*
import sa.*
import app.QueueCard.*
import app.Profile.*


class QueueController{
	Grid grid

	String genQueue(String q){
		Integer x = Integer.parseInt(q)
		x++

		return (String)x
	}
	void addQueue(Integer queue_number,Date date1,Profile profiles){
		//Queue queue = new Queue()
		QueueCard queue = new QueueCard()
		//Date date = date1.getValue()
		queue.setQueue_number(queue_number)
		queue.setDate(date1)
		queue.setProfiles(profiles)
		queue = queue.save(flush:true)
		loadAll()
	}
	void loadAll(){
		def qlist = QueueCard.executeQuery("from QueueCard")
		grid.getContainerDataSource().removeAllItems()
		
		for(q in qlist){
			grid.addRow(q.profiles.fname,q.profiles.lname,q.queue_number,q.date)
		}

	}
	String resetQueue(){
		def qlist = QueueCard.executeQuery("from QueueCard")
		for(i in qlist){
			if(QueueCard.get(i.id) != null){
				QueueCard.get(i.id).delete(flush:true)
			}
		}

		loadAll()

		return "0"
	}
	void searchQueue(String str){
		def qlist = QueueCard.executeQuery(
			"from QueueCard where p.fname like :s or p.lname like :s",[s: "%${str}%"])
		grid.getContainerDataSource().removeAllItems()
		for(q in qlist){
			grid.addRow(q.profiles.fname,q.profiles.lname,q.queue_number,q.date)
		}
	}
}