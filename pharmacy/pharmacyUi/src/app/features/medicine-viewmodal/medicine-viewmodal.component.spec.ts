import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicineViewmodalComponent } from './medicine-viewmodal.component';

describe('MedicineViewmodalComponent', () => {
  let component: MedicineViewmodalComponent;
  let fixture: ComponentFixture<MedicineViewmodalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicineViewmodalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedicineViewmodalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
