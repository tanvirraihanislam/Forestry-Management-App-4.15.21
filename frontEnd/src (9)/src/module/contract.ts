import { Admin } from "./admin";

export interface Contract{

    contractNumber:string;
    quotation:number;
    startDate:Date;
    endDate:Date;
    contractStatus:string

    admin:Admin

}
