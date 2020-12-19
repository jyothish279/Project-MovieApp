import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewfavouritesComponent } from './viewfavourites.component';

describe('ViewfavouritesComponent', () => {
  let component: ViewfavouritesComponent;
  let fixture: ComponentFixture<ViewfavouritesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewfavouritesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewfavouritesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
