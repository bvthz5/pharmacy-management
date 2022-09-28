import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpiredMedicineComponent } from './expired-medicine.component';

describe('ExpiredMedicineComponent', () => {
  let component: ExpiredMedicineComponent;
  let fixture: ComponentFixture<ExpiredMedicineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExpiredMedicineComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExpiredMedicineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
