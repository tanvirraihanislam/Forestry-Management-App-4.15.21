import { Component, OnInit } from '@angular/core';
import { Contract } from 'src/module/contract';
import { ContractServiceService } from '../contract-service.service';

@Component({
  selector: 'app-contract',
  templateUrl: './contract.component.html',
  styleUrls: ['./contract.component.css']
})
export class ContractComponent implements OnInit {

  constructor(private service: ContractServiceService) { }

  ngOnInit() {
    this.loadData();
  }

  header: string = "List of Contracts";

  contracts: Contract[];
  message: string = null;
  errorMessage: string = null;

  delete(contractNumber: string): void {    
    this.service.deleteContract(contractNumber).subscribe(
      (response) => {
        this.message = response;
        this.loadData();
      },
      (error) => console.log(error)
    );

  }

  loadData(): void {

    this.service.getContracts().subscribe(
      (data) => {
        this.contracts = data;
        this.errorMessage = null;
      },
      (failResponse) => {
        this.errorMessage = failResponse.error.errorMessage;
      }
    )
  }








}
