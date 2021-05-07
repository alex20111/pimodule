import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators  } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfigService } from '../services/config.service';

@Component({
  selector: 'app-password-modal',
  templateUrl: './password-modal.component.html',
  styleUrls: ['./password-modal.component.css']
})
export class PasswordModalComponent implements  OnInit {

  error: string;
  passwordForm: FormGroup;

  constructor(private formBuilder: FormBuilder, public activeModal: NgbActiveModal, private cfg: ConfigService) { }

  ngOnInit(): void {
    this.passwordForm = this.formBuilder.group({
      frm_password: ['', [Validators.required, Validators.minLength(2)]]
    });
  }

  submitForm(): void {

    this.error = undefined;

    const validPass = this.cfg.passwordValid(this.passwordForm.value.frm_password);

    if (validPass){
      this.cfg.authenticated = true;
      this.activeModal.close('ValidPassword');

    } else {
      this.error = 'Password invalid';
    }


  }

}
