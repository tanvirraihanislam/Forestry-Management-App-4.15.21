import { Component, OnInit } from '@angular/core';
import { Land } from 'src/module/land';
import { LandServiceService } from '../land-service.service';

@Component({
  selector: 'app-land',
  templateUrl: './land.component.html',
  styleUrls: ['./land.component.css']
})
export class LandComponent implements OnInit {

  constructor(private service: LandServiceService) { }

  ngOnInit() {
    this.loadData();
  }

  header: string = "List of Lands";

  lands: Land[];
  message: string = null;
  errorMessage: string = null;

  delete(landId: string): void {
    this.service.deleteLand(landId).subscribe(
      (response) => {
        this.message = response;
        this.loadData();
      },
      (error) => console.log(error)
    );

  }

  loadData(): void {

    this.service.getLands().subscribe(
      (data) => {
        this.lands = data;
        this.errorMessage = null;
      },
      (failResponse) => {
        this.errorMessage = failResponse.error.errorMessage;
      }
    )
  }


}
