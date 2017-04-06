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
import java.awt.image.*
import java.util.*

/**
 *
 *
 * @author
 */

@Theme("todo")

class MyUI extends UI {
    public VerticalLayout page = new VerticalLayout()
 String getLogin(){
    Object i = UI.getCurrent().getSession().getAttribute("login")
    return i
 }
     PatientController controller = new PatientController()
     DrugController controllerdrug = new DrugController()
     QueueController controllerQueue = new QueueController()
     DispenController controllerDispen = new DispenController()


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        this.getSession().setAttribute("controller", controller)
        this.getSession().setAttribute("controllerdrug", controllerdrug)
        this.getSession().setAttribute("controllerQueue", controllerQueue)
        this.getSession().setAttribute("controllerDispen", controllerDispen)

       
        

        Object i = getLogin()
        VerticalLayout layout = new VerticalLayout()
        layout.setMargin(true)
        layout.setWidth("100%")
        if(i == null){
            VerticalLayout page = home()
            layout.addComponent(page)
            layout.setComponentAlignment(page,Alignment.MIDDLE_CENTER)
        }
        else{
            VerticalLayout page = index()
            layout.addComponent(page)
            layout.setComponentAlignment(page,Alignment.MIDDLE_CENTER)
        }
        setContent(layout)
    }

    protected VerticalLayout home(){
        VerticalLayout layoutlogin = new VerticalLayout()
       // layoutlogin.setMargin(true)
       // layoutlogin.setSpacing(true)

        Label labellogin = new Label("::เข้าสู่ระบบ::")
        Label labelloginh = new Label("ระบบห้องยา")
        ThemeResource resourec = new ThemeResource("img/YY.png")
        Image image = new Image(null, resourec)
        layoutlogin.addComponent(image)
        layoutlogin.addComponent(labelloginh)
        layoutlogin.addComponent(labellogin)
        labelloginh.setStyleName("h2")
        labellogin.setStyleName("h3")

        labelloginh.setSizeUndefined()
        labellogin.setSizeUndefined()
        layoutlogin.setComponentAlignment(image,Alignment.MIDDLE_CENTER)
        layoutlogin.setComponentAlignment(labelloginh,Alignment.MIDDLE_CENTER)
        layoutlogin.setComponentAlignment(labellogin,Alignment.MIDDLE_CENTER)

        ComboBox id = new ComboBox("Username")
          id.setInputPrompt("Username")
        def plist = Phamacist.executeQuery("from Phamacist")
        for(p in plist){
            id.addItem(p)
        }
        layoutlogin.addComponent(id)
        layoutlogin.setExpandRatio(id,1.0f)
        layoutlogin.setComponentAlignment(id,Alignment.MIDDLE_CENTER)

        PasswordField password  = new PasswordField("Password");
        password.setInputPrompt("Password")
        layoutlogin.addComponent(password)
        layoutlogin.setExpandRatio(password,2.0f)
        password.setImmediate(true);
        password.setMaxLength(10);
        layoutlogin.addComponent(password)
        layoutlogin.setComponentAlignment(password,Alignment.MIDDLE_CENTER)


        Button loginButton = new Button("Login")
        layoutlogin.setSpacing(true)
        layoutlogin.addComponent(loginButton)
        layoutlogin.setComponentAlignment(loginButton,Alignment.MIDDLE_CENTER)
    

        loginButton.addClickListener({event ->
                //LoginController r = new LoginController()
                if(pCheck(id.getValue().name,password.getValue())==1){
                    UI.getCurrent().getSession().setAttribute("login",id.getValue())
                        Page.getCurrent().getJavaScript().execute("location.reload()")
                }
                else{
                    new Notification("Please insert correct name or password!",Notification.Type.WARNING_MESSAGE).show(Page.getCurrent())
                }
            }as Button.ClickListener)


        return layoutlogin

    }
    protected VerticalLayout index(){
        VerticalLayout all = new VerticalLayout()
        HorizontalLayout statusbar = new HorizontalLayout()
        //statusbar.setMargin(true)
        //statusbar.setSpacing(true)
        statusbar.setWidth("300px")
        Label user = new Label("::ผู้ใช้งานระบบ:: "+ getLogin())
        user.setStyleName("h3")
        user.setWidth("100%")
        Button signoutbutton = new Button("Log Out")
        signoutbutton.setWidth("100%")
        signoutbutton.addClickListener({ event ->
                UI.getCurrent().getSession().setAttribute("login",null)
                Page.getCurrent().getJavaScript().execute("location.reload()")
            }as Button.ClickListener)
        statusbar.addComponent(user)
        statusbar.addComponent(signoutbutton)
        statusbar.setExpandRatio(user,1.0f)
        statusbar.setExpandRatio(signoutbutton,1.0f)

        statusbar.setComponentAlignment(user,Alignment.MIDDLE_RIGHT)
        statusbar.setComponentAlignment(signoutbutton,Alignment.MIDDLE_RIGHT)
        
        Panel panelsystem = new Panel()
        VerticalLayout layoutsystem = new VerticalLayout()
        panelsystem.setContent(layoutsystem)

        
        TabSheet m = new TabSheet()
        layoutsystem.addComponent(m)

        PatientBoundary hongtae = new PatientBoundary()
        DrugBoundary arm = new DrugBoundary()
        QueueBoundary mos = new QueueBoundary()
        Dispensing bon = new Dispensing()

        VerticalLayout layout1 = mos.queueBoundary()
        VerticalLayout layout2 = hongtae.patientBoundary()
        VerticalLayout layout3 = bon.dispensing()
        VerticalLayout layout4 = arm.drugBoundary()



        m.addTab(layout1,"ระบบแสดง  Queue ")
        m.addTab(layout2,"ระบบบันทึกข้อมูลผู้ป่วย")
        m.addTab(layout3,"ระบบจ่ายยาผู้ป่วย")
        m.addTab(layout4,"ระบบลงทะเบียนเวชภัณฑ์")
        all.addComponent(statusbar)
        all.setComponentAlignment(statusbar,Alignment.MIDDLE_RIGHT)
        all.addComponent(panelsystem)
        return all

    }

    public Integer pCheck(String id,String password){
        def plist = Phamacist.executeQuery("from Phamacist")
        System.out.println("id: "+id+"pass: "+password)
        int i=0
        for(t in plist){
            if(id== t.name && password == t.password){
                i = 1
                new Notification("Login Succesful.","welcome: ${t.name}",
                    Notification.Type.WARNING_MESSAGE).show(Page.getCurrent())
            

            }
            else{
                new Notification("Cannot Login.","Because Password is incorrect", 
                    Notification.Type.WARNING_MESSAGE).show(Page.getCurrent())

            }
        }
        return i

    }


}
