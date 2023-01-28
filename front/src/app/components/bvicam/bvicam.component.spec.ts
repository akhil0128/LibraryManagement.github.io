import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BvicamComponent } from './bvicam.component';

describe('BvicamComponent', () => {
  let component: BvicamComponent;
  let fixture: ComponentFixture<BvicamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BvicamComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BvicamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
