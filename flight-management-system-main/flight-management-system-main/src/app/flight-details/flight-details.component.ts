import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';

@Component({
    selector: 'app-flight-details',
    templateUrl: './flight-details.component.html',
    styleUrls: ['./flight-details.component.css']
})
export class FlightDetailsComponent implements OnInit {
    displayedColumns: string[] = ['id', 'flightNumber', 'source', 'destination', 'date', 'fare'];
    dataSource: any = [];
    constructor(private userService: UserService) { }

    ngOnInit(): void {
        this.userService.getFlights().subscribe(
            (response: any) => {
                this.dataSource = response;
            },
            (error: any) => {
                console.log(error.error.text);
            }
        );
    }

}
