import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './user/login/login.component';
import { HomeComponent } from './home/home.component';
import { FooterComponent } from './sidebar/footer/footer.component';
import { NavigationComponent } from './sidebar/navigation/navigation.component';
import { PageNotFoundComponent } from './sidebar/page-not-found/page-not-found.component';
import { PersonalComponent } from './user/personal/personal.component';
import { WriteArticleComponent } from './article/write-article/write-article.component';
import { AddAlbumComponent } from './sidebar/add-album/add-album.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    FooterComponent,
    NavigationComponent,
    PageNotFoundComponent,
    PersonalComponent,
    WriteArticleComponent,
    AddAlbumComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
