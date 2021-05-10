import { Component, OnInit } from '@angular/core';
import { ContractServiceService } from '../contract-service.service';

@Component({
  selector: 'app-create-contract',
  templateUrl: './create-contract.component.html',
  styleUrls: ['./create-contract.component.css']
})
export class CreateContractComponent implements OnInit {

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;

  constructor(private service:ContractServiceService) { }

  ngOnInit() {
  }

  createNew(data) {  
    this.service.addContract(data.contractNumber, data.quotation, data.startDate, 
      data.endDate, data.contractStatus, data.customerId, 
      data.adminId, data.landId).subscribe( // change to data as object
      (message) => {
        console.log(data.contractNumber);
//        console.log(this.service.addContract(data));
        this.successMessage = message;
        this.validationMessages = null;
        this.errorMessage = null;
      },
      (failure) => {
        this.successMessage = null;
        this.validationMessages = JSON.parse(failure.error).errors;
        this.errorMessage = JSON.parse(failure.error).errorMessage;
      }

    )

  }


}
