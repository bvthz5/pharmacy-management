import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyDetainViewComponent } from './company-detain-view.component';

describe('CompanyDetainViewComponent', () => {
  let component: CompanyDetainViewComponent;
  let fixture: ComponentFixture<CompanyDetainViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompanyDetainViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CompanyDetainViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
