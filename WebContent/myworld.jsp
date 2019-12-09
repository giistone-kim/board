<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>My Worl
d</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
    body {
      font: 400 15px/1.8 Lato, sans-serif;
      color: #777;
    }
    h3, h4 {
      margin: 10px, 0, 30px, 0;
      letter-spacing: 10px;
      font-size: 20px;
      color: #111
    }
    .container {
      padding: 80px 120px;
    }
    .person {
      border: 10px solid transparent;
      margin-bottom: 25px;
      width: 80%;
      height: 80%;
      opacity: 0.7;
    }
    .carousel-inner img {
      -webkit-filter: grayscale(90%);
      filter: grayscale(90%); /*흑백사진*/
      width: 100%;
      margin: auto;;
    }
    @media (max-width: 600px) {
      .carousel-caption {
        display: none;
      } 
    }
    .bg-1 {
      background: #2d2d30;
      color: #bdbdbd;
    }
    .bg-1 h3 {
      color: #fff;
    }
    .bg-1 p {
      font-style: italic;
    }
    .list-group-item:first-child {
      border-top-right-radius: 0;
      border-top-left-radius: 0;
    }
    .thumbnail {
      padding: 0 0 15px 0;
      border: none;
      border-radius: 0;
    }
    .thumbnail p {
      margin-top: 15px;
      color: #555;
    }
    .btn {
      padding: 10px 20px;
      background-color: #333;
      color: #f1f1f1;
      border-radius: 0;
      transition: .2s;
    }
    .btn:hover, .btn:focus {
      border: 1px solid #333;
      background-color: #fff;
      color: #000;
    }
    .modal-header, .modal-body {
      padding: 40px, 50px;
    }
    .nav-tabs li a {
      color: #777;
    }
    #googleMap {
      width: 100%;
      height: 400px;
      -webkit-filter: grayscale(100%);
      filter: grayscale(100%);
    }
    .navbar {
      font-family: Monterrat, sans-serif;
      margin-bottom: 0;
      background-color: #2d2d30;
      border: 0;
      font-size: 11px !important;
      letter-spacing: 4px;
      opacity: 0.9;
    }
    .navbar li a, .navbar .navbar-brand {
      color: #d5d5d5 !important;
    }
    .navbar-nav li a:hover {
      color: #fff !important;
    }
    .navbar-nav li.active a {
      color: #fff !important;
      background-color: #29292c !important;
    }
    .navbar-default .navbar-toggle {
      border-color: transparent;;
    }
    .open .dropdown-toggle {
      color: #fff;
      background-color: #555 !important;
    }
    .dropdown-menu li a {
      color: #000 !important;
    }
    .dropdown-menu li a:hover {
      background-color: red !important;
    }
    .footer {
      background-color: #2d2d30;
      color: #f5f5f5;
      padding: 32px;
    }
    .footer a {
      color: #f5f5f5;
    }
    .footer a:hover {
      color: #777;
      text-decoration:  none;
    }
    .form-control {
      border-radius: 0;
    }
    textarea {
      resize: none;
    }
  </style>

</style>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="50">
      
  		<!-- navigation bar -->
  		<nav class="navbar navbar-default navbar-fixed-top">
  			
  			<div class="container-fluid">
  				
  				<div class="navbar-header">
  					
  					<button type="button" class="navbar-toggle" data-toggle="collapse"
  					data-target="#myNavbar">

	  					<span class="icon-bar"></span>
	  					<span class="icon-bar"></span>
	  					<span class="icon-bar"></span>
  						
  					</button>
  					<a href="#myPage" class="navbar-brand"></a>
  				</div>
  				<div class="collapse navbar-collapse" id="myNavBar">
  					
  					<ul class="nav navbar-nav navbar-right">
  						
						<li><a href="#myPage">HOME</a></li>
						<li><a href="#blank">BAND</a></li>
						<li><a href="#tour">TOUR</a></li>
						<li><a href="#contact">CONTACT</a></li>
						<li><a href="./list.do">BOARD</a><li>
						<li><a href="./login.do">LOGIN</a></li>
			
						<li class="dropdown">

							<a class="dropdown-toggle" data-toggle="dropdown" href="#">
								MORE
                 <span class="caret"></span>
								<ul class="dropdown-menu">
									<li><a href="#">Merchandise</a></li>
									<li><a href="#">Extras</a></li>
									<li><a href="#">Media</a></li>
								</ul>
							</a>
						</li>
						
						<li><a href="#"><span class="glyphicon glyphicon-search"></span></a></li>
						


  					</ul>
  				</div>
  			</div>
  		</nav>

  		<!-- <회전목마> -->
  		<div id="myCarousel" class="carousel slide" data-ride="carousel">
  			<!-- indicators -->
  			<ol class="carousel-indicators">
  				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
  				<li data-target="#myCarousel" data-slide-to="1"></li>
  				<li data-target="#myCarousel" data-slide-to="2"></li>

  			</ol>

  			<!-- wrapper for slides -->

  			<div class="carousel-inner" role="listbox">
  				<div class="item active" >
  					<img src="ny.jpg" alt="New York" width="1200" height="700">

  					<div class="carousel-caption">
  						
  						<h4>New Youk</h4>
  						<p>hi</p>
  					</div>
  			
  				</div>
  				<div class="item" >
  					<img src="chicago.jpg" alt="Chicago" width="1200" height="700">

  					<div class="carousel-caption">
  						
  						<h4>Chicago</h4>
  						<p>hi</p>
  					</div>
  					

  				</div>
  				<div class="item" >
  					<img src="la.jpg" alt="Los Angeles" width="1200" height="700">

  					<div class="carousel-caption">
  						
  						<h4>LA</h4>
  						<p>hi</p>
  					</div>
  					

  				</div>





  			</div>

  			<!-- left and right control -->

  			<a href="#myCarousel" role="button" data-slide="prev" class="left carousel-control">
  				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
  				<span class="sr-only">Previous</span>
  			</a>
  			<a href="#myCarousel" role="button" data-slide="next" class="right carousel-control">
  				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
  				<span class="sr-only">Next</span>
  			</a>

  		</div>

  		<!-- band section -->
  		<div id="band" class="container text-center">
  			<h3>Where are you</h3>
  			<p>We love you </p>
  			

				<div class="row">
					<div class="col-sm-4">
						<p class="text-center"><strong>Stone</strong></p><br>
						<a href="#demo" data-toggle="collapse">
							<img src="bandmember.jpg" alt="Random Name" class="img-circle person"
							width="255" height="255"></a>
							<div id="demo" class="collapse">
								<p>Guitarlist and Lead Vocalist</p>
								<p>Loves long walks on the beach</p>
								<p>member since 1998</p>
							</div>
						



					</div>
					
					<div class="col-sm-4">
						<p class="text-center"><strong>Stone</strong></p><br>
						<a href="#demo2" data-toggle="collapse">
							<img src="bandmember.jpg" alt="Random Name" class="img-circle person"
							width="255" height="255"></a>
							<div id="demo2" class="collapse">
								<p>Guitarlist and Lead Vocalist</p>
								<p>Loves long walks on the beach</p>
								<p>member since 1998</p>
							</div>
						



					</div>
					
					<div class="col-sm-4">
						<p class="text-center"><strong>♥</strong></p><br>
						<a href="#demo3" data-toggle="collapse">
							<img src="bandmember.jpg" alt="Random Name" class="img-circle person"
							width="255" height="255"></a>
							<div id="demo3" class="collapse">
								<p>Guitarlist and Lead Vocalist</p>
								<p>Loves long walks on the beach</p>
								<p>member since 1998</p>
							</div>
						



					</div>




				</div>

  		</div>
  		<!-- Tour -->
  		<div id="tour" class="bg-1">
  			<div class="container">
  				<h3 class="text-center">TOUR DATES</h3>
  				<p class="text-center">Lorem ipsum we'll play you some music <br>
  				Remember to book your tickets!!</p>

  				<ul class="list-group">
  					
  					<li class="list-group-item">September <span class="label label-danger">Sold Out</span></li>
  					<li class="list-group-item">October <span class="label label-danger">Sold Out</span></li>
  					<li class="list-group-item">November <span class="badge">3</span></li>
  				</ul>

  				<div class="row text-center">
  					
  					<div class="col-sm-4">
  						
  						<div class="thumbnail">

  							<img src="paris.jpg" alt="Paris" width="400" height="300">
  							<p><strong>Paris</strong></p>
  							<p>Friday 27 November 2017</p>
  							<button class="btn" data-toggle="modal" data-target="#myModal">Buy Tickets</button>
  							
  						</div>


  					</div>
  					<div class="col-sm-4">
  						
  						<div class="thumbnail">

  							<img src="newyork.jpg" alt="New York" width="400" height="300">
  							<p><strong>New York</strong></p>
  							<p>Friday 27 November 2017</p>
  							<button class="btn" data-toggle="modal" data-target="#myModal">Buy Tickets</button>
  							
  						</div>
  						

  					</div>
  					<div class="col-sm-4">
  						
  						<div class="thumbnail">

  							<img src="sanfran.jpg" alt="San Francisco" width="400" height="300">
  							<p><strong>San Francisco</strong></p>
  							<p>Friday 27 November 2017</p>
  							<button class="btn" data-toggle="modal" data-target="#myModal">Buy Tickets</button>
  							
  						</div>
  						

  					</div>

  				</div>

  			</div>
  		</div>

  		<!-- Modal -->

  		<div class="modal fade" id="myModal" role="dialog">
  			
  			<div class="modal-dialog">
  				
  				<!-- modal content -->
  				<div class="modal-content">

  					<!-- modal header -->
  					<div class="modal-header">
  						<button type="button" class="close" data-dismiss="modal">x</button>
  						<h4><span class="glyphicon glyphicon-lock"> Tickets</span></h4>					
	


  					</div>
  					<!-- modal body -->
  					<div class="modal-body">
  						<form role="form">
  							<div class="form-group">
  								
  							  <label for="psw"><span class="glyphicon glyphicon-shopping-cart"> </span>Tickets, $23per  person</label>
  							   <input type="number" class="form-control" id="psw" placeholder="How many?">

  							</div>
  								<div class="form-group">
  								
  							  <label for="username"><span class="glyphicon glyphicon-shopping-cart"> </span>Send To</label>
  							   <input type="text" class="form-control" id="username" placeholder="Enter E-mail?">

  							</div>
  							<button type="submit" class="btn btn-block">Pay
								<span class="glyphicon glyphicon-ok"></span>
  							</button>


  						</form>
  					</div>
  					<!-- modal footer -->
  					<div class="modal-footer">
  						<button class="btn btn-danger btn-default pull-left"
  						data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>
  					    Cancel</button>
  					    <p>Need <a href="#">help?</a></p>

  					</div>


  				</div>



  			
  			</div>


  		</div>
  		<!-- Contact section -->
  		<div id="contact" class="container">
  			
  			<h3 class="text-center">Contact</h3>
  			<p class="text-center"><em>We love our fans!!</em></p>
  			<div class="row">
  				
  			<div class="col-md-4">
  				<p>Fan? Drop a note.</p>
  				<p><span class="glyphicon glyphicon-map-marker"></span>Chicago, US</p>
  				<p><span class="glyphicon glyphicon-phone"></span>Phone: 02-555-5555</p>
        		<p><span class="glyphicon glyphicon-envelope"></span>E-mail: mail@naver.com </p>


  			</div>
  			<div class="col-md-8">
  				<div class="row">
  					
  					<div class="col-sm-6form-group">
  						<input type="text" class="form-control" id="name" name="name" placeholder="name..." required>
  					</div>
  					<div class="col-sm-6form-group">
  						<input type="E-mail" class="form-control" id="E-mail" name="E-mail" placeholder="name..." required>
  					</div>
  					<textarea name="" id="comments" rows="5" class="form-control"
  					name="comments"></textarea>

  					<br>
  					<div class="row">
  						<div class="col-md-12 form-group">
  							<button type="submit" class="btn pull-right">Send</button>
  						</div>
  					</div>
  				</div>

  			</div>

  			<br>
  			<h3 class="text-center">From The Blog</h3>
  			<ul class="nav nav-tabs">
  				<li class="active"><a data-toggle="tab" href="#home">Mike</a></li>
  				<li><a data-toggle="tab" href="#menu1">Chandler</a></li>
  				<li><a data-toggle="tab" href="#menu2">Peter</a></li>
  				
  			</ul>
  			<div class="tab-content">
  				<div id="home" class="tab-pane fade in active">
  					<h2>Mike Ross, Manager</h2>
  					<p>Lorem Ipsum</p>
  				</div>
  				<div id="menu1" class="tab-pane fade">
  					<h2>Chandler Bing, Guitarist</h2>
  					<p>Lorem Ipsum</p>
  				</div>
  				<div id="menu2" class="tab-pane fade">
  					<h2>Peter Griffin, Bass Guitarist</h2>
  					<p>Lorem Ipsum</p>
  				</div>
  			</div>
  		</div>
        
  <!-- Google Map -->
  
  <div id="googleMap"></div>
  <script>
    function myMap() {
    var myCenter = new google.maps.LatLng(41.878114, -87.629798);
    var mapProp = {center:myCenter, zoom:12, scrollwheel:true, draggable:true, mapTypeId:google.maps.MapTypeId.ROADMAP};
    var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
    var marker = new google.maps.Marker({position:myCenter});
    marker.setMap(map);
    }

  </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=
AIzaSyDyggF9NZ3g7yehZYVMqHaJ5YrYj-d13KE
&callback=myMap"></script>  

  <!-- footer -->


<footer class="text-center">
    <a href="#myPage" data-toggle="tooltip" title="TO TOP" class="up-arrow">
      <span class="glyphicon glyphicon-chevron-up"></span>
    </a><br><br>
    <p>Bootstrap Website 
      <a href="http://www.mycompany.com" data-toggle="tooltip" title="Welcome My Company">
      http://www.mycompany.com</a>
    </p>  
  </footer>



<script type="text/javascript">
        $(function(){
          //툴팁초기화
          $('[data-toggle="tooltip")]').tooltip();
          
          //navbar와 footer 부드럽게 scrolling하기
          $(".navbar a, footer a[href='#myPage']").on('click', function(){
            if(this.hash !=="") {
              event.preventDefault();
              var hash = this.hash;
              $('html, body').animate({
                scrollTop: $(hash).offset().top

              }, 900, function() {
                window.location.hash = hash;
              })

            } //endif
          })//$(".navbar a, footer a[href='#myPage']")

        })
</script>






</body>
</html>