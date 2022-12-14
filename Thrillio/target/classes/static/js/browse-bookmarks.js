const profileBox = document.querySelector(".profile-box");
const profileBoxBody = document.querySelector(".profile-box-body");
const configButton = document.querySelector(".config-button");
const logoutButton = document.querySelector(".logout-button");
const filterButtonsWrapper = document.querySelector(".filter-buttons-wrapper");
const filterButtons = document.querySelectorAll(".filter-button");
const bookmarkTypes = document.querySelectorAll(".bookmark-type");
const localStorage = window.localStorage;
//EVENT LISTENERS

filterButtonsWrapper.addEventListener("click", (e) => listenToFilters(e));

profileBox.addEventListener("click", (e) => {
	if (e.target.closest(".profile-box-body")) return;
	profileBox.classList.toggle("opened");
});

configButton.addEventListener("click", (e) => {
	console.log('clicking config button')
	window.location.href =
		"/auth/user";
});

logoutButton.addEventListener("click", (e) => {
	console.log("clicking logout");
	window.location.href = "/logout";
	localStorage.clear();
});

//CALLBACKS
function listenToFilters(e) {
	const target = e.target;
	let filter = "";
	if (!target.classList.contains("filter-button")) return;
	if (target.classList.contains("movies-filter")) filter = "movies";
	else if (target.classList.contains("books-filter")) filter = "books";
	else if (target.classList.contains("weblinks-filter")) filter = "weblinks";
	else filter = "all";
	filterBookmarks(filter);
	updateFilterButtonsStyle(filter);
}

function filterBookmarks(filter) {
	console.log(filter);
	const container = filter + "-container";
	console.log(container);
	if (filter === "all") {
		bookmarkTypes.forEach((type) => {
			console.log('BookmarkType: ', type);
			type.style.display = "block";
		});
	} else {
		bookmarkTypes.forEach((type) => {
			type.style.display = "none";
			if (type.classList.contains(container)) {
				console.log('contain container: ', type)
				type.style.display = "block";
				console.log('after adding style: ', type)
			}
		});
	}
}

function updateFilterButtonsStyle(filter) {
	const buttonClass = filter + "-filter";
	filterButtons.forEach((button) => {
		button.classList.remove("current-filter");
		if (button.classList.contains(buttonClass))
			button.classList.add("current-filter");
	});
}
