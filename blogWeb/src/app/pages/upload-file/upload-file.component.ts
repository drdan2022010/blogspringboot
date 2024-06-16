import { Component } from '@angular/core';
import { FileService } from '../../service/file.service';



@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrl: './upload-file.component.scss'
})
export class UploadFileComponent {

  selectedFile: File | null;
  uploadProgress: number = 0;
  fileName: string = '';

  constructor(private fileUploadService: FileService) { 
    this.selectedFile = new File([], '');
  }

  onFileSelected(event: any): void {
    const fileList: FileList = event.target.files;
    if (fileList && fileList.length > 0) {
      this.selectedFile = fileList[0];
    }
  }

  uploadFile(): void {
    if (this.selectedFile && this.fileName) {
        const fileWithNewName = new File([this.selectedFile], this.fileName, { type: this.selectedFile.type });

        this.fileUploadService.uploadFile(fileWithNewName)
            .subscribe(progress => {
                this.uploadProgress = progress;
                if (progress === 100) {
                    alert("File upload completed")
                    // File upload completed
                    this.selectedFile = null;
                }
            });
    }
}
}