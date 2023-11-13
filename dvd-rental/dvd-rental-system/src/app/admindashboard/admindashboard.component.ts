import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { AdminDvdRentalService } from '../admindvdrental.service';
import { DisplayDialogComponent } from '../display-dialog/display-dialog.component';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { FilmDialogComponent } from '../film-dialog/film-dialog.component';
import { Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';


interface YourData {
  id: number;
  address: string;
  storeId: string;

}

@Component({
  selector: 'app-admindashboard',
  templateUrl: './admindashboard.component.html',
  styleUrls: ['./admindashboard.component.css']

  
 
})


export class AdmindashboardComponent {
  displayedColumns: string[] = ['storeid', 'address', 'action'];

  dataSource = new MatTableDataSource<YourData>();

  constructor(private adminService: AdminDvdRentalService,private dialog: MatDialog) {}
  openAddDialog(): void{
   // const dialogRef = this.dialog.open(FilmDialogComponent, 
      //{ width: '4000px' }); // Added closing parentheses after FilmDialogComponent
  }

  storeId1: string = "1";
  storeId2: string = "2";

  @Component({
    selector: 'add-dialog',
    template: `
      <h2>Add Items</h2>
      <div>
        <div>
          <label for="addStore">Add Store:</label>
          <input type="text" id="addStore" name="addStore">
        </div>
        <div>
          <label for="addStaff">Add Staff:</label>
          <input type="text" id="addStaff" name="addStaff">
        </div>
        <div>
          <label for="addFilms">Add Films:</label>
          <input type="text" id="addFilms" name="addFilms">
        </div>
      </div>
    `,
  })
  
  ngOnInit() {
    // const storeId1 = "1";
    // const storeId2 = "2"; // Replace with the actual store IDs you want to fetch
    this.loadData1(this.storeId1);
    this.loadData2(this.storeId2);
    // this.getFilmByStoreId() 
  }
  
  

  loadData1(storeId: string) {
    this.adminService.AdminStore(storeId).subscribe((data: YourData[]) => {
      //storeId property for each row
      data.forEach((row) => {
        row.storeId = storeId;
      });
      this.dataSource.data = data;
    });
  }
  
  loadData2(storeId: string) {
    this.adminService.AdminStore(storeId).subscribe((data: YourData[]) => {
      //  storeId for each row
      data.forEach((row) => {
        row.storeId = storeId;
      });
      this.dataSource.data = this.dataSource.data.concat(data);
    });
  }
  
  staff(id: string) {
    console.log(id);
    this.adminService.AdminStoreDetail(id).subscribe((data) => {
      // Open a dialog to display staff details
      this.dialog.open(DisplayDialogComponent, {
        data: { title: 'Staff Details', content: data } // Pass data to the dialog
      });
    });
  }

  film(id: string) {
    const storeId = id; // Replace with the actual store ID
    this.adminService.AdminFilm(storeId).subscribe(
      (response) => {
        // Open the dialog and pass the response data (table data)
        const dialogRef = this.dialog.open(FilmDialogComponent, {
          data: { tableData: response }, // Pass the response data to the dialog component
        });
      },
      (error) => {
        console.error(error);
      }
    );
  }
  openFilmDetailsDialog(storeId: string) {
    // Retrieve film details for the given store ID
    this.adminService.AdminFilm(storeId).subscribe(
      (response) => {
        // Open the dialog and pass the response data (table data)
        const dialogRef = this.dialog.open(FilmDialogComponent, {
          panelClass: 'dialog-content',
          data: { tableData: response }, // Pass the response data to the dialog component
        });
      },
      (error) => {
        console.error(error);
      }
    );
  }
}

