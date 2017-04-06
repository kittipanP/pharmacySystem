package app


import com.vaadin.ui.*
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.Table
import com.vaadin.data.Item
import com.vaadin.grails.Grails
import com.vaadin.server.ExternalResource
import com.vaadin.shared.ui.label.ContentMode
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.DateField
import com.vaadin.ui.Grid.SelectionMode
import app.*
import app.Patient.*
import sa.*
import app.QueueCard.*
import com.vaadin.ui.DateField

class TableBody extends CustomComponent { 
    
    public TableBody() { 

        Panel panel = new Panel()
        HorizontalLayout layout = new HorizontalLayout()
                layout.setSpacing(true)
                layout.setWidth("99%")
              
              
        

        VerticalLayout vlayout = new VerticalLayout()

        vlayout.setSpacing(true)  
        vlayout.setWidth("100%")


        layout.addComponent(panel)
        panel.setContent(vlayout)

        // Create a grid
        Grid grid = new Grid();

        getController().setGs(grid)
        grid.setWidth("90%")
        //grid.setWidth("100%")
        // Define some columns
        grid.setSelectionMode(SelectionMode.SINGLE)
        grid.addColumn("ลำดับผู้ป่วย",Integer.class);
        grid.addColumn("เลขที่บัตรประชาชน", String.class);
        grid.addColumn("ชื่อ", String.class);
        grid.addColumn("นามสกุล", String.class);
        grid.addColumn("เพศ", String.class);
        grid.addColumn("อายุ", String.class);
        grid.addColumn("วันเกิด", Date.class);
        grid.addColumn("หมู่เลือด", String.class);
        grid.addColumn("Rh",String.class);
        grid.addColumn("แพ้ยา", String.class);
        //======================================================================
         Button button = new Button("Delete")
         
         
         button.addClickListener({ event ->
        if(grid.getSelectedRow() > 0 ) {
            int id = grid.getSelectedRow()
            getController().deleteItem(id)
        }else{
            new Notification("No selected Row",
            Notification.Type.WARNING_MESSAGE).show(Page.getCurrent())
        }       
        } as Button.ClickListener)

        
        //grid.addRow("1-1199-00526-45-4","นายพงษ์พิพัฒน์","พรมวงค์","ชาย","20","17/3/2538","โสด","ไทย","ไทย","พุทธ","O","paracetamol") 
        //grid.addRow("1-2345-6789-00-0","นางโชคดี","มีชัย","หญิง","30","18/3/2528","โสด","จิน","จีน","คริส","AB","acetaminophen")
        vlayout.addComponent(grid)
        vlayout.addComponent(button)
        vlayout.setComponentAlignment(button, Alignment.BOTTOM_RIGHT)
        setCompositionRoot(layout)   
    }
    private PatientController getController(){
        Object c = UI.getCurrent().getSession().getAttribute("controller")
     
}
}    