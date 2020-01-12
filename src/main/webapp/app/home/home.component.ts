import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
//import { MatTableModule } from '@angular/material/table';

import { LoginModalService, Principal, Account } from 'app/core';
import { CourseService } from 'app/shared/service/CourseService';
import { CourseDto } from 'app/shared/model/course-dto.model';
import { CourseWithTNDto } from 'app/shared/model/courseWithTN-dto.model';
import { Ship } from 'app/shared/model/ship.model';
import { ShipService } from 'app/shared/service/ShipService';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.css']
})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;
    classeNameNeedToReg: string;

    courses: CourseDto[] = [];
    ships_name: Ship[] = [];
    ships_freq: Ship[] = [];
    country = ['USA', 'Japan'];
    default_acc = 0.98;

    coursesWithTN: CourseWithTNDto[] = [];

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private shipService: ShipService,
        private courseService: CourseService
    ) {}

    ngOnInit() {
        this.principal.identity().then(account => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', message => {
            this.principal.identity().then(account => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    getAllCourses() {
        this.courseService.getCourseInfo().subscribe(curDto => {
            if (!curDto) {
                this.courses = [];
            } else {
                this.courses = curDto;
            }
        });
    }

    getAllCoursesWithTN() {
        this.courseService.getCourseInfoWithTN().subscribe(curDto => {
            if (!curDto) {
                this.coursesWithTN = [];
            } else {
                this.coursesWithTN = curDto;
            }
        });
    }

    // registerCourse(courseName) {
    //
    // }

    clearAllCourses() {
        this.courses = [];
    }

    //addCourseToStudent() {
    //    const courseName = 'temp';
    //    this.courseService.addCourseToStudent(courseName, currentUserCredential);
    //}

    /*
    findByName(name) {
        this.courseService.findByName(name).subscribe(curDto => {
            if (!curDto) {
                this.courses = [];
            } else {
                this.courses = curDto;
            }
        });
    }

     */

    findByName(name) {
        this.shipService.findByName(name).subscribe(curDto => {
            if (!curDto) {
                this.ships_name = [];
            } else {
                this.ships_name = curDto;
            }
        });
    }

    defaultTo(arr, str) {
        for (let i = 0; i < arr.length; i++) {
            if (arr[i] == '') {
                arr[i] = str;
            }
        }
        return arr;
    }

    findByFreq(vlf, lf, mf, hf, vhf, af, count, acc) {
        const arg = this.defaultTo([vlf, lf, mf, hf, vhf, af], '-1');
        if (count == '') {
            count = 'NA';
        }
        if (acc == '' || Number(acc) < 0 || Number(acc) > 1) {
            acc = this.default_acc;
        }
        this.shipService.findByFreq(arg, acc, count).subscribe(curDto => {
            if (!curDto) {
                this.ships_freq = [];
            } else {
                this.ships_freq = curDto;
            }
        });
    }

    resetFreqSearch() {
        this.ships_freq = [];
    }

    postShip(name, count, type, vlf, lf, mf, hf, vhf, af, tpk, num_blades) {
        const argStr = this.defaultTo([name, count, type], 'NA');
        const argNum = this.defaultTo([vlf, lf, mf, hf, vhf, af, tpk, num_blades], '-1');
        this.shipService.postShip(argStr, argNum);
    }
}
