/**
 * Created by Java on 2016/10/22.
 */
function getInfo(){
    var xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function () {
        if(xmlhttp.readyState==4&&xmlhttp.status==200){
            document.getElementById("p1").innerHTML="欢迎"+xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET","",true);
    xmlhttp.send();
}

