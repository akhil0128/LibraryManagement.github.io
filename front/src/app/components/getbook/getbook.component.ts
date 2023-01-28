import { Component } from '@angular/core';
import { BookService } from 'src/app/services/bookService/book.service';

@Component({
  selector: 'app-getbook',
  templateUrl: './getbook.component.html',
  styleUrls: ['./getbook.component.css']
})
export class GetbookComponent {

  bookId:any 
  bookfetched:any

constructor(private book: BookService) { }

getdata(){
  console.log("button clicked");
  console.log(this.bookId);
  this.book.getData(this.bookId).subscribe(
    response=>{
      this.bookfetched=response;
      console.log(this.bookfetched);
    },
    error=>{
      console.log(error.error.message.message);
      alert(error.error.message.message);
    }
  )
}
}