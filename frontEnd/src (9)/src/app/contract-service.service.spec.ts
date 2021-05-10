import { TestBed } from '@angular/core/testing';

import { ContractServiceService } from './contract-service.service';

describe('ContractServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ContractServiceService = TestBed.get(ContractServiceService);
    expect(service).toBeTruthy();
  });
});
