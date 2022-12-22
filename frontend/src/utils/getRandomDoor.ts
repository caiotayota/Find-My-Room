import door01 from '../assets/img/doors/8.jpg';
import door02 from '../assets/img/doors/16.jpg';
import door03 from '../assets/img/doors/20.jpg';
import door04 from '../assets/img/doors/28.jpg';
import door05 from '../assets/img/doors/32.jpg';
import door06 from '../assets/img/doors/33.jpg';
import door07 from '../assets/img/doors/34.jpg';
import door08 from '../assets/img/doors/35.jpg';
import door09 from '../assets/img/doors/38.jpg';
import door10 from '../assets/img/doors/39.jpg';
import door11 from '../assets/img/doors/58.jpg';
import door12 from '../assets/img/doors/67.jpg';
import door13 from '../assets/img/doors/68.jpg';
import door14 from '../assets/img/doors/783.jpg';
import door15 from '../assets/img/doors/1991.jpg';

const doors = [
  door01,
  door02,
  door03,
  door04,
  door05,
  door06,
  door07,
  door08,
  door09,
  door10,
  door11,
  door12,
  door13,
  door14,
  door15,
];

function getRandomDoor() {
  let min = Math.ceil(0);
  let max = Math.floor(14);
  let randomIndex = Math.floor(Math.random() * (max - min + 1) + min);
  console.log(randomIndex);
  return doors[randomIndex];
}

export default getRandomDoor;