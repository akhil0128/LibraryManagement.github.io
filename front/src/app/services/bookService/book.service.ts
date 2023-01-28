import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private baseurl: string = "http://localhost:9999";

  constructor(private http: HttpClient) { }
  sendData(data: any) {
    return this.http.post(`${this.baseurl}/book`, data)
  }
  getData(bookId:any) {
    return this.http.get(`${this.baseurl}/book/getbook/${bookId}`)
  }
 /*  issueBookData(studentId:any,bookId:any){
    return this.http.get(`${this.baseurl}/student/issueBook?studentId=${studentId}&bookId=${bookId}`)
  }
  returnBookData(studentId: any, bookId: any) {
    return this.http.get(`${this.baseurl}/student/returnBook?studentId=${studentId}&bookId=${bookId}`)
  } */
}