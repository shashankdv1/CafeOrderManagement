let clickcount=0;
document.addEventListener("DOMContentLoaded",function(){
	document.getElementById("menu").addEventListener("click",function(){
		clickcount+=1;
		if(clickcount%2==0)
			{
				document.getElementsByClassName('menu-block')[0].style.display='none';
		     }
			 else if(clickcount%2!=0){
		document.getElementsByClassName('menu-block')[0].style.display='flex';
		}
	});
});
let clickcount2=0;
document.addEventListener("DOMContentLoaded",function(){
	document.getElementById("profile").addEventListener("click",function(){
		clickcount2+=1;
		if(clickcount2%2==0)
			{
				document.getElementsByClassName('profile-container')[0].style.display='none';
		     }
			 else if(clickcount2%2!=0){
		document.getElementsByClassName('profile-container')[0].style.display='flex';
		}
	});
});
function showBuyMenu(itemId) {
    var buyMenu = document.getElementById("buyMenu_" + itemId);
    if (buyMenu) {
        buyMenu.style.display = "block"; 
		
    }
}
function closeBuyMenu(itemId) {
    var buyMenu = document.getElementById("buyMenu_" + itemId);
    if (buyMenu) {
        buyMenu.style.display = "none"; 
    }
}
document.addEventListener("click", function(event) {
    if (!event.target.closest('.BuyMenu[0]') && !event.target.closest('.images') && !event.target.closest('.description')) {
        var openMenus = document.querySelectorAll('.BuyMenu[style="display: block;"]');
        openMenus.forEach(function(menu) {
            menu.style.display = "none";  
        });
   }
   });

document.addEventListener("DOMContentLoaded",function(){
document.getElementById('search').addEventListener('click',function()
{		
	const SearchValue = document.getElementById('search-block').value;
	console.log(SearchValue);
	const SearchLink=document.getElementById('searchlink');
	SearchLink.href=`http://localhost:8080/CafeOrderManageme/Search?searchname=${encodeURIComponent(SearchValue)}`;
	window.location.href=SearchLink.href;
	SearchLink.style.display = 'inline';
});
});
function calculatePrice(itemId,price)
{	price=parseInt(price);
	const qtyfield=document.getElementById("qty_"+itemId);
	if(!qtyfield)
		{
			alert("Please Enter valid quantity");
			return false;
		}
	const totalpricefield=document.getElementById("totalprice_"+itemId);
	const quantity = parseInt(qtyfield.value, 10);
	const totalprice=quantity*price;
	totalpricefield.value= totalprice;
	return true;
}
