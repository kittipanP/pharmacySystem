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
import com.vaadin.ui.DateField
import app.Profile.*


class TicketEdit extends CustomComponent{
	Date date = new Date()
	public TicketEdit(){
	Panel panel = new Panel("รายชื่อผู้ป่วย")
	panel.setWidth("650px")
	
	HorizontalLayout layout = new HorizontalLayout()
	VerticalLayout l = new VerticalLayout()
	l.setWidth("100%")
	VerticalLayout ver = new VerticalLayout()
	ver.setWidth("100%")
	layout.setMargin(true)
	layout.setSpacing(true)
	
	panel.setContent(ver)
	ver.addComponent(layout)
	ver.addComponent(l)
 
 	ComboBox combo = new ComboBox("")
 	def plist = Profile.executeQuery("from Profile")
 	for(p in plist){
 		combo.addItem(p)
 		combo.setItemCaption(p,p.fname+" "+p.lname)
 	}
 	combo.setWidth("80%")
 	layout.setWidth("100%")
 	layout.addComponent(combo)
 	layout.setComponentAlignment(combo,Alignment.MIDDLE_CENTER)

	TextField qShow = new TextField("ลำดับคิวล่าสุด: ")
	qShow.setValue("0")
	qShow.setWidth("200px")
	l.addComponent(qShow)
	//TextArea shownext = new TextArea("ลำดับต่อไป: ")
	//shownext.setHeight("40px")
	//shownext.setValue( String( Integer.parseInt(qShow.getValue()) +1))
	//l.addComponent(shownext)

	//l.setComponentAlignment(shownext,Alignment.TOP_CENTER)

	
	
	

	l.setSpacing(true)
	l.setMargin(true)
	
	l.setComponentAlignment(qShow,Alignment.TOP_CENTER)
	qShow.setIcon(FontAwesome.WHEELCHAIR)
	
	Label label = new Label("\n")
	l.addComponent(label)
	
	Button genQ = new Button("บันทึก")
	l.addComponent(genQ)
	l.setComponentAlignment(genQ,Alignment.BOTTOM_CENTER)
	genQ.setIcon(FontAwesome.CHECK)
	Button resetQ = new Button("รีเซ็ตคิว")
	l.addComponent(resetQ)
	
	genQ.addClickListener({ event ->
		println(qShow.getValue())
			//getController().genQueue(qShow.getValue())
			if(combo.getValue() != null){
				qShow.setValue( getQueueController().genQueue(qShow.getValue()) )
				//shownext.setValue( getQueueController().genQueue( String ( Integer.parseInt(qShow.getValue() ) +1 ))\
				int x = Integer.parseInt(qShow.getValue())
				getQueueController().addQueue( x , date, combo.getValue())
			}
			else{
				System.out.println("profile is null")
			}
		} as Button.ClickListener)
	
	resetQ.addClickListener({ event ->
			//getController().genQueue(qShow.getValue())
			qShow.setValue( getQueueController().resetQueue() )
			//shownext.setValue( getQueueController().resetQueue() )
		} as Button.ClickListener)

	panel.setContent(ver)
	
	
	setCompositionRoot(panel)
	}

	private QueueController getQueueController(){
		Object q = UI.getCurrent().getSession().getAttribute("controllerQueue")
		return (QueueController)q
	}
	private TicketEdit getTicketEdit(){
		Object t = UI.getCurrent().getSession().getAttribute("ticketEdit")
		return (TicketEdit)t
	}

}