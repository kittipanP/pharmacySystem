package app

import com.vaadin.ui.UI
import com.vaadin.grails.Grails
import com.vaadin.annotations.Theme
import com.vaadin.server.VaadinRequest
//import com.vaadin.ui.Panel
import com.vaadin.ui.Alignment
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.TextField 
import com.vaadin.ui.Button
import com.vaadin.ui.PasswordField
import com.vaadin.ui.Label
import com.vaadin.data.Item
import com.vaadin.ui.Table
import com.vaadin.ui.*
import project.*
import sa.* //domain
import app.* //allUI
import app.Patient.* //HongtaeUI
import app.Dispen.* // Bon
import app.Drug.*
import app.Profile.*
import app.Dispense_table.*
import app.QueueCard.*


//@Theme("timetable")
class Dispensing extends CustomComponent {
    private DispenController getControllerDispen(){
        Object c = UI.getCurrent().getSession().getAttribute("controllerDispen")

}

    public VerticalLayout dispensing(){

        Panel editPanelUI = new Panel()
        VerticalLayout layoutUI = new VerticalLayout()
        editPanelUI.setContent(layoutUI)
        //layoutUI.setSizeFull()
        //layoutUI.setMargin(true)
        //layoutUI.setSpacing(true)
        //layout.setWidth("100%")

        layoutUI.setWidth("100%")
        //editPanelUI.setComponentAlignment(layoutUI, Alignment.MIDDLE_CENTER)



        //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        //Panel editPanel = new Panel()
        VerticalLayout layout2 = new VerticalLayout()
        //editPanel.setContent(layout2)
        //layout2.setSizeFull()
        layout2.setMargin(true)
        //layout.setSpacing(true)

        Label label2 = new Label("::ระบบจ่ายยาผู้ป่วย::")
        layout2.addComponent(label2)
        label2.setStyleName("h2")
        label2.setSizeUndefined()
        layout2.setComponentAlignment(label2, Alignment.MIDDLE_LEFT)

        //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        //Panel editPanel = new Panel()
        HorizontalLayout layout = new HorizontalLayout()
        //editPanel.setContent(layout)
        //layout.setSizeFull()
        //layout.setMargin(true)
        layout.setSpacing(true)

        //Label label = new Label("Queue")
        //label.setWidth("100px")
        //layout.addComponent(label)
        //label.setSizeUndefined()
        //layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER)

        /*

        TextField textfield1 = new TextField()
        textfield1.setWidth("100px")
        //textfield1.setSizeFull()
        layout.addComponent(textfield1)
        layout.setExpandRatio(textfield1, 1.0f)
        //label.setSizeUndefined()
        layout.setComponentAlignment(textfield1, Alignment.MIDDLE_CENTER)

        */

        ComboBox comboBoxQ = new ComboBox("Queue")
        comboBoxQ.setWidth("150px")
        comboBoxQ.setNullSelectionAllowed(false)

       // def queueList = Queue.executeQuery("from Queue")
        def qlist = QueueCard.executeQuery("from QueueCard")
        for(p in qlist){
            comboBoxQ.addItem(p)
        }

         layout.addComponent(comboBoxQ)   

        /*TextField textfield2 = new TextField()
        textfield2.setWidth("300px")
        //textfield2.setSizeFull()
        textfield2.setInputPrompt("ชื่อ - นามสกุล")
        layout.addComponent(textfield2)
        layout.setExpandRatio(textfield2, 1.0f)
        layout.setComponentAlignment(textfield2, Alignment.MIDDLE_CENTER) */

        ComboBox comboBoxName = new ComboBox("ชื่อ - สกุล")
        comboBoxName.setWidth("300px")
        comboBoxName.setNullSelectionAllowed(false)

       // def queueList = Queue.executeQuery("from Queue")

        def namelist = Profile.executeQuery("from Profile")
        for(e in namelist){
            comboBoxName.addItem(e)
        }
         layout.addComponent(comboBoxName) 

        /*TextField textfield3 = new TextField()
        //textfield3.setSizeFull()
        textfield3.setInputPrompt("อายุ")
        layout.addComponent(textfield3)
        layout.setExpandRatio(textfield3, 1.0f)
        layout.setComponentAlignment(textfield3, Alignment.MIDDLE_CENTER)*/

        layout2.addComponent(layout)

        // Drug //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        /*Drug drug = new Drug()
        drug.setWidth("50em")
        layout2.addComponent(drug)
        layout2.setComponentAlignment(drug, Alignment.MIDDLE_CENTER)*/

        //Panel editPanel = new Panel()
        HorizontalLayout layoutD = new HorizontalLayout()
        //editPanel.setContent(layout)
        //layoutD.setSizeFull()
        //layoutD.setMargin(true)
        layoutD.setSpacing(true)

        ComboBox comboBoxDrug = new ComboBox("ชื่อยา")
        comboBoxDrug.setWidth("300px")
        comboBoxDrug.setNullSelectionAllowed(false)


        def druglist = Drug.executeQuery("from Drug")
          for(f in druglist){
            comboBoxDrug.addItem(f)
        }

        layoutD.addComponent(comboBoxDrug)
        layoutD.setExpandRatio(comboBoxDrug, 1.0f)
        layoutD.setComponentAlignment(comboBoxDrug, Alignment.MIDDLE_LEFT)

        TextField num= new TextField("จำนวน")
        //num.setSizeFull()
        num.setWidth("77px")
        num.setInputPrompt("20")
        layoutD.addComponent(num)
        layoutD.setExpandRatio(num, 1.0f)
        layoutD.setComponentAlignment(num, Alignment.MIDDLE_LEFT)


        ComboBox unit = new ComboBox("หน่วยบรรจุ");
          
                unit.addItem("เม็ด");
                unit.addItem("แคนซูน");
                unit.addItem("ขวด");
                unit.addItem("แผง");
                unit.addItem("กล่อง");
                unit.addItem("กระปุก");
                unit.addItem("หลอด");
                unit.addItem("- -");
            
        
 
        unit.setNullSelectionAllowed(false);
        layoutD.addComponent(unit)
        layoutD.setExpandRatio(unit, 1.0f)

        unit.setWidth("200px")

        layoutD.setComponentAlignment(unit, Alignment.MIDDLE_LEFT)

/*
        TextField unit = new TextField("หน่วยบรรจุ")
        //unit.setSizeFull()
        unit.setWidth("200px")
        unit.setInputPrompt("เม็ด, แค็บซูน, ขวด, กระปุก, ....")
        layoutD.addComponent(unit)
        layoutD.setExpandRatio(unit, 1.0f)
        layoutD.setComponentAlignment(unit, Alignment.MIDDLE_CENTER)
*/
        

        //addButton.addClickListener({event ->
          //  getMyController().addItem(num.getValue(),unit.getValue())
            //} as Button.ClickListener)

        layout2.addComponent(layoutD)

        //  How To  //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        /*HowTo howTo = new HowTo()
        howTo.setWidth("50em")
        layout2.addComponent(howTo)
        layout2.setComponentAlignment(howTo, Alignment.MIDDLE_CENTER)
        */

          //Panel editPanel = new Panel()
        HorizontalLayout layoutHT = new HorizontalLayout()
        //editPanel.setContent(layoutHT)
        //layoutHT.setSizeFull()
       // layoutHT.setMargin(true)
        layoutHT.setSpacing(true)

        //Label label = new Label("วิธีใช้")
       // layoutHT.addComponent(label)
       // label.setSizeUndefined()
       // layoutHT.setComponentAlignment(label, Alignment.MIDDLE_CENTER)

    

        ComboBox a = new ComboBox("วิธีใช้..");
                a.addItem("รับประทานครั้งละ 1 เม็ด");
                a.addItem("รับประทานครั้งละ 2 ช้อนชา");
                a.addItem("อมครั้งละ 1 เม็ด");
                a.addItem("หยอดตาข้างที่เป็น ครั้งละ 1-2 หยด");
                a.addItem("ทาบางๆเฉพาะที่");
                a.addItem("เหน็บทวารครั้งละ 1 เม็ด");
                a.addItem("สอดช่องคลอดครั้งละ 1 เม็ด");
                a.addItem("- -");
            
         
        a.setNullSelectionAllowed(false);
        layoutHT.addComponent(a)
        layoutHT.setExpandRatio(a, 1.0f)

        a.setWidth("263px")

        layoutHT.setComponentAlignment(a, Alignment.MIDDLE_LEFT)



        ComboBox c = new ComboBox("")
                c.addItem("วันละ 1 ครั้ง")
                c.addItem("วันละ 2 ครั้ง")
                c.addItem("วันละ 3 ครั้ง")
                c.addItem("วันละ 4 ครั้ง")
                c.addItem("ทุกๆ 6 ชั่วโมง")
                c.addItem("ทุกๆ 4 ชั่วโมง")
                c.addItem("ทุกๆ 2 ชั่วโมง")
                c.addItem("- -")
            
        
 
        c.setNullSelectionAllowed(false);
        layoutHT.addComponent(c)
        layoutHT.setExpandRatio(c, 1.0f)

        layoutHT.setComponentAlignment(c, Alignment.MIDDLE_LEFT)

        ComboBox d = new ComboBox("");
                d.addItem("ก่อนอาหาร");
                d.addItem("หลังอาหาร");
                d.addItem("พร้อมอาหาร");
                d.addItem("หลังอาหารทันที");
                d.addItem("- -");
            
        
 
        d.setNullSelectionAllowed(false);
        d.setValue(0);
        d.setImmediate(true);
        layoutHT.addComponent(d)
        layoutHT.setExpandRatio(d, 1.0f)

        layoutHT.setComponentAlignment(d, Alignment.MIDDLE_LEFT)

        ComboBox t = new ComboBox("")
                 t.addItem("เช้า");
                 t.addItem("กลางวัน");
                 t.addItem("เย็น");
                 t.addItem("ก่อนนอน");
                 t.addItem("เช้า-เย็น");
                 t.addItem("เช้า-กลางวัน-เย็น");
                 t.addItem("- -");
            
        
 
        t.setNullSelectionAllowed(false);
        layoutHT.addComponent(t)
        layoutHT.setExpandRatio(t, 1.0f)

        layoutHT.setComponentAlignment(t, Alignment.MIDDLE_LEFT)


        layout2.addComponent(layoutHT)






         //Panel editPanel = new Panel()
        //HorizontalLayout layoutAdd = new HorizontalLayout()
        //editPanel.setContent(layout)
        //layout.setSizeFull()
       // layoutAdd.setMargin(true)
       // layoutAdd.setSpacing(true)


        Button addButtonD = new Button("เพิ่ม")
        layoutHT.addComponent(addButtonD)
        layoutHT.setComponentAlignment(addButtonD, Alignment.BOTTOM_CENTER)
                layout2.addComponent(layoutHT)


          addButtonD.addClickListener({ event ->
            getControllerDispen().addDispen(comboBoxQ.getValue(),comboBoxName.getValue(),comboBoxDrug.getValue(),Integer.parseInt(num.getValue()),unit.getValue(),a.getValue(),c.getValue(),d.getValue(),t.getValue())
        } as Button.ClickListener)

            //addButtonD.addClickListener({ event -> 
              //  }as Button.ClickListener)



           // layout2.addComponent(layoutAdd)
        

        //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        Grid table = new Grid("")
        table.setWidth("100%")
        table.addColumn("ชื่อยา ", String.class)
        table.addColumn("จำนวน", Integer.class)
        table.addColumn("หน่วยบรรจุ", String.class)
        table.addColumn("รับประทานครั้งละ", String.class)
        table.addColumn("วันละ", String.class)
        table.addColumn("เวลา", String.class)
        table.addColumn("ตอน", String.class)

        getControllerDispen().setK(table)

       



        layout2.addComponent(table)
        layout2.setComponentAlignment(table, Alignment.MIDDLE_CENTER)

        Button addButton = new Button("Save")
        layout2.addComponent(addButton)
        layout2.setComponentAlignment(addButton,Alignment.MIDDLE_LEFT)

        layoutUI.addComponent(layout2)

        //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||



        //setContent(layoutUI)
        return layoutUI
    }
    }
