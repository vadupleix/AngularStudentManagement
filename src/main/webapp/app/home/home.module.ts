import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
//import {MatTableModule} from '@angular/material/table';

import { JiuzhangquanzhankeSharedModule } from 'app/shared';
import { HOME_ROUTE, HomeComponent } from './';
import { CourseService } from 'app/shared/service/CourseService';
import { ShipService } from 'app/shared/service/ShipService';

@NgModule({
    imports: [JiuzhangquanzhankeSharedModule, RouterModule.forChild([HOME_ROUTE])],
    declarations: [HomeComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    providers: [CourseService, ShipService]
    //providers: [ShipService]
})
export class JiuzhangquanzhankeHomeModule {}
