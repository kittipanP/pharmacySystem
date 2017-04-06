class BootStrap {

    def init = { servletContext ->
    	//Phamacist
    	new sa.Phamacist(name:"Hongtae",password:"5612970").save()
    	new sa.Phamacist(name:"Arm",password:"1234").save()
    	new sa.Phamacist(name:"Bon",password:"19680").save()
    	new sa.Phamacist(name:"Mos",password:"142536").save()
        //Drug
    	new sa.Drug(drugid:"1111",name:"Paracetamol",amount:"ed",types:"sfaf",expdate:'9999-12-31 23:59:59',mfddate:'9999-12-31 23:59:59',prop:"prop",boy:"wee",dekto:"dekto",men:"men",how:"how").save()
    	//Patient
    	new sa.Patient(identification: "1-1199-00526-45-4" ,status:0).save()

        def p1 = new sa.Patient(
            identification: "1119900513034",
            status:1).save()
        def p2 = new sa.Patient(
            identification: "1119900514534",
            status:1).save()

        def d1 = new sa.Drug
            (drugid:"1111",name:"Paracetamol",amount:"ed",types:"sfaf",expdate:'9999-12-31 23:59:59',mfddate:'9999-12-31 23:59:59',prop:"prop",boy:"wee",dekto:"dekto",men:"men",how:"how").save()


            new sa.Profile(
                id:1,
                fname: "Moss",
                lname: "Handsome",
                sex: "Male",
                age: 20,
                birthday: "4 Jan 1995",
                blood: "B",
                rh: "-",
                status: "single",
                nationality: "Thailand",
                race: "Thai",
                religion: "Buddha",
                career:"Gamer",
                address: "5/265 M.23",
                postcode:"10540",
                tel:"0829763823",
                p:p1 ,
                d:d1).save()

            new sa.Profile(
                id:2,
                fname: "qqq",
                lname: "abc",
                sex: "Female",
                age: 20,
                birthday: "4 Jan 1995",
                blood: "B",
                rh: "-",
                status: "single",
                nationality: "Thailand",
                race: "Thai",
                religion: "Buddha",
                career:"Gamer",
                address: "5/265 M.23",
                postcode:"10540",
                tel:"0829763823",
                p:p2 ,
                d:d1).save()
    	    }
    def destroy = {
    }
}
