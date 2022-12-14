const removeButton = document.querySelector(".remove-button");

// Event Listeners
// CALLBACK
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
