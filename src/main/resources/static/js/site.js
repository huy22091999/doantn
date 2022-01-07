var mode = true;
$(".btn-menu").click(function () {
  if (mode == true) {
    $(this).css("background-color", "#cde4ce");
    $(".navv").css("display", "block");
    mode = false;
    $(".overflow").css("display", "block");
  } else {
    $(this).css("background-color", "#f7f8fa");
    $(".navv").css("display", "none");
    $(".overflow").css("display", "none");
    mode = true;
  }
});
$(".overflow").click(function () {
  $(".btn-menu").css("background-color", "#f7f8fa");
  $(".navv").css("display", "none");
  $(".overflow").css("display", "none");
  mode = true;
});
var slideIndex = 1;
showSlides(slideIndex);

// Next/previous controls
function plusSlides(n) {
  showSlides(slideIndex += n);
}

// Thumbnail image controls
function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  if (slides.length > 0) {
    if (n > slides.length) { slideIndex = 1 }
    if (n < 1) { slideIndex = slides.length }
    for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
    }
    slides[slideIndex - 1].style.display = "block";

  }
}
function autoShowslider() {
  plusSlides(1);
  setTimeout(autoShowslider, 4000);

}
autoShowslider();
var imageIndex = 1;
showProductImage(imageIndex);
function plusImage(n) {
  showProductImage(imageIndex += n);
}
function showProductImage(n) {
  var i;
  var image = document.getElementsByClassName("image-product");
  if (image.length > 0) {
    if (n > image.length) imageIndex = 1;
    if (n < 1) imageIndex = image.length;
    for (i = 0; i < image.length; i++) {
      image[i].style.display = "none";
    }
    image[imageIndex - 1].style.display = "block";
  }

}
function proSize() {
  var productSize = document.getElementsByClassName("product-size");
  var i;
  for (i = 0; i < productSize.length; i++) {
    productSize[i].style.backgroundColor = "#efefef";
  }
}
$(".product-size").click(function () {
  proSize();
  $(this).css("background-color", "#61d880");
});
$(".nav_item").click(function(){
  $(".dropdown-container").style.display="block";

})

function openPage(pageName, elmnt) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("sub-tabcontent");
  if (tabcontent.length > 0) {
    for (i = 0; i < tabcontent.length; i++) {
      tabcontent[i].style.display = "none";
    }
  }
  tablinks = document.getElementsByClassName("tablink");
  if (tablinks.length > 0) {
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].style.backgroundColor = "";
    }
  }
  elmnt.style.backgroundColor = "#61d880";
  document.getElementById(pageName).style.display = "block";
}
var tabcontent = document.getElementsByClassName("sub-tabcontent");
if (tabcontent.length > 0) {
  document.getElementById("sub-defaultOpen").click();
}
// Get the element with id="defaultOpen" and click on it

function openChange() {
  var input_text = document.getElementsByClassName("input-text");
  for (i = 0; i < input_text.length; i++) {
    input_text[i].style.pointerEvents = "visible";
  }
}

function removeSize(n) {
  var x = document.getElementsByClassName("product_size_item");
  var i;
  for (i = n; i < x.length; i++) {
    x[i].style.display = "none";
  }
}
removeSize(0);
var size = 0;
function addSize() {
  var x = document.getElementsByClassName("product_size_item");
  var i; size++;
  if (size <= x.length) {
    for (i = 0; i < size; i++) {
      x[i].style.display = "flex";
    }
  }
  else {
    document.getElementsByClassName("btn-product-size")[0].style.display = "none";
  }
}

function addQuantity(n) {
  let valueQuantity = document.getElementsByClassName("product-quantity")[0].value;
  var v= Number(valueQuantity) + n;
  if(v>1){
    document.getElementsByClassName("product-quantity")[0].value =v;
    document.getElementsByClassName("btn-minus")[0].style.backgroundColor = "#FF6F61";
  }
  if(v==1){
    document.getElementsByClassName("product-quantity")[0].value =v;
    document.getElementsByClassName("btn-minus")[0].style.backgroundColor = "#f3b7b1";
  }
  
}
function choseSize(elem){
  document.getElementsByClassName("choseProSize")[0].value=elem.value;
}


function search(){
  let key=document.getElementsByClassName("search-txt")[0].value;
  
  if(key.trim()!=''){
    document.getElementById("form-search").submit();
  }
}


