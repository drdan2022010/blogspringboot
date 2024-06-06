import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core'; // Added import for Injectable

// Mock PostService for demonstration
@Injectable({ providedIn: 'root' })
class PostService {
  getAllPosts(): Observable<any> {
    // Mocking API call
    return new Observable<any>((observer) => {
      setTimeout(() => {
        observer.next([{ id: 1, title: 'Post 1' }, { id: 2, title: 'Post 2' }]);
        observer.complete();
      }, 1000);
    });
  }
}

@Component({
  selector: 'app-view-all',
  templateUrl: './view-all.component.html',
  styleUrls: ['./view-all.component.scss']
})
export class ViewAllComponent implements OnInit {

  allPosts: any;

  constructor(private snackBar: MatSnackBar, private postService: PostService) { }

  ngOnInit() {
    this.getAllPosts();
  }

  getAllPosts() {
    this.postService.getAllPosts().subscribe(
      (res: any) => {
        console.log(res);
        this.allPosts = res;
      },
      (error: any) => {
        this.snackBar.open('Something went wrong', 'Ok');
      }
    );
  }
}
