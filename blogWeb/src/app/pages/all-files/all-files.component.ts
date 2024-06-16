import { Component } from '@angular/core';
import { FileService } from '../../service/file.service';


@Component({
  selector: 'app-all-files',
  templateUrl: './all-files.component.html',
  styleUrl: './all-files.component.scss'
})
export class AllFilesComponent {

  files: any = [];

  constructor(private fileService: FileService) { }

  ngOnInit(): void {
    this.getFiles();
  }

  getFiles(): void {
    this.fileService.getFiles().subscribe(
      (response: any[]) => {
        response.forEach(element => {
          element.processedImg = 'data:image/jpeg;base64,' + element.data;
          this.files.push(element);
        });
      },
      error => {
        console.error('Error fetching files:', error);
      }
    );
  }


  downloadFile(fileId: number): void {
    this.fileService.downloadFile(fileId).subscribe(response => {
      let fileNameFromUrl = "file";
      if (fileNameFromUrl) {
        const contentType = response.headers.get("Content-Type");
        
        if (response.body !== null) {
          const blob = new Blob([response.body], { type: contentType || undefined });

          const link = document.createElement("a");
          link.href = window.URL.createObjectURL(blob);
          fileNameFromUrl += ".ydk"; // Add the .ydk extension to the file name
          link.download = fileNameFromUrl;

          link.click();

          window.URL.revokeObjectURL(link.href);
          link.remove();
        } else {
          console.log("Response body is null");
        }
      } else {
        console.log("Unable to extract file")
      }
    })
  }

}




