import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsteroidDetailsComponent } from './asteroid-details.component';

describe('DetailsComponent', () => {
  let component: AsteroidDetailsComponent;
  let fixture: ComponentFixture<AsteroidDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AsteroidDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AsteroidDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
