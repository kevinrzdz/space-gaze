import {Component, OnInit} from '@angular/core';
import {AstronomicalEvent} from "../../models/astronomical-event/astronomical-event";
import {AstronomicalEventService} from "../../services/astronomical-event/astronomical-event.service";

@Component({
  selector: 'app-astronomical-event',
  templateUrl: './astronomical-event.component.html',
  styleUrls: ['./astronomical-event.component.scss']
})
export class AstronomicalEventComponent implements OnInit {
  events!: AstronomicalEvent[];
  img: string[] = [
    "/assets/img/event1.jpg",
    "/assets/img/event2.jpg",
    "/assets/img/event3.jpg",
    "/assets/img/event4.jpg",
    "/assets/img/event5.jpg",
    "/assets/img/event6.jpg",
    "/assets/img/event7.jpg",
    "/assets/img/event8.jpg",
    "/assets/img/event9.jpg",
    "/assets/img/event10.jpg",
  ]

  constructor(private eventService: AstronomicalEventService) {
  }

  ngOnInit(): void {
    this.loadEvents();
  }

  loadEvents() {
    this.eventService.getAstronomicalEvents().subscribe((events) => {
      const sortedEvents = events.sort((a, b) => {
        return new Date(a.startDate).getTime() - new Date(b.startDate).getTime();
      });

      this.events = sortedEvents.map((event) => ({
        ...event,
        image: this.getRandomImage(),
      }));
    });
  }


  getRandomImage(): string {
    return this.img[Math.floor(Math.random() * this.img.length)];
  }
}
