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
import ticket.*
import app.*
import app.Patient.*
import app.Profile.*
import sa.*
import app.QueueCard.*
import com.vaadin.ui.DateField

class TableBodyQueue extends CustomComponent { 

    private QueueController getControllerQueue(){
Object c = UI.getCurrent().getSession().getAttribute("controllerQueue")
} 

    private QueueController getQueueController(){
        Object q = UI.getCurrent().getSession().getAttribute("controllerQueuetabe")
   }
    
    public TableBodyQueue() { 

        Panel panel = new Panel()
        HorizontalLayout layout = new HorizontalLayout()
                layout.setSpacing(true)
                layout.setWidth("100%")
                
              
        TextField textfield = new TextField("ค้นหาผู้ป่วยด้วยชื่อ นามสกุล")
        textfield.setIcon(FontAwesome.WHEELCHAIR)
        textfield.setInputPrompt("Search . . . ")


        Button searchbutton = new Button("Search")
        searchbutton.setIcon(FontAwesome.SEARCH)

        searchbutton.addClickListener({ event ->
            getQueueController().searchQueue(textfield.getValue())
        } as Button.ClickListener)
        VerticalLayout vlayout = new VerticalLayout()
        VerticalLayout gridlayout = new VerticalLayout()
        gridlayout.setHeight("320px")
        vlayout.setSpacing(true)  
        vlayout.setWidth("100%")


        layout.addComponent(panel)
        panel.setContent(vlayout)
        
        Grid grid = new Grid();
        getControllerQueue().setGrid(grid)
        grid.setWidth("100%")
        //grid.addColumn("คำนำหน้า", String.class);
        grid.addColumn("ชื่อจริง", String.class);
        grid.addColumn("นามสกุล", String.class);
        //grid.addColumn("อายุ", String.class);
        //grid.addColumn("กรุ๊ปเลือด", String.class);
        grid.addColumn("ลำดับคิว", Integer.class);
        grid.addColumn("วันที่", Date.class)

        Button button = new Button("พิมพ์บัตรคิว")
        
         button.setIcon(FontAwesome.CHECK)
        
        //grid.addRow("นาย","กฤษดา","คำวงค์","20","B","1") 

        HorizontalLayout searchbar = new HorizontalLayout()
        searchbar.setWidth("100%")
        searchbar.addComponent(textfield)
        textfield.setWidth("100%")
        searchbar.addComponent(searchbutton)
        searchbutton.setWidth("100px")
        searchbar.addComponent(button)
        searchbar.setSpacing(true)
        searchbar.setMargin(true)
        searchbar.setComponentAlignment(searchbutton,Alignment.BOTTOM_LEFT)
        searchbar.setComponentAlignment(button,Alignment.BOTTOM_RIGHT)

        vlayout.addComponent(searchbar)
        gridlayout.addComponent(grid)
        vlayout.addComponent(gridlayout)
      

        setCompositionRoot(layout) 
    }
    //private Controller getController(){
        //Object c = UI.getCurrent().getSession().getAttribute("controller")
    //} 
}