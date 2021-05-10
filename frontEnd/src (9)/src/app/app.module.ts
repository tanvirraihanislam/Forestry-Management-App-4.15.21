import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandComponent } from './land/land.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CreateLandComponent } from './create-land/create-land.component';
import { UpdateLandComponent } from './update-land/update-land.component';
import { ContractComponent } from './contract/contract.component';
import { CreateContractComponent } from './create-contract/create-contract.component';
import { UpdateContractComponent } from './update-contract/update-contract.component';
import { ProductComponent } from './product/product.component';
import { CreateProductComponent } from './create-product/create-product.component';
import { UpdateProductComponent } from './update-product/update-product.component';
import { AdminComponent } from './admin/admin.component';
import { CreateAdminComponent } from './create-admin/create-admin.component';
import { UpdateAdminComponent } from './update-admin/update-admin.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { CreateSchedulerComponent } from './create-scheduler/create-scheduler.component';
import { SchedulerComponent } from './scheduler/scheduler.component';
import { UpdateSchedulerComponent } from './update-scheduler/update-scheduler.component';
import { UpdateOrderComponent } from './update-order/update-order.component';
import { OrderComponent } from './order/order.component';
import { CreateOrderComponent } from './create-order/create-order.component';
import { CustomerComponent } from './customer/customer.component';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';

@NgModule({
  declarations: [
    AppComponent,
    LandComponent,
    CreateLandComponent,
    UpdateLandComponent,
    ContractComponent,
    CreateContractComponent,
    UpdateContractComponent,
    ProductComponent,
    CreateProductComponent,
    UpdateProductComponent,
    AdminComponent,
    CreateAdminComponent,
    UpdateAdminComponent,
    LoginComponent,
    LogoutComponent,
    CreateSchedulerComponent,
    SchedulerComponent,
    UpdateSchedulerComponent,
    OrderComponent,
    CreateOrderComponent,
    UpdateOrderComponent,
    
    CustomerComponent,
    CreateCustomerComponent,
    UpdateCustomerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
