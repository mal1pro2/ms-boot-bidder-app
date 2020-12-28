import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { GenericHttpService } from 'src/app/services/generichttp.service';
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { ActionItem } from 'src/app/models/actionItem';
import { Bid } from 'src/app/models/bid';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bids',
  templateUrl: './bids.component.html',
  styleUrls: ['./bids.component.css']
})
export class BidsComponent implements OnInit {

  modalRef: BsModalRef;
  public actionItems: ActionItem[];
  public bids: Bid[];
  private url: string;
  private urlAction: string;
  public bForm: FormGroup;
  public successMessage: string;
  public errorMessage: string;
  public outbidmessage: string;

  idToBeDeleted = ''; 
  message: string;

  p3: number = 1;


  constructor(
    private formBuilder: FormBuilder,
    private modalService: BsModalService,
    private genericService: GenericHttpService,
    private router: Router
  ) {
    this.url = "bids";
    this.urlAction = 'auctionItems';
  }

  pageChanged(event: any): void {
    this.p3 = event.page;
  }

  ngOnInit(): void {
    this.bForm = this.formBuilder
      .group({
        bidId: [],
        maxAutoBidAmount: [],
        bidderName: [],
        actionItem: []
      });
    this.successMessage = "";
    this.errorMessage = "";
    this.outbidmessage = "";
    this.getAllActionItems();
    this.getAllBids();

  }


  openConfirmModal(template4: TemplateRef<any>, id: any) {
    this.modalRef = this.modalService.show(template4, { class: 'modal-sm' });
    this.idToBeDeleted = id;
  }

  confirm(): void {
    this.message = 'Confirmed!';
    this.modalRef.hide();
    this.quitBid();
  }

  delete():void{
    console.log('deleted',this.idToBeDeleted,' record');
  }


  decline(): void {
    this.message = 'Declined!';
    this.modalRef.hide();

  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  onSubmit() {
    console.log("form: " + this.bForm.value);
    this.createBid(this.bForm.value);
    this.formReset();
  }


  getAllBids() {
    this.genericService.getAll<Bid>(this.url)
      .subscribe((data: Bid[]) => {
        this.bids = data;
        console.log("Bids:", data);
      }, (errorResponse) => { 
          this.errorMessage = errorResponse.error;
          console.log("Bids not fetched!, Error: ", this.errorMessage);         
      });
  }

  quitBid() {
    this.genericService.getById(this.url + '/outbid?id=' + this.idToBeDeleted)
    .subscribe((data: Bid[]) => {
      this.bids = data;
      this.outbidmessage= 'You have Quit your Bid!!';
      console.log("Bids:", data);
    }, (errorResponse) => {
      this.errorMessage = errorResponse.error;
      console.log("actionItem not fetched!, Error: ", this.errorMessage);
    });
  }

  getAllActionItems() {
    this.genericService.getAll<ActionItem>(this.urlAction)
      .subscribe((data: ActionItem[]) => {
        this.actionItems = data;
        console.log("ActionItems:", data);
      }, (errorResponse) => {
        if(errorResponse.status == 404){ 
          this.router.navigate(['/auctionItems']);
        } else {
          this.errorMessage = errorResponse.error;
          console.log("actionItem not fetched!, Error: ", this.errorMessage);
        }   
        
      });
  }

  public createBid(bid: Bid) {
    debugger
    this.genericService.add(
      {
        "auctionItemId": bid.actionItem.actionItemId,
        "bidderName": bid.bidderName,
        "maxAutoBidAmount": bid.maxAutoBidAmount
      }, this.url).subscribe(
        data => {
          console.log(this.successMessage)
          this.getAllBids();
        }, (errorResponse) => {
          this.errorMessage = errorResponse.error;
          console.log("Bids not created!, Error: ", this.errorMessage);
        });
  }

  formReset() {
    this.bForm = this.formBuilder.group({
      bidId: '',
      maxAutoBidAmount: '',
      bidderName: '',
      actionItem: ''
    });
  }
}

