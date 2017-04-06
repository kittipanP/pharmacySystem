package app
import com.vaadin.ui.*
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.Alignment
import com.vaadin.ui.Label
import com.vaadin.grails.Grails
import com.vaadin.ui.TabSheet
import com.vaadin.data.Item
import com.vaadin.ui.Table
import com.vaadin.annotations.Theme
import app.*
import app.Patient.*
import sa.*
import app.Drug.*
import com.vaadin.Grails.*
import com.vaadin.server.*
import com.vaadin.terminal.*
import com.vaadin.ui.*
import com.vaadin.annotations.*
import com.vaadin.data.*
import java.util.*
import system.*




class DrugBoundary extends CustomComponent {

private DrugController getControllerdrug(){
        Object c = UI.getCurrent().getSession().getAttribute("controllerdrug")
    } 
   public VerticalLayout drugBoundary(){

		VerticalLayout layout = new VerticalLayout()
        layout.setMargin(true)
        //layout.setSpacing(true)
        layout.setWidth("100%")
        TabSheet tabsheet = new TabSheet()
//เข้าสู่ระบบ/////////////////////////////////////////////////////////////////////
        /*Timetable timetable = new Timetable()
        timetable.setWidth("30em")
        layout.addComponent(timetable)
        layout.setComponentAlignment(timetable, Alignment.MIDDLE_RIGHT)*/
//หัวข้อ///////////////////////////////////////////////////////////////////////
        Label label = new Label("::บัญชีเวชภัณฑ์ยา::")
        layout.addComponent(label)
        label.setStyleName("h2")
        label.setSizeUndefined()
        layout.setComponentAlignment(label, Alignment.MIDDLE_LEFT)
//ค้นหา///////////////////////////////////////////////////////////////////////
        //Tab tab = new Tab()
       // tab.setSizeFull()
        //layout.addComponent(tab)
        //layout.setComponentAlignment(tab, Alignment.MIDDLE_CENTER)
//บันทึกรายการใหม่//////////////////////////////////////////////////////////////
        TodoEdit todoEdit = new TodoEdit()
        todoEdit.setSizeFull()
        //layout.addComponent(todoEdit)
        //layout.setComponentAlignment(todoEdit, Alignment.MIDDLE_CENTER)
//ตารางแสดงข้อมูล///////////////////////////////////////////////////////////////
        Showtable showtable = new Showtable()
        showtable.setSizeFull()
        //layout.addComponent(showtable)
        //layout.setComponentAlignment(showtable, Alignment.MIDDLE_CENTER)
//รวมtap sheet////////////////////////////////////////////////////////////   
        VerticalLayout table_layout = new VerticalLayout()
       //table_layout.addComponent(tab)
        table_layout.addComponent(showtable)
        tabsheet.addTab(table_layout,"ข้อมูลยา")
        tabsheet.addTab(todoEdit,"บันทึกรายการใหม่")
        layout.addComponent(tabsheet)
//////////////////////////////////////////////////////////////////////
        VerticalLayout todoLayout = new VerticalLayout()
        Panel p = new Panel("523331 SYSTEM ANALYSIS AND DESIGN : Group 1 ระบบห้องยา")
        p.setContent(todoLayout)
        todoLayout.setWidth("40em")
        layout.addComponent(p)
        layout.setComponentAlignment(p, Alignment.TOP_LEFT)
////////////////////////////////////////////////////////////////////////////
        //Controller controller = new Controller()
       // this.getSession().setAttribute("controller", controller)
        controllerdrug.loadAllDrug()
/////////////////////////////////////////////////////////////////////////////
		//setContent(layout)
        return layout
    }  
}