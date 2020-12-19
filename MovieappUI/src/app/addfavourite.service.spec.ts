import { TestBed } from '@angular/core/testing';

import { AddfavouriteService } from './addfavourite.service';

describe('AddfavouriteService', () => {
  let service: AddfavouriteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddfavouriteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
