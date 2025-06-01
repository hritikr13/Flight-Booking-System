import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { MatSnackBar, MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

    public loginForm!: FormGroup;
    durationInSeconds: number = 3;

    constructor(
        private userService: UserService,
        private userAuthService: UserAuthService,
        private router: Router,
        private formBuilder: FormBuilder,
        private _snackBar: MatSnackBar
    ) { }

    ngOnInit(): void {

        this.loginForm = this.formBuilder.group({
            'username': ['', Validators.required],
            'password': ['', Validators.required]
        })

    }


    login() {
        this.userService.login(this.loginForm.value).subscribe(
            (response: any) => {
                this.userAuthService.setUserData(response.user);
                this.userAuthService.setRoles(response.user.roles);
                this.userAuthService.setToken(response.jwtToken);

                const role = response.user.roles[0].roleName;
                if (role === 'Admin') {
                    this.router.navigate([''], { skipLocationChange: true });
                } else {
                    this.router.navigate([''], { skipLocationChange: true });
                }
            },
            (error) => {
                this.openSnackBar('Username or Password is Invalid');
            }
        );
    }
    openSnackBar(message: String) {
        this._snackBar.openFromComponent(PizzaPartyComponent, {
            data: { message: message },
            duration: this.durationInSeconds * 1000,
            horizontalPosition: 'end',
            verticalPosition: 'top'
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
    constructor(@Inject(MAT_SNACK_BAR_DATA) public data: any) { }
}
