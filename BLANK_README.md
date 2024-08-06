<!-- PROJECT LOGO -->

<h3 align="center">Kumiko Machine</h3>

  <p align="center">
    An automated means of cutting pieces for Kumiko panels
    <br />
    <br />
    <a href="https://www.youtube.com/watch?v=sn2D4_aM-SU&t=13s">View Demo</a>
  </p>
</div>




<!-- ABOUT THE PROJECT -->
## About The Project

<p>The idea for this project was born from a woodworking hobby of mine, Kumiko, a traditional Japanese woodworking craft where decorative panels are created by precisely measuring, cutting and assembling hundreds of pieces of wood into complex geometric patterns. This process is very time, labor and trial and error intensive so I wondered if a CNC (computer numerical control) inspired take on the traditional tools used in the process might make things more efficient.</p>
<br>
<h3 align="center"><img width="500" height="675" src="Images/Example Panel.jpg"></h3>
<p align="center">
A basic Kumiko panel



<!-- Design -->
## Design

<p>The software’s design was modeled around the Model - View - Adapter design pattern. This pattern was chosen because it allowed for clean separation of duties between the various classes of the software as well as the fact that it fit the project design requirements of having a GUI (View) and Model (Machine). Additionally, the singleton and factory design patterns were utilized to prevent issues with shared resource management between classes and class bloating respectively.<p/>
<p> Two external libraries were utilized for the project:<p/>
<br>
1. <a href="https://github.com/HirdayGupta/Java-Arduino-Communication-Library">The Java Arduino Library</a> - Responsible for initliizing, maintaining and closing serial communcations between the Arduino and the control computer.
<br>
<br>
2. <a href="https://poi.apache.org/apidocs/5.0/org/apache/poi/ss/usermodel/package-summary.html">Apache POI (usermodel)</a>- Used to handle the reading and writing of data between the Java program and the excel sheet where project information would be stored in non-volatile memory.
<br>


### Built With


* IntelliJ IDEA
* Java
* Arduino
* Apache POI
* JavaArduinoLibrary

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Pictures

<h3 align="center"><img width="800" height="675" src="Images/Machine.jpg"></h3>
<br>
<p align="center">
The finished machine
<h3 align="center"><img width="675" height="675" src="Images/Gui example picture.png"></h3>
<p align="center">
The GUI used to control the machine
<br>

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Aidan Klug -  - aklug@luc.edu

Project Link: [https://github.com/aklug59/Kumiko-Machine](https://github.com/aklug59/Kumiko-Machine)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [Dr. Robert Yacobellis](https://github.com/ares09x)
* [Jesse Thibault](https://www.linkedin.com/in/thibaultjessej/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/github_username/repo_name.svg?style=for-the-badge
[contributors-url]: https://github.com/github_username/repo_name/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/github_username/repo_name.svg?style=for-the-badge
[forks-url]: https://github.com/github_username/repo_name/network/members
[stars-shield]: https://img.shields.io/github/stars/github_username/repo_name.svg?style=for-the-badge
[stars-url]: https://github.com/github_username/repo_name/stargazers
[issues-shield]: https://img.shields.io/github/issues/github_username/repo_name.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo_name/issues
[license-shield]: https://img.shields.io/github/license/github_username/repo_name.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[MachinePic]: Images/Machine.jpg
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
