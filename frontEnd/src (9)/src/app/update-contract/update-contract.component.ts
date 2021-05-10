import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Contract } from 'src/module/contract';
import { ContractServiceService } from '../contract-service.service';

@Component({
  selector: 'app-update-contract',
  templateUrl: './update-contract.component.html',
  styleUrls: ['./update-contract.component.css']
})
export class UpdateContractComponent implements OnInit {

  contract: Contract=null;

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;

  constructor(private service:ContractServiceService,
              private route: ActivatedRoute, 
              private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(
      (params) => {
        let contractNumber: string = params.get('contractNumber');
        console.log(contractNumber);

        this.service.getContract(contractNumber).subscribe(
          (data) => {
            console.log('abir')
            console.log(this.service.getContract(contractNumber));
            this.contract = data;
            console.log(data);
          },
          (fail) => {
            this.errorMessage = fail.error.errorMessage;
          }
        )
      }
    )
  }

  updated() {
    this.service.updateContract(this.contract).subscribe(
      (message) => {
       this.successMessage=message
      this.validationMessages = null
        this.errorMessage = null
      },
     (failure) => {
      this.successMessage = null;
      this.validationMessages = JSON.parse(failure.error).errors;
      this.errorMessage = JSON.parse(failure.error).errorMessage;
      }

    )

  }

  goBack(){
    this.router.navigate(["contractlist"]);
  }




}
