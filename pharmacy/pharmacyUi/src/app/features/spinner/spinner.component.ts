import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ApiService } from '../../common-lib/service/api.service';

@Component({
  selector: 'app-spinner',
  templateUrl: './spinner.component.html',
  styleUrls: ['./spinner.component.css']
})
export class SpinnerComponent implements OnInit {

  constructor(private service:ApiService, private cdRef:ChangeDetectorRef) { }
  showSpinner = false;



  ngOnInit(): void {
    this.init();
  }
  init() {
    this.service.getSpinnerObserver().subscribe((status) => {
      this.showSpinner = status === 'start';
      this.cdRef.detectChanges();
    });
  }
}

