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
    ships: Ship[] = [];

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
    public clickcounter = 0;
    deleteCourse() {
        this.clickcounter++;
        //    this.courseService.delete();
    }

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
                this.ships = [];
            } else {
                this.ships = curDto;
            }
        });
    }

    findByFreq(vlf, lf, mf, hf, vhf) {
        this.shipService.findByFreq(vlf, lf, mf, hf, vhf).subscribe(curDto => {
            if (!curDto) {
                this.ships = [];
            } else {
                this.ships = curDto;
            }
        });
    }
}
