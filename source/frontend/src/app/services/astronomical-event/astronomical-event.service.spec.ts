import { TestBed } from '@angular/core/testing';

import { AstronomicalEventService } from './astronomical-event.service';

describe('AstronomicalEventService', () => {
  let service: AstronomicalEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AstronomicalEventService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
