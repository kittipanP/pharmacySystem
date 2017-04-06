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
import sa.* //domian
import app.*
import app.Patient.*
import com.vaadin.ui.DateField

class PatientBoundary extends CustomComponent {

private PatientController getController(){
        Object c = UI.getCurrent().getSession().getAttribute("controller")
    } 
   public VerticalLayout patientBoundary(){

       

         //Panel panelsystem = new Panel("ระบบห้องยา")
         //VerticalLayout o = new VerticalLayout()
        // panelsystem.setWidth("100%")
       
         VerticalLayout layoutsystem = new VerticalLayout()
         //panelsystem.setContent(layoutsystem)
         layoutsystem.setWidth("100%")
         
         Panel spanel = new Panel()
        HorizontalLayout searchlayout = new HorizontalLayout()
        Label text = new Label("::บันทึกข้อมูลผู้ป่วย::")
        TextField searchField = new TextField()
        searchField.setWidth("297px")
        searchField.setInputPrompt("                                   ...ค้นหาข้อมูลผู้ป่วย")
        text.setStyleName("h2")
        Button searchButton = new Button("Search")
        searchlayout.setMargin(true)
        searchlayout.setSpacing(true)
        searchlayout.setWidth("100%")
        spanel.setContent(searchlayout)
        searchlayout.addComponent(text)
        searchlayout.addComponent(searchField)
        searchlayout.addComponent(searchButton)
        searchlayout.setExpandRatio(text,7.0f)
        searchlayout.setExpandRatio(searchField,2.0f)
        searchlayout.setExpandRatio(searchButton,1.0f)
        searchlayout.setComponentAlignment(text, Alignment.MIDDLE_RIGHT)
        searchlayout.setComponentAlignment(searchField, Alignment.MIDDLE_RIGHT)
        searchlayout.setComponentAlignment(searchButton, Alignment.MIDDLE_RIGHT)
        searchButton.addClickListener({ event ->
            getController().search_patient(searchField.getValue())} as Button.ClickListener)

        
        //o.addComponent(panelsystem)

        

       

       
        VerticalLayout bodylayout = new VerticalLayout()
        Panel allpanel = new Panel("ระบบประวัติผู้ป่วย-บันทึกประวัติผู้ป่วย")

        VerticalLayout layout = new VerticalLayout()

        allpanel.setContent(layout)
        layout.setMargin(true)
        layout.setWidth("100%")
        allpanel.setWidth("999px")
        bodylayout.setMargin(true)
        bodylayout.addComponent(allpanel)
        HorizontalLayout bar0 = new HorizontalLayout()
        ComboBox selectPatient = new ComboBox()
        selectPatient.setInputPrompt("...เลือกผู้ป่วยเพื่อบันทึกข้อมูล")
        selectPatient.setWidth("250px")
        getController().setSelectPatient(selectPatient)
        bar0.addComponent(selectPatient)
        def patientlist = Patient.executeQuery("from Patient")
        for(p in patientlist){
            selectPatient.addItem(p)
        }

        HorizontalLayout bar1 = new HorizontalLayout()
        bar1.setSpacing(true)

        TextField p1 = new TextField("ชื่อ :")
        p1.setWidth("200px");
     
        TextField p2 = new TextField("นามสกุล :")
        p2.setWidth("200px");
       
        ComboBox p3 = new ComboBox("เพศ :")
        p3.setWidth("100px")
        p3.addItem("ชาย")
        p3.addItem("หญิง")

       
        TextField p4 = new TextField("อายุ:")
        p4.setWidth("50px")
      
        DateField p6 = new DateField("วันเกิด :")
        p6.setImmediate(false)
        p6.setDateFormat("yyyy-MM-dd 'เวลา' HH:mm:ss")
        p6.setLocale(new Locale("us", "US"))
        p6.setResolution(DateField.RESOLUTION_SEC)
        //p6.setResolution(DateField.RESOLUTION_DAY)
        //p6.setResolution(DateField.RESOLUTION_MINUTE)
        p6.setValue(null)
        p6.setValue(new Date())
        p6.setWidth("100%")
       

        
        HorizontalLayout bar2 = new HorizontalLayout()
        bar2.setSpacing(true)

        bar2.addComponent(p1)
        bar2.addComponent(p2)
        bar2.addComponent(p3)
        bar2.addComponent(p4)
        bar2.addComponent(p6)


        
       
        ComboBox p7 = new ComboBox("หมู่เลือด :")
        p7.setWidth("80px")
        p7.addItem("O")
        p7.addItem("B")
        p7.addItem("A")
        p7.addItem("AB")

       
        ComboBox p8 = new ComboBox("RH :")
        p8.setWidth("70px")
        p8.addItem("-")
        p8.addItem("+")
        
        TextField p9 = new TextField("สภาพสมรส :")
        p9.setWidth("100px")
        
        TextField p10 = new TextField("สัญชาติ :")
        p10.setWidth("100px")
        
        TextField p11 = new TextField("เชื้อชาติ :")
        p11.setWidth("100px")
        
        TextField p12 = new TextField("ศาสนา :")
        p12.setWidth("100px")
        
        TextField p13 = new TextField("อาชีพ :")
        p13.setWidth("100px")
       

        HorizontalLayout bar = new HorizontalLayout()
        bar.setSpacing(true)

      
        bar.addComponent(p7)
        bar.addComponent(p8)
        bar.addComponent(p9)
        bar.addComponent(p10)
        bar.addComponent(p11)
        bar.addComponent(p12)
        bar.addComponent(p13)

        TextField p16 = new TextField("ที่อยู่ :")
        p16.setHeight("100%")
        p16.setWidth("700px")
       

        HorizontalLayout bar5 = new HorizontalLayout()
        bar5.setSpacing(true)

    
        bar5.addComponent(p16)

        
        TextField p17 = new TextField("รหัสไปรษณี :")
        p17.setWidth("80px")
       
        TextField p18 = new TextField("โทรศัพท์: ")
        p18.setWidth("130")
       
        ComboBox p19 = new ComboBox("การแพ้ยา : ")
         def drugslist = Drug.executeQuery("from Drug")
        for(d in drugslist){
            p19.addItem(d)
        }


        p19.setWidth("250")
       
        Button button1 = new Button("Add")
        
        
        HorizontalLayout bar4 = new HorizontalLayout()
        bar4.setSpacing(true)
        
        bar4.addComponent(p17)
        bar4.addComponent(p18)
        bar4.addComponent(p19)
        bar4.addComponent(button1)
        bar4.setComponentAlignment(button1, Alignment.BOTTOM_LEFT)
        
        button1.addClickListener({ event ->
            getController().add_Profile(p1.getValue(),p2.getValue(),p3,p4.getValue(),p6,p7,p8,p9.getValue(),p10.getValue()
                ,p11.getValue(),p12.getValue(),p13.getValue(),p16.getValue(),p17.getValue(),p18.getValue(), p19 ,selectPatient)
        } as Button.ClickListener)


        TextField y2 = new TextField("เลขที่บัตรประชาชน :")
        
        Button button4 = new Button("Add")

        
        HorizontalLayout bar9 = new HorizontalLayout()
        bar9.setSpacing(true)

        
        bar9.addComponent(y2)
        bar9.addComponent(button4)
        bar9.setComponentAlignment(button4, Alignment.BOTTOM_LEFT)
        button4.addClickListener({ event ->
            getController().add_Patient(y2.getValue()) } as Button.ClickListener)


        TabSheet t = new TabSheet()
        t.setWidth("100%")
        TableBody table = new TableBody()
        


        t.addTab(table,"ข้อมูลผู้ป่วย")
        table.setWidth("100%")

        t.addTab(bodylayout,"ระบบลงทะเบียนผู้ป่วย")
        layoutsystem.addComponent(spanel)
        bodylayout.setComponentAlignment(allpanel,Alignment.MIDDLE_CENTER)
        layoutsystem.addComponent(t)
        layoutsystem.setComponentAlignment(t,Alignment.MIDDLE_CENTER)

        layout.setSpacing(true)
        layout.addComponent(bar9)
        layout.addComponent(bar0)
        layout.addComponent(bar1)
        layout.addComponent(bar2)
        layout.addComponent(bar)
        layout.addComponent(bar5)
        layout.addComponent(bar4)

     
       

       
       

        
        getController().load_patient()
        getController().load_all()
        //setContent(o)
            return layoutsystem
    }
}
