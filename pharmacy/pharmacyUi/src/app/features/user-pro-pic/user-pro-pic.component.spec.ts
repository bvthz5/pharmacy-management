import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProPicComponent } from './user-pro-pic.component';

describe('UserProPicComponent', () => {
  let component: UserProPicComponent;
  let fixture: ComponentFixture<UserProPicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserProPicComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserProPicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
