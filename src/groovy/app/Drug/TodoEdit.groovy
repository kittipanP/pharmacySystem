package app

import com.vaadin.ui.Alignment
import com.vaadin.ui.Button
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.VerticalLayout
//import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Panel
import com.vaadin.ui.TextField
import com.vaadin.ui.UI
import com.vaadin.ui.TextArea
import com.vaadin.ui.OptionGroup
import com.vaadin.ui.DateField
import com.vaadin.ui.ComboBox
import com.vaadin.ui.Table
import com.vaadin.ui.NativeSelect
import com.vaadin.ui.Label
import com.vaadin.Grails.*
import com.vaadin.server.*
import com.vaadin.terminal.*
import com.vaadin.ui.*
import com.vaadin.annotations.*
import com.vaadin.data.*
import java.util.*
import system.*
import app.*
import app.Patient.*
import sa.*
import app.Drug.*
class TodoEdit extends CustomComponent {

	public TodoEdit() {
		Panel editPanel = new Panel()
		VerticalLayout layout = new VerticalLayout()
		editPanel.setContent(layout)
		layout.setSizeFull()
		layout.setMargin(true)
		layout.setSpacing(true)

		Label label = new Label("บันทึกรายการใหม่")
        layout.addComponent(label)
        label.setStyleName("h3")
        label.setSizeUndefined()
        layout.setComponentAlignment(label, Alignment.MIDDLE_LEFT)

        //TextField drugx = new TextField("ตัด Stock")
        //drugx.setWidth("15em")
       // drugx.setInputPrompt("0 ไม่ตัด 1 ตัด");
        //drugx.setMaxLength(1);
        //layout.addComponent(drugx)
       // layout.setExpandRatio(drugx, 1.0f)

		TextField drug = new TextField("รหัสยา")
		drug.setWidth("15em")
        drug.setInputPrompt("100100");
        drug.setMaxLength(6);
        layout.addComponent(drug)
        layout.setExpandRatio(drug, 1.0f)

		TextField drug1 = new TextField("ชื่อยา")
		drug1.setWidth("25em")
        drug1.setInputPrompt("paracetamol");
        layout.addComponent(drug1)
        layout.setExpandRatio(drug1, 1.0f)

        TextField drug3 = new TextField("หน่วยนับ")
		drug3.setWidth("15em")
        drug3.setInputPrompt("500 กรัม");
        layout.addComponent(drug3)
        layout.setExpandRatio(drug3, 1.0f)

		OptionGroup service = new OptionGroup("ประเภทยา")
        service.setWidth("200px");
        service.addItem("ยาทาภายนอก")
        service.addItem("ยาทาภายใน")
        service.addItem("ยารับประทาน")
        service.addItem("ยาเหน็บ")
        layout.addComponent(service)
        layout.setComponentAlignment(service, Alignment.BOTTOM_LEFT) 

        DateField date1= new DateField("วัน/เดือน/ปี,เวลา ที่ผลิต")
        date1.setImmediate(false)
        date1.setDateFormat("yyyy-MM-dd 'เวลา' HH:mm:ss")
        date1.setLocale(new Locale("us", "US"))
        date1.setResolution(DateField.RESOLUTION_SEC)
        //date1.setResolution(DateField.RESOLUTION_DAY)
        //date1.setResolution(DateField.RESOLUTION_MINUTE)
        date1.setValue(null)
        date1.setValue(new Date())
        date1.setWidth("100%")
        layout.addComponent(date1)

       DateField date2= new DateField("วัน/เดือน/ปี,เวลา ที่หมดอายุ")
        date2.setImmediate(false)
        date2.setDateFormat("yyyy-MM-dd 'เวลา' HH:mm:ss")
        date2.setLocale(new Locale("us", "US"))
        date2.setResolution(DateField.RESOLUTION_SEC)
        //date1.setResolution(DateField.RESOLUTION_DAY)
        //date1.setResolution(DateField.RESOLUTION_MINUTE)
        date2.setValue(null)
        date2.setValue(new Date())
        date2.setWidth("100%")
        layout.addComponent(date2)

        TextArea drug4 = new TextArea("สรรพคุณ");
        drug4.setRows(5);
        drug4.setImmediate(true);
        drug4.setSizeFull();
        drug4.setInputPrompt("แก้ปวด ลดไข้");
        layout.addComponent(drug4)

        ComboBox select1 = new ComboBox("เด็กเล็ก ครั้งละ")
        select1.setWidth("19em")
        select1.addItem("ไม่ระบุ")
        select1.addItem("ชงกับน้ำร้อน 1 ถ้วย")
        select1.addItem("ทาบนแผลแล้วนวดเบา ๆ")
        select1.addItem("ทาบนแผลแล้วล้างน้ำออก")
        select1.addItem("รับประทานครั้งละ 1 เม็ด")
        select1.addItem("รับประทานครั้งละ 1 ช้อนชา")
        select1.addItem("รับประทานครั้งละ 1 ช้อนโต๊ะ")
        select1.addItem("รับประทานครั้งละ 2 เม็ด")
        select1.addItem("รับประทานครั้งละ 2 ช้อนชา")
        select1.addItem("รับประทานครั้งละ 2 ช้อนโต๊ะ")
        select1.addItem("รับประทานครั้งละ 3 เม็ด")
        select1.addItem("รับประทานครั้งละ 3 ช้อนชา")
        select1.addItem("รับประทานครั้งละ 3 ช้อนโต๊ะ")
        select1.addItem("สูดดมทุกครั้งที่เวียนหัว")
        layout.addComponent(select1)
        layout.setComponentAlignment(select1, Alignment.MIDDLE_LEFT)

        ComboBox select2 = new ComboBox("เด็กโต ครั้งละ")
        select2.setWidth("19em")
        select2.addItem("ไม่ระบุ")
        select2.addItem("ชงกับน้ำร้อน 1 ถ้วย")
        select2.addItem("ทาบนแผลแล้วนวดเบา ๆ")
        select2.addItem("ทาบนแผลแล้วล้างน้ำออก")
        select2.addItem("รับประทานครั้งละ 1 เม็ด")
        select2.addItem("รับประทานครั้งละ 1 ช้อนชา")
        select2.addItem("รับประทานครั้งละ 1 ช้อนโต๊ะ")
        select2.addItem("รับประทานครั้งละ 2 เม็ด")
        select2.addItem("รับประทานครั้งละ 2 ช้อนชา")
        select2.addItem("รับประทานครั้งละ 2 ช้อนโต๊ะ")
        select2.addItem("รับประทานครั้งละ 3 เม็ด")
        select2.addItem("รับประทานครั้งละ 3 ช้อนชา")
        select2.addItem("รับประทานครั้งละ 3 ช้อนโต๊ะ")
        select2.addItem("สูดดมทุกครั้งที่เวียนหัว")
        layout.addComponent(select2)
        layout.setComponentAlignment(select2, Alignment.MIDDLE_LEFT)

        ComboBox select3 = new ComboBox("ผู้ใหญ่ ครั้งละ")
        select3.setWidth("19em")
        select3.addItem("ไม่ระบุ")
        select3.addItem("ชงกับน้ำร้อน 1 ถ้วย")
        select3.addItem("ทาบนแผลแล้วนวดเบา ๆ")
        select3.addItem("ทาบนแผลแล้วล้างน้ำออก")
        select3.addItem("รับประทานครั้งละ 1 เม็ด")
        select3.addItem("รับประทานครั้งละ 1 ช้อนชา")
        select3.addItem("รับประทานครั้งละ 1 ช้อนโต๊ะ")
        select3.addItem("รับประทานครั้งละ 2 เม็ด")
        select3.addItem("รับประทานครั้งละ 2 ช้อนชา")
        select3.addItem("รับประทานครั้งละ 2 ช้อนโต๊ะ")
        select3.addItem("รับประทานครั้งละ 3 เม็ด")
        select3.addItem("รับประทานครั้งละ 3 ช้อนชา")
        select3.addItem("รับประทานครั้งละ 3 ช้อนโต๊ะ")
        select3.addItem("สูดดมทุกครั้งที่เวียนหัว")
        layout.addComponent(select3)
        layout.setComponentAlignment(select3, Alignment.MIDDLE_LEFT)
		
        TextField drug5 = new TextField("เวลาที่ใช้รับประทาน")
        drug5.setWidth("20em")
        drug5.setInputPrompt("วันละ 3 ครั้ง หลังอาหารทันที");
        layout.addComponent(drug5)
        layout.setExpandRatio(drug5, 1.0f)

        Button addButton = new Button("บันทึก")
        layout.addComponent(addButton)
        layout.setComponentAlignment(addButton, Alignment.MIDDLE_LEFT)
        
        addButton.addClickListener({ event -> 
            getControllerdrug().addItem(drug.getValue()
                ,drug1.getValue()
                ,drug3.getValue()
                ,service
                ,date1
                ,date2
                ,drug4.getValue()
                ,select1
                ,select2
                ,select3
                ,drug5.getValue())
        } as Button.ClickListener)

        setCompositionRoot(editPanel)
    }
        private DrugController getControllerdrug() {
        Object c = UI.getCurrent().getSession().getAttribute("controllerdrug")
        return (DrugController)c
    }
}