import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from 'src/module/admin';
import { AdminServiceService } from '../admin-service.service';
import { LandServiceService } from '../land-service.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  contractNumber!: string;
  contractMsg!:string;
  constructor(private service: AdminServiceService) { }

  ngOnInit() {
    this.loadData();
  }
  header: string = "List of Admins";

  admins: Admin[];
  message: string = null;
  errorMessage: string = null;

  delete(adminId: string): void {
    this.service.deleteAdmin(adminId).subscribe(
      (response) => {
        this.message = response;
        this.loadData();
      },
      (error) => console.log(error)
    );



  }

  verifyContract(){
    this.service.verifyContract(this.contractNumber).subscribe((data) => {
      this.contractMsg=data;
     
    },
    (failResponse) => {
      this.contractMsg ="Record Doesn't Exist";
    })
  }

  loadData(): void {

    this.service.getAdmins().subscribe(
      (data) => {
        this.admins = data;
        this.errorMessage = null;
      },
      (failResponse) => {
 //       this.errorMessage = failResponse.error.errorMessage;
      }
    )
  }


 


}


