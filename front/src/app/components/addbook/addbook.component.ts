import { Component } from '@angular/core';
import { BookService } from 'src/app/services/bookService/book.service';

@Component({
  selector: 'app-addbook',
  templateUrl: './addbook.component.html',
  styleUrls: ['./addbook.component.css']
})
export class AddbookComponent {

  data={
    bookId:"",
	  bookName:"",
	  authorName:"",
	  issueedTo:"",
    issueDate:"",
    availability:""
  }

  constructor(private book:BookService){}

  doSubmitForm(){
    console.log("button clicked");
    console.log(this.data);
    /* call the object */
    this.book.sendData(this.data).subscribe(
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
