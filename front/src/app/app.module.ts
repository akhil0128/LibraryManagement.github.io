import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavbarComponent } from './components/navbar/navbar.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { HttpClientModule } from '@angular/common/http';
import { AdminComponent } from './components/admin/admin.component';
import { BvicamComponent } from './components/bvicam/bvicam.component';
import { BooksComponent } from './components/books/books.component';
import { FormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import { AddstudentComponent } from './components/addstudent/addstudent.component';
import { GetstudentComponent } from './components/getstudent/getstudent.component';
import { IssuebookComponent } from './components/issuebook/issuebook.component';
import { AddbookComponent } from './components/addbook/addbook.component';
import { GetbookComponent } from './components/getbook/getbook.component';
import { ReturnbookComponent } from './components/returnbook/returnbook.component';
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    BooksComponent,
    AdminComponent,
    BvicamComponent,
    AddstudentComponent,
    GetstudentComponent,
    IssuebookComponent,
    AddbookComponent,
    GetbookComponent,
    ReturnbookComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    HttpClientModule,
    FormsModule,
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
