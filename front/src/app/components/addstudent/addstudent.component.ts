import { Component } from '@angular/core';
import { StudentService } from 'src/app/services/studentService/student.service';

@Component({
  selector: 'app-addstudent',
  templateUrl: './addstudent.component.html',
  styleUrls: ['./addstudent.component.css']
})
export class AddstudentComponent {

  data={
    studentId:"",
	  name:"",
	  emailId:"",
	  phoneNumber:"",
  }

  constructor(private student:StudentService){}

  doSubmitForm(){
    console.log("button clicked");
    console.log(this.data);
    /* call the object */
    this.student.sendData(this.data).subscribe(
      response=>{
        console.log(response);
        alert("Data Submitted");
      },
      error=>{
        console.log(error);
        alert("Data not Submitted");
      }
      )
}
}
