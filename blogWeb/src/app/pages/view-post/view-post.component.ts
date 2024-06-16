import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { PostService } from '../../service/post.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { CommentService } from '../../service/comment.service';


@Component({
  selector: 'app-view-post',
  templateUrl: './view-post.component.html',
  styleUrls: ['./view-post.component.scss']
})
export class ViewPostComponent {

  postId =  this.activatedRoute.snapshot.params['id'].replace('}', '');
  postData: any;
  commentForm!: FormGroup;
  comments: any;

  constructor(private postService: PostService,
              private activatedRoute: ActivatedRoute,
              private matSnackBar: MatSnackBar,
              private fb: FormBuilder,
              private commentService: CommentService) {
  }

  ngOnInit() {
    console.log(this.postId);
    this.getPostById();
    this.commentForm = this.fb.group({
      postedBy: [null, Validators.required],
      content: [null, Validators.required]

    });
  }

  getPostById() {
    const postIdClean = this.postId.replace('}', '');
    const postIdNumber = Number(postIdClean);
    if (!isNaN(postIdNumber)) {
      this.postService.getPostById(postIdNumber).subscribe(
        (res: any) => {
          this.postData = res;
          console.log(this.postData);
          this.getCommentsByPost();
        },
        (error: any) => {
          console.error('Something went wrong', error);
          this.matSnackBar.open('Something went wrong', 'Ok');
        }
      );
    } else {
      console.error('Invalid post id', this.postId);
      this.matSnackBar.open('Invalid post id', 'Ok');
    }
  }
  likePost() {
    this.postService.likePost(this.postData.id).subscribe(
      (res: any) => {
        this.matSnackBar.open('Post liked', 'Ok');
        this.getPostById();

      },
      (error: any) => {
        console.error('Something went wrong', error);
        this.matSnackBar.open('Something went wrong', 'Ok');
      }
    );
  }

  updatePost() {
    const updatedPost: any = {
      name: this.postData.name,
      content: this.postData.content,
      postedBy: this.postData.postedBy,
      img: this.postData.img,
      tags: this.postData.tags,
    };
  
    this.postService.updatePost(this.postData.id, updatedPost).subscribe(
      (res: any) => {
        this.matSnackBar.open('Post updated', 'Ok');
        this.getPostById();
      },
      (error: any) => {
        console.error('Something went wrong', error);
        this.matSnackBar.open('Something went wrong', 'Ok');
      }
    );
  }

  editMode = false;

toggleEditMode() {
  this.editMode = !this.editMode;
  if (!this.editMode) {
    this.updatePost();
  }
}

  publishComment() {
    const postedBy = this.commentForm.get('postedBy')?.value;
    const content = this.commentForm.get('content')?.value;
    this.commentService.createComment(this.postId, postedBy, content)
      .subscribe(res=>{
        this.matSnackBar.open("Comment Successfully Made","Ok");
        this.getPostById();
        this.getCommentsByPost();
      },error =>{
        this.matSnackBar.open("Something Wrong ...","Ok");
      }
      )

  }
  getCommentsByPost() {
    this.commentService.getAllCommentsByPost(this.postId).subscribe(
      (res: any) => {
        this.comments = res;
        console.log(this.comments);
      },
      (error: any) => {
        console.error('Something went wrong', error);
        this.matSnackBar.open('Something went wrong', 'Ok');
      }
    );
  }

}
