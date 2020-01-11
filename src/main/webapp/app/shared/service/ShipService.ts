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

    constructor(private http: HttpClient) {}

    findByName(shipName: String): Observable<Ship[]> {
        return this.http.get<Ship[]>(`${this.findByNameUrl}/${shipName}`);
    }

    findByFreq(vlf, lf, mf, hf, vhf): Observable<Ship[]> {
        //private str = vlf.toString + '%' + lf.toString + '%' + mf.toString + '%' + hf.toString + '%' + vhf.toString;
        return this.http.get<Ship[]>(
            `${this.findByFreqUrl}/${String(vlf) + '&' + String(lf) + '&' + String(mf) + '&' + String(hf) + '&' + String(vhf)}`
        );
    }

    /*addCourseToStudent(courseName: String, currentUserCredential: String) {
        return this.http.post(SERVER_API_URL + 'api/course/addCourseToStudent', { courseName, currentUserCredential });
    }*/
}
