import { Admin } from "./admin";
import { Scheduler } from "./scheduler";

export interface Customer{
    customerId: string;
    customerName:string;
    customerType: string;
    customerPassword: string;
    customerEmail: string;
    customerAddress:string;
    customerTown: string;
    customerPostalCode: string;
    customerContact:string;

    
}