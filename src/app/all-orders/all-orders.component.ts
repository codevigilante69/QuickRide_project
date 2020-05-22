import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Subscription } from 'rxjs';
import { ProductServiceService } from '../product-service.service';

@Component({
  selector: 'app-all-orders',
  templateUrl: './all-orders.component.html',
  styleUrls: ['./all-orders.component.css']
})
export class AllOrdersComponent implements OnInit {

  displayedColumns: string[] = ['imgUrl','orderId','productId','name','price','qtyAdded'];
  dataSource;
  orders:any[];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  subscription:Subscription;


  constructor(private productService:ProductServiceService) { }

  refresh(){
    this.subscription.unsubscribe();
    this.subscription=this.productService.getAllOrders()
    .subscribe(res =>{
      this.orders=res;
      console.log(this.orders)
      this.dataSource = new MatTableDataSource(this.orders);
      this.dataSource.paginator = this.paginator;
    })

  }

  ngOnInit() {

    this.subscription=this.productService.getAllOrders()
    .subscribe(res =>{
      this.orders=res;
      console.log(this.orders)
      this.dataSource = new MatTableDataSource(this.orders);
      this.dataSource.paginator = this.paginator;
    })
  }

  applyFilter(query:string) {
    this.dataSource.filter = query.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
