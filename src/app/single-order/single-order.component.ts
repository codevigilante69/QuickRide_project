import { Component, OnInit, ViewChild } from '@angular/core';
import { ProductServiceService } from '../product-service.service';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-single-order',
  templateUrl: './single-order.component.html',
  styleUrls: ['./single-order.component.css']
})
export class SingleOrderComponent implements OnInit {


  displayedColumns: string[] = ['imgUrl','productId','name','price','qtyAdded'];
  dataSource;
  product:any[];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  subscription:Subscription;
  orders:any[]
  
  orderId:any;


  constructor(private productServiceService:ProductServiceService) { }

  ngOnInit() {
    
    
  }

  getOrderDetail(){
    this.productServiceService.getProductsForOrder(this.orderId)
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
