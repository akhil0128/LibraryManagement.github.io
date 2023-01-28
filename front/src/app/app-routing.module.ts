import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './components/admin/admin.component';
import { BvicamComponent } from './components/bvicam/bvicam.component';
import { BooksComponent } from './components/books/books.component';
import { AddstudentComponent } from './components/addstudent/addstudent.component';
import { GetstudentComponent } from './components/getstudent/getstudent.component';
import { IssuebookComponent } from './components/issuebook/issuebook.component';
import { AddbookComponent } from './components/addbook/addbook.component';
import { GetbookComponent } from './components/getbook/getbook.component';
import { ReturnbookComponent } from './components/returnbook/returnbook.component';
const routes: Routes = [
  {
  path:"showAdmin",
  component:AdminComponent,
  pathMatch:"full"
},
{
  path:"showBooks",
  component:BooksComponent,
  pathMatch:"full"
},
{
  path:"issuebook",
  component:IssuebookComponent,
  pathMatch:"full"
},
{
  path:"returnbook",
  component:ReturnbookComponent,
  pathMatch:"full"
},
{
  path:"addstudent",
  component:AddstudentComponent,
  pathMatch:"full"
},
{
  path:"getstudent",
  component:GetstudentComponent,
  pathMatch:"full"
},
{
  path:"addbook",
  component:AddbookComponent,
  pathMatch:"full"
},
{
  path:"getbook",
  component:GetbookComponent,
  pathMatch:"full"
},
{
  path:"showBvicam",
  component:BvicamComponent,
  pathMatch:"full"
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
