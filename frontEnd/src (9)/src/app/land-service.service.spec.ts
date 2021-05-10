import { TestBed } from '@angular/core/testing';

import { LandServiceService } from './land-service.service';

describe('LandServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LandServiceService = TestBed.get(LandServiceService);
    expect(service).toBeTruthy();
  });
});
