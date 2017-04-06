package app

import com.vaadin.ui.*
import com.vaadin.ui.VerticalLayout
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.Label
import com.vaadin.grails.Grails
import com.vaadin.ui.ComboBox
import com.vaadin.ui.Table
import com.vaadin.ui.Alignment
import com.vaadin.server.ExternalResource
import com.vaadin.shared.ui.label.ContentMode
import com.vaadin.annotations.Theme
import sa.* //domain
import app.* //allUI
import app.Patient.* //HongtaeUI
import app.Dispen.* // Bon
import app.Drug.*
import app.Profile.*
import app.Dispense_table.*
import app.QueueCard.*

import com.vaadin.grails.*
import com.vaadin.server.*
import com.vaadin.terminal.*
import com.vaadin.ui.*
import com.vaadin.ui.MenuBar.MenuItem
import com.vaadin.annotations.*
import com.vaadin.data.*
import com.vaadin.shared.ui.combobox.FilteringMode
import com.vaadin.data.Property.ValueChangeEvent
import com.vaadin.data.Property.ValueChangeListener
import com.vaadin.data.util.BeanItemContainer
import java.util.*
import system.*
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Label
import project.*


class DispenController {


	Grid k
	void addDispen(QueueCard queueCards, Profile profiles, Drug drugs,
		Integer no_ID, String unit, String  howto, String	howto2, 
		String	howto3, String	howto4){
		Dispense_table disPT = new Dispense_table() 
		Phamacist p =  UI.getCurrent().getSession().getAttribute("login")
		disPT.setQueueCards(queueCards)
		//disPT.setProfiles(profiles)
		disPT.setDrugs(drugs)
		disPT.setNo_ID(no_ID)
		disPT.setUnit(unit)
		disPT.setHowto(howto)
		disPT.setHowto2(howto2)
		disPT.setHowto3(howto3)
		disPT.setHowto4(howto4)
		disPT.setPhamacists(p)

		disPT = disPT.save()
		loadAllDis()

	}
		void loadAllDis(){
		def mlist = Dispense_table.executeQuery("from Dispense_table")
		k.getContainerDataSource().removeAllItems()
		
		for(q in mlist){
			k.addRow(q.drugs.name,q.no_ID,q.unit,q.howto,q.howto2,q.howto3,q.howto4)
		}

	}
		


	
	

	
	
}