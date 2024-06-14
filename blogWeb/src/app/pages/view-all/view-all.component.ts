import { Component, OnInit } from '@angular/core';
import { PostService } from '../../service/post.service';

@Component({
  selector: 'app-view-all',
  templateUrl: './view-all.component.html',
  styleUrls: ['./view-all.component.scss']
})
export class ViewAllComponent implements OnInit {
  allPosts: any[] = [];

  constructor(private postService: PostService) { }

  ngOnInit() {
    this.getAllPosts();
  }

  getAllPosts() {
    this.postService.getAllPosts().subscribe(
      (res: any) => {
        this.allPosts = res;
      },
      (error: any) => {
        console.error('Something went wrong', error);
      }
    );
  }
}
