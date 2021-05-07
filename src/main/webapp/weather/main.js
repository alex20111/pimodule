(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./$$_lazy_route_resource lazy recursive":
/*!******************************************************!*\
  !*** ./$$_lazy_route_resource lazy namespace object ***!
  \******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/_enums/alertsLvl.ts":
/*!*************************************!*\
  !*** ./src/app/_enums/alertsLvl.ts ***!
  \*************************************/
/*! exports provided: AlertLvl */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AlertLvl", function() { return AlertLvl; });
var AlertLvl;
(function (AlertLvl) {
    AlertLvl["WARNING"] = "Warnings";
    AlertLvl["WATCH"] = "Watchs";
    AlertLvl["STATEMENT"] = "Statements";
})(AlertLvl || (AlertLvl = {}));


/***/ }),

/***/ "./src/app/_enums/bckImage.ts":
/*!************************************!*\
  !*** ./src/app/_enums/bckImage.ts ***!
  \************************************/
/*! exports provided: BackImage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "BackImage", function() { return BackImage; });
var BackImage;
(function (BackImage) {
    BackImage[BackImage["RAIN"] = 0] = "RAIN";
    // RAIN_NIGHT = '',
    BackImage[BackImage["CLOUDY"] = 1] = "CLOUDY";
    // CLOUDY_NIGHT = '',
    BackImage[BackImage["SUNNY"] = 2] = "SUNNY";
    // SUNNY_NIGHT = '',
    BackImage[BackImage["THUNDER"] = 3] = "THUNDER";
    // THUNDER_NIGHT = '',
    BackImage[BackImage["SNOW"] = 4] = "SNOW";
    BackImage[BackImage["FOG"] = 5] = "FOG"; // fog and mist
    // SNOW_NIGHT = ''
})(BackImage || (BackImage = {}));


/***/ }),

/***/ "./src/app/_enums/cycle.ts":
/*!*********************************!*\
  !*** ./src/app/_enums/cycle.ts ***!
  \*********************************/
/*! exports provided: Cycle */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Cycle", function() { return Cycle; });
var Cycle;
(function (Cycle) {
    Cycle[Cycle["DAY"] = 0] = "DAY";
    Cycle[Cycle["NIGHT"] = 1] = "NIGHT";
    Cycle[Cycle["NA"] = 2] = "NA";
})(Cycle || (Cycle = {}));


/***/ }),

/***/ "./src/app/_enums/icons.ts":
/*!*********************************!*\
  !*** ./src/app/_enums/icons.ts ***!
  \*********************************/
/*! exports provided: Icons */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Icons", function() { return Icons; });
var Icons;
(function (Icons) {
    Icons["RAIN"] = "Rain";
    Icons["LIGHT_RAIN"] = "Light Rain";
    Icons["MIST"] = "Mist";
    Icons["FOG"] = "Fog";
    Icons["MAINLY_SUNNY"] = "Mainly Sunny";
    Icons["PARTYL_CLOUDY"] = "Partly Cloudy";
    Icons["MOSTLY_CLOUDY"] = "Mostly Cloudy";
    Icons["CLOUDY"] = "Cloudy";
    Icons["SUNNY"] = "Sunny";
    Icons["CLEAR"] = "Clear";
    Icons["LIGHT_SNOW"] = "Light Snow";
    Icons["LIGHT_DRIZZLE"] = "Light Drizzle";
    Icons["MAINLY_CLEAR"] = "Mainly Clear";
    Icons["LIGHT_RAIN_AND_FOG"] = "Light Rain and Fog";
    Icons["LIGHT_RAINSHOWER"] = "Light Rainshower";
    Icons["HAZE"] = "Haze";
    Icons["FOG_PATCHES"] = "Fog Patches";
    Icons["THUNDERSTORM_WITH_LIGHT_RAINSHOWERS"] = "Thunderstorm with light rainshowers";
    Icons["LIGHT_SNOWSHOWER"] = "Light Snowshower";
    Icons["SNOW"] = "Snow";
    Icons["NA"] = "Not observed";
    // mostly forecasts
    Icons["CHANCE_OF_SHOWERS"] = "Chance of showers";
    Icons["A_MIX_OF_SUN_AND_CLOUD"] = "A mix of sun and cloud";
    Icons["PERIODS_OF_RAIN"] = "Periods of rain";
    Icons["SHOWERS"] = "Showers";
    Icons["A_FEW_CLOUDS"] = "A few clouds";
    Icons["CLOUDY_PERIODS"] = "Cloudy periods";
    Icons["RAIN_OR_DRIZZLE"] = "Rain or drizzle";
    Icons["RAIN_AT_TIMES_HEAVY_OR_DRIZZLE"] = "Rain at times heavy or drizzle";
    Icons["RAIN_AT_TIMES_HEAVY"] = "Rain at times heavy";
    Icons["A_FEW_SHOWERS_OR_DRIZZLE"] = "A few showers or drizzle";
    Icons["SHOWERS_OR_DRIZZLE"] = "Showers or drizzle";
    Icons["A_FEW_SHOWERS"] = "A few showers";
    Icons["CLEARING"] = "Clearing";
    Icons["FOG_DISSIPATING"] = "Fog dissipating";
    Icons["INCREASING_CLOUDINESS"] = "Increasing cloudiness";
    Icons["CHANCE_OF_RAIN_SHOWERS_OR_FLURRIES"] = "Chance of rain showers or flurries";
    Icons["CHANCE_OF_FLURRIES"] = "Chance of flurries";
    Icons["SNOW_OR_RAIN"] = "Snow or rain";
    Icons["RAIN_OR_SNOW"] = "Rain or snow";
    Icons["CHANCE_OF_FLURRIES_OR_RAIN_SHOWERS"] = "Chance of flurries or rain showers";
    Icons["FLURRIES_OR_RAIN_SHOWERS"] = "Flurries or rain showers";
    Icons["PERIODS_OF_RAIN_MIXED_WITH_SNOW"] = "Periods of rain mixed with snow";
    Icons["A_FEW_FLURRIES"] = "A few flurries";
    Icons["PERIODS_OF_SNOW"] = "Periods of snow";
    Icons["PERIODS_OF_RAIN_OR_DRIZZLE"] = "Periods of rain or drizzle";
    Icons["CHANCE_OF_DRIZZLE_OR_RAIN"] = "Chance of drizzle or rain";
    Icons["OVERCAST"] = "Overcast";
})(Icons || (Icons = {}));


/***/ }),

/***/ "./src/app/_helpers/HttpHelper.ts":
/*!****************************************!*\
  !*** ./src/app/_helpers/HttpHelper.ts ***!
  \****************************************/
/*! exports provided: delayedRetry */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "delayedRetry", function() { return delayedRetry; });
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm2015/index.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm2015/operators/index.js");


function delayedRetry(delayMs, maxRetry) {
    let retries = maxRetry;
    return (src) => src.pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_1__["retryWhen"])((errors) => errors.pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_1__["delay"])(delayMs), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_1__["mergeMap"])(error => retries-- > 0 ? Object(rxjs__WEBPACK_IMPORTED_MODULE_0__["of"])(error) : Object(rxjs__WEBPACK_IMPORTED_MODULE_0__["throwError"])(maxRetry)))));
}


/***/ }),

/***/ "./src/app/_helpers/IconHelper.ts":
/*!****************************************!*\
  !*** ./src/app/_helpers/IconHelper.ts ***!
  \****************************************/
/*! exports provided: getIconsFromString */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getIconsFromString", function() { return getIconsFromString; });
/* harmony import */ var _enums_icons__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./../_enums/icons */ "./src/app/_enums/icons.ts");

// get the string name from the Icons to fetch the file
function getIconsFromString(iconStringName) {
    let returnedIcon;
    // tslint:disable-next-line: forin
    for (const icon in _enums_icons__WEBPACK_IMPORTED_MODULE_0__["Icons"]) {
        const ico = _enums_icons__WEBPACK_IMPORTED_MODULE_0__["Icons"][icon];
        if (iconStringName.toUpperCase() === ico.toUpperCase()) {
            returnedIcon = _enums_icons__WEBPACK_IMPORTED_MODULE_0__["Icons"][icon];
            break;
        }
        // console.log("values--> ", iconStringName, icon, ico, returnedIcon);
    }
    if (returnedIcon == null) {
        returnedIcon = _enums_icons__WEBPACK_IMPORTED_MODULE_0__["Icons"].NA;
    }
    return returnedIcon;
}


/***/ }),

/***/ "./src/app/_helpers/config.access.ts":
/*!*******************************************!*\
  !*** ./src/app/_helpers/config.access.ts ***!
  \*******************************************/
/*! exports provided: ConfigAccess */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ConfigAccess", function() { return ConfigAccess; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/__ivy_ngcc__/fesm2015/router.js");
/* harmony import */ var _services_config_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./../services/config.service */ "./src/app/services/config.service.ts");




class ConfigAccess {
    constructor(router, cfg) {
        this.router = router;
        this.cfg = cfg;
    }
    canActivate(route, state) {
        // const currentUser = this.authSvc.getCurrentUser();
        const auth = this.cfg.authenticated;
        console.log('Config authenticated: ', auth);
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
ConfigAccess.ɵfac = function ConfigAccess_Factory(t) { return new (t || ConfigAccess)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_services_config_service__WEBPACK_IMPORTED_MODULE_2__["ConfigService"])); };
ConfigAccess.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({ token: ConfigAccess, factory: ConfigAccess.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](ConfigAccess, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"],
        args: [{ providedIn: 'root' }]
    }], function () { return [{ type: _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"] }, { type: _services_config_service__WEBPACK_IMPORTED_MODULE_2__["ConfigService"] }]; }, null); })();


/***/ }),

/***/ "./src/app/_helpers/number.validator.ts":
/*!**********************************************!*\
  !*** ./src/app/_helpers/number.validator.ts ***!
  \**********************************************/
/*! exports provided: numberValidator */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "numberValidator", function() { return numberValidator; });
// custom validator to check that the input is a number
function numberValidator(controlName) {
    return (formGroup) => {
        const control = formGroup.controls[controlName];
        if (control.errors && !control.errors.mustBeNumber) {
            // return if another validator has already found an error on the number
            return;
        }
        const nbr = control.value;
        if (!nbr.toString().match(/^[0-9]+(\.?[0-9]+)?$/)) {
            control.setErrors({ mustBeNumber: true });
        }
        else {
            control.setErrors(null);
        }
    };
}


/***/ }),

/***/ "./src/app/_model/constants.ts":
/*!*************************************!*\
  !*** ./src/app/_model/constants.ts ***!
  \*************************************/
/*! exports provided: Constants */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Constants", function() { return Constants; });
class Constants {
}
Constants.FULL_SCREEN_INDX = 98;
Constants.BACK_INDX = 99;
Constants.LOG_FILE_NAME_STORAGE = 'logFileName';
Constants.WEATHER_LOG_FILENAME = 'WeatherLog';


/***/ }),

/***/ "./src/app/_model/weatherError.ts":
/*!****************************************!*\
  !*** ./src/app/_model/weatherError.ts ***!
  \****************************************/
/*! exports provided: WeatherError */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "WeatherError", function() { return WeatherError; });
class WeatherError {
    constructor(key, title, message) {
        this.key = key;
        this.title = title;
        this.message = message;
    }
}


/***/ }),

/***/ "./src/app/app-routing.module.ts":
/*!***************************************!*\
  !*** ./src/app/app-routing.module.ts ***!
  \***************************************/
/*! exports provided: AppRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function() { return AppRoutingModule; });
/* harmony import */ var _helpers_config_access__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./_helpers/config.access */ "./src/app/_helpers/config.access.ts");
/* harmony import */ var _weather_config_weather_config_component__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./weather-config/weather-config.component */ "./src/app/weather-config/weather-config.component.ts");
/* harmony import */ var _weather_alert_weather_alert_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./weather-alert/weather-alert.component */ "./src/app/weather-alert/weather-alert.component.ts");
/* harmony import */ var _weather_weather_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./weather/weather.component */ "./src/app/weather/weather.component.ts");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/__ivy_ngcc__/fesm2015/router.js");
/* harmony import */ var _weather_extended_weather_extended_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./weather-extended/weather-extended.component */ "./src/app/weather-extended/weather-extended.component.ts");









const routes = [
    { path: '', component: _weather_weather_component__WEBPACK_IMPORTED_MODULE_3__["WeatherComponent"] },
    { path: 'extForecast', component: _weather_extended_weather_extended_component__WEBPACK_IMPORTED_MODULE_6__["WeatherExtendedComponent"] },
    { path: 'alert', component: _weather_alert_weather_alert_component__WEBPACK_IMPORTED_MODULE_2__["WeatherAlertComponent"] },
    { path: 'config', component: _weather_config_weather_config_component__WEBPACK_IMPORTED_MODULE_1__["WeatherConfigComponent"], canActivate: [_helpers_config_access__WEBPACK_IMPORTED_MODULE_0__["ConfigAccess"]] },
];
class AppRoutingModule {
}
AppRoutingModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵdefineNgModule"]({ type: AppRoutingModule });
AppRoutingModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵdefineInjector"]({ factory: function AppRoutingModule_Factory(t) { return new (t || AppRoutingModule)(); }, imports: [[_angular_router__WEBPACK_IMPORTED_MODULE_5__["RouterModule"].forRoot(routes)], _angular_router__WEBPACK_IMPORTED_MODULE_5__["RouterModule"]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵsetNgModuleScope"](AppRoutingModule, { imports: [_angular_router__WEBPACK_IMPORTED_MODULE_5__["RouterModule"]], exports: [_angular_router__WEBPACK_IMPORTED_MODULE_5__["RouterModule"]] }); })();
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵsetClassMetadata"](AppRoutingModule, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_4__["NgModule"],
        args: [{
                imports: [_angular_router__WEBPACK_IMPORTED_MODULE_5__["RouterModule"].forRoot(routes)],
                exports: [_angular_router__WEBPACK_IMPORTED_MODULE_5__["RouterModule"]]
            }]
    }], null, null); })();


/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _model_constants__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./_model/constants */ "./src/app/_model/constants.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/__ivy_ngcc__/fesm2015/router.js");




class AppComponent {
    constructor() {
        this.title = 'weather';
        // get from local storage to see if we have stored a logFileName.
        const logFileName = localStorage.getItem(_model_constants__WEBPACK_IMPORTED_MODULE_1__["Constants"].LOG_FILE_NAME_STORAGE);
        if (!logFileName) {
            const logF = `AngWeatherLog${Math.floor((Math.random() * 1000) + 1)}`;
            localStorage.setItem(_model_constants__WEBPACK_IMPORTED_MODULE_1__["Constants"].LOG_FILE_NAME_STORAGE, logF);
            _model_constants__WEBPACK_IMPORTED_MODULE_1__["Constants"].WEATHER_LOG_FILENAME = logF;
        }
        else {
            _model_constants__WEBPACK_IMPORTED_MODULE_1__["Constants"].WEATHER_LOG_FILENAME = logFileName;
        }
        console.log('Log name: ', _model_constants__WEBPACK_IMPORTED_MODULE_1__["Constants"].WEATHER_LOG_FILENAME);
    }
    ngOnInit() {
    }
}
AppComponent.ɵfac = function AppComponent_Factory(t) { return new (t || AppComponent)(); };
AppComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: AppComponent, selectors: [["app-root"]], decls: 1, vars: 0, template: function AppComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](0, "router-outlet");
    } }, directives: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterOutlet"]], styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2FwcC5jb21wb25lbnQuY3NzIn0= */"] });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](AppComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
        args: [{
                selector: 'app-root',
                templateUrl: './app.component.html',
                styleUrls: ['./app.component.css']
            }]
    }], function () { return []; }, null); })();


/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/__ivy_ngcc__/fesm2015/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/__ivy_ngcc__/fesm2015/forms.js");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./app-routing.module */ "./src/app/app-routing.module.ts");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ng-bootstrap/ng-bootstrap */ "./node_modules/@ng-bootstrap/ng-bootstrap/__ivy_ngcc__/fesm2015/ng-bootstrap.js");
/* harmony import */ var _weather_weather_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./weather/weather.component */ "./src/app/weather/weather.component.ts");
/* harmony import */ var _weather_extended_weather_extended_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./weather-extended/weather-extended.component */ "./src/app/weather-extended/weather-extended.component.ts");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/__ivy_ngcc__/fesm2015/http.js");
/* harmony import */ var _toast_toast_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./toast/toast.component */ "./src/app/toast/toast.component.ts");
/* harmony import */ var _weather_alert_weather_alert_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./weather-alert/weather-alert.component */ "./src/app/weather-alert/weather-alert.component.ts");
/* harmony import */ var _weather_config_weather_config_component__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ./weather-config/weather-config.component */ "./src/app/weather-config/weather-config.component.ts");
/* harmony import */ var _password_modal_password_modal_component__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ./password-modal/password-modal.component */ "./src/app/password-modal/password-modal.component.ts");
/* harmony import */ var _fortawesome_angular_fontawesome__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! @fortawesome/angular-fontawesome */ "./node_modules/@fortawesome/angular-fontawesome/__ivy_ngcc__/fesm2015/angular-fontawesome.js");















class AppModule {
}
AppModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineNgModule"]({ type: AppModule, bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_4__["AppComponent"]] });
AppModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineInjector"]({ factory: function AppModule_Factory(t) { return new (t || AppModule)(); }, providers: [], imports: [[
            _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
            _app_routing_module__WEBPACK_IMPORTED_MODULE_3__["AppRoutingModule"],
            _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_5__["NgbModule"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_8__["HttpClientModule"],
            _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormsModule"],
            _angular_forms__WEBPACK_IMPORTED_MODULE_2__["ReactiveFormsModule"],
            _fortawesome_angular_fontawesome__WEBPACK_IMPORTED_MODULE_13__["FontAwesomeModule"]
        ]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵsetNgModuleScope"](AppModule, { declarations: [_app_component__WEBPACK_IMPORTED_MODULE_4__["AppComponent"],
        _weather_weather_component__WEBPACK_IMPORTED_MODULE_6__["WeatherComponent"],
        _weather_extended_weather_extended_component__WEBPACK_IMPORTED_MODULE_7__["WeatherExtendedComponent"],
        _toast_toast_component__WEBPACK_IMPORTED_MODULE_9__["ToastComponent"],
        _weather_alert_weather_alert_component__WEBPACK_IMPORTED_MODULE_10__["WeatherAlertComponent"],
        _weather_config_weather_config_component__WEBPACK_IMPORTED_MODULE_11__["WeatherConfigComponent"],
        _password_modal_password_modal_component__WEBPACK_IMPORTED_MODULE_12__["PasswordModalComponent"]], imports: [_angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
        _app_routing_module__WEBPACK_IMPORTED_MODULE_3__["AppRoutingModule"],
        _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_5__["NgbModule"],
        _angular_common_http__WEBPACK_IMPORTED_MODULE_8__["HttpClientModule"],
        _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormsModule"],
        _angular_forms__WEBPACK_IMPORTED_MODULE_2__["ReactiveFormsModule"],
        _fortawesome_angular_fontawesome__WEBPACK_IMPORTED_MODULE_13__["FontAwesomeModule"]] }); })();
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵsetClassMetadata"](AppModule, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"],
        args: [{
                declarations: [
                    _app_component__WEBPACK_IMPORTED_MODULE_4__["AppComponent"],
                    _weather_weather_component__WEBPACK_IMPORTED_MODULE_6__["WeatherComponent"],
                    _weather_extended_weather_extended_component__WEBPACK_IMPORTED_MODULE_7__["WeatherExtendedComponent"],
                    _toast_toast_component__WEBPACK_IMPORTED_MODULE_9__["ToastComponent"],
                    _weather_alert_weather_alert_component__WEBPACK_IMPORTED_MODULE_10__["WeatherAlertComponent"],
                    _weather_config_weather_config_component__WEBPACK_IMPORTED_MODULE_11__["WeatherConfigComponent"],
                    _password_modal_password_modal_component__WEBPACK_IMPORTED_MODULE_12__["PasswordModalComponent"]
                ],
                imports: [
                    _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
                    _app_routing_module__WEBPACK_IMPORTED_MODULE_3__["AppRoutingModule"],
                    _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_5__["NgbModule"],
                    _angular_common_http__WEBPACK_IMPORTED_MODULE_8__["HttpClientModule"],
                    _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormsModule"],
                    _angular_forms__WEBPACK_IMPORTED_MODULE_2__["ReactiveFormsModule"],
                    _fortawesome_angular_fontawesome__WEBPACK_IMPORTED_MODULE_13__["FontAwesomeModule"]
                ],
                providers: [],
                bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_4__["AppComponent"]]
            }]
    }], null, null); })();


/***/ }),

/***/ "./src/app/password-modal/password-modal.component.ts":
/*!************************************************************!*\
  !*** ./src/app/password-modal/password-modal.component.ts ***!
  \************************************************************/
/*! exports provided: PasswordModalComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PasswordModalComponent", function() { return PasswordModalComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/__ivy_ngcc__/fesm2015/forms.js");
/* harmony import */ var _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ng-bootstrap/ng-bootstrap */ "./node_modules/@ng-bootstrap/ng-bootstrap/__ivy_ngcc__/fesm2015/ng-bootstrap.js");
/* harmony import */ var _services_config_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/config.service */ "./src/app/services/config.service.ts");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/__ivy_ngcc__/fesm2015/common.js");







function PasswordModalComponent_p_13_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "p", 13);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](ctx_r0.error);
} }
class PasswordModalComponent {
    constructor(formBuilder, activeModal, cfg) {
        this.formBuilder = formBuilder;
        this.activeModal = activeModal;
        this.cfg = cfg;
    }
    ngOnInit() {
        this.passwordForm = this.formBuilder.group({
            frm_password: ['', [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].minLength(2)]]
        });
    }
    submitForm() {
        this.error = undefined;
        const validPass = this.cfg.passwordValid(this.passwordForm.value.frm_password);
        if (validPass) {
            this.cfg.authenticated = true;
            this.activeModal.close('ValidPassword');
        }
        else {
            this.error = 'Password invalid';
        }
    }
}
PasswordModalComponent.ɵfac = function PasswordModalComponent_Factory(t) { return new (t || PasswordModalComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormBuilder"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_2__["NgbActiveModal"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_services_config_service__WEBPACK_IMPORTED_MODULE_3__["ConfigService"])); };
PasswordModalComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: PasswordModalComponent, selectors: [["app-password-modal"]], decls: 17, vars: 3, consts: [[1, "modal-header"], [1, "modal-title"], ["type", "button", "aria-label", "Close", 1, "close", 3, "click"], [1, "fas", "fa-times"], [3, "formGroup", "submit"], [1, "modal-boy"], [1, "container"], [1, "form-group"], ["for", "groupName"], ["id", "groupName", "type", "text", "formControlName", "frm_password", 1, "form-control"], ["class", "alert alert-danger", 4, "ngIf"], [1, "modal-footer"], [1, "btn", "btn-success", 3, "disabled"], [1, "alert", "alert-danger"]], template: function PasswordModalComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "h4", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](2, "Config password");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "button", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function PasswordModalComponent_Template_button_click_3_listener() { return ctx.activeModal.dismiss("Cross click"); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "i", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](5, "X");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](6, "form", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("submit", function PasswordModalComponent_Template_form_submit_6_listener() { return ctx.submitForm(); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](7, "div", 5);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](8, "div", 6);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](9, "div", 7);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](10, "label", 8);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](11, "Password");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](12, "input", 9);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](13, PasswordModalComponent_p_13_Template, 2, 1, "p", 10);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](14, "div", 11);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](15, "button", 12);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](16, " Next ");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](6);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("formGroup", ctx.passwordForm);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](7);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.error);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("disabled", !ctx.passwordForm.valid);
    } }, directives: [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["ɵangular_packages_forms_forms_y"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["NgControlStatusGroup"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormGroupDirective"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["DefaultValueAccessor"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["NgControlStatus"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormControlName"], _angular_common__WEBPACK_IMPORTED_MODULE_4__["NgIf"]], styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3Bhc3N3b3JkLW1vZGFsL3Bhc3N3b3JkLW1vZGFsLmNvbXBvbmVudC5jc3MifQ== */"] });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](PasswordModalComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
        args: [{
                selector: 'app-password-modal',
                templateUrl: './password-modal.component.html',
                styleUrls: ['./password-modal.component.css']
            }]
    }], function () { return [{ type: _angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormBuilder"] }, { type: _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_2__["NgbActiveModal"] }, { type: _services_config_service__WEBPACK_IMPORTED_MODULE_3__["ConfigService"] }]; }, null); })();


/***/ }),

/***/ "./src/app/services/config.service.ts":
/*!********************************************!*\
  !*** ./src/app/services/config.service.ts ***!
  \********************************************/
/*! exports provided: City, Config, ConfigService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "City", function() { return City; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Config", function() { return Config; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ConfigService", function() { return ConfigService; });
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm2015/index.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/__ivy_ngcc__/fesm2015/http.js");




class City {
}
class Config {
    constructor(currentCity, refreshTime) {
        this.currentCity = currentCity;
        this.refreshTime = refreshTime;
    }
    setCurrentCity(city) {
        this.currentCity = city;
    }
    setRefreshTime(time) {
        this.refreshTime = time; // (in minutes)
    }
}
class ConfigService {
    constructor(http) {
        this.http = http;
        this.configPass = 'kitty12345';
        this.isConfigUpdated = false;
        this.authenticated = false;
        // check for any stored city
        const config = JSON.parse(localStorage.getItem('weatherCity'));
        if (config === undefined || config == null) {
            this.configFields = new Config('on-118', 10);
        }
        else {
            this.configFields = new Config(config.currentCity, config.refreshTime);
        }
        this.weatherCity = new rxjs__WEBPACK_IMPORTED_MODULE_0__["BehaviorSubject"]([]);
        this.configUpdate = new rxjs__WEBPACK_IMPORTED_MODULE_0__["BehaviorSubject"](this.configFields);
    }
    passwordValid(pass) {
        return this.configPass === pass;
    }
    getEnvCanCity() {
        if (this.weatherCity.getValue().length === 0) {
            console.log('getFromWeb');
            // const options = { responseType: 'text' as 'text' };
            // return cities , but first pipe it and map it to save it to cache.
            this.http.get('http://192.168.1.110:8081/web/temperature/envCanCities').subscribe(result => {
                // console.log("Result: " , result);
                const cc = result;
                this.weatherCity.next(cc);
            }, error => {
                console.log('erorr in config,', error);
            });
        }
        return this.weatherCity.asObservable();
    }
    getCurrentCity() {
        return this.configFields.currentCity;
    }
    getRefreshTimeMinutes() {
        return this.configFields.refreshTime;
    }
    getRefreshTimeMillis() {
        return this.configFields.refreshTime * 1000 * 60;
    }
    configUpdated() {
        return this.configUpdate;
    }
    updateConfig(currentCity, refreshTime) {
        if (this.configFields.currentCity !== currentCity) {
            this.configFields.setCurrentCity(currentCity);
            this.isConfigUpdated = true;
        }
        if (this.configFields.refreshTime !== refreshTime) {
            this.configFields.setRefreshTime(refreshTime);
            this.isConfigUpdated = true;
        }
        if (this.configUpdate) {
            console.log('this.configFields', this.configFields);
            localStorage.setItem('weatherCity', JSON.stringify(this.configFields));
            this.configUpdate.next(this.configFields);
        }
    }
}
ConfigService.ɵfac = function ConfigService_Factory(t) { return new (t || ConfigService)(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"])); };
ConfigService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineInjectable"]({ token: ConfigService, factory: ConfigService.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵsetClassMetadata"](ConfigService, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"],
        args: [{
                providedIn: 'root'
            }]
    }], function () { return [{ type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"] }]; }, null); })();


/***/ }),

/***/ "./src/app/services/day-night-cycle.service.ts":
/*!*****************************************************!*\
  !*** ./src/app/services/day-night-cycle.service.ts ***!
  \*****************************************************/
/*! exports provided: DayNightCycleService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DayNightCycleService", function() { return DayNightCycleService; });
/* harmony import */ var _enums_cycle__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./../_enums/cycle */ "./src/app/_enums/cycle.ts");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm2015/index.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm2015/operators/index.js");
/* harmony import */ var _helpers_HttpHelper__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../_helpers/HttpHelper */ "./src/app/_helpers/HttpHelper.ts");
/* harmony import */ var _model_weatherError__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../_model/weatherError */ "./src/app/_model/weatherError.ts");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/__ivy_ngcc__/fesm2015/http.js");
/* harmony import */ var _config_service__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./config.service */ "./src/app/services/config.service.ts");









class DayNightCycleService {
    constructor(http, configService) {
        this.http = http;
        this.configService = configService;
        this.configService.configUpdated().subscribe(newConfig => {
            this.dayNightCycleTimer();
        });
    }
    // timer that  get the weather every 5 minutes.
    dayNightCycleTimer() {
        if (this.cycleTimer) {
            this.cycleTimer.unsubscribe();
        }
        this.cycleTimer = Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["timer"])(1000, this.configService.getRefreshTimeMillis()).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["flatMap"])(() => this.fetchCycle())).subscribe(cycleData => {
            console.log('cycle data: ', cycleData);
            if (cycleData.cycle === 'DAY') {
                this.cycle = _enums_cycle__WEBPACK_IMPORTED_MODULE_0__["Cycle"].DAY;
            }
            else if (cycleData.cycle === 'NIGHT') {
                this.cycle = _enums_cycle__WEBPACK_IMPORTED_MODULE_0__["Cycle"].NIGHT;
            }
            else {
                this.cycle = _enums_cycle__WEBPACK_IMPORTED_MODULE_0__["Cycle"].NA;
            }
        }, cycleError => {
            this.cycle = _enums_cycle__WEBPACK_IMPORTED_MODULE_0__["Cycle"].NA;
            console.log('cycleError svc', cycleError);
        });
    }
    fetchCycle() {
        return this.http.get('http://192.168.1.110:8081/web/date/dayNightCycle/45.41117/-75.69812').pipe(Object(_helpers_HttpHelper__WEBPACK_IMPORTED_MODULE_4__["delayedRetry"])(5000, 5), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])(err => {
            const wError = new _model_weatherError__WEBPACK_IMPORTED_MODULE_5__["WeatherError"]('Retries', 'Retries failure', 'Number of retries exceeded for day night cycle. Cannot connect to server ');
            return rxjs__WEBPACK_IMPORTED_MODULE_2__["EMPTY"];
        }));
    }
}
DayNightCycleService.ɵfac = function DayNightCycleService_Factory(t) { return new (t || DayNightCycleService)(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_6__["HttpClient"]), _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵinject"](_config_service__WEBPACK_IMPORTED_MODULE_7__["ConfigService"])); };
DayNightCycleService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineInjectable"]({ token: DayNightCycleService, factory: DayNightCycleService.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵsetClassMetadata"](DayNightCycleService, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"],
        args: [{
                providedIn: 'root'
            }]
    }], function () { return [{ type: _angular_common_http__WEBPACK_IMPORTED_MODULE_6__["HttpClient"] }, { type: _config_service__WEBPACK_IMPORTED_MODULE_7__["ConfigService"] }]; }, null); })();


/***/ }),

/***/ "./src/app/services/image-service.service.ts":
/*!***************************************************!*\
  !*** ./src/app/services/image-service.service.ts ***!
  \***************************************************/
/*! exports provided: ImageMap, ImageService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ImageMap", function() { return ImageMap; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ImageService", function() { return ImageService; });
/* harmony import */ var _enums_cycle__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./../_enums/cycle */ "./src/app/_enums/cycle.ts");
/* harmony import */ var _enums_bckImage__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./../_enums/bckImage */ "./src/app/_enums/bckImage.ts");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _enums_icons__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../_enums/icons */ "./src/app/_enums/icons.ts");
/* harmony import */ var _helpers_IconHelper__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./../_helpers/IconHelper */ "./src/app/_helpers/IconHelper.ts");
/* harmony import */ var _day_night_cycle_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./day-night-cycle.service */ "./src/app/services/day-night-cycle.service.ts");







class ImageMap {
    constructor(imageNameDay, imageNameNight) {
        this.imageNameDay = imageNameDay;
        this.imageNameNight = imageNameNight;
    }
    getImage(cycle) {
        return cycle === _enums_cycle__WEBPACK_IMPORTED_MODULE_0__["Cycle"].NIGHT ? this.imageNameNight : this.imageNameDay;
    }
}
class ImageService {
    constructor(cycleService) {
        this.cycleService = cycleService;
        this.pathToIcon = 'assets/icon/';
        this.pathToBackground = 'assets/bck/';
        // build map for Env canada
        this.buildIconMap();
        this.buildBackgroundMap();
    }
    getWeatherIcon(weather) {
        /* return the weather icon based on the weather condition. Looking for keywords */
        const icn = this.envCanWeatherIconMap.get(Object(_helpers_IconHelper__WEBPACK_IMPORTED_MODULE_4__["getIconsFromString"])(weather)).getImage(this.cycleService.cycle);
        return this.pathToIcon.concat(icn);
    }
    getIconsForForecast(forecasts) {
        for (const f of forecasts) {
            if (f.dayOfWeek.search('night') !== -1) {
                f.iconLink = this.pathToIcon + this.envCanWeatherIconMap.get(Object(_helpers_IconHelper__WEBPACK_IMPORTED_MODULE_4__["getIconsFromString"])(f.weather)).getImage(_enums_cycle__WEBPACK_IMPORTED_MODULE_0__["Cycle"].NIGHT);
            }
            else {
                f.iconLink = this.pathToIcon + this.envCanWeatherIconMap.get(Object(_helpers_IconHelper__WEBPACK_IMPORTED_MODULE_4__["getIconsFromString"])(f.weather)).getImage(_enums_cycle__WEBPACK_IMPORTED_MODULE_0__["Cycle"].DAY);
            }
        }
        return forecasts;
    }
    getBackgroundImage(weather) {
        let image = '';
        if (weather.search('Rain') !== -1) {
            image = this.envCanBackgroundImgMap.get(_enums_bckImage__WEBPACK_IMPORTED_MODULE_1__["BackImage"].RAIN).getImage(this.cycleService.cycle);
        }
        else if (weather.search('Sunny') !== -1) {
            image = this.envCanBackgroundImgMap.get(_enums_bckImage__WEBPACK_IMPORTED_MODULE_1__["BackImage"].SUNNY).getImage(this.cycleService.cycle);
        }
        else if (weather.search('Cloudy') !== -1) {
            image = this.envCanBackgroundImgMap.get(_enums_bckImage__WEBPACK_IMPORTED_MODULE_1__["BackImage"].CLOUDY).getImage(this.cycleService.cycle);
        }
        else if (weather.search('Thunder') !== -1) {
            image = this.envCanBackgroundImgMap.get(_enums_bckImage__WEBPACK_IMPORTED_MODULE_1__["BackImage"].THUNDER).getImage(this.cycleService.cycle);
        }
        else if (weather.search('Snow') !== -1) {
            image = this.envCanBackgroundImgMap.get(_enums_bckImage__WEBPACK_IMPORTED_MODULE_1__["BackImage"].SNOW).getImage(this.cycleService.cycle);
        }
        else if (weather.search('Fog') !== -1 || weather.search('Mist') !== -1) {
            image = this.envCanBackgroundImgMap.get(_enums_bckImage__WEBPACK_IMPORTED_MODULE_1__["BackImage"].FOG).getImage(this.cycleService.cycle);
        }
        else {
            image = this.envCanBackgroundImgMap.get(_enums_bckImage__WEBPACK_IMPORTED_MODULE_1__["BackImage"].SUNNY).getImage(this.cycleService.cycle);
        }
        // console.log('getBackgroundImage: ', this.pathToBackground, image, weather);
        return this.pathToBackground.concat(image);
    }
    buildBackgroundMap() {
        this.envCanBackgroundImgMap = new Map([
            [_enums_bckImage__WEBPACK_IMPORTED_MODULE_1__["BackImage"].CLOUDY, new ImageMap('cloudy-day.jpg', 'cloudy-night.jpg')],
            [_enums_bckImage__WEBPACK_IMPORTED_MODULE_1__["BackImage"].SUNNY, new ImageMap('sunny.jpg', 'clear-night.jpg')],
            [_enums_bckImage__WEBPACK_IMPORTED_MODULE_1__["BackImage"].THUNDER, new ImageMap('thunder-day.jpg', 'thunder-night.jpg')],
            [_enums_bckImage__WEBPACK_IMPORTED_MODULE_1__["BackImage"].SNOW, new ImageMap('snowing-day.jpg', 'snow-night.jpg')],
            [_enums_bckImage__WEBPACK_IMPORTED_MODULE_1__["BackImage"].RAIN, new ImageMap('rain.jpg', 'rain-night.jpg')],
            [_enums_bckImage__WEBPACK_IMPORTED_MODULE_1__["BackImage"].FOG, new ImageMap('fog-day.jpg', 'fog-night.jpg')],
        ]);
    }
    buildIconMap() {
        // build map of env canada weather summary to their respective icons file name
        this.envCanWeatherIconMap = new Map([
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].NA, new ImageMap('not-available.png', 'not-available.png')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].CLOUDY, new ImageMap('cloudy.svg', 'cloudy.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].MOSTLY_CLOUDY, new ImageMap('cloudy-day-3.svg', 'cloudy-night-3.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].SUNNY, new ImageMap('day.svg', 'night.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].MAINLY_SUNNY, new ImageMap('cloudy-day-1.svg', 'cloudy-night-1.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].FOG, new ImageMap('cloudy.svg', 'cloudy.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].LIGHT_RAIN, new ImageMap('rainy-4.svg', 'rainy-4.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].LIGHT_SNOW, new ImageMap('snowy-4.svg', 'snowy-4.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].MIST, new ImageMap('cloudy.svg', 'cloudy.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].PARTYL_CLOUDY, new ImageMap('cloudy-day-2.svg', 'cloudy-night-2.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].RAIN, new ImageMap('rainy-5.svg', 'rainy-5.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].CLEAR, new ImageMap('day.svg', 'night.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].CHANCE_OF_SHOWERS, new ImageMap('rainy-2.svg', 'rainy-5.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].A_MIX_OF_SUN_AND_CLOUD, new ImageMap('cloudy-day-2.svg', 'cloudy-night-2.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].PERIODS_OF_RAIN, new ImageMap('rainy-5.svg', 'rainy-5.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].SHOWERS, new ImageMap('rainy-6.svg', 'rainy-6.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].A_FEW_CLOUDS, new ImageMap('cloudy-day-1.svg', 'cloudy-night-1.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].CLOUDY_PERIODS, new ImageMap('cloudy-day-3.svg', 'cloudy-night-3.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].LIGHT_DRIZZLE, new ImageMap('rainy-4.svg', 'rainy-4.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].MAINLY_CLEAR, new ImageMap('cloudy-day-1.svg', 'cloudy-night-1.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].LIGHT_RAIN_AND_FOG, new ImageMap('rainy-4.svg', 'rainy-4.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].RAIN_OR_DRIZZLE, new ImageMap('rainy-5.svg', 'rainy-5.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].RAIN_AT_TIMES_HEAVY_OR_DRIZZLE, new ImageMap('rainy-6.svg', 'rainy-6.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].RAIN_AT_TIMES_HEAVY, new ImageMap('rainy-6.svg', 'rainy-6.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].A_FEW_SHOWERS_OR_DRIZZLE, new ImageMap('rainy-4.svg', 'rainy-4.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].SHOWERS_OR_DRIZZLE, new ImageMap('rainy-6.svg', 'rainy-6.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].A_FEW_SHOWERS, new ImageMap('rainy-5.svg', 'rainy-5.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].LIGHT_RAINSHOWER, new ImageMap('rainy-4.svg', 'rainy-4.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].HAZE, new ImageMap('cloudy.svg', 'cloudy.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].FOG_PATCHES, new ImageMap('cloudy.svg', 'cloudy.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].CLEARING, new ImageMap('day.svg', 'night.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].FOG_DISSIPATING, new ImageMap('cloudy-day-3.svg', 'cloudy-night-3.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].INCREASING_CLOUDINESS, new ImageMap('cloudy-day-1.svg', 'cloudy-night-1.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].THUNDERSTORM_WITH_LIGHT_RAINSHOWERS, new ImageMap('thunder.svg', 'thunder.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].CHANCE_OF_RAIN_SHOWERS_OR_FLURRIES, new ImageMap('snowy-1.svg', 'snowy-4.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].CHANCE_OF_FLURRIES, new ImageMap('snowy-1.svg', 'snowy-4.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].SNOW_OR_RAIN, new ImageMap('snowy-1.svg', 'snowy-4.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].LIGHT_SNOWSHOWER, new ImageMap('snowy-2.svg', 'snowy-5.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].RAIN_OR_SNOW, new ImageMap('snowy-1.svg', 'snowy-4.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].CHANCE_OF_FLURRIES_OR_RAIN_SHOWERS, new ImageMap('snowy-1.svg', 'snowy-4.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].FLURRIES_OR_RAIN_SHOWERS, new ImageMap('snowy-1.svg', 'snowy-4.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].PERIODS_OF_RAIN_MIXED_WITH_SNOW, new ImageMap('rainy-2.svg', 'rainy-5.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].A_FEW_FLURRIES, new ImageMap('snowy-1.svg', 'snowy-4.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].PERIODS_OF_SNOW, new ImageMap('snowy-2.svg', 'snowy-5.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].PERIODS_OF_RAIN_OR_DRIZZLE, new ImageMap('rainy-2.svg', 'rainy-5.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].CHANCE_OF_DRIZZLE_OR_RAIN, new ImageMap('rainy-2.svg', 'rainy-5.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].SNOW, new ImageMap('snowy-6.svg', 'snowy-6.svg')],
            [_enums_icons__WEBPACK_IMPORTED_MODULE_3__["Icons"].OVERCAST, new ImageMap('cloudy.svg', 'cloudy.svg')]
        ]);
    }
}
ImageService.ɵfac = function ImageService_Factory(t) { return new (t || ImageService)(_angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵinject"](_day_night_cycle_service__WEBPACK_IMPORTED_MODULE_5__["DayNightCycleService"])); };
ImageService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵdefineInjectable"]({ token: ImageService, factory: ImageService.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵsetClassMetadata"](ImageService, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_2__["Injectable"],
        args: [{
                providedIn: 'root'
            }]
    }], function () { return [{ type: _day_night_cycle_service__WEBPACK_IMPORTED_MODULE_5__["DayNightCycleService"] }]; }, null); })();


/***/ }),

/***/ "./src/app/services/toast.service.ts":
/*!*******************************************!*\
  !*** ./src/app/services/toast.service.ts ***!
  \*******************************************/
/*! exports provided: ToastService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ToastService", function() { return ToastService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
// toast.service.ts


class ToastService {
    constructor() {
        this.toasts = [];
        this.fullscreen = false;
    }
    // Push new Toasts to array with content and options
    show(textOrTpl, options = {}) {
        this.toasts.push(Object.assign({ textOrTpl }, options));
    }
    // Callback method to remove Toast DOM element from view
    remove(toast) {
        this.toasts = this.toasts.filter(t => t !== toast);
    }
    show2() {
        console.log("toasts: ", this.toasts);
    }
    // Callback method to remove Toast DOM element from view
    remove2(key) {
        this.toasts = this.toasts.filter(t => t.removeKey !== key);
    }
}
ToastService.ɵfac = function ToastService_Factory(t) { return new (t || ToastService)(); };
ToastService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({ token: ToastService, factory: ToastService.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](ToastService, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"],
        args: [{
                providedIn: 'root'
            }]
    }], null, null); })();


/***/ }),

/***/ "./src/app/services/weather.service.ts":
/*!*********************************************!*\
  !*** ./src/app/services/weather.service.ts ***!
  \*********************************************/
/*! exports provided: WeatherService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "WeatherService", function() { return WeatherService; });
/* harmony import */ var _model_weatherError__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./../_model/weatherError */ "./src/app/_model/weatherError.ts");
/* harmony import */ var _helpers_HttpHelper__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./../_helpers/HttpHelper */ "./src/app/_helpers/HttpHelper.ts");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm2015/index.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm2015/operators/index.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/__ivy_ngcc__/fesm2015/http.js");
/* harmony import */ var _config_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./config.service */ "./src/app/services/config.service.ts");








class WeatherService {
    constructor(http, configService) {
        this.http = http;
        this.configService = configService;
        this.loading = true;
        this.fullWeather = new rxjs__WEBPACK_IMPORTED_MODULE_3__["BehaviorSubject"](null);
        this.wthError = new rxjs__WEBPACK_IMPORTED_MODULE_3__["BehaviorSubject"](null);
        this.configSub = this.configService.configUpdated().subscribe(newConfig => {
            this.weatherTimer();
        });
    }
    getFullWeather() {
        return this.fullWeather.asObservable();
    }
    // if any error generated by the weather service
    errorGenerated() {
        return this.wthError.asObservable();
    }
    // timer that  get the weather every 5 minutes.
    weatherTimer() {
        if (this.wthTimer) {
            this.wthTimer.unsubscribe();
        }
        this.wthTimer = Object(rxjs__WEBPACK_IMPORTED_MODULE_3__["timer"])(0, this.configService.getRefreshTimeMillis()).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["flatMap"])(() => this.fetchFullWeather())).subscribe(wthData => {
            this.loading = false;
            this.fullWeather.next(wthData);
        }, weatherError => {
            this.loading = false;
            console.log('weatherError svc', weatherError);
            const wError = new _model_weatherError__WEBPACK_IMPORTED_MODULE_0__["WeatherError"]('Unexpected', 'Unexpected error', 'Timer in weather service caught error, see logs.');
            this.wthError.next(wError);
        });
    }
    fetchFullWeather() {
        console.log('fetchFullWeather', new Date());
        this.loading = true;
        return this.http.get(`http://192.168.1.110:8081/web/temperature/weather/${this.configService.getCurrentCity()}/yes`).pipe(Object(_helpers_HttpHelper__WEBPACK_IMPORTED_MODULE_1__["delayedRetry"])(5000, 5), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["catchError"])(err => {
            this.loading = false;
            const wError = new _model_weatherError__WEBPACK_IMPORTED_MODULE_0__["WeatherError"]('Retries', 'Retries failure', 'Number of retries exceeded. Cannot connect to server ');
            this.wthError.next(wError);
            return rxjs__WEBPACK_IMPORTED_MODULE_3__["EMPTY"];
        }));
    }
    ngOnDestroy() {
        this.configSub.unsubscribe();
        this.wthTimer.unsubscribe();
    }
}
WeatherService.ɵfac = function WeatherService_Factory(t) { return new (t || WeatherService)(_angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HttpClient"]), _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵinject"](_config_service__WEBPACK_IMPORTED_MODULE_6__["ConfigService"])); };
WeatherService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵdefineInjectable"]({ token: WeatherService, factory: WeatherService.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵsetClassMetadata"](WeatherService, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_2__["Injectable"],
        args: [{
                providedIn: 'root'
            }]
    }], function () { return [{ type: _angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HttpClient"] }, { type: _config_service__WEBPACK_IMPORTED_MODULE_6__["ConfigService"] }]; }, null); })();


/***/ }),

/***/ "./src/app/toast/toast.component.ts":
/*!******************************************!*\
  !*** ./src/app/toast/toast.component.ts ***!
  \******************************************/
/*! exports provided: ToastComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ToastComponent", function() { return ToastComponent; });
/* harmony import */ var _model_constants__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../_model/constants */ "./src/app/_model/constants.ts");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/__ivy_ngcc__/fesm2015/common.js");
/* harmony import */ var _services_toast_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./../services/toast.service */ "./src/app/services/toast.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/__ivy_ngcc__/fesm2015/router.js");
/* harmony import */ var _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ng-bootstrap/ng-bootstrap */ "./node_modules/@ng-bootstrap/ng-bootstrap/__ivy_ngcc__/fesm2015/ng-bootstrap.js");








function ToastComponent_ngb_toast_0_ng_template_1_ng_template_0_Template(rf, ctx) { }
function ToastComponent_ngb_toast_0_ng_template_1_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](0, ToastComponent_ngb_toast_0_ng_template_1_ng_template_0_Template, 0, 0, "ng-template", 4);
} if (rf & 2) {
    const toast_r1 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵnextContext"]().$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngTemplateOutlet", toast_r1.textOrTpl);
} }
function ToastComponent_ngb_toast_0_ng_template_2_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](0);
} if (rf & 2) {
    const toast_r1 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵnextContext"]().$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtextInterpolate"](toast_r1.textOrTpl);
} }
function ToastComponent_ngb_toast_0_Template(rf, ctx) { if (rf & 1) {
    const _r9 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "ngb-toast", 1);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵlistener"]("hide", function ToastComponent_ngb_toast_0_Template_ngb_toast_hide_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵrestoreView"](_r9); const toast_r1 = ctx.$implicit; const ctx_r8 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵnextContext"](); return ctx_r8.toastService.remove(toast_r1); })("click", function ToastComponent_ngb_toast_0_Template_ngb_toast_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵrestoreView"](_r9); const toast_r1 = ctx.$implicit; const ctx_r10 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵnextContext"](); return ctx_r10.action(toast_r1.navigate); });
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](1, ToastComponent_ngb_toast_0_ng_template_1_Template, 1, 1, "ng-template", 2);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](2, ToastComponent_ngb_toast_0_ng_template_2_Template, 1, 1, "ng-template", null, 3, _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplateRefExtractor"]);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
} if (rf & 2) {
    const toast_r1 = ctx.$implicit;
    const _r3 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵreference"](3);
    const ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵclassMap"](toast_r1.classname);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("header", toast_r1.headertext)("autohide", toast_r1.autohide)("delay", toast_r1.delay || 5000);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngIf", ctx_r0.isTemplate(toast_r1))("ngIfElse", _r3);
} }
class ToastComponent {
    constructor(toastService, document, router) {
        this.toastService = toastService;
        this.document = document;
        this.router = router;
    }
    isTemplate(toast) { return toast.textOrTpl instanceof _angular_core__WEBPACK_IMPORTED_MODULE_1__["TemplateRef"]; }
    fullScreen() {
        this.elem = document.documentElement;
        if (this.elem.requestFullscreen) {
            this.elem.requestFullscreen();
        }
        else if (this.elem.mozRequestFullScreen) {
            /* Firefox */
            this.elem.mozRequestFullScreen();
        }
        else if (this.elem.webkitRequestFullscreen) {
            /* Chrome, Safari and Opera */
            this.elem.webkitRequestFullscreen();
        }
        else if (this.elem.msRequestFullscreen) {
            /* IE/Edge */
            this.elem.msRequestFullscreen();
        }
        this.toastService.remove2(_model_constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].FULL_SCREEN_INDX);
    }
    action(navigate) {
        if (navigate === 'fullscreen') {
            this.fullScreen();
        }
        else {
            this.router.navigate([navigate]);
            this.toastService.remove2(_model_constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].BACK_INDX);
        }
    }
}
ToastComponent.ɵfac = function ToastComponent_Factory(t) { return new (t || ToastComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_services_toast_service__WEBPACK_IMPORTED_MODULE_3__["ToastService"]), _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_angular_common__WEBPACK_IMPORTED_MODULE_2__["DOCUMENT"]), _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"])); };
ToastComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineComponent"]({ type: ToastComponent, selectors: [["app-toast"]], hostVars: 2, hostBindings: function ToastComponent_HostBindings(rf, ctx) { if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵclassProp"]("ngb-toasts", true);
    } }, decls: 1, vars: 1, consts: [[3, "header", "class", "autohide", "delay", "hide", "click", 4, "ngFor", "ngForOf"], [3, "header", "autohide", "delay", "hide", "click"], [3, "ngIf", "ngIfElse"], ["text", ""], [3, "ngTemplateOutlet"]], template: function ToastComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](0, ToastComponent_ngb_toast_0_Template, 4, 7, "ngb-toast", 0);
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngForOf", ctx.toastService.toasts);
    } }, directives: [_angular_common__WEBPACK_IMPORTED_MODULE_2__["NgForOf"], _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_5__["NgbToast"], _angular_common__WEBPACK_IMPORTED_MODULE_2__["NgIf"], _angular_common__WEBPACK_IMPORTED_MODULE_2__["NgTemplateOutlet"]], encapsulation: 2 });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵsetClassMetadata"](ToastComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"],
        args: [{
                selector: 'app-toast',
                template: `
    <ngb-toast
      *ngFor="let toast of toastService.toasts"
      [header]="toast.headertext"
      [class]="toast.classname"
      [autohide]="toast.autohide"
      [delay]="toast.delay || 5000"
      (hide)="toastService.remove(toast)"
      (click)="action(toast.navigate)"
    >
      <ng-template [ngIf]="isTemplate(toast)" [ngIfElse]="text">
        <ng-template [ngTemplateOutlet]="toast.textOrTpl"></ng-template>
      </ng-template>

      <ng-template #text>{{ toast.textOrTpl }}</ng-template>
    </ngb-toast>
  `,
                host: { '[class.ngb-toasts]': 'true' }
            }]
    }], function () { return [{ type: _services_toast_service__WEBPACK_IMPORTED_MODULE_3__["ToastService"] }, { type: undefined, decorators: [{
                type: _angular_core__WEBPACK_IMPORTED_MODULE_1__["Inject"],
                args: [_angular_common__WEBPACK_IMPORTED_MODULE_2__["DOCUMENT"]]
            }] }, { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"] }]; }, null); })();


/***/ }),

/***/ "./src/app/weather-alert/weather-alert.component.ts":
/*!**********************************************************!*\
  !*** ./src/app/weather-alert/weather-alert.component.ts ***!
  \**********************************************************/
/*! exports provided: WeatherAlertComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "WeatherAlertComponent", function() { return WeatherAlertComponent; });
/* harmony import */ var _model_constants__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../_model/constants */ "./src/app/_model/constants.ts");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _services_weather_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./../services/weather.service */ "./src/app/services/weather.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/__ivy_ngcc__/fesm2015/router.js");
/* harmony import */ var _services_toast_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../services/toast.service */ "./src/app/services/toast.service.ts");
/* harmony import */ var _toast_toast_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../toast/toast.component */ "./src/app/toast/toast.component.ts");







class WeatherAlertComponent {
    constructor(weatherSvc, router, toastService) {
        this.weatherSvc = weatherSvc;
        this.router = router;
        this.toastService = toastService;
    }
    ngOnInit() {
        this.wthSubscription = this.weatherSvc.getFullWeather().subscribe(weatherData => {
            if (weatherData && weatherData.alert) {
                this.alert = weatherData.alert;
            }
        });
        setTimeout(() => { this.enableBackToaster(); }, 500); // remove preloader
        this.timer = setTimeout(() => {
            this.router.navigate(['/']);
            this.toastService.remove2(_model_constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].BACK_INDX);
        }, 45000); //45s
    }
    enableBackToaster() {
        this.toastService.show('Back', {
            classname: 'bg-success text-light',
            autohide: false,
            navigate: '/',
            removeKey: _model_constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].BACK_INDX
        });
    }
    ngOnDestroy() {
        this.wthSubscription.unsubscribe();
        clearTimeout(this.timer);
    }
}
WeatherAlertComponent.ɵfac = function WeatherAlertComponent_Factory(t) { return new (t || WeatherAlertComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_services_weather_service__WEBPACK_IMPORTED_MODULE_2__["WeatherService"]), _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"]), _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_services_toast_service__WEBPACK_IMPORTED_MODULE_4__["ToastService"])); };
WeatherAlertComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineComponent"]({ type: WeatherAlertComponent, selectors: [["app-weather-alert"]], decls: 8, vars: 2, consts: [[1, "backg"], [1, "row"], [1, "col-md-12", "mt-1"], [1, "jumbotron", "card", "card-block", "ml-1", "mr-1", 2, "padding", "10px 8px 10px 08px"], [2, "font-size", "4vw"], [2, "font-size", "2vw", 3, "innerHTML"]], template: function WeatherAlertComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelement"](1, "app-toast");
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](2, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](3, "div", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](4, "div", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](5, "h3", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](6);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelement"](7, "p", 5);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](6);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtextInterpolate1"](" ", ctx.alert.title, " ");
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("innerHTML", ctx.alert.message, _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵsanitizeHtml"]);
    } }, directives: [_toast_toast_component__WEBPACK_IMPORTED_MODULE_5__["ToastComponent"]], styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3dlYXRoZXItYWxlcnQvd2VhdGhlci1hbGVydC5jb21wb25lbnQuY3NzIn0= */"] });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵsetClassMetadata"](WeatherAlertComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"],
        args: [{
                selector: 'app-weather-alert',
                templateUrl: './weather-alert.component.html',
                styleUrls: ['./weather-alert.component.css']
            }]
    }], function () { return [{ type: _services_weather_service__WEBPACK_IMPORTED_MODULE_2__["WeatherService"] }, { type: _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"] }, { type: _services_toast_service__WEBPACK_IMPORTED_MODULE_4__["ToastService"] }]; }, null); })();


/***/ }),

/***/ "./src/app/weather-config/weather-config.component.ts":
/*!************************************************************!*\
  !*** ./src/app/weather-config/weather-config.component.ts ***!
  \************************************************************/
/*! exports provided: WeatherConfigComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "WeatherConfigComponent", function() { return WeatherConfigComponent; });
/* harmony import */ var src_app_helpers_number_validator__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! src/app/_helpers/number.validator */ "./src/app/_helpers/number.validator.ts");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/__ivy_ngcc__/fesm2015/forms.js");
/* harmony import */ var _services_config_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./../services/config.service */ "./src/app/services/config.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/__ivy_ngcc__/fesm2015/router.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/__ivy_ngcc__/fesm2015/common.js");








function WeatherConfigComponent_option_12_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "option", 15);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
} if (rf & 2) {
    const city_r4 = ctx.$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngValue", city_r4.key);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtextInterpolate1"](" ", city_r4.nameEn, " ");
} }
function WeatherConfigComponent_div_17_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "div", 16);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](1, "Only numbers are accepted");
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
} }
function WeatherConfigComponent_div_18_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "div", 16);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](1, "Number must be between 0 and 60");
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
} }
function WeatherConfigComponent_span_23_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelement"](0, "span", 17);
} }
class WeatherConfigComponent {
    constructor(formBuilder, cfg, router) {
        this.formBuilder = formBuilder;
        this.cfg = cfg;
        this.router = router;
        this.cityList = [];
        this.submit = false;
        this.loadingCities = true;
    }
    ngOnInit() {
        this.configForm = this.formBuilder.group({
            city: [this.cfg.getCurrentCity(), [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required]],
            frm_refresh: [this.cfg.getRefreshTimeMinutes(), [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].min(1), _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].max(60)]]
        }, {
            validator: Object(src_app_helpers_number_validator__WEBPACK_IMPORTED_MODULE_0__["numberValidator"])('frm_refresh')
        });
        this.cfg.getEnvCanCity().subscribe(cities => {
            this.cityList = cities;
            this.loadingCities = false;
        }, fetchError => {
            console.log('City fetch error', fetchError);
            this.error = 'Error fetching cities';
        });
    }
    onSubmit() {
        console.log('onSubmit', this.configForm.value);
        if (this.configForm.invalid) {
            return;
        }
        this.submit = true;
        const formValue = this.configForm.value;
        this.cfg.updateConfig(formValue.city, Number(formValue.frm_refresh));
        this.router.navigate(['/']);
    }
    onCancel() {
        this.router.navigate(['/']);
    }
}
WeatherConfigComponent.ɵfac = function WeatherConfigComponent_Factory(t) { return new (t || WeatherConfigComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormBuilder"]), _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_services_config_service__WEBPACK_IMPORTED_MODULE_3__["ConfigService"]), _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"])); };
WeatherConfigComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineComponent"]({ type: WeatherConfigComponent, selectors: [["app-weather-config"]], decls: 24, vars: 6, consts: [[1, "backg"], [1, "row"], [1, "col-md-12", "mt-1"], [1, "jumbotron", "card", "card-block", "ml-1", "mr-1", 2, "padding", "10px 8px 10px 08px", "height", "99vh"], [2, "color", "white"], [3, "formGroup", "ngSubmit"], [1, "col-md-3"], [1, "form-group"], ["formControlName", "city", 1, "form-control"], [3, "ngValue", 4, "ngFor", "ngForOf"], ["type", "text", "formControlName", "frm_refresh", 1, "form-control"], ["class", "alert alert-danger", 4, "ngIf"], ["type", "button", 1, "btn", "btn-secondary", "mr-1", 3, "click"], ["type", "submit", 1, "btn", "btn-primary", 3, "disabled"], ["class", "spinner-border spinner-border-sm mr-1", 4, "ngIf"], [3, "ngValue"], [1, "alert", "alert-danger"], [1, "spinner-border", "spinner-border-sm", "mr-1"]], template: function WeatherConfigComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](2, "div", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](3, "div", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](4, "h1", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](5, "Weather Configuration ");
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](6, "form", 5);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵlistener"]("ngSubmit", function WeatherConfigComponent_Template_form_ngSubmit_6_listener() { return ctx.onSubmit(); });
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](7, "div", 6);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](8, "div", 7);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](9, "label");
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](10, "City:");
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](11, "select", 8);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](12, WeatherConfigComponent_option_12_Template, 2, 2, "option", 9);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](13, "div", 7);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](14, "label");
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](15, "Refresh time:");
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelement"](16, "input", 10);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](17, WeatherConfigComponent_div_17_Template, 2, 0, "div", 11);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](18, WeatherConfigComponent_div_18_Template, 2, 0, "div", 11);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](19, "button", 12);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵlistener"]("click", function WeatherConfigComponent_Template_button_click_19_listener() { return ctx.onCancel(); });
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](20, "cancel");
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](21, "button", 13);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](22, "Save ");
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](23, WeatherConfigComponent_span_23_Template, 1, 0, "span", 14);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](6);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("formGroup", ctx.configForm);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](6);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngForOf", ctx.cityList);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](5);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngIf", ctx.configForm.controls.frm_refresh.errors == null ? null : ctx.configForm.controls.frm_refresh.errors.mustBeNumber);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngIf", (ctx.configForm.controls.frm_refresh.errors == null ? null : ctx.configForm.controls.frm_refresh.errors.min) || (ctx.configForm.controls.frm_refresh.errors == null ? null : ctx.configForm.controls.frm_refresh.errors.max));
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](3);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("disabled", ctx.submit || ctx.configForm.invalid);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](2);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngIf", ctx.submit);
    } }, directives: [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["ɵangular_packages_forms_forms_y"], _angular_forms__WEBPACK_IMPORTED_MODULE_2__["NgControlStatusGroup"], _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormGroupDirective"], _angular_forms__WEBPACK_IMPORTED_MODULE_2__["SelectControlValueAccessor"], _angular_forms__WEBPACK_IMPORTED_MODULE_2__["NgControlStatus"], _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormControlName"], _angular_common__WEBPACK_IMPORTED_MODULE_5__["NgForOf"], _angular_forms__WEBPACK_IMPORTED_MODULE_2__["DefaultValueAccessor"], _angular_common__WEBPACK_IMPORTED_MODULE_5__["NgIf"], _angular_forms__WEBPACK_IMPORTED_MODULE_2__["NgSelectOption"], _angular_forms__WEBPACK_IMPORTED_MODULE_2__["ɵangular_packages_forms_forms_x"]], styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3dlYXRoZXItY29uZmlnL3dlYXRoZXItY29uZmlnLmNvbXBvbmVudC5jc3MifQ== */"] });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵsetClassMetadata"](WeatherConfigComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"],
        args: [{
                selector: 'app-weather-config',
                templateUrl: './weather-config.component.html',
                styleUrls: ['./weather-config.component.css']
            }]
    }], function () { return [{ type: _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormBuilder"] }, { type: _services_config_service__WEBPACK_IMPORTED_MODULE_3__["ConfigService"] }, { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"] }]; }, null); })();


/***/ }),

/***/ "./src/app/weather-extended/weather-extended.component.ts":
/*!****************************************************************!*\
  !*** ./src/app/weather-extended/weather-extended.component.ts ***!
  \****************************************************************/
/*! exports provided: WeatherExtendedComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "WeatherExtendedComponent", function() { return WeatherExtendedComponent; });
/* harmony import */ var _model_constants__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../_model/constants */ "./src/app/_model/constants.ts");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _services_weather_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/weather.service */ "./src/app/services/weather.service.ts");
/* harmony import */ var _services_toast_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/toast.service */ "./src/app/services/toast.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/__ivy_ngcc__/fesm2015/router.js");
/* harmony import */ var _services_image_service_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../services/image-service.service */ "./src/app/services/image-service.service.ts");
/* harmony import */ var _toast_toast_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../toast/toast.component */ "./src/app/toast/toast.component.ts");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/__ivy_ngcc__/fesm2015/common.js");









function WeatherExtendedComponent_tr_5_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "tr", 5);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](1, "td", 6);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](3, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelement"](4, "img", 7);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](5, "td", 8);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](6);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
} if (rf & 2) {
    const wth_r1 = ctx.$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtextInterpolate1"](" ", wth_r1.dayOfWeek, " ");
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("src", wth_r1.iconLink, _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵsanitizeUrl"]);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtextInterpolate1"](" ", wth_r1.forecast, "");
} }
class WeatherExtendedComponent {
    constructor(weatherSvc, toastService, router, imageService) {
        this.weatherSvc = weatherSvc;
        this.toastService = toastService;
        this.router = router;
        this.imageService = imageService;
    }
    ngOnInit() {
        setTimeout(() => { this.enableBackToaster(); }, 500); // remove preloader
        this.timer = setTimeout(() => {
            this.router.navigate(['/']);
            this.toastService.remove2(_model_constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].BACK_INDX);
        }, 45000); // 45s
        // get weather!!
        this.wthSubscription = this.weatherSvc.getFullWeather().subscribe(weatherData => {
            console.log('Getting extended forecast', new Date());
            this.forecast = weatherData;
            this.wthErrors = null;
            if (this.forecast) { // populate weather icons for the forecast
                this.forecast.forecast = this.imageService.getIconsForForecast(this.forecast.forecast);
            }
        });
        this.toastService.remove2(_model_constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].BACK_INDX);
    }
    enableBackToaster() {
        this.toastService.show('Back', {
            classname: 'bg-success text-light',
            autohide: false,
            navigate: '/',
            removeKey: _model_constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].BACK_INDX
        });
    }
    ngOnDestroy() {
        this.wthSubscription.unsubscribe();
        clearTimeout(this.timer);
    }
}
WeatherExtendedComponent.ɵfac = function WeatherExtendedComponent_Factory(t) { return new (t || WeatherExtendedComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_services_weather_service__WEBPACK_IMPORTED_MODULE_2__["WeatherService"]), _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_services_toast_service__WEBPACK_IMPORTED_MODULE_3__["ToastService"]), _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"]), _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_services_image_service_service__WEBPACK_IMPORTED_MODULE_5__["ImageService"])); };
WeatherExtendedComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineComponent"]({ type: WeatherExtendedComponent, selectors: [["app-weather-extended"]], decls: 7, vars: 4, consts: [[1, "backg"], [1, "row"], [1, "col-md-12", "mt-1"], [1, "ml-3", "mr-2"], ["style", "height:100px;margin-left: 10px;", 4, "ngFor", "ngForOf"], [2, "height", "100px", "margin-left", "10px"], [2, "width", "150px", "color", "white", "font-size", "2vw", "text-align", "center"], ["alt", "weather", "width", "128", "height", "128", 1, "rounded-circle", 3, "src"], [2, "color", "white", "font-size", "2vw", "width", "95%"]], template: function WeatherExtendedComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelement"](1, "app-toast");
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](2, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](3, "div", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](4, "table", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](5, WeatherExtendedComponent_tr_5_Template, 7, 3, "tr", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵpipe"](6, "slice");
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](5);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngForOf", _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵpipeBind2"](6, 1, ctx.forecast == null ? null : ctx.forecast.forecast, 1));
    } }, directives: [_toast_toast_component__WEBPACK_IMPORTED_MODULE_6__["ToastComponent"], _angular_common__WEBPACK_IMPORTED_MODULE_7__["NgForOf"]], pipes: [_angular_common__WEBPACK_IMPORTED_MODULE_7__["SlicePipe"]], styles: [".text-size[_ngcontent-%COMP%] {\r\n    font-size: 8em;\r\n  }\r\n\r\n  tr[_ngcontent-%COMP%] {\r\n    background: rgb(54, 53, 53);  \r\n    background: rgba(54, 53, 53, 0.5);  \r\n    border-top-left-radius: 10px; \r\n    border-top-right-radius: 10px;\r\n    border-bottom-left-radius: 10px;\r\n    border-bottom-right-radius: 10px;\r\n  }\r\n\r\n  table[_ngcontent-%COMP%] {\r\n    border-collapse: separate;\r\n    border-spacing: 0 5px;\r\n  }\r\n\r\n  td[_ngcontent-%COMP%] {\r\n    \r\n    padding: 5px;\r\n    vertical-align: middle;\r\n  }\r\n\r\n  \r\n\r\n  td[_ngcontent-%COMP%]:first-child { border-top-left-radius: 10px; }\r\n\r\n  td[_ngcontent-%COMP%]:last-child { border-top-right-radius: 10px; }\r\n\r\n  td[_ngcontent-%COMP%]:first-child { border-bottom-left-radius: 10px; }\r\n\r\n  td[_ngcontent-%COMP%]:last-child { border-bottom-right-radius: 10px; }\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvd2VhdGhlci1leHRlbmRlZC93ZWF0aGVyLWV4dGVuZGVkLmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7SUFDSSxjQUFjO0VBQ2hCOztFQUVBO0lBQ0UsMkJBQTJCO0lBQzNCLGlDQUFpQztJQUNqQyw0QkFBNEI7SUFDNUIsNkJBQTZCO0lBQzdCLCtCQUErQjtJQUMvQixnQ0FBZ0M7RUFDbEM7O0VBR0E7SUFDRSx5QkFBeUI7SUFDekIscUJBQXFCO0VBQ3ZCOztFQUVBOztJQUVFLFlBQVk7SUFDWixzQkFBc0I7RUFDeEI7O0VBRUE7OzttRUFHaUU7O0VBQ25FLGlCQUFpQiw0QkFBNEIsRUFBRTs7RUFDOUMsZ0JBQWdCLDZCQUE2QixFQUFFOztFQUMvQyxpQkFBaUIsK0JBQStCLEVBQUU7O0VBQ2xELGdCQUFnQixnQ0FBZ0MsRUFBRSIsImZpbGUiOiJzcmMvYXBwL3dlYXRoZXItZXh0ZW5kZWQvd2VhdGhlci1leHRlbmRlZC5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLnRleHQtc2l6ZSB7XHJcbiAgICBmb250LXNpemU6IDhlbTtcclxuICB9XHJcblxyXG4gIHRyIHtcclxuICAgIGJhY2tncm91bmQ6IHJnYig1NCwgNTMsIDUzKTsgIFxyXG4gICAgYmFja2dyb3VuZDogcmdiYSg1NCwgNTMsIDUzLCAwLjUpOyAgXHJcbiAgICBib3JkZXItdG9wLWxlZnQtcmFkaXVzOiAxMHB4OyBcclxuICAgIGJvcmRlci10b3AtcmlnaHQtcmFkaXVzOiAxMHB4O1xyXG4gICAgYm9yZGVyLWJvdHRvbS1sZWZ0LXJhZGl1czogMTBweDtcclxuICAgIGJvcmRlci1ib3R0b20tcmlnaHQtcmFkaXVzOiAxMHB4O1xyXG4gIH1cclxuXHJcblxyXG4gIHRhYmxlIHtcclxuICAgIGJvcmRlci1jb2xsYXBzZTogc2VwYXJhdGU7XHJcbiAgICBib3JkZXItc3BhY2luZzogMCA1cHg7XHJcbiAgfVxyXG5cclxuICB0ZCB7XHJcbiAgICBcclxuICAgIHBhZGRpbmc6IDVweDtcclxuICAgIHZlcnRpY2FsLWFsaWduOiBtaWRkbGU7XHJcbiAgfVxyXG5cclxuICAvKiB0cjpmaXJzdC1jaGlsZCB0ZDpmaXJzdC1jaGlsZCB7IGJvcmRlci10b3AtbGVmdC1yYWRpdXM6IDEwcHg7IH1cclxudHI6Zmlyc3QtY2hpbGQgdGQ6bGFzdC1jaGlsZCB7IGJvcmRlci10b3AtcmlnaHQtcmFkaXVzOiAxMHB4OyB9XHJcbnRyOmxhc3QtY2hpbGQgdGQ6Zmlyc3QtY2hpbGQgeyBib3JkZXItYm90dG9tLWxlZnQtcmFkaXVzOiAxMHB4OyB9XHJcbnRyOmxhc3QtY2hpbGQgdGQ6bGFzdC1jaGlsZCB7IGJvcmRlci1ib3R0b20tcmlnaHQtcmFkaXVzOiAxMHB4OyB9ICovXHJcbnRkOmZpcnN0LWNoaWxkIHsgYm9yZGVyLXRvcC1sZWZ0LXJhZGl1czogMTBweDsgfVxyXG4gdGQ6bGFzdC1jaGlsZCB7IGJvcmRlci10b3AtcmlnaHQtcmFkaXVzOiAxMHB4OyB9XHJcbiB0ZDpmaXJzdC1jaGlsZCB7IGJvcmRlci1ib3R0b20tbGVmdC1yYWRpdXM6IDEwcHg7IH1cclxuIHRkOmxhc3QtY2hpbGQgeyBib3JkZXItYm90dG9tLXJpZ2h0LXJhZGl1czogMTBweDsgfSJdfQ== */"] });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵsetClassMetadata"](WeatherExtendedComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"],
        args: [{
                selector: 'app-weather-extended',
                templateUrl: './weather-extended.component.html',
                styleUrls: ['./weather-extended.component.css']
            }]
    }], function () { return [{ type: _services_weather_service__WEBPACK_IMPORTED_MODULE_2__["WeatherService"] }, { type: _services_toast_service__WEBPACK_IMPORTED_MODULE_3__["ToastService"] }, { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"] }, { type: _services_image_service_service__WEBPACK_IMPORTED_MODULE_5__["ImageService"] }]; }, null); })();


/***/ }),

/***/ "./src/app/weather/weather.component.ts":
/*!**********************************************!*\
  !*** ./src/app/weather/weather.component.ts ***!
  \**********************************************/
/*! exports provided: WeatherComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "WeatherComponent", function() { return WeatherComponent; });
/* harmony import */ var _model_constants__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../_model/constants */ "./src/app/_model/constants.ts");
/* harmony import */ var _enums_alertsLvl__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../_enums/alertsLvl */ "./src/app/_enums/alertsLvl.ts");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _password_modal_password_modal_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../password-modal/password-modal.component */ "./src/app/password-modal/password-modal.component.ts");
/* harmony import */ var _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @fortawesome/free-solid-svg-icons */ "./node_modules/@fortawesome/free-solid-svg-icons/index.es.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/__ivy_ngcc__/fesm2015/router.js");
/* harmony import */ var _services_weather_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./../services/weather.service */ "./src/app/services/weather.service.ts");
/* harmony import */ var _services_toast_service__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./../services/toast.service */ "./src/app/services/toast.service.ts");
/* harmony import */ var _services_image_service_service__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./../services/image-service.service */ "./src/app/services/image-service.service.ts");
/* harmony import */ var _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @ng-bootstrap/ng-bootstrap */ "./node_modules/@ng-bootstrap/ng-bootstrap/__ivy_ngcc__/fesm2015/ng-bootstrap.js");
/* harmony import */ var _toast_toast_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ../toast/toast.component */ "./src/app/toast/toast.component.ts");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/__ivy_ngcc__/fesm2015/common.js");
/* harmony import */ var _fortawesome_angular_fontawesome__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! @fortawesome/angular-fontawesome */ "./node_modules/@fortawesome/angular-fontawesome/__ivy_ngcc__/fesm2015/angular-fontawesome.js");














function WeatherComponent_div_9_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 27);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" ", ctx_r0.fullWeather.localtemp.tempSun, "");
} }
function WeatherComponent_div_10_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 28);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵpipe"](2, "date");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r1 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" ", _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵpipeBind2"](2, 1, ctx_r1.fullWeather.localtemp.tmpSunUpdDt, "HH:mm"), "");
} }
function WeatherComponent_div_15_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelement"](0, "div", 29);
} }
function WeatherComponent_div_16_div_3_div_1_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 34);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](1, "div", 35);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r15 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"](3);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" UV: ", ctx_r15.fullWeather.UV, " ");
} }
function WeatherComponent_div_16_div_3_div_2_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 34);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](1, "div", 36);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r16 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"](3);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" Humidity: ", ctx_r16.fullWeather.humidity, " ");
} }
function WeatherComponent_div_16_div_3_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](1, WeatherComponent_div_16_div_3_div_1_Template, 3, 1, "div", 33);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](2, WeatherComponent_div_16_div_3_div_2_Template, 3, 1, "div", 33);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx_r11.fullWeather.UV);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx_r11.fullWeather.humidity);
} }
function WeatherComponent_div_16_div_4_div_1_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 34);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](1, "div", 35);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r17 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"](3);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" WindChill: ", ctx_r17.fullWeather.wingChill, " ");
} }
function WeatherComponent_div_16_div_4_div_2_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 34);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](1, "div", 36);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r18 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"](3);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" Humidex: ", ctx_r18.fullWeather.humidex, " ");
} }
function WeatherComponent_div_16_div_4_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](1, WeatherComponent_div_16_div_4_div_1_Template, 3, 1, "div", 33);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](2, WeatherComponent_div_16_div_4_div_2_Template, 3, 1, "div", 33);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r12 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx_r12.fullWeather.wingChill);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx_r12.fullWeather.humidex);
} }
function WeatherComponent_div_16_div_5_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 37);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r13 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" ", ctx_r13.fullWeather.wind, " ");
} }
function WeatherComponent_div_16_div_6_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 28);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](2, "span");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](3, "\u00B0");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](4, "C");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r14 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" ", ctx_r14.fullWeather.temperature, "");
} }
function WeatherComponent_div_16_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](1, "div", 30);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](3, WeatherComponent_div_16_div_3_Template, 3, 2, "div", 31);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](4, WeatherComponent_div_16_div_4_Template, 3, 2, "div", 31);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](5, WeatherComponent_div_16_div_5_Template, 2, 1, "div", 32);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](6, WeatherComponent_div_16_div_6_Template, 5, 1, "div", 6);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r4 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" ", ctx_r4.fullWeather.weather, " ");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx_r4.fullWeather.UV || ctx_r4.fullWeather.humidity);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx_r4.fullWeather.wingChill || ctx_r4.fullWeather.humidex);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx_r4.fullWeather.wind);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx_r4.fullWeather.temperature);
} }
function WeatherComponent_div_17_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 38);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r5 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" ", ctx_r5.wthErrors.message, " ");
} }
function WeatherComponent_div_18_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 39);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](1, "small");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵpipe"](3, "date");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r6 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate"](_angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵpipeBind2"](3, 1, ctx_r6.obsDate, "medium"));
} }
function WeatherComponent_div_23_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 27);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r7 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" ", ctx_r7.fullWeather.localtemp.tempShade, "");
} }
function WeatherComponent_div_24_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 39);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵpipe"](2, "date");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r8 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" ", _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵpipeBind2"](2, 1, ctx_r8.fullWeather.localtemp.tmpShadeUpdDt, "HH:mm"), " ");
} }
function WeatherComponent_div_28_ngb_alert_1_Template(rf, ctx) { if (rf & 1) {
    const _r22 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "ngb-alert", 46);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵlistener"]("click", function WeatherComponent_div_28_ngb_alert_1_Template_ngb_alert_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵrestoreView"](_r22); const ctx_r21 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"](2); return ctx_r21.showAlert(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](1, "strong");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](3);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r19 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("type", ctx_r19.alertType())("dismissible", false);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate"](ctx_r19.fullWeather.alert.level);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" ", ctx_r19.fullWeather.alert.title, " ");
} }
function WeatherComponent_div_28_span_5_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "span");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelement"](1, "fa-icon", 47);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r20 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("icon", ctx_r20.faSwimming);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" ", ctx_r20.fullWeather.localtemp.tempPool, "\u2103 ");
} }
function WeatherComponent_div_28_Template(rf, ctx) { if (rf & 1) {
    const _r24 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](1, WeatherComponent_div_28_ngb_alert_1_Template, 4, 4, "ngb-alert", 40);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](2, "span", 41);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelement"](3, "fa-icon", 42);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](5, WeatherComponent_div_28_span_5_Template, 3, 2, "span", 12);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](6, "div", 43);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵlistener"]("click", function WeatherComponent_div_28_Template_div_click_6_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵrestoreView"](_r24); const ctx_r23 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"](); return ctx_r23.fullForecast(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](7, "h3");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](8, "div");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](9, "span", 44);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](10);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](11, "p", 45);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](12);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r9 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx_r9.fullWeather.alert);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("icon", ctx_r9.faWarehouse);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"]("", ctx_r9.fullWeather.localtemp.tempMap.GARAGE, "\u2103 ");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx_r9.fullWeather.localtemp.tempPool);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](5);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate"](ctx_r9.fullWeather.forecast[0].dayOfWeek);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" ", ctx_r9.fullWeather.forecast[0].forecast, " ");
} }
function WeatherComponent_div_29_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 48);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵpipe"](2, "date");
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r10 = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtextInterpolate1"](" ", _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵpipeBind2"](2, 1, ctx_r10.currDate, "MMM d, y, HH:mm:ss"), "");
} }
class WeatherComponent {
    constructor(router, wsApi, toastService, renderer, imageService, modalService) {
        this.router = router;
        this.wsApi = wsApi;
        this.toastService = toastService;
        this.renderer = renderer;
        this.imageService = imageService;
        this.modalService = modalService;
        //font awesome icon
        this.faWarehouse = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_4__["faWarehouse"];
        this.faSwimming = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_4__["faSwimmingPool"];
        this.title = 'weather';
    }
    ngOnInit() {
        //start date
        this.currDate = new Date();
        this.currDateTimer = setInterval(() => { this.currDate = new Date(); }, 1000);
        // get weather from service
        this.wthSubscription = this.wsApi.getFullWeather().subscribe(weatherData => {
            this.fullWeather = weatherData;
            this.wthErrors = null;
            if (this.fullWeather) {
                // console.log(this.fullWeather);
                this.weatherIcon = this.imageService.getWeatherIcon(this.fullWeather.weather);
                this.changeBackgroundImage();
                this.filterSensors();
                this.obsDate = new Date(this.fullWeather.observationTime);
                // console.log('temp: ' , this.fullWeather.localtemp.tempMap.GARAGE, this.fullWeather.localtemp.tempDateMap.GARAGE);
            }
        });
        // verify if we have any errors
        this.wsApi.errorGenerated().subscribe(wthErr => {
            if (wthErr != null) {
                this.wthErrors = wthErr;
                this.fullWeather = null;
            }
        });
    }
    // load up full forecast to display.
    fullForecast() {
        this.router.navigate(['/extForecast']);
    }
    showRedToast() {
        this.toastService.show('Garage Door Open', {
            classname: 'bg-danger text-light',
            delay: 150000,
            autohide: true,
            removeKey: 1
        });
    }
    // show toaster for full screen
    enableFullScreenToaster() {
        this.toastService.show('Enable Full screen', {
            classname: 'bg-success text-light  toast-custom',
            autohide: false,
            navigate: 'fullscreen',
            removeKey: _model_constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].FULL_SCREEN_INDX
        });
    }
    ngOnDestroy() {
        this.wthSubscription.unsubscribe();
        clearInterval(this.currDateTimer);
    }
    loading() {
        return this.wsApi.loading;
    }
    // return the color for the alert
    alertType() {
        if (this.fullWeather) {
            if (this.fullWeather.alert) {
                const alertLvl = this.fullWeather.alert.level;
                if (alertLvl === _enums_alertsLvl__WEBPACK_IMPORTED_MODULE_1__["AlertLvl"].STATEMENT) {
                    return 'dark';
                }
                else if (alertLvl === _enums_alertsLvl__WEBPACK_IMPORTED_MODULE_1__["AlertLvl"].WARNING) {
                    return 'danger';
                }
                else if (alertLvl === _enums_alertsLvl__WEBPACK_IMPORTED_MODULE_1__["AlertLvl"].WATCH) {
                    return 'warning';
                }
            }
        }
        return 'light';
    }
    // navigate to the alert screen.
    showAlert() {
        this.router.navigate(['/alert']);
    }
    // test to change the background.
    changeBackgroundImage() {
        const b = this.imageService.getBackgroundImage(this.fullWeather.weather);
        this.renderer.setStyle(document.body, 'background-image', `url(${b})`);
    }
    // open the modal component to Rename the group
    openFormModal(btnNbr) {
        const modalRef = this.modalService.open(_password_modal_password_modal_component__WEBPACK_IMPORTED_MODULE_3__["PasswordModalComponent"]);
        modalRef.result.then((result) => {
            if (result === 'ValidPassword') {
                this.router.navigate(['/config']);
            }
        }).catch((error) => {
            if (error !== 'Cross click') {
                console.error('password error : ', error);
            }
        });
    }
    filterSensors() {
        // it will filter the sensors.. example, if we did not get any data from a sensor for more than a day, remove it.
        // pool sensor filter
        if (this.fullWeather.localtemp.tempPool) {
            const poolDate = new Date(this.fullWeather.localtemp.tmpPoolUpdDt);
            const timeSpent = this.currDate.getTime() - poolDate.getTime();
            //  console.log('time spent: ' , timeSpent, this.currDate.getTime() ,poolDate.getTime(), poolDate );
            if (timeSpent > 86400000) { //one day for the pool sensor
                this.fullWeather.localtemp.tmpPoolUpdDt = null;
                this.fullWeather.localtemp.tempPool = null;
            }
        }
    }
}
WeatherComponent.ɵfac = function WeatherComponent_Factory(t) { return new (t || WeatherComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵdirectiveInject"](_angular_router__WEBPACK_IMPORTED_MODULE_5__["Router"]), _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵdirectiveInject"](_services_weather_service__WEBPACK_IMPORTED_MODULE_6__["WeatherService"]), _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵdirectiveInject"](_services_toast_service__WEBPACK_IMPORTED_MODULE_7__["ToastService"]), _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵdirectiveInject"](_angular_core__WEBPACK_IMPORTED_MODULE_2__["Renderer2"]), _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵdirectiveInject"](_services_image_service_service__WEBPACK_IMPORTED_MODULE_8__["ImageService"]), _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵdirectiveInject"](_ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_9__["NgbModal"])); };
WeatherComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵdefineComponent"]({ type: WeatherComponent, selectors: [["app-weather"]], decls: 40, vars: 11, consts: [[1, "imageBack"], [1, "row"], [1, "col-md-12", "mt-1"], [1, "col-md-4", 2, "padding-right", "10px"], [1, "jumbotron", "card", "card-block", "ml-1", 2, "padding", "10px 8px 10px 08px", "height", "47vh"], ["style", "font-size:12vw;text-align: center;", 4, "ngIf"], ["class", "bottomleft", 4, "ngIf"], [1, "col-md-4", 2, "padding-right", "10px", "padding-left", "10px"], [1, "jumbotron", "card", "card-block", 2, "color", "white", "border-color", "white", "border-width", "2px", "padding", "10px 8px 10px 08px", "height", "47vh"], ["middleJumbotron", ""], ["alt", "weather", "width", "108", "height", "108", 1, "center", "rounded-circle", 3, "src"], ["class", "spinner-border spinner-border-sm mr-1", "style", "  text-align: center;font-size:3vw;", 4, "ngIf"], [4, "ngIf"], ["style", "font-size:1.5vw;", 4, "ngIf"], ["class", "bottomright", 4, "ngIf"], [1, "col-md-4", 2, "padding-left", "10px"], [1, "jumbotron", "card", "card-block", "mr-1", 2, "color", "white", "border-color", "white", "border-width", "2px", "padding", "10px 8px 10px 10px", "height", "47vh"], [1, "col-md-12", "mr-1"], [1, "jumbotron", "card", "card-block", "ml-1", "mr-1", 2, "padding", "10px 8px 10px 08px", "min-height", "200px", "height", "50vh"], ["style", "font-size: 2vw", "class", "bottomright", 4, "ngIf"], [1, "row", "ml-1"], [1, "col"], [1, "float-right", 2, "background-color", "white"], ["ngbDropdown", "", 1, "d-inline-block"], ["id", "dropdownBasic1", "ngbDropdownToggle", "", 1, "btn", "btn-primary"], ["ngbDropdownMenu", "", "aria-labelledby", "dropdownBasic1"], ["ngbDropdownItem", "", 3, "click"], [2, "font-size", "12vw", "text-align", "center"], [1, "bottomleft"], [1, "spinner-border", "spinner-border-sm", "mr-1", 2, "text-align", "center", "font-size", "3vw"], [2, "font-size", "2em", "text-align", "center", "font-size", "3vw"], ["class", "row", 4, "ngIf"], ["style", "text-align: left;font-size:2vw;", 4, "ngIf"], ["class", "col-md-6", 4, "ngIf"], [1, "col-md-6"], [2, "font-size", "2vw"], [2, "font-size", "2vw", "text-align", "right"], [2, "text-align", "left", "font-size", "2vw"], [2, "font-size", "1.5vw"], [1, "bottomright"], ["style", "text-align: center", 3, "type", "dismissible", "click", 4, "ngIf"], [1, "float-right"], [1, "mr-2", 3, "icon"], [3, "click"], [2, "font-size", "4vw"], [2, "font-size", "3vw"], [2, "text-align", "center", 3, "type", "dismissible", "click"], [1, "ml-2", "mr-1", 3, "icon"], [1, "bottomright", 2, "font-size", "2vw"]], template: function WeatherComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelement"](1, "app-toast");
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](2, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](3, "div", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](4, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](5, "div", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](6, "div", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](7, "div");
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](8, "Sensor 1");
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](9, WeatherComponent_div_9_Template, 2, 1, "div", 5);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](10, WeatherComponent_div_10_Template, 3, 4, "div", 6);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](11, "div", 7);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](12, "div", 8, 9);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelement"](14, "img", 10);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](15, WeatherComponent_div_15_Template, 1, 0, "div", 11);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](16, WeatherComponent_div_16_Template, 7, 5, "div", 12);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](17, WeatherComponent_div_17_Template, 2, 1, "div", 13);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](18, WeatherComponent_div_18_Template, 4, 4, "div", 14);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](19, "div", 15);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](20, "div", 16);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](21, "div");
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](22, "Sensor 2");
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](23, WeatherComponent_div_23_Template, 2, 1, "div", 5);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](24, WeatherComponent_div_24_Template, 3, 4, "div", 14);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](25, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](26, "div", 17);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](27, "div", 18);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](28, WeatherComponent_div_28_Template, 13, 6, "div", 12);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtemplate"](29, WeatherComponent_div_29_Template, 3, 4, "div", 19);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](30, "div", 20);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](31, "div", 21);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](32, "div", 22);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](33, "Tada");
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](34, "div", 23);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](35, "button", 24);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](36, "Settings");
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](37, "div", 25);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](38, "button", 26);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵlistener"]("click", function WeatherComponent_Template_button_click_38_listener() { return ctx.openFormModal(1); });
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](39, "Weather Config");
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](9);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx.fullWeather == null ? null : ctx.fullWeather.localtemp.tempSun);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx.fullWeather == null ? null : ctx.fullWeather.localtemp.tmpSunUpdDt);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("src", ctx.weatherIcon, _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵsanitizeUrl"]);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx.loading());
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx.fullWeather);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx.wthErrors);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx.obsDate);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](5);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx.fullWeather == null ? null : ctx.fullWeather.localtemp.tempShade);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx.fullWeather == null ? null : ctx.fullWeather.localtemp.tmpShadeUpdDt);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx.fullWeather);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("ngIf", ctx.obsDate);
    } }, directives: [_toast_toast_component__WEBPACK_IMPORTED_MODULE_10__["ToastComponent"], _angular_common__WEBPACK_IMPORTED_MODULE_11__["NgIf"], _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_9__["NgbDropdown"], _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_9__["NgbDropdownToggle"], _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_9__["NgbDropdownMenu"], _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_9__["NgbDropdownItem"], _fortawesome_angular_fontawesome__WEBPACK_IMPORTED_MODULE_12__["FaIconComponent"], _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_9__["NgbAlert"]], pipes: [_angular_common__WEBPACK_IMPORTED_MODULE_11__["DatePipe"]], styles: [".noCursor[_ngcontent-%COMP%] {\r\n    cursor: none;\r\n}\r\n\r\n.center[_ngcontent-%COMP%] { \r\n    display: block;\r\n    margin-left: auto;\r\n    margin-right: auto;\r\n    \r\n  }\r\n\r\n.bottomright[_ngcontent-%COMP%] {\r\n    position: absolute;\r\n    bottom: 8px;\r\n    right: 16px;\r\n  }\r\n\r\n.bottomleft[_ngcontent-%COMP%] {\r\n    position: absolute;\r\n    bottom: 8px;\r\n    left: 16px;\r\n  }\r\n\r\n\r\n\r\n\r\n\r\n@media screen and (min-width: 801px) {\r\n    .text-size[_ngcontent-%COMP%] {\r\n      font-size: 8em;\r\n    }\r\n  }\r\n\r\n\r\n\r\n@media screen and (max-width: 800px) {\r\n    .text-size[_ngcontent-%COMP%] {\r\n      font-size: 6em;\r\n    }\r\n  }\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvd2VhdGhlci93ZWF0aGVyLmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7QUFFQTtJQUNJLFlBQVk7QUFDaEI7O0FBRUE7SUFDSSxjQUFjO0lBQ2QsaUJBQWlCO0lBQ2pCLGtCQUFrQjtJQUNsQixnQkFBZ0I7RUFDbEI7O0FBQ0E7SUFDRSxrQkFBa0I7SUFDbEIsV0FBVztJQUNYLFdBQVc7RUFDYjs7QUFDQTtJQUNFLGtCQUFrQjtJQUNsQixXQUFXO0lBQ1gsVUFBVTtFQUNaOztBQUtGOzs7O3NCQUlzQjs7QUFDdEIsaUZBQWlGOztBQUNqRjtJQUNJO01BQ0UsY0FBYztJQUNoQjtFQUNGOztBQUVBLGlGQUFpRjs7QUFDakY7SUFDRTtNQUNFLGNBQWM7SUFDaEI7RUFDRiIsImZpbGUiOiJzcmMvYXBwL3dlYXRoZXIvd2VhdGhlci5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiXHJcblxyXG4ubm9DdXJzb3Ige1xyXG4gICAgY3Vyc29yOiBub25lO1xyXG59XHJcblxyXG4uY2VudGVyIHsgXHJcbiAgICBkaXNwbGF5OiBibG9jaztcclxuICAgIG1hcmdpbi1sZWZ0OiBhdXRvO1xyXG4gICAgbWFyZ2luLXJpZ2h0OiBhdXRvO1xyXG4gICAgLyogd2lkdGg6IDMwJTsgKi9cclxuICB9XHJcbiAgLmJvdHRvbXJpZ2h0IHtcclxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcclxuICAgIGJvdHRvbTogOHB4O1xyXG4gICAgcmlnaHQ6IDE2cHg7XHJcbiAgfVxyXG4gIC5ib3R0b21sZWZ0IHtcclxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcclxuICAgIGJvdHRvbTogOHB4O1xyXG4gICAgbGVmdDogMTZweDtcclxuICB9XHJcblxyXG5cclxuXHJcblxyXG4vKlxyXG50b3AgcGFkZGluZyBpcyAyNXB4XHJcbnJpZ2h0IHBhZGRpbmcgaXMgNTBweFxyXG5ib3R0b20gcGFkZGluZyBpcyA3NXB4XHJcbmxlZnQgcGFkZGluZyBpcyAxMDBweCovXHJcbi8qIElmIHRoZSBzY3JlZW4gc2l6ZSBpcyA2MDFweCB3aWRlIG9yIG1vcmUsIHNldCB0aGUgZm9udC1zaXplIG9mIDxkaXY+IHRvIDgwcHggKi9cclxuQG1lZGlhIHNjcmVlbiBhbmQgKG1pbi13aWR0aDogODAxcHgpIHtcclxuICAgIC50ZXh0LXNpemUge1xyXG4gICAgICBmb250LXNpemU6IDhlbTtcclxuICAgIH1cclxuICB9XHJcbiAgXHJcbiAgLyogSWYgdGhlIHNjcmVlbiBzaXplIGlzIDYwMHB4IHdpZGUgb3IgbGVzcywgc2V0IHRoZSBmb250LXNpemUgb2YgPGRpdj4gdG8gMzBweCAqL1xyXG4gIEBtZWRpYSBzY3JlZW4gYW5kIChtYXgtd2lkdGg6IDgwMHB4KSB7XHJcbiAgICAudGV4dC1zaXplIHtcclxuICAgICAgZm9udC1zaXplOiA2ZW07XHJcbiAgICB9XHJcbiAgfVxyXG5cclxuXHJcblxyXG4gICJdfQ== */"] });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵsetClassMetadata"](WeatherComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_2__["Component"],
        args: [{
                selector: 'app-weather',
                templateUrl: './weather.component.html',
                styleUrls: ['./weather.component.css']
            }]
    }], function () { return [{ type: _angular_router__WEBPACK_IMPORTED_MODULE_5__["Router"] }, { type: _services_weather_service__WEBPACK_IMPORTED_MODULE_6__["WeatherService"] }, { type: _services_toast_service__WEBPACK_IMPORTED_MODULE_7__["ToastService"] }, { type: _angular_core__WEBPACK_IMPORTED_MODULE_2__["Renderer2"] }, { type: _services_image_service_service__WEBPACK_IMPORTED_MODULE_8__["ImageService"] }, { type: _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_9__["NgbModal"] }]; }, null); })();


/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
const environment = {
    production: false
};
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/__ivy_ngcc__/fesm2015/platform-browser.js");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_1__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
_angular_platform_browser__WEBPACK_IMPORTED_MODULE_3__["platformBrowser"]().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(err => console.error(err));


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! C:\Users\ADMIN\workspace\pimoduleV2\web\weather\src\main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map