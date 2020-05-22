import { element } from 'protractor';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {

  private _url="http://localhost:8085/orders/get_all_products";
  private cartUrl="http://localhost:8085/orders/add_product";
  private getOrderUrl="http://localhost:8085/orders/get_all_orders";
  private getSigleOrderUrl="http://localhost:8085/orders/get_single_order";
  private completeOrderUrl="http://localhost:8085/orders/complete_order";

  private data={
    userId:"1",
    productId:'',
    qty:''
  };
  constructor(private http: HttpClient) { }

  getAllProducts():Observable<any>{
    return this.http.get<any>(this._url) ;
  }


  addToCart(row){


    this.data.qty=row.qtyAdded;
    this.data.productId=row.id;

    console.log(this.data);
    return this.http.post<any>(this.cartUrl,this.data);
  }


  getAllOrders(){
    return this.http.get<any>(this.getOrderUrl)
  }

  getProductsForOrder(orderId){

    return this.http.get<any>(this.getSigleOrderUrl,{ params: { orderId:orderId}})
  }

  completeOrder(){
    return this.http.get<any>(this.completeOrderUrl);
  }
}
