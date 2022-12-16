const removeButton = document.querySelector(".remove-button");
const localStorage = window.localStorage;
let isBookmarksCached = localStorage.getItem('isBookmarksCached');
// EVENT LISTENERS
window.addEventListener('load', listenToPageLoad)

// CALLBACK	
async function listenToPageLoad() {
	if(!isBookmarksCached) fetchBookmarks()
}

async function removeBookmark(e) {
	console.log("removing");
	const bookmark = e.target;
	const id = bookmark.closest(".bookmark").getAttribute("movie-id");
	if (bookmark.classList.contains("movie")) {
		const response = await fetch(`@{/bookmark-management/movies/${id}}`, {
			method: "DELETE",
		});
		console.log("SERVER RESPONSE:", response);
	}
	console.log("removed");
}
