package app

import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Label
import com.vaadin.ui.DateField
import com.vaadin.ui.OptionGroup
import com.vaadin.ui.ComboBox
import app.*
import app.Patient.*
import sa.*
import app.Drug.*
import com.vaadin.grails.Grails
import com.vaadin.ui.Grid
import com.vaadin.Grails.*
import com.vaadin.server.*
import com.vaadin.terminal.*
import com.vaadin.ui.*
import com.vaadin.annotations.*
import com.vaadin.data.*
import java.util.*
import system.*
class DrugController {

	//VerticalLayout todoLayout
	Grid gsd

	void addItem(String drug
		,String drug1 
		,String drug3 
		,OptionGroup service
		,DateField date1 
		,DateField date2
		,String drug4
		,ComboBox select1
		,ComboBox select2
		,ComboBox select3
		,String drug5) 
	{
		//User user = ownerSelector.getValue()
		Drug todos = new Drug()
		//todoLayout.addComponent(new Label(detail))

		Date one = date1.getValue()
		Date two = date2.getValue()
		//User u = new User()
		todos.setDrugid(drug)
		todos.setName(drug1)
		todos.setAmount(drug3)
		todos.setTypes(service.getValue())
		todos.setExpdate(one)
		todos.setMfddate(two)
		todos.setProp(drug4)
		todos.setBoy(select1.getValue())
		todos.setDekto(select2.getValue())
		todos.setMen(select3.getValue())
		todos.setHow(drug5)
		//todos.setOwner(u)
		todos = todos.save(flush:true)
		loadAllDrug()
		
	}

	void loadAllDrug() {
		// select * from TODO
		// def list = Todo.list()
		def list = Drug.executeQuery("from Drug")
		gsd.getContainerDataSource().removeAllItems()
		for(todos in list) {
			gsd.addRow(todos.id,todos.drugid
				,todos.name
				,todos.amount
				,todos.types
				,todos.expdate
				,todos.mfddate
				,todos.prop
				,todos.boy
				,todos.dekto
				,todos.men
				,todos.how)
		}
	}
	void deleteItem(Integer id){
		String cid = gsd.getContainerDataSource().getItem(id).getItemProperty("ตัด Stock")
		def list = Drug.get(cid)
		if(list != null){
			list.delete(flush:true)
		}
		loadAllDrug()
	}
}