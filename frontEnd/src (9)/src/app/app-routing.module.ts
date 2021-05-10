import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { ContractServiceService } from './contract-service.service';
import { ContractComponent } from './contract/contract.component';
import { CreateAdminComponent } from './create-admin/create-admin.component';
import { CreateContractComponent } from './create-contract/create-contract.component';
import { CreateLandComponent } from './create-land/create-land.component';
import { CreateProductComponent } from './create-product/create-product.component';
import { LandComponent } from './land/land.component';
import { ProductComponent } from './product/product.component';
import { UpdateAdminComponent } from './update-admin/update-admin.component';
import { UpdateContractComponent } from './update-contract/update-contract.component';
import { UpdateLandComponent } from './update-land/update-land.component';
import { UpdateProductComponent } from './update-product/update-product.component';
import { AuthGuardService } from './auth-guard.service';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { CreateSchedulerComponent } from './create-scheduler/create-scheduler.component';
import { UpdateSchedulerComponent } from './update-scheduler/update-scheduler.component';
import { SchedulerComponent } from './scheduler/scheduler.component';
import { OrderComponent } from './order/order.component';
import { CreateOrderComponent } from './create-order/create-order.component';
import { UpdateOrderComponent } from './update-order/update-order.component';
import { CustomerComponent } from './customer/customer.component';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';

const routes: Routes = [


  {path:'login', component:LoginComponent},
  {path:'logout',component:LogoutComponent},

  

  {path: 'landlist', component:LandComponent, canActivate:[AuthGuardService]},
//  {path:'create/:landId/:surveyNumber/:ownerName/:landArea',component:CreateLandComponent}
  {path: 'create',component:CreateLandComponent, canActivate:[AuthGuardService]},
  {path: 'update/:landId',component:UpdateLandComponent, canActivate:[AuthGuardService]},



  {path: 'contractlist',   component:ContractComponent, canActivate:[AuthGuardService]},
  {path: 'createcontract', component:CreateContractComponent, canActivate:[AuthGuardService]},
  {path: 'updatecontract/:contractNumber',component:UpdateContractComponent, canActivate:[AuthGuardService]},



  {path: 'productlist',   component:ProductComponent, canActivate:[AuthGuardService] },
  {path: 'createproduct', component:CreateProductComponent, canActivate:[AuthGuardService]},
  {path: 'updateProduct/:productId',component:UpdateProductComponent, canActivate:[AuthGuardService]},


  {path: 'adminlist', component:AdminComponent, canActivate:[AuthGuardService]},
//  {path:'create/:landId/:surveyNumber/:ownerName/:landArea',component:CreateLandComponent}
  {path:'createadmin',component:CreateAdminComponent, canActivate:[AuthGuardService]},
  {path:'update-admin/:adminId',component:UpdateAdminComponent, canActivate:[AuthGuardService]},



  {path: 'schedulerlist', component:SchedulerComponent, canActivate:[AuthGuardService]},
  {path: 'createscheduler', component:CreateSchedulerComponent, canActivate:[AuthGuardService]},
  {path: 'updatescheduler/:schedulerId', component:UpdateSchedulerComponent, canActivate:[AuthGuardService]},


  {path: 'orderlist', component:OrderComponent, canActivate:[AuthGuardService]},
//  {path:'create/:landId/:surveyNumber/:ownerName/:landArea',component:CreateLandComponent}
  {path:'create-order', component:CreateOrderComponent, canActivate:[AuthGuardService]},
  {path:'update-order/:orderNumber', component:UpdateOrderComponent, canActivate:[AuthGuardService]},


  {path: 'customerlist',   component:CustomerComponent, canActivate:[AuthGuardService]},
  {path: 'createcustomer', component:CreateCustomerComponent, canActivate:[AuthGuardService]},
  {path: 'updatecustomer/:customerId',component:UpdateCustomerComponent, canActivate:[AuthGuardService]}
 



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
