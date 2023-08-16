import { Component, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { VideoService } from '../services/video.service';
import { DomSanitizer, SafeResourceUrl, } from '@angular/platform-browser';

@Component({
  selector: 'app-video-component',
  templateUrl: './video-component.component.html',
  styleUrls: ['./video-component.component.css']
})
export class VideoComponentComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private videoService: VideoService,
    public sanitizer: DomSanitizer,

  ) { }
  // src = "https://www.youtube.com/embed/"
  // mapUrl: SafeResourceUrl = '';
  videoKey: any;
  apiLoaded = false;
  // @Pipe({ name: 'safe' })
  ngOnInit(): void {
    console.log(this.data.dataKey);
    this.videoService.getVideo(this.data.dataKey).subscribe(
      (response: any) => {
        let movie = response.results.filter((movie: any) =>
          movie.name == "Official Trailer" || movie.name == "Trailer")

        if (!movie || !movie.length) {
          this.videoKey = response.results[2].key;
        }
        else {
          this.videoKey = movie[0].key;
        }
        // console.log(movie[0].key)
        //  const videoUrl = this.src + response.results[2].key;
        //  this.mapUrl = this.sanitizer.bypassSecurityTrustResourceUrl(videoUrl);
        // this.videoKey =  response.results[2].key;
        console.log(this.videoKey)

      })
    if (!this.apiLoaded) {
      const tag = document.createElement('script');
      tag.src = 'http://www.youtube.com/iframe_api';
      document.body.appendChild(tag);
      this.apiLoaded = true;
    }
  }

}


