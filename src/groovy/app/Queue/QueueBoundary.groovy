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
import app.Profile.*
import app.QueueCard.*



class QueueBoundary extends CustomComponent {

private QueueController getControllerQueue(){
        Object c = UI.getCurrent().getSession().getAttribute("controllerQueue")
    } 
   public VerticalLayout queueBoundary(){


        VerticalLayout layout = new VerticalLayout()
        HorizontalLayout hon = new HorizontalLayout()
        HorizontalLayout honn = new HorizontalLayout()
        HorizontalLayout honnn = new HorizontalLayout()
        VerticalLayout verr = new VerticalLayout()
       
       
        layout.addComponent(hon)
        Label la = new Label("::แผนกห้องยา::")
        hon.addComponent(la)
        la.setStyleName("h2")
        layout.setMargin(true)
        layout.setSpacing(true)

        TabSheet tab = new TabSheet()
        tab.setWidth("100%")
        layout.addComponent(tab)

     
        TicketEdit ticketEdit = new TicketEdit()
        //honn.setSpacing(true)
        honn.addComponent(ticketEdit)
        PopupDateField date1 = new PopupDateField()
        date1.setValue(new Date())
        ticketEdit.setDate(date1.getValue())
       // honn.setMargin(true)
        honn.setSpacing(true)
        layout.addComponent(honn)
        honn.addComponent(date1)
        honn.setComponentAlignment(date1,Alignment.TOP_LEFT)
        layout.setComponentAlignment(honn,Alignment.TOP_LEFT)

       
       
        honn.addComponent(honnn)
        honn.setWidth("100%") 
        honn.setComponentAlignment(honnn,Alignment.TOP_LEFT)

        tab.addTab(honn,"บันทึกข้อมูลผู้ป่วย")
        TableBodyQueue table1 = new TableBodyQueue()
        tab.addTab(table1,"พิมพ์บัตรคิว")
        getControllerQueue().loadAll()
        
    //setContent(layout)
    return layout
    }
}
