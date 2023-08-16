import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoteviewDashboardComponent } from './noteview-dashboard.component';

describe('NoteviewDashboardComponent', () => {
  let component: NoteviewDashboardComponent;
  let fixture: ComponentFixture<NoteviewDashboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NoteviewDashboardComponent]
    });
    fixture = TestBed.createComponent(NoteviewDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
