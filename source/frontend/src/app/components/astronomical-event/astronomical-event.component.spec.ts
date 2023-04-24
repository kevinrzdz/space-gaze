import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AstronomicalEventComponent } from './astronomical-event.component';

describe('AstronomicalEventComponent', () => {
  let component: AstronomicalEventComponent;
  let fixture: ComponentFixture<AstronomicalEventComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AstronomicalEventComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AstronomicalEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
