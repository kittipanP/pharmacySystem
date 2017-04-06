package app

import com.vaadin.ui.*
import com.vaadin.ui.VerticalLayout
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.Label
import com.vaadin.grails.Grails
import com.vaadin.ui.Table
import com.vaadin.ui.Alignment
import com.vaadin.server.ExternalResource
import com.vaadin.shared.ui.label.ContentMode
import com.vaadin.annotations.Theme
import com.vaadin.ui.ComboBox
import app.*
import app.Patient.*
import sa.*
import com.vaadin.ui.DateField
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




class PatientController {
	ComboBox selectPatient
	Grid gs

	void add_Patient(String patientid){
		

		def list = Patient.executeQuery("from Patient") 
			for(q in list){
				if(q.identification == patientid){
					System.out.println("already have id")
					break
				}
				else{
					Patient p = new Patient()
					p.setIdentification(patientid)
					p.setStatus(0)
					p = p.save()
					System.out.println("added")
					break
				}
			}
			load_patient()
			load_all()

		}

	void add_Profile(String fname,String lname,ComboBox p3,String age,DateField p6,ComboBox p7,ComboBox p8,String status,String nationality,String race,String religion,String career,
		String address,String postcode,String tel,ComboBox drugbox,ComboBox patient){
		Profile profile = new Profile()
		Date birthday = p6.getValue()
		Drug drugs = drugbox.getValue()
		profile.setFname(fname)
		profile.setLname(lname)
		profile.setSex(p3.getValue())
		profile.setAge(age)
		profile.setBirthday(birthday)
		profile.setBlood(p7.getValue())
		profile.setRh(p8.getValue())
		profile.setStatus(status)
		profile.setNationality(nationality)
		profile.setRace(race)
		profile.setReligion(religion)
		profile.setCareer(career)
		profile.setAddress(address)
		profile.setPostcode(postcode)
		profile.setTel(tel)
		profile.setDrugs(drugs)
		profile.setPatients(patient.getValue())



		profile = profile.save()
		def statusset = Patient.get(profile.patients.id)
		statusset.setStatus(1)
		statusset = statusset.save(flush:true)
		if(profile != null){
	        	profile.save(flush:true)
	        //	rs.delete(flush:true)
				new Notification("added complete",
            		Notification.Type.WARNING_MESSAGE).show(Page.getCurrent())
            }


		load_patient()
		load_all()
		}


	void load_patient(){
		selectPatient.removeAllItems()
		def patientlist = Patient.executeQuery("from Patient")
		for(p in patientlist){
			if(p.status == 0){
				selectPatient.addItem(p)
				
			}
		}
	}
	void search_patient(String find){
		def	findlist = Profile.executeQuery("from Profile where p.identification like :f",[f: "%${find}%"])
		gs.getContainerDataSource().removeAllItems()
		for(e in findlist){
			gs.addRow(e.id,e.patients.identification,e.fname,e.lname,e.sex,e.age,e.birthday,e.blood,e.rh,e.drugs.name)
		}

	}
	void load_all(){
		def listall = Profile.executeQuery("from Profile")
		 gs.getContainerDataSource().removeAllItems()
		 for(b in listall){
		 	gs.addRow(b.id,b.patients.identification,b.fname,b.lname,b.sex,b.age,b.birthday,b.blood,b.rh,b.drugs.name)
		 }
		}
	void deleteItem(Integer id){
		String op = gs.getContainerDataSource().getItem(id).getItemProperty("ลำดับผู้ป่วย")
			def rp = Profile.get(op)
			//def rs = Patient.get(op)
			if(rp != null){
	        	rp.delete(flush:true)
	        //	rs.delete(flush:true)
				new Notification("delete complete",
            		Notification.Type.WARNING_MESSAGE).show(Page.getCurrent())
            }
            
		load_all()  
	}

	
}
