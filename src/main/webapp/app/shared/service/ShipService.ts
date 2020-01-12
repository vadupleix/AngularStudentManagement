import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ship } from 'app/shared/model/ship.model';
import { SERVER_API_URL } from 'app/app.constants';
import { CourseWithTNDto } from 'app/shared/model/courseWithTN-dto.model';

@Injectable()
export class ShipService {
    private findByNameUrl = SERVER_API_URL + 'api/ship/findByName';
    private findByFreqUrl = SERVER_API_URL + 'api/ship/findByFreq';
    private postShipUrl = SERVER_API_URL + 'api/ship/postShip';

    constructor(private http: HttpClient) {}

    findByName(shipName: String): Observable<Ship[]> {
        return this.http.get<Ship[]>(`${this.findByNameUrl}/${shipName}`);
    }

    findByFreq(arg, acc, count): Observable<Ship[]> {
        //private str = vlf.toString + '%' + lf.toString + '%' + mf.toString + '%' + hf.toString + '%' + vhf.toString;
        return this.http.get<Ship[]>(
            `${this.findByFreqUrl}/${arg[0] +
                '&' +
                arg[1] +
                '&' +
                arg[2] +
                '&' +
                arg[3] +
                '&' +
                arg[4] +
                '&' +
                arg[5] +
                '&' +
                acc +
                '&' +
                count}`
        );
    }

    postShip(argStr, argNum) {
        const ship: Ship = {
            id: 0, //never used
            shipName: argStr[0],
            country: argStr[1],
            type: argStr[2],
            freqVeryLow: Number(argNum[0]),
            freqLow: Number(argNum[1]),
            freqMed: Number(argNum[2]),
            freqHigh: Number(argNum[3]),
            freqVeryHigh: Number(argNum[4]),
            freqActive: Number(argNum[5]),
            tpk: Number(argNum[6]),
            numBlades: Number(argNum[7])
        };
        this.http.post(`${this.postShipUrl}`, ship).subscribe(
            data => {
                alert('ok');
            },
            error => {
                console.log(JSON.stringify(error.json()));
            }
        );
    }

    /*addCourseToStudent(courseName: String, currentUserCredential: String) {
        return this.http.post(SERVER_API_URL + 'api/course/addCourseToStudent', { courseName, currentUserCredential });
    }*/
}
