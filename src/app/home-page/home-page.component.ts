import { element } from 'protractor';
import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ProductServiceService } from '../product-service.service';
import { MatPaginator, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit,OnDestroy {



  displayedColumns: string[] = ['imgUrl','name','price','qty','id','qtyAdded','edit'];
  dataSource;
  product:any[];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  subscription:Subscription;

  constructor( private productService:ProductServiceService) { }

  ngOnInit() {

    this.productService.getAllProducts()
    .subscribe(res =>{
      this.product=res;
      console.log(this.product)
      this.dataSource = new MatTableDataSource(this.product);
      this.dataSource.paginator = this.paginator;
    })
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }

  applyFilter(query:string) {
    this.dataSource.filter = query.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  addToCart(element){

    console.log(element);
    this.productService.addToCart(element)
    .subscribe(res =>{
      console.log(res)
    })

  }

  completeOrderNow(){
    this.productService.completeOrder()
    .subscribe(res=>{
      console.log(res)
    })
  }

}
