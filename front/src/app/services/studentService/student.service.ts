import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StudentService{
 
  private baseurl: string = "http://localhost:9991";

  constructor(private http: HttpClient) { }

  sendData(data: any) {
    return this.http.post(`${this.baseurl}/student`, data)
  }
  getData(studentId:any) {
    return this.http.get(`${this.baseurl}/student/getstudent/${studentId}`)
  }
  issueBookData(studentId:any,bookId:any){
    return this.http.get(`${this.baseurl}/student/issueBook?studentId=${studentId}&bookId=${bookId}`)
  }
  returnBookData(studentId: any, bookId: any) {
    return this.http.get(`${this.baseurl}/student/returnBook?studentId=${studentId}&bookId=${bookId}`)
  }
}