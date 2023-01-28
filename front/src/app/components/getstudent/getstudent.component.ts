import { Component } from '@angular/core';
import { StudentService } from 'src/app/services/studentService/student.service';

@Component({
  selector: 'app-getstudent',
  templateUrl: './getstudent.component.html',
  styleUrls: ['./getstudent.component.css']
})
export class GetstudentComponent {

  studentId:any 
  studentfetched:any

constructor(private student: StudentService) { }

getdata(){
  console.log("button clicked");
  console.log(this.studentId);
  this.student.getData(this.studentId).subscribe(
    response=>{
      this.studentfetched=response;
      console.log(this.studentfetched);
    },
    error=>{
      console.log(error.error.message.message);
      alert(error.error.message.message);
    }
  )
}
}
