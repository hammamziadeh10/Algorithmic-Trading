[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- INTRO. -->
<br />
<p align="center">
  <h3 align="center">Algorithmic Trading - Momentum strategy, Python & Java</h3>

  <p align="center">
An Algorithmic Trading Program that momentum and that....
    <br />
    <a href="https://github.com/hammamziadeh10/Algorithmic-Trading"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/hammamziadeh10/Algorithmic-Trading/issues">Report Bug</a>
    ·
    <a href="https://github.com/hammamziadeh10/Algorithmic-Trading/issues">Request Feature</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
## Table of Contents

* [Project - Details](#details)
  * [Backend](#backend)
  * [Frontend](#frontend)
* [Contributing](#contributing)
* [Contact](#contact)

<!-- DESIGN -->
## Details
An algorithmic trading program that analyzes the best stocks to buy using the Momentum strategy. The Ken Frenchs approach is used; thus the past year - except the last month- is analyzed. Stock data is web scraped (using Pandas Datareader) from Yahoo Finance; Pandas, Numpy are both used for the analysis. Finally the Robinhood private-api is used to automatically buy the stocks and sell after 30 days. The time and stock prices are both sent to a Firebase realtime database. The program is hosted on a google cloud server. Bash CLI: tmux and vim are used to edit and run the script. Hence a realtime connection is established between the python program and Android Frontend that I created to track process on the go.

<img src="gitImages/java.svg?raw=true" align="left" height="50" >
<img src="gitImages/python.svg?raw=true" align="left" height="50" >
<img src="gitImages/google-cloud.svg?raw=true" align="left" height="50" >
<img src="gitImages/firebase.svg?raw=true" align="left" height="50" >
<img src="gitImages/android-icon.svg?raw=true" height="50">


<!-- Backend -->
## Backend

<!-- Frontend -->
## Frontend

<!-- CONTRIBUTING -->
## Contributing

Contributions from anyone who wants to make a meaningful change are much appreciated!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/NewFeature`)
3. Commit your Changes (`git commit -m 'Add some NewFeature'`)
4. Push to the Branch (`git push origin feature/NewFeature`)
5. Open a Pull Request



<!-- CONTACT -->
## Contact

Hammam Ziadeh - hammamziadeh10@gmail.com

Project Link: [https://github.com/hammamziadeh10/Algorithmic-Trading](https://github.com/hammamziadeh10/Algorithmic-Trading)

[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=flat-square
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/hammam-ziadeh/
