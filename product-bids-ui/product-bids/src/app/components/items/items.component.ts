import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { Item } from 'src/app/models/item';
import { GenericHttpService } from 'src/app/services/generichttp.service';
import { FormGroup, FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})
export class ItemsComponent implements OnInit {

  modalRef: BsModalRef;
  public items: Item[];
  private url: string;
  public form: FormGroup; 
  public successMessage: string;
  public errorMessage: string;

  page: number = 1;
  

  constructor(
    private formBuilder: FormBuilder,
    private modalService: BsModalService,  
    private genericService: GenericHttpService
  ) {
    this.url = "items";
  }

  pageChanged(event: any): void {
    this.page = event.page;
  }

  ngOnInit(): void {
    this.form = this.formBuilder
    .group({
      id: [],
      description: ['', Validators.required] 
    });  
    this.successMessage = "";
    this.errorMessage = "";
    this.getAllItems();
  }

  onSubmit() {
    this.createItem(this.form.value);
    this.formReset();
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  getAllItems() {
    this.genericService.getAll<Item>(this.url)
      .subscribe((data: Item[]) => {
        this.items = data;
        console.log("items:", data);
      }, (errorResponse) => {
        this.errorMessage = errorResponse.error;
        console.log("item not fetched!, Error: ", this.errorMessage);
      });
  } 

  public createItem(item: Item) { 
    this.genericService.add(item,this.url).subscribe( 
      data => {
        this.successMessage = "Item Created successfully!";   
        console.log(this.successMessage) 
        this.getAllItems();
      },(errorResponse) => {
        this.errorMessage = errorResponse.error;
        console.log("Item not created!, Error: ",this.errorMessage); 
      }); 
  }

  formReset(){
    this.form = this.formBuilder.group({  
      description: ''
    });
  }

}
