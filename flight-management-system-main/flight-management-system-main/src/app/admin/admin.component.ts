import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { Inject } from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  public addUserForm!: FormGroup;
  public addFlightDetailsForm!: FormGroup;
  public deleteFlightDetailsForm!: FormGroup;
  public addAirportDetailsForm!: FormGroup;
  displayedColumns: string[] = ['id', 'flightNumber', 'source', 'action'];
  dataSource: any = [];

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private userService: UserService,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.addUserForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      mobile: ['', Validators.required],
      emailId: ['', Validators.required],
      password: ['', Validators.required],
    });

    this.addFlightDetailsForm = this.formBuilder.group({
      id: ['', Validators.required],
      flightNumber: ['', Validators.required],
      source: ['', Validators.required],
      destination: ['', Validators.required],
      date: ['', Validators.required],
      fare: ['', Validators.required],
    });

    this.deleteFlightDetailsForm = this.formBuilder.group({
      flightNumber: ['', Validators.required],
    });
    this.addAirportDetailsForm = this.formBuilder.group({
      airportCode: ['', Validators.required],
      airportLocation: ['', Validators.required],
      airportName: ['', Validators.required],
    });

    this.userService.getAirports().subscribe(
      (response: any) => {
        this.dataSource = response;
      },
      (error: any) => {
        console.log(error.error.text);
      }
    );
  }

  addUser() {
    this.userService.addUser(this.addUserForm.value).subscribe(
      (response: any) => {
        this.openSnackBar('User Added Successfully');
        this.addUserForm.reset();
      },
      (error) => {
        console.log(error);
        this.addUserForm.reset();
      }
    );
  }

  addFlight() {
    let body = this.addFlightDetailsForm.value;
    let d = new Date(body.date);
    console.log(d);
    let month =
      d.getMonth() + 1 < 10 ? '0' + (d.getMonth() + 1) : d.getMonth() + 1;
    body.date = d.getDate() + '-' + month + '-' + d.getFullYear();
    console.log(
      body,
      '-' + (d.getMonth() + 1 < 10)
        ? '0' + (d.getMonth() + 1)
        : d.getMonth() + 1 + '-' + d.getFullYear()
    );
    this.userService.addFlight(body).subscribe(
      (response: any) => {
        this.addFlightDetailsForm.reset();
        this.openSnackBar(response.error.text);
      },
      (error) => {
        this.addFlightDetailsForm.reset();
        this.openSnackBar(error.error.text);
      }
    );
  }

  deleteFlight() {
    this.userService
      .deleteFlight(this.deleteFlightDetailsForm.value.flightNumber)
      .subscribe(
        (response: any) => {
          this.deleteFlightDetailsForm.reset();
          this.openSnackBar('Flight Deleted Successfully');
        },
        (error) => {
          this.deleteFlightDetailsForm.reset();
          this.openSnackBar(error.error.text);
        }
      );
  }

  deleteAirport(data: any) {
    this.userService.deleteAirport(data.airportCode).subscribe(
      (response: any) => {
        this.openSnackBar('Airport Deleted Successfully.');
        this.userService.getAirports().subscribe(
          (response: any) => {
            this.dataSource = response;
          },
          (error: any) => {
            console.log(error);
          }
        );
      },
      (error) => {
        this.openSnackBar(error.error.text);
      }
    );
  }

  addAirport() {
    console.log(this.addAirportDetailsForm.value);
    this.userService.addAirport(this.addAirportDetailsForm.value).subscribe(
      (response: any) => {
        this.addAirportDetailsForm.reset();
        this.openSnackBar('Airport Added Successfully.');
        this.userService.getAirports().subscribe(
          (response: any) => {
            this.dataSource = response;
          },
          (error: any) => {
            console.log(error);
          }
        );
      },
      (error) => {
        this.openSnackBar(error.error.text);
      }
    );
  }

  durationInSeconds = 3;
  openSnackBar(message: String) {
    this._snackBar.openFromComponent(PizzaPartyComponent, {
      data: { message: message },
      duration: this.durationInSeconds * 1000,
      horizontalPosition: 'end',
      verticalPosition: 'top',
    });
  }
}

@Component({
  selector: 'snack-bar-component-example-snack',
  templateUrl: '../booking-status/snackBarComponent.html',
  styles: [
    `
      .example-pizza-party {
        color: hotpink;
      }
    `,
  ],
})
export class PizzaPartyComponent {
  message = this.data.message;
  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: any) {}
}
