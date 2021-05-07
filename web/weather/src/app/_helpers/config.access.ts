import { ConfigService } from './../services/config.service';


import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';


@Injectable({ providedIn: 'root' })
export class ConfigAccess implements CanActivate {
    constructor(
        private router: Router,
        private cfg: ConfigService
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        // const currentUser = this.authSvc.getCurrentUser();
        const auth = this.cfg.authenticated;
        console.log('Config authenticated: ' , auth);
        if (auth) {
            // console.log("User logged in");
            // logged in so return true
            return true;
        }
        // not logged in so redirect to login page with the return url
        this.router.navigate(['/'], { queryParams: { returnUrl: state.url } });
        return false;
    }
}