import {RouterModule, Routes } from "@angular/router";
import { CreatePostComponent } from "./pages/create-post/create-post.component";
import { ViewAllComponent } from "./pages/view-all/view-all.component";
import { ViewPostComponent } from "./pages/view-post/view-post.component";
import { NgModule } from "@angular/core";
import {SearchByNameComponent} from "./pages/search-by-name/search-by-name.component";
import {UploadFileComponent} from "./pages/upload-file/upload-file.component";
import {AllFilesComponent} from "./pages/all-files/all-files.component";

const routes: Routes = [
  { path: 'create-post', component: CreatePostComponent },
  { path: 'view-all', component: ViewAllComponent },// Default route
  { path: 'view-post/:id', component: ViewPostComponent },
  { path: 'search-by-name', component: SearchByNameComponent },
  { path: "view-all-deck", component: AllFilesComponent },
  { path: "file", component: UploadFileComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
