import { Component, Inject } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-side-dialogue',
  templateUrl: './side-dialogue.component.html',
  styleUrls: ['./side-dialogue.component.css']
})
export class SideDialogueComponent {
  constructor(public dialogRef: MatDialogRef<SideDialogueComponent>, private authService: AuthService, private router: Router) { }
  // GETTING ITEMS FROM LOCAL STORAGE
  name = localStorage.getItem('FullName');
  id = localStorage.getItem('StoreId');
  email = localStorage.getItem('Email');

  // CLOSE DIALOG BOX METHOD
  closeDialogue(): void {
    this.dialogRef.close();
  }

  // LOGOUT METHOD
  logout() {
    // Calling the logout method from AuthService
    this.authService.logout();
    this.router.navigate(['/login']);
    this.dialogRef.close('logout');
  }
}
