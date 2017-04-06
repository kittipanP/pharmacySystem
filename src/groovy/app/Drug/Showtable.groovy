package app

import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Alignment
import com.vaadin.ui.Label
import com.vaadin.grails.Grails
import com.vaadin.ui.TabSheet
import com.vaadin.data.Item
import com.vaadin.ui.Table
import com.vaadin.ui.Panel
import com.vaadin.ui.Grid
import com.vaadin.ui.CustomComponent
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
import com.vaadin.ui.Grid.SelectionMode


class Showtable extends CustomComponent {

    
    public Showtable() {
        VerticalLayout layout = new VerticalLayout()
        layout.setMargin(true)
        layout.setSpacing(true)
        layout.setWidth("100%")

        Grid grid = new Grid("ตารางแสดงข้อมูล")
        getControllerdrug().setGsd(grid)
        grid.setWidth("100%")
        //grid.setWidth("100%")
        // Define some columns
        grid.setSelectionMode(SelectionMode.SINGLE)
        grid.addColumn("ตัด Stock", Integer.class)
        grid.addColumn("รหัสยา", String.class)
        grid.addColumn("ชื่อยา", String.class)
        grid.addColumn("หน่วยนับ", String.class)
        grid.addColumn("ประเภทยา", String.class)
        grid.addColumn("วัน/เดือน/ปี ที่ผลิต", Date.class)
        grid.addColumn("วัน/เดือน/ปี ที่หมดอายุ", Date.class)
        grid.addColumn("สรรพคุณ", String.class)
        grid.addColumn("เด็กเล็ก ครั้งละ", String.class)
        grid.addColumn("เด็กโต ครั้งละ", String.class)
        grid.addColumn("ผู้ใหญ่ ครั้งละ", String.class)
        grid.addColumn("เวลาที่ใช้รับประทาน", String.class)

        /*for (u in Todo.list()){
        Object newItemId = grid.addItem()
        Item row1 = grid.getItem(newItemId)
        row1.getItemColumn("รหัสยา").setValue(u.id)
        //row1.getItemProperty("Day").setValue(u.day)
        //row1.getItemProperty("Time").setValue(u.time)
        }*/

        //table.setPageLength(table.size())
        //table.setSelectable(true);
        layout.addComponent(grid)
        //table.setImmediate(true);
        layout.setComponentAlignment(grid, Alignment.MIDDLE_CENTER)
       
        Button addButton2 = new Button("ลบ")
        layout.addComponent(addButton2)
        layout.setComponentAlignment(addButton2, Alignment.MIDDLE_LEFT)

       addButton2.addClickListener({ event ->
        if(grid.getSelectedRow() > 0 ) {
            int id = grid.getSelectedRow()
            getControllerdrug().deleteItem(id)
        }else{
            new Notification("No selected Row",
            Notification.Type.WARNING_MESSAGE).show(Page.getCurrent())
        }       
        } as Button.ClickListener)

        setCompositionRoot(layout)
    }
        private DrugController getControllerdrug() {
        Object c = UI.getCurrent().getSession().getAttribute("controllerdrug")
        return (DrugController)c
    }
}