import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PagosViewComponent } from './pagos-view.component';

describe('PagosViewComponent', () => {
  let component: PagosViewComponent;
  let fixture: ComponentFixture<PagosViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PagosViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PagosViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
