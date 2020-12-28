import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { GenericHttpService } from 'src/app/services/generichttp.service';
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { ActionItem } from 'src/app/models/actionItem';
import { Item } from 'src/app/models/item';
import { Router } from '@angular/router';

@Component({
  selector: 'app-actionitems',
  templateUrl: './actionitems.component.html',
  styleUrls: ['./actionitems.component.css']
})
export class ActionitemsComponent implements OnInit {

  modalRef: BsModalRef;
  public actionItems: ActionItem[];
  public items: Item[];
  private url: string;
  private urlItem: string;
  public aiForm: FormGroup;
  public successMessage: string;
  public errorMessage: string;

  p2: number = 1;


  constructor(
    private formBuilder: FormBuilder,
    private modalService: BsModalService,
    private genericService: GenericHttpService,
    private router: Router
  ) {
    this.url = "auctionItems";
    this.urlItem = 'items';
  }

  pageChanged(event: any): void {
    this.p2 = event.page;
  }

  ngOnInit(): void {
    this.aiForm = this.formBuilder
      .group({
        actionItemId: [],
        currentBid: [],
        reservePrice: ['', Validators.required],
        item: []
      });
    this.successMessage = "";
    this.errorMessage = "";
    this.getAllActionItems();
    this.getAllItems();
  
  }

  onSubmit() {
    console.log("form: "+this.aiForm.value);
    this.createActionItem(this.aiForm.value);
    this.formReset();
  }

  openModal(template2: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template2);
  }

  getAllItems() {
    this.genericService.getAll<Item>(this.urlItem)
      .subscribe((data: Item[]) => {
        this.items = data;        
        console.log("items:", data);
      }, (errorResponse) => {
        if(errorResponse.status == 404){ 
          this.router.navigate(['/items']);
        } else {
          this.errorMessage = errorResponse.error;
          console.log("item not fetched!, Error: ", this.errorMessage);
        }        
      });
  }


  getAllActionItems() {
    debugger
    this.genericService.getAll<ActionItem>(this.url)
      .subscribe((data: ActionItem[]) => {
        this.actionItems = data; 
        console.log("ActionItems:", data);
      }, (errorResponse) => { 
        this.errorMessage = errorResponse.error;
        console.log("actionItem not fetched!, Error: ", this.errorMessage);         
      });
  }

  public createActionItem(actionItem: ActionItem) {
    debugger
    this.genericService.add(actionItem, this.url).subscribe(
      data => {
        this.successMessage = "ActionItem Created successfully!";
        console.log(this.successMessage)
        this.getAllActionItems();
      }, (errorResponse) => {
        this.errorMessage = errorResponse.error;
        console.log("ActionItem not created!, Error: ", this.errorMessage);
      });
  }

  formReset() {
    this.aiForm = this.formBuilder.group({
      actionItemId: '',
      currentBid: '',
      reservePrice: '',
      item: {}
    });
  }


}
