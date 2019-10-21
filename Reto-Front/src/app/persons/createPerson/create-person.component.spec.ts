import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePersonComponent } from './create-person.component';

describe('ManagePersonComponent', () => {
  let component: CreatePersonComponent;
  let fixture: ComponentFixture<CreatePersonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatePersonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
