import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WatchlaterDashboardComponent } from './watchlater-dashboard.component';

describe('WatchlaterDashboardComponent', () => {
  let component: WatchlaterDashboardComponent;
  let fixture: ComponentFixture<WatchlaterDashboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WatchlaterDashboardComponent]
    });
    fixture = TestBed.createComponent(WatchlaterDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
